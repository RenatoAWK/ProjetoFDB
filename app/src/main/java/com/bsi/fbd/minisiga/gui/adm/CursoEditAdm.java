package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Bloco;
import com.bsi.fbd.minisiga.modelo.Curso;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.HashMap;
import java.util.Map;

public class CursoEditAdm extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout duracaoLayout;
    private com.google.android.material.textfield.TextInputLayout siglaLayout;
    private com.google.android.material.textfield.TextInputLayout ramalLayout;
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Curso curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_edit_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        curso = intent.getParcelableExtra("curso");

        nomeLayout = findViewById(R.id.nomeLayoutEditCurso);
        codigoLayout = findViewById(R.id.codigoLayoutEditCurso);
        duracaoLayout = findViewById(R.id.duracaoLayoutEditCurso);
        ramalLayout = findViewById(R.id.ramalLayoutEditCurso);
        siglaLayout = findViewById(R.id.siglaLayoutEditCurso);

        nomeLayout.getEditText().setText(curso.getNome());
        codigoLayout.getEditText().setText(String.valueOf(curso.getCodigo()));
        duracaoLayout.getEditText().setText(String.valueOf(curso.getDuracao()));
        ramalLayout.getEditText().setText(String.valueOf(curso.getRamal()));
        siglaLayout.getEditText().setText(curso.getSigla());

        response = new com.bsi.fbd.minisiga.modelo.Response("editcurso.php",this);
    }

    public void salvar(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("codigo_antigo", String.valueOf(curso.getCodigo()));
        params.put("codigo", codigoLayout.getEditText().getText().toString().trim());
        params.put("nome", nomeLayout.getEditText().getText().toString().trim());
        params.put("duracao", duracaoLayout.getEditText().getText().toString().trim());
        params.put("sigla", siglaLayout.getEditText().getText().toString().trim());
        params.put("ramal", ramalLayout.getEditText().getText().toString().trim());
        params.put("sigla_faculdade", curso.getSigla_faculdade());
        params.put("codigo_bloco", String.valueOf(curso.getCodigo_bloco()));
        response.run(params);
    }
}
