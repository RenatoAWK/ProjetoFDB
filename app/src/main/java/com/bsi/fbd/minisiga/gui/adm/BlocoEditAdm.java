package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Bloco;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlocoEditAdm extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout areaLayout;
    private com.google.android.material.textfield.TextInputLayout tipoLayout;
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Bloco bloco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloco_edit_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        bloco = intent.getParcelableExtra("bloco");

        codigoLayout = findViewById(R.id.codigoLayoutEditBloco);
        areaLayout = findViewById(R.id.areaLayoutEditBloco);
        tipoLayout = findViewById(R.id.tipoLayoutEditBloco);

        codigoLayout.getEditText().setText(String.valueOf(bloco.getCodigo()));
        areaLayout.getEditText().setText(bloco.getArea());
        tipoLayout.getEditText().setText(bloco.getTipo());

        response = new com.bsi.fbd.minisiga.modelo.Response("editbloco.php",this);

    }

    public void salvar(View view) {

        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add(codigoLayout);
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
            params.put("codigo_antigo", String.valueOf(bloco.getCodigo()));
            params.put("codigo", codigoLayout.getEditText().getText().toString().trim());
            params.put("area", areaLayout.getEditText().getText().toString().trim());
            params.put("tipo", tipoLayout.getEditText().getText().toString().trim());
            params.put("sigla_faculdade", faculdade.getSigla() );
            response.run(params);

        }



    }
}
