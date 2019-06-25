package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_bloco_activity.CursoAdmFragment;
import com.bsi.fbd.minisiga.gui.adm.fragmentos_main_bloco_activity.SalaAdmFragment;
import com.bsi.fbd.minisiga.modelo.Bloco;
import com.bsi.fbd.minisiga.modelo.User;
import com.bsi.fbd.minisiga.modelo.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainBlocoActivity extends AppCompatActivity {
    private Bloco bloco;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bloco);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        bloco = intent.getParcelableExtra("bloco");

        title = findViewById(R.id.titleMainBloco);
        title.setText(bloco.getSiglaFaculdade());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = tabLayout.getSelectedTabPosition();
                if (position == 0) {
                    /// cadastrar sala
                    Intent intent = new Intent(getApplicationContext(), SalaRegisterAdm.class);
                    intent.putExtra("bloco",bloco);
                    startActivity(intent);
                } else if (position == 1) {
                    /// cadastrar curso
                    Intent intent = new Intent(getApplicationContext(), CursoRegisterAdm.class);
                    intent.putExtra("bloco",bloco);
                    startActivity(intent);
                }
            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SalaAdmFragment(), "Salas");
        adapter.addFragment(new CursoAdmFragment(), "Cursos");
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
