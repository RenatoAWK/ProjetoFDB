package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Disciplina;
import com.bsi.fbd.minisiga.modelo.User;

public class DisciplinaAluno extends AppCompatActivity {
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina_aluno);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        intent.getParcelableExtra("disciplina");

        User.setDisciplina(disciplina);

    }
}
