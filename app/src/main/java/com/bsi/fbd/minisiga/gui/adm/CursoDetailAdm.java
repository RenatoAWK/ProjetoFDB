package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Curso;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.User;

public class CursoDetailAdm extends AppCompatActivity {private com.google.android.material.textfield.TextInputLayout nomeLayout;
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
        setContentView(R.layout.activity_curso_detail_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        curso = intent.getParcelableExtra("curso");

        nomeLayout = findViewById(R.id.nomeLayoutDetailCurso);
        codigoLayout = findViewById(R.id.codigoLayoutDetailCurso);
        duracaoLayout = findViewById(R.id.duracaoLayoutDetailCurso);
        ramalLayout = findViewById(R.id.ramalLayoutDetailCurso);
        siglaLayout = findViewById(R.id.siglaLayoutDetailCurso);

        nomeLayout.getEditText().setText(curso.getNome());
        codigoLayout.getEditText().setText(String.valueOf(curso.getCodigo()));
        duracaoLayout.getEditText().setText(String.valueOf(curso.getDuracao()));
        ramalLayout.getEditText().setText(String.valueOf(curso.getRamal()));
        siglaLayout.getEditText().setText(curso.getSigla());
    }
}
