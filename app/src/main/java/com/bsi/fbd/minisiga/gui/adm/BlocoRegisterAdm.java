package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Connection;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlocoRegisterAdm extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private com.google.android.material.textfield.TextInputLayout areaLayout;
    private com.google.android.material.textfield.TextInputLayout tipoLayout;
    private RequestQueue requestQueue;
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloco_register_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        codigoLayout = findViewById(R.id.codigoLayoutRegisterBloco);
        areaLayout = findViewById(R.id.areaLayoutRegisterBloco);
        tipoLayout = findViewById(R.id.tipoLayoutRegisterBloco);

        requestQueue = Volley.newRequestQueue(this);


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
            if (Connection.getUrl() != null) {

                String url = Connection.getUrl() + "registerbloco.php";

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean error = jsonObject.getBoolean("erro"); //Esse erro é o mesmo passado lá no php
                            if (!error) {
                                finish();
                                Toast.makeText(getApplicationContext(), "Cadastro realizado", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Erro 1", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Erro 2 ", Toast.LENGTH_LONG).show();
                        }

                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String motivo = error.getMessage();
                        Toast.makeText(getApplicationContext(), "Erro desconhecido: " + motivo, Toast.LENGTH_LONG).show();
                    }
                };

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, url, responseListener, errorListener) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("codigo", codigoLayout.getEditText().getText().toString().trim());
                        params.put("area", areaLayout.getEditText().getText().toString().trim());
                        params.put("tipo", tipoLayout.getEditText().getText().toString().trim());
                        params.put("sigla_faculdade", faculdade.getSigla() );
                        return params;
                    }
                };

                requestQueue.add(stringRequest);

            } else {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.IP_required), Toast.LENGTH_LONG);
            }
        }
    }
}
