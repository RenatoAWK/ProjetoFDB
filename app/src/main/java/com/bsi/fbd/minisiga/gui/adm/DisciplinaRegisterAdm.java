package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Curso;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.Turma;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisciplinaRegisterAdm extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout carga_horariaLayout;
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Curso curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina_register_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        curso = intent.getParcelableExtra("curso");

        nomeLayout = findViewById(R.id.nomeLayoutRegisterDisciplina);
        codigoLayout = findViewById(R.id.codigoLayoutRegisterDisciplina);
        carga_horariaLayout = findViewById(R.id.carga_horariaLayoutRegisterDisciplina);

        response = new com.bsi.fbd.minisiga.modelo.Response("registerdisciplina.php", this);

    }

    public void salvar(View view) {
        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add(nomeLayout);
        list.add(codigoLayout);
        list.add(carga_horariaLayout);

        for (Object object : list) {
            com.google.android.material.textfield.TextInputLayout textInputLayout = (com.google.android.material.textfield.TextInputLayout) object;
            if (textInputLayout.getEditText().getText().toString().isEmpty()) {
                textInputLayout.setError(getApplicationContext().getString(R.string.required_field));
                valido = false;
            } else {
                textInputLayout.setError(null);
            }
        }


        if (valido) {
            Map<String, String> params = new HashMap<>();
            Faculdade faculdade = (Faculdade) User.getCurrentUser();
            params.put("codigo", codigoLayout.getEditText().getText().toString().trim());
            params.put("nome", nomeLayout.getEditText().getText().toString().trim());
            params.put("carga_horaria", carga_horariaLayout.getEditText().getText().toString().trim());
            params.put("sigla_faculdade", faculdade.getSigla());
            response.run(params);


        }
    }
}
