package com.bsi.fbd.minisiga.gui.adm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_adm_activity.AlunoAdmFragment;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_adm_activity.BlocoAdmFragment;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_adm_activity.ProfessorAdmFragment;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.User;
import com.bsi.fbd.minisiga.modelo.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class Main_Adm_Activity extends AppCompatActivity {
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adm_);

        title = findViewById(R.id.titleMainAdm);
        title.setText(faculdade.getSigla());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = tabLayout.getSelectedTabPosition();
                if (position == 0) {
                    /// cadastrar bloco
                    Intent intent = new Intent(getApplicationContext(), BlocoRegisterAdm.class);
                    startActivity(intent);
                } else if (position == 1) {
                    /// cadastrar aluno
                    Intent intent = new Intent(getApplicationContext(), AlunoRegisterAdm.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), ProfessorRegisterAdm.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BlocoAdmFragment(), "Blocos");
        adapter.addFragment(new AlunoAdmFragment(), "Alunos");
        adapter.addFragment(new ProfessorAdmFragment(), "Professores");
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
