package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Connection;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlunoRegisterAdm extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout cpfLayout;
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout enderecoLayout;
    private com.google.android.material.textfield.TextInputLayout emailLayout;
    private com.google.android.material.textfield.TextInputLayout senhaLayout;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        cpfLayout = findViewById(R.id.cpfLayoutRegisterAluno);
        nomeLayout = findViewById(R.id.nomeLayoutRegisterAluno);
        enderecoLayout = findViewById(R.id.enderecoLayoutRegisterAluno);
        emailLayout = findViewById(R.id.emailLayoutRegisterAluno);
        senhaLayout = findViewById(R.id.senhaLayoutRegisterAluno);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void salvar(View view) {
        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add(cpfLayout);
        list.add (nomeLayout);
        list.add (enderecoLayout);
        list.add(emailLayout);
        list.add (senhaLayout);
        for (Object object: list){
            com.google.android.material.textfield.TextInputLayout textInputLayout = (com.google.android.material.textfield.TextInputLayout)object;
            if (textInputLayout.getEditText().getText().toString().isEmpty()){
                textInputLayout.setError(getApplicationContext().getString(R.string.required_field));
                valido = false;
            } else {
                textInputLayout.setError(null);
            }
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailLayout.getEditText().getText().toString().trim()).matches()){
            emailLayout.setError(getApplicationContext().getString(R.string.invalid_email));
            valido = false;
        } else {
            emailLayout.setError(null);
        }


        if (valido){
            if (Connection.getUrl() != null){

                String url = Connection.getUrl()+"registeraluno.php"; // Completa com o ip já salvo

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    // Chama quando consegue uma resposta
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
                        Toast.makeText(getApplicationContext(), "Erro desconhecido: "+motivo, Toast.LENGTH_LONG).show();
                    }
                };

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, url, responseListener, errorListener)
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("cpf",cpfLayout.getEditText().getText().toString().trim());
                        params.put("nome",nomeLayout.getEditText().getText().toString().trim());
                        params.put("endereco",enderecoLayout.getEditText().getText().toString().trim());
                        params.put("email", emailLayout.getEditText().getText().toString().trim());
                        params.put("senha", senhaLayout.getEditText().getText().toString().trim());
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
