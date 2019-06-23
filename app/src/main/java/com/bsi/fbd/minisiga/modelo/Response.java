package com.bsi.fbd.minisiga.modelo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bsi.fbd.minisiga.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;

public class Response {
    private String url;
    private com.android.volley.Response.Listener<String> responseListener;
    private com.android.volley.Response.ErrorListener errorListener;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;
    private JSONObject jsonObject;

    public Response(String php, final Context context) {
        this.url = Connection.getUrl() + php;
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void run(final Map<String, String> params){

        if (Connection.getUrl() != null) {

            responseListener = new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        jsonObject = new JSONObject(response);
                        boolean error = jsonObject.getBoolean("erro"); //Esse erro é o mesmo passado lá no php
                        if (!error) {
                            Toast.makeText(context.getApplicationContext(), "Operação realizada", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context.getApplicationContext(), "Erro 1", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Erro 2 ", Toast.LENGTH_LONG).show();
                    }

                }
            };

            errorListener = new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String motivo = error.getMessage();
                    Toast.makeText(context.getApplicationContext(), "Erro desconhecido: " + motivo, Toast.LENGTH_LONG).show();
                }
            };

            stringRequest = new StringRequest(
                    Request.Method.POST, url, responseListener, errorListener) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Exemplo da estrutura do dicionario
                    //params.put("codigo", codigoLayout.getEditText().getText().toString().trim());
                    //params.put("area", areaLayout.getEditText().getText().toString().trim());
                    //params.put("tipo", tipoLayout.getEditText().getText().toString().trim());
                    //params.put("sigla_faculdade", faculdade.getSigla() );
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(context.getApplicationContext(), context.getApplicationContext().getString(R.string.IP_required), Toast.LENGTH_LONG);
        }
    }
}
