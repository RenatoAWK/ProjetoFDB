package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.gui.adm.fragmento_main_disciplina_acitivity.DisciplinasCadastradasAdmFragment;
import com.bsi.fbd.minisiga.gui.adm.fragmento_main_disciplina_acitivity.DisciplinasNaoCadastradasCursoAdmFragment;
import com.bsi.fbd.minisiga.modelo.Curso;
import com.bsi.fbd.minisiga.modelo.User;
import com.bsi.fbd.minisiga.modelo.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;

public class CursoDisciplinaEditAdm extends AppCompatActivity {
    private Curso curso;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_disciplina_edit_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        curso = intent.getParcelableExtra("curso");
        User.setCurso(curso);

        title = findViewById(R.id.titleMainCursoDisciplina);
        title.setText(User.getBloco().getSiglaFaculdade());
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DisciplinasCadastradasAdmFragment(), "Estão no curso");
        adapter.addFragment(new DisciplinasNaoCadastradasCursoAdmFragment(), "Não estão no curso");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1){
                    Map<String, String> params = new HashMap<>();
                    params.put("sigla_faculdade", User.getCurso().getSigla_faculdade());
                    params.put("codigo_curso",String.valueOf(User.getCurso().getCodigo()));
                    User.getDisciplinasNaoCadastradas().run(params);

                } else {
                    Map<String, String> params = new HashMap<>();
                    params.put("sigla_faculdade", User.getCurso().getSigla_faculdade());
                    params.put("codigo_curso",String.valueOf(User.getCurso().getCodigo()));
                    User.getDisciplinasCadastradas().run(params);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        super.onStart();
    }



}
