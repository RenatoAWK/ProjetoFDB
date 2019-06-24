package com.bsi.fbd.minisiga.modelo;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bsi.fbd.minisiga.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Response {
    private String url;
    private com.android.volley.Response.Listener<String> responseListener;
    private com.android.volley.Response.ErrorListener errorListener;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;
    private JSONObject jsonObject;
    private Activity edit;
    private RecyclerView recyclerView;
    private QuickAdapter adapter;


    public Response(String php, final Context context) {
        this.url = Connection.getUrl() + php;
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }
    public Response(String php, final Context context, Activity edit, RecyclerView recyclerView){
        this.url = Connection.getUrl() + php;
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
        this.edit = edit;
        this.recyclerView = recyclerView;
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
                            if (recyclerView != null){
                                String tipo = jsonObject.getString("tipo");
                                if (tipo.equals("bloco")){

                                    int qtd = jsonObject.getInt("qtd");
                                    if (qtd > 0){
                                        ArrayList<Bloco> resultado = new ArrayList<>();
                                        for (int i = 0; i < qtd ; i++) {
                                            JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                            Bloco bloco = new Bloco();
                                            bloco.setSiglaFaculdade(item.getString("sigla_faculdade"));
                                            bloco.setArea(item.getString("area"));
                                            bloco.setCodigo(Integer.parseInt(item.getString("codigo")));
                                            bloco.setTipo(item.getString("tipo"));
                                            resultado.add(bloco);
                                        }
                                        adapter = new QuickAdapter(R.layout.layout_item_recycler, resultado);
                                        setUpAdapter();
                                    }

                                } else if (tipo.equals("aluno")){

                                    int qtd = jsonObject.getInt("qtd");
                                    if (qtd > 0){
                                        ArrayList<Aluno> resultado = new ArrayList<>();
                                        for (int i = 0; i < qtd ; i++) {
                                            JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                            Aluno aluno = new Aluno();
                                            aluno.setCpf(item.getString("cpf"));
                                            aluno.setNome(item.getString("nome"));
                                            aluno.setEndereco(item.getString("endereco"));
                                            aluno.setEmail(item.getString("email"));
                                            aluno.setSenha(item.getString("senha"));
                                            resultado.add(aluno);
                                        }
                                        adapter = new QuickAdapter(R.layout.layout_item_recycler, resultado);
                                        setUpAdapter();
                                    }


                                } else if (tipo.equals("professor")){

                                    int qtd = jsonObject.getInt("qtd");
                                    if (qtd > 0){
                                        ArrayList<Professor> resultado = new ArrayList<>();
                                        for (int i = 0; i < qtd ; i++) {
                                            JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                            Professor professor = new Professor();
                                            professor.setCpf(item.getString("cpf"));
                                            professor.setNome(item.getString("nome"));
                                            professor.setEndereco(item.getString("endereco"));
                                            professor.setEmail(item.getString("email"));
                                            professor.setSenha(item.getString("senha"));
                                            resultado.add(professor);
                                        }
                                        adapter = new QuickAdapter(R.layout.layout_item_recycler, resultado);
                                        setUpAdapter();
                                    }
                                    
                                }

                            } else {
                                Toast.makeText(context.getApplicationContext(), "Operação realizada", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(context.getApplicationContext(), "Erro 1", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Erro 2 "+e.getMessage(), Toast.LENGTH_LONG).show();
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
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(context.getApplicationContext(), context.getApplicationContext().getString(R.string.IP_required), Toast.LENGTH_LONG);
        }
    }

    private void setUpAdapter() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ////// mandar pra tela de detalhes
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.edit){
                    ////// mandar pra tela de edição
                    Toast.makeText(context,"Clicou em editar", Toast.LENGTH_SHORT).show();
                } else {
                    ////// apagar
                    Toast.makeText(context,"Clicou em apagar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
