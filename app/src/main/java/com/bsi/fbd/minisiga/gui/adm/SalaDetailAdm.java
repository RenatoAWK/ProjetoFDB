package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.Sala;
import com.bsi.fbd.minisiga.modelo.User;

public class SalaDetailAdm extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout numeroLayoutEditSala;
    private com.google.android.material.textfield.TextInputLayout areaLayoutEditSala;
    private com.google.android.material.textfield.TextInputLayout tipoLayoutEditSala;
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Sala sala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_detail_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        sala = intent.getParcelableExtra("sala");

        numeroLayoutEditSala = findViewById(R.id.numeroLayoutDetailSala);
        areaLayoutEditSala = findViewById(R.id.areaLayoutDetailSala);
        tipoLayoutEditSala = findViewById(R.id.tipoLayoutDetailSala);

        numeroLayoutEditSala.getEditText().setText(String.valueOf(sala.getNumero()));
        areaLayoutEditSala.getEditText().setText(sala.getArea());
        tipoLayoutEditSala.getEditText().setText(sala.getTipo());
    }
}
