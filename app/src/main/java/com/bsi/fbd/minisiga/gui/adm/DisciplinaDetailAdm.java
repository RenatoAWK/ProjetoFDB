package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Disciplina;

public class DisciplinaDetailAdm extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout carga_horariaLayout;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina_detail_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        disciplina = intent.getParcelableExtra("disciplina");

        nomeLayout = findViewById(R.id.nomeLayoutDetailDisciplina);
        codigoLayout = findViewById(R.id.codigoLayoutDetailDisciplina);
        carga_horariaLayout = findViewById(R.id.carga_horariaLayoutDetailDisciplina);

        nomeLayout.getEditText().setText(disciplina.getNome());
        codigoLayout.getEditText().setText(String.valueOf(disciplina.getCodigo()));
        carga_horariaLayout.getEditText().setText(String.valueOf(disciplina.getNome()));
    }
}
