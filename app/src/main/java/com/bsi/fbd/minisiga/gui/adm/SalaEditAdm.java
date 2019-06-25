package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.Sala;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SalaEditAdm extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout numeroLayoutEditSala;
    private com.google.android.material.textfield.TextInputLayout areaLayoutEditSala;
    private com.google.android.material.textfield.TextInputLayout tipoLayoutEditSala;
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();
    private com.bsi.fbd.minisiga.modelo.Response response;
    private Sala sala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_edit_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        sala = intent.getParcelableExtra("sala");

        numeroLayoutEditSala = findViewById(R.id.numeroLayoutEditSala);
        areaLayoutEditSala = findViewById(R.id.areaLayoutEditSala);
        tipoLayoutEditSala = findViewById(R.id.tipoLayoutEditSala);

        numeroLayoutEditSala.getEditText().setText(String.valueOf(sala.getNumero()));
        areaLayoutEditSala.getEditText().setText(sala.getArea());
        tipoLayoutEditSala.getEditText().setText(sala.getTipo());

        response = new com.bsi.fbd.minisiga.modelo.Response("editsala.php",this);
    }

    public void salvar(View view) {

        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add(numeroLayoutEditSala);
        list.add(areaLayoutEditSala);
        list.add(tipoLayoutEditSala);

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
            params.put("numero_antigo", String.valueOf(sala.getNumero()));
            params.put("numero", numeroLayoutEditSala.getEditText().getText().toString().trim());
            params.put("area", areaLayoutEditSala.getEditText().getText().toString().trim());
            params.put("tipo", tipoLayoutEditSala.getEditText().getText().toString().trim());
            params.put("sigla_faculdade", faculdade.getSigla() );
            params.put("codigo_bloco", String.valueOf(sala.getCodigoBloco()));
            response.run(params);

        }



    }
}
