package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Aluno;

public class AlunoDetailAdm extends AppCompatActivity {
    private Aluno aluno;
    private com.google.android.material.textfield.TextInputLayout cpfLayout;
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout enderecoLayout;
    private com.google.android.material.textfield.TextInputLayout emailLayout;
    private com.google.android.material.textfield.TextInputLayout senhaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_detail_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        aluno = intent.getParcelableExtra("aluno");

        cpfLayout = findViewById(R.id.cpfLayoutDetailAluno);
        nomeLayout = findViewById(R.id.nomeLayoutDetailAluno);
        enderecoLayout = findViewById(R.id.enderecoLayoutDetailAluno);
        emailLayout = findViewById(R.id.emailLayoutDetailAluno);
        senhaLayout = findViewById(R.id.senhaLayoutDetailAluno);

        cpfLayout.getEditText().setText(aluno.getCpf());
        nomeLayout.getEditText().setText(aluno.getNome());
        enderecoLayout.getEditText().setText(aluno.getEndereco());
        emailLayout.getEditText().setText(aluno.getEmail());
        senhaLayout.getEditText().setText(aluno.getSenha());
    }
}
