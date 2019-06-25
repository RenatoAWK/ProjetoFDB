package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Bloco;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CursoRegisterAdm extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout duracaoLayout;
    private com.google.android.material.textfield.TextInputLayout siglaLayout;
    private com.google.android.material.textfield.TextInputLayout ramalLayout;
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Bloco bloco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_register_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        bloco = intent.getParcelableExtra("bloco");

        nomeLayout = findViewById(R.id.nomeLayoutRegisterCurso);
        codigoLayout = findViewById(R.id.codigoLayoutRegisterCurso);
        duracaoLayout = findViewById(R.id.duracaoLayoutRegisterCurso);
        ramalLayout = findViewById(R.id.ramalLayoutRegisterCurso);
        siglaLayout = findViewById(R.id.siglaLayoutRegisterCurso);

        response = new com.bsi.fbd.minisiga.modelo.Response("registercurso.php",this);
    }

    public void salvar(View view) {
        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add(nomeLayout);
        list.add(codigoLayout);
        list.add(duracaoLayout);
        list.add(siglaLayout);
        list.add(ramalLayout);

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
            params.put("nome", nomeLayout.getEditText().getText().toString().trim());
            params.put("duracao", duracaoLayout.getEditText().getText().toString().trim());
            params.put("sigla", siglaLayout.getEditText().getText().toString().trim());
            params.put("ramal", ramalLayout.getEditText().getText().toString().trim());
            params.put("sigla_faculdade", faculdade.getSigla() );
            response.run(params);


        }
    }
}
