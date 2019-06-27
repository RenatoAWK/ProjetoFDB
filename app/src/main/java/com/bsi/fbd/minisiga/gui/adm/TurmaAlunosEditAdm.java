package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_turma_activity.AlunosCadastradosTurmaAdmFragment;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_turma_activity.AlunosNaoCadastradosTurmaAdmFragment;
import com.bsi.fbd.minisiga.modelo.User;
import com.bsi.fbd.minisiga.modelo.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TurmaAlunosEditAdm extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_alunos_edit_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        title = findViewById(R.id.titleMainTurmaAlunos);
        title.setText(User.getTurma().getSiglaFaculdade());
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AlunosCadastradosTurmaAdmFragment(), "Estão na turma");
        adapter.addFragment(new AlunosNaoCadastradosTurmaAdmFragment(), "Não estão na turma ");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        super.onStart();
    }
}
