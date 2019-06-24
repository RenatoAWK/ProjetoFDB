package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Professor;

public class ProfessorDetailAdm extends AppCompatActivity {
    private Professor professor;
    private com.google.android.material.textfield.TextInputLayout cpfLayout;
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout enderecoLayout;
    private com.google.android.material.textfield.TextInputLayout emailLayout;
    private com.google.android.material.textfield.TextInputLayout senhaLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_detail_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        professor = intent.getParcelableExtra("professor");

        cpfLayout = findViewById(R.id.cpfLayoutDetailProfessor);
        nomeLayout = findViewById(R.id.nomeLayoutDetailProfessor);
        enderecoLayout = findViewById(R.id.enderecoLayoutDetailProfessor);
        emailLayout = findViewById(R.id.emailLayoutDetailProfessor);
        senhaLayout = findViewById(R.id.senhaLayoutDetailProfessor);

        cpfLayout.getEditText().setText(professor.getCpf());
        nomeLayout.getEditText().setText(professor.getNome());
        enderecoLayout.getEditText().setText(professor.getEndereco());
        emailLayout.getEditText().setText(professor.getEmail());
        senhaLayout.getEditText().setText(professor.getSenha());
    }
}
