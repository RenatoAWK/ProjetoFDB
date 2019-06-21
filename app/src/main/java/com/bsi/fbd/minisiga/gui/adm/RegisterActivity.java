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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout siglaLayout;
    private com.google.android.material.textfield.TextInputEditText siglaEdit;

    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputEditText nomeEdit;

    private com.google.android.material.textfield.TextInputLayout cidadeLayout;
    private com.google.android.material.textfield.TextInputEditText cidadeEdit;

    private com.google.android.material.textfield.TextInputLayout enderecoLayout;
    private com.google.android.material.textfield.TextInputEditText enderecoEdit;

    private com.google.android.material.textfield.TextInputLayout senhaLayout;
    private com.google.android.material.textfield.TextInputEditText senhaEdit;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        siglaLayout = findViewById(R.id.siglaLayoutRegister);
        siglaEdit = findViewById(R.id.siglaEditRegister);

        nomeLayout = findViewById(R.id.nomeLayoutRegister);
        nomeEdit = findViewById(R.id.nomeEditRegister);

        cidadeLayout = findViewById(R.id.cidadeLayoutRegister);
        cidadeEdit = findViewById(R.id.cidadeEditRegister);

        enderecoLayout = findViewById(R.id.enderecoLayoutRegister);
        enderecoEdit = findViewById(R.id.enderecoEditRegister);

        senhaLayout = findViewById(R.id.senhaLayoutRegister);
        senhaEdit = findViewById(R.id.senhaEditRegister);

        requestQueue = Volley.newRequestQueue(this);

    }


    public void salvar(View view) {
        boolean valido = true;
        if (nomeEdit.getText().toString().isEmpty()){
            valido = false;
            siglaLayout.setError(getApplicationContext().getString(R.string.required_field));
        } else {
            siglaLayout.setError(null);
        }

        if (nomeEdit.getText().toString().isEmpty()){
            valido = false;
            nomeLayout.setError(getApplicationContext().getString(R.string.required_field));
        } else {
            nomeLayout.setError(null);
        }


        if (cidadeEdit.getText().toString().isEmpty()){
            valido = false;
            cidadeLayout.setError(getApplicationContext().getString(R.string.required_field));
        } else {
            cidadeLayout.setError(null);
        }

        if (enderecoEdit.getText().toString().isEmpty()){
            valido = false;
            enderecoLayout.setError(getApplicationContext().getString(R.string.required_field));
        } else {
            enderecoLayout.setError(null);
        }

        if (senhaEdit.getText().toString().isEmpty()){
            valido = false;
            senhaLayout.setError(getApplicationContext().getString(R.string.required_field));
        } else {
            senhaLayout.setError(null);
        }

        if (valido){
            if (Connection.getUrl() != null){
                String url = Connection.getUrl()+"registerfaculdade.php"; // Completa com o ip já salvo
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            // aquit trata da resposta, o jsonObject vira tipo um dicionário
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean error = jsonObject.getBoolean("erro"); //Esse erro é o mesmo passado lá no php
                                    if (!error) {
                                        Toast.makeText(getApplicationContext(), "Cadastro realizado", Toast.LENGTH_LONG).show();
                                    } else {
                                        String motivo = jsonObject.getString("motivo");
                                        Toast.makeText(getApplicationContext(), motivo, Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "Não foi possível cadastrar ", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                    new Response.ErrorListener() {
                        @Override
                        //Ele cai aqui se der algum erro bizarro, como não ter conexão
                        public void onErrorResponse(VolleyError error) {
                            String motivo = error.getMessage();
                            Toast.makeText(getApplicationContext(), "Erro desconhecido: "+motivo, Toast.LENGTH_LONG).show();

                        }
                }
                ) {
                    @Override
                    //aqui são os parâmetros que serão enviados
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("sigla",siglaEdit.getText().toString());
                        params.put("nome",nomeEdit.getText().toString());
                        params.put("cidade",cidadeEdit.getText().toString());
                        params.put("endereco",enderecoEdit.getText().toString());
                        params.put("senha", senhaEdit.getText().toString());
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
