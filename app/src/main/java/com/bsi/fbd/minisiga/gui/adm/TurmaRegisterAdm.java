package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Bloco;
import com.bsi.fbd.minisiga.modelo.Connection;
import com.bsi.fbd.minisiga.modelo.Curso;
import com.bsi.fbd.minisiga.modelo.Response;
import com.bsi.fbd.minisiga.modelo.Sala;
import com.bsi.fbd.minisiga.modelo.Turma;
import com.bsi.fbd.minisiga.modelo.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TurmaRegisterAdm extends AppCompatActivity {
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url;
    private String query;
    private com.android.volley.Response.Listener<String> responseListener;
    private com.android.volley.Response.ErrorListener errorListener;
    private JSONObject jsonObject;
    private ArrayList<Curso> resultado = new ArrayList<>();
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> paramsR = new HashMap<>();
    private RadioGroup radioGroup;
    private RadioGroup.LayoutParams p = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
    private com.google.android.material.textfield.TextInputLayout anoLayout;
    private com.google.android.material.textfield.TextInputLayout codigoLayout;
    private Sala sala;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_register_adm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        sala = intent.getParcelableExtra("sala");

        anoLayout = findViewById(R.id.anoLayoutRegisterTurma);
        codigoLayout = findViewById(R.id.codigoLayoutRegisterTurma);

        radioGroup = findViewById(R.id.cursoRadioTurma);
        requestQueue = Volley.newRequestQueue(this);
        Bloco bloco = User.getBloco();
        params.put("codigo_bloco",String.valueOf(bloco.getCodigo()));
        params.put("sigla_faculdade", bloco.getSiglaFaculdade());
        getCursos();


    }

    private void getCursos() {
        if (Connection.getUrl() != null) {
            url = Connection.getUrl() + "getcursos.php";
            responseListener = new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        jsonObject = new JSONObject(response);
                        boolean error = jsonObject.getBoolean("erro"); //Esse erro é o mesmo passado lá no php
                        query = jsonObject.getString("query");
                        if (!error) {
                            String tipo = jsonObject.getString("tipo");
                            if (tipo.equals("curso")) {

                                int qtd = jsonObject.getInt("qtd");
                                if (qtd > 0) {
                                    for (int i = 0; i < qtd; i++) {
                                        JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                        Curso curso = new Curso();
                                        curso.setNome(item.getString("nome"));
                                        curso.setCodigo(item.getInt("codigo"));
                                        curso.setDuracao(item.getInt("duracao"));
                                        curso.setSigla(item.getString("sigla"));
                                        curso.setRamal(item.getInt("ramal"));
                                        curso.setSigla_faculdade(item.getString("sigla_faculdade"));
                                        curso.setCodigo_bloco(item.getInt("codigo_bloco"));
                                        resultado.add(curso);
                                        RadioButton radioButton = new RadioButton(getApplicationContext());
                                        radioButton.setText(curso.getSigla());
                                        radioButton.setId(i);
                                        radioGroup.addView(radioButton, p);

                                    }


                                }

                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "Erro 1", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext().getApplicationContext(), "Erro 2 " + e.getMessage() + "\n" + query + "\n" + response, Toast.LENGTH_LONG).show();
                    }
                }

            };

            stringRequest = new StringRequest(
                    Request.Method.POST, url, responseListener, errorListener) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };

            errorListener = new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String motivo = error.getMessage();
                    Toast.makeText(getApplicationContext(), "Erro desconhecido: " + motivo, Toast.LENGTH_LONG).show();
                }
            };
            requestQueue.add(stringRequest);

        }
    }

    public void salvar(View view) {
        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add (codigoLayout);
        list.add (anoLayout);
        for (Object object: list){
            com.google.android.material.textfield.TextInputLayout textInputLayout = (com.google.android.material.textfield.TextInputLayout)object;
            if (textInputLayout.getEditText().getText().toString().isEmpty()){
                textInputLayout.setError(getApplicationContext().getString(R.string.required_field));
                valido = false;
            } else {
                textInputLayout.setError(null);
            }
        }
        if (anoLayout.getEditText().getText().toString().length() != 4){
            anoLayout.setError(getApplicationContext().getString(R.string.required_field));
            valido = false;
        } else {
            anoLayout.setError(null);
        }

        if (radioGroup.getCheckedRadioButtonId() == -1 ){
            valido = false;
        }

        if (valido){
            Response response = new Response("registerturma.php",getApplicationContext());
            paramsR.put("ano",anoLayout.getEditText().getText().toString());
            paramsR.put("codigo", codigoLayout.getEditText().getText().toString());
            paramsR.put("codigo_bloco", String.valueOf(sala.getCodigoBloco()));
            paramsR.put("codigo_curso", String.valueOf(resultado.get(radioGroup.getCheckedRadioButtonId()).getCodigo()));
            paramsR.put("numero_sala", String.valueOf(sala.getNumero()));
            paramsR.put("sigla_faculdade", sala.getSiglaFaculdade());
            response.run(paramsR);
        }
    }

}
