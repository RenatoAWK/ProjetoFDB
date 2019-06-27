package com.bsi.fbd.minisiga.gui.adm.fragmentos_main_turma_activity;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Aluno;
import com.bsi.fbd.minisiga.modelo.ResponseAR;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlunosCadastradosTurmaAdmFragment extends Fragment {
    private ArrayList<Aluno> resultado = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private View view;


    public AlunosCadastradosTurmaAdmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alunos_cadastrados_turma_adm, container, false);
        recyclerView = view.findViewById(R.id.alunosCadastradosRecyclerAdm);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        resultado.clear();
        com.bsi.fbd.minisiga.modelo.ResponseAR response = new ResponseAR("getturma_alunos_cadastrados.php", context, recyclerView);
        Map<String, String> params = new HashMap<>();
        params.put("sigla_faculdade", User.getTurma().getSiglaFaculdade());
        params.put("codigo_turma",String.valueOf(User.getTurma().getCodigo()));
        response.run(params);
        User.setAlunosCadastrados(response);
        return view;
    }

}
