package com.bsi.fbd.minisiga.gui.adm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.bsi.fbd.minisiga.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adm_);

        viewPager = findViewById(R.id.view_pager);
        setUpViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



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
                    /// cadastrar professor
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

}
