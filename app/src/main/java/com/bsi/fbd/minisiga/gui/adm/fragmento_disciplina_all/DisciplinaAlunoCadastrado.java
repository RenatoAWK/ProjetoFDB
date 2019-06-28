package com.bsi.fbd.minisiga.gui.adm.fragmento_disciplina_all;


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
import com.bsi.fbd.minisiga.modelo.Bloco;
import com.bsi.fbd.minisiga.modelo.Disciplina;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.Response;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisciplinaAlunoCadastrado extends Fragment {
    private ArrayList<Aluno> resultado = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private View view;


    public DisciplinaAlunoCadastrado() {
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

        view = inflater.inflate(R.layout.fragment_disciplina_aluno_cadastrado, container, false);
        recyclerView = view.findViewById(R.id.disciplinaAlunoRecyclerAdm);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        resultado.clear();
        com.bsi.fbd.minisiga.modelo.Response response = new Response("getdisciplinaalunocadastrado.php", context, recyclerView);
        Map<String, String> params = new HashMap<>();
        Disciplina disciplina = (Disciplina) User.getCurrentUser();
        params.put("sigla_faculdade",disciplina.getSigla_faculdade());
        params.put("codigo_disciplina", String.valueOf(disciplina.getCodigo()));
        response.run(params);
        return view;
    }

}
