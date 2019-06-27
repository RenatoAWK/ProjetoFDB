package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Response;
import com.bsi.fbd.minisiga.modelo.Turma;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.HashMap;
import java.util.Map;

public class TurmaDetailAdm extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout anoLayout;
    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout cursoLayout;
    private RecyclerView recyclerView;
    private Turma turma;
    private Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_detail_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        turma = intent.getParcelableExtra("turma");

        anoLayout = findViewById(R.id.anoLayoutDetailTurma);
        codigoLayout = findViewById(R.id.codigoLayoutDetailTurma);
        cursoLayout = findViewById(R.id.codigoCursoLayoutDetailTurma);
        recyclerView = findViewById(R.id.alunosRecyclerdetailTurma);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        anoLayout.getEditText().setText(String.valueOf(turma.getAno()));
        codigoLayout.getEditText().setText(String.valueOf(turma.getCodigo()));
        cursoLayout.getEditText().setText(String.valueOf(turma.getCodigoCurso()));

        response = new Response("getturma_alunos_cadastrados.php", this, recyclerView);
        Map<String, String> params = new HashMap<>();
        params.put("sigla_faculdade", turma.getSiglaFaculdade());
        params.put("codigo_turma",String.valueOf(turma.getCodigo()));
        response.run(params);
    }
}
