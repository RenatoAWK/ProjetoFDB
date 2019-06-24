package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Bloco;

public class BlocoDetailAdm extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout areaLayout;
    private com.google.android.material.textfield.TextInputLayout tipoLayout;
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Bloco bloco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloco_detail_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        bloco = intent.getParcelableExtra("bloco");

        codigoLayout = findViewById(R.id.codigoLayoutDetailBloco);
        areaLayout = findViewById(R.id.areaLayoutDetailBloco);
        tipoLayout = findViewById(R.id.tipoLayoutDetailBloco);

        codigoLayout.getEditText().setText(String.valueOf(bloco.getCodigo()));
        areaLayout.getEditText().setText(bloco.getArea());
        tipoLayout.getEditText().setText(bloco.getTipo());
    }
}
