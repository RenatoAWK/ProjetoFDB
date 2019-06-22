package com.bsi.fbd.minisiga.gui.adm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.bsi.fbd.minisiga.gui.adm.ui.main.SectionsPagerAdapter;

public class Main_Adm_Activity extends AppCompatActivity {
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adm_);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        final TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (tabs.getSelectedTabPosition()){
                    case 0:
                        /// cadastrar bloco
                        Intent intent = new Intent(getApplicationContext(), BlocoRegisterAdm.class);
                        startActivity(intent);
                        break;
                    case 1:
                        /// cadastrar aluno
                        break;
                    case  2:
                        /// cadastrar professor
                        break;
                }




            }
        });
    }
}