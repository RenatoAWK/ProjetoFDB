package com.bsi.fbd.minisiga.gui.adm.fragmento_disciplina_all;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsi.fbd.minisiga.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisciplinaAlunoNaoCadastrado extends Fragment {


    public DisciplinaAlunoNaoCadastrado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disciplina_aluno_nao_cadastrado, container, false);
    }

}
