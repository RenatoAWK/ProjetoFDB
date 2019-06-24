package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private com.google.android.material.textfield.TextInputLayout siglaLayout;
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout cidadeLayout;
    private com.google.android.material.textfield.TextInputLayout enderecoLayout;
    private com.google.android.material.textfield.TextInputLayout emailLayout;
    private com.google.android.material.textfield.TextInputLayout senhaLayout;
    private com.google.android.material.textfield.TextInputLayout dataLayout;
    private RequestQueue requestQueue;
    private Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        siglaLayout = findViewById(R.id.siglaLayoutRegister);
        nomeLayout = findViewById(R.id.nomeLayoutRegister);
        cidadeLayout = findViewById(R.id.cidadeLayoutRegister);
        enderecoLayout = findViewById(R.id.enderecoLayoutRegister);
        emailLayout = findViewById(R.id.emailLayoutRegister);
        senhaLayout = findViewById(R.id.senhaLayoutRegister);
        dataLayout = findViewById(R.id.dataLayoutRegister);
        requestQueue = Volley.newRequestQueue(this);

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dataLayout.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        dataLayout.getEditText().setFocusable(false);


    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));
        dataLayout.getEditText().setText(sdf.format(myCalendar.getTime()));
    }


    public void salvar(View view) {
        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add(siglaLayout);
        list.add (nomeLayout);
        list.add (cidadeLayout);
        list.add (enderecoLayout);
        list.add(emailLayout);
        list.add (senhaLayout);
        list.add (dataLayout);
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

                String url = Connection.getUrl()+"registerfaculdade.php"; // Completa com o ip já salvo

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
                                params.put("sigla",siglaLayout.getEditText().getText().toString().trim());
                                params.put("nome",nomeLayout.getEditText().getText().toString().trim());
                                params.put("cidade",cidadeLayout.getEditText().getText().toString().trim());
                                params.put("endereco",enderecoLayout.getEditText().getText().toString().trim());
                                params.put("email", emailLayout.getEditText().getText().toString().trim());
                                params.put("senha", senhaLayout.getEditText().getText().toString().trim());
                                params.put("data_implantacao", myCalendar.get(Calendar.YEAR)+"-"+myCalendar.get(Calendar.MONTH)+"-"+myCalendar.get(Calendar.DAY_OF_MONTH));
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
