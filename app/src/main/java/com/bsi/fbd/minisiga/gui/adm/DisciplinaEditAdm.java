package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Disciplina;
import com.bsi.fbd.minisiga.modelo.Response;
import com.bsi.fbd.minisiga.modelo.Turma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisciplinaEditAdm extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout carga_horariaLayout;
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina_edit_adm);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        disciplina = intent.getParcelableExtra("disciplina");

        nomeLayout = findViewById(R.id.nomeLayoutEditDisciplina);
        codigoLayout = findViewById(R.id.codigoLayoutEditDisciplina);
        carga_horariaLayout = findViewById(R.id.carga_horariaLayoutEditDisciplina);

        nomeLayout.getEditText().setText(disciplina.getNome());
        codigoLayout.getEditText().setText(String.valueOf(disciplina.getCodigo()));
        carga_horariaLayout.getEditText().setText(String.valueOf(disciplina.getNome()));

        response = new Response("editdisciplina.php", this);

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
            params.put("codigo_antigo", String.valueOf(disciplina.getCodigo()));
            params.put("codigo", codigoLayout.getEditText().getText().toString().trim());
            params.put("nome", nomeLayout.getEditText().getText().toString().trim());
            params.put("carga_horaria", carga_horariaLayout.getEditText().getText().toString().trim());
            params.put("sigla_faculdade", disciplina.getSigla_faculdade());
            response.run(params);


        }
    }
}
