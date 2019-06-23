package com.bsi.fbd.minisiga.gui.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.bsi.fbd.minisiga.gui.adm.Main_Adm_Activity;
import com.bsi.fbd.minisiga.gui.adm.RegisterActivity;
import com.bsi.fbd.minisiga.modelo.Aluno;
import com.bsi.fbd.minisiga.modelo.Connection;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.Professor;
import com.bsi.fbd.minisiga.modelo.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout email;
    private com.google.android.material.textfield.TextInputLayout senha;
    private RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        email = findViewById(R.id.emailLayoutLogin);
        senha = findViewById(R.id.senhaLayoutLogin);
        requestQueue = Volley.newRequestQueue(this);

    }


    public void configurarRede(View view) {
        Intent intent = new Intent(getApplicationContext(), Network.class);
        startActivity(intent);
    }

    public void registrarFaculdade(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {

        boolean valido = true;
        if (email.getEditText().getText().toString().isEmpty()){
            email.setError(getText(R.string.required_field));
            valido = false;
        } else {
            email.setError(null);
        }

        if (email.getEditText().getText().toString().isEmpty()){
            senha.setError(getText(R.string.required_field));
            valido = false;
        } else {
            if (Patterns.EMAIL_ADDRESS.matcher(email.getEditText().getText().toString().trim()).matches())
                email.setError(null);
            else {
                email.setError(getText(R.string.invalid_email));
            }
        }

        if (senha.getEditText().getText().toString().isEmpty()){
            senha.setError(getText(R.string.required_field));
            valido = false;
        } else {
            email.setError(null);
        }

        if (valido){
            if (Connection.getUrl() != null){
                String url = Connection.getUrl()+"login.php";

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean erro = jsonObject.getBoolean("erro");
                            if (!erro){
                                String tipo = jsonObject.getString("tipo");
                                if (tipo.equals("faculdade")) {
                                    Faculdade faculdade = new Faculdade();
                                    faculdade.setSigla(jsonObject.getString("sigla"));
                                    faculdade.setNome(jsonObject.getString("nome"));
                                    faculdade.setCidade(jsonObject.getString("cidade"));
                                    faculdade.setEndereco(jsonObject.getString("endereco"));
                                    faculdade.setEmail(jsonObject.getString("email"));
                                    faculdade.setSenha(jsonObject.getString("senha"));

                                    User.setCurrentUser(faculdade);

                                    Intent intent = new Intent(getApplicationContext(), Main_Adm_Activity.class);
                                    finish();
                                    startActivity(intent);

                                } else if (tipo.equals("aluno")){

                                    Aluno aluno = new Aluno();
                                    aluno.setCpf(jsonObject.getString("cpf"));
                                    aluno.setNome(jsonObject.getString("nome"));
                                    aluno.setEndereco(jsonObject.getString("endereco"));
                                    aluno.setEmail(jsonObject.getString("email"));
                                    aluno.setSenha(jsonObject.getString("senha"));

                                    User.setCurrentUser(aluno);

                                    ///////

                                } else if (tipo.equals("professor")){

                                    Professor professor = new Professor();
                                    professor.setCpf(jsonObject.getString("cpf"));
                                    professor.setNome(jsonObject.getString("nome"));
                                    professor.setEndereco(jsonObject.getString("endereco"));
                                    professor.setEmail(jsonObject.getString("email"));
                                    professor.setSenha(jsonObject.getString("senha"));

                                    User.setCurrentUser(professor);
                                    //////

                                }
                            } else {
                                String motivo = jsonObject.getString("motivo");
                                Toast.makeText(getApplicationContext(), motivo, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                Response.ErrorListener errorListener =  new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String motivo = error.getMessage();
                        Toast.makeText(getApplicationContext(), "Erro desconhecido: "+motivo, Toast.LENGTH_LONG).show();
                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener)
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email",email.getEditText().getText().toString().trim());
                        params.put("senha",senha.getEditText().getText().toString().trim());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
            else {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.IP_required), Toast.LENGTH_LONG);
            }
        }

    }
}
