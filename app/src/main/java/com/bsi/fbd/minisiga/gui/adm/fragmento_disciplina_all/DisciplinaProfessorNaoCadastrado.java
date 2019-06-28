package com.bsi.fbd.minisiga.gui.adm.fragmento_disciplina_all;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsi.fbd.minisiga.R;

public class DisciplinaProfessorNaoCadastrado extends Fragment {

    public DisciplinaProfessorNaoCadastrado() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disciplina_professor_nao_cadastrado, container, false);
    }
}
