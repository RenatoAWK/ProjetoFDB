package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.User;

import com.bsi.fbd.minisiga.modelo.Bloco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SalaRegisterAdm extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout numeroLayout;
    private com.google.android.material.textfield.TextInputLayout tipoLayout;
    private com.google.android.material.textfield.TextInputLayout areaLayout;
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Bloco bloco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_register_adm);

        Intent intent = getIntent();
        bloco = intent.getParcelableExtra("sala");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        numeroLayout = findViewById(R.id.codigoLayoutRegisterSala);
        areaLayout = findViewById(R.id.areaLayoutRegisterSala);
        tipoLayout = findViewById(R.id.tipoLayoutRegisterSala);

        response = new com.bsi.fbd.minisiga.modelo.Response("registersala.php",this);

    }

    public void salvar(View view) {
        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add(numeroLayout);
        list.add(areaLayout);
        list.add(tipoLayout);

        for (Object object : list) {
            com.google.android.material.textfield.TextInputLayout textInputLayout = (com.google.android.material.textfield.TextInputLayout) object;
            if (textInputLayout.getEditText().getText().toString().isEmpty()) {
                textInputLayout.setError(getApplicationContext().getString(R.string.required_field));
                valido = false;
            } else {
                textInputLayout.setError(null);
            }
        }


        if (valido) {
            Map<String, String> params = new HashMap<>();
            params.put("numero", numeroLayout.getEditText().getText().toString().trim());
            params.put("area", areaLayout.getEditText().getText().toString().trim());
            params.put("tipo", tipoLayout.getEditText().getText().toString().trim());
            params.put("sigla_faculdade", faculdade.getSigla() );
            response.run(params);

        }
    }


}
