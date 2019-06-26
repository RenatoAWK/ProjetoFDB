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
import com.bsi.fbd.minisiga.modelo.Sala;
import com.bsi.fbd.minisiga.modelo.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainTurmaActivity extends AppCompatActivity {
    private Sala sala;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_turma);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        sala = intent.getParcelableExtra("sala");


        title = findViewById(R.id.titleMainTurma);
        title.setText(sala.getSiglaFaculdade());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TurmaAdmFragment(), "Turmas");
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
