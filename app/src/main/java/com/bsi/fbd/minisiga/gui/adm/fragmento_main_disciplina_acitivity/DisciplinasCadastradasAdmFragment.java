package com.bsi.fbd.minisiga.gui.adm.fragmento_main_disciplina_acitivity;


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
import com.bsi.fbd.minisiga.modelo.Disciplina;
import com.bsi.fbd.minisiga.modelo.ResponseAR;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DisciplinasCadastradasAdmFragment extends Fragment {
    private ArrayList<Disciplina> resultado = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private View view;


    public DisciplinasCadastradasAdmFragment() {
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
        view = inflater.inflate(R.layout.fragment_disciplinas_cadastradas_adm, container, false);
        recyclerView = view.findViewById(R.id.disciplinasCadastradasRecyclerAdm);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        resultado.clear();
        com.bsi.fbd.minisiga.modelo.ResponseAR response = new ResponseAR("getcursodisciplinascadastradas.php", context, recyclerView);
        Map<String, String> params = new HashMap<>();
        params.put("sigla_faculdade", User.getCurso().getSigla_faculdade());
        params.put("codigo_curso",String.valueOf(User.getCurso().getCodigo()));
        response.run(params);
        User.setDisciplinasCadastradas(response);
        return view;
    }

}
