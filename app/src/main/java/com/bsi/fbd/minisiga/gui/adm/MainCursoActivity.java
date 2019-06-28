package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_adm_activity.TurmaAdmFragment;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_curso_activity.DisciplinaAdmFragment;
import com.bsi.fbd.minisiga.modelo.Curso;
import com.bsi.fbd.minisiga.modelo.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainCursoActivity extends AppCompatActivity {
    private Curso curso;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_main_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final Intent intent = getIntent();
        curso = intent.getParcelableExtra("curso");

        title = findViewById(R.id.titleMainCurso);
        title.setText(curso.getSigla_faculdade());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), DisciplinaRegisterAdm.class);
                intent1.putExtra("curso",curso);
                startActivity(intent1);

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DisciplinaAdmFragment(), "Disciplinas");
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
