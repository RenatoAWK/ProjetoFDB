package com.bsi.fbd.minisiga.modelo;

import android.content.Context;
import android.content.Intent;
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
import java.util.HashMap;
import java.util.Map;

public class ResponseAR {
    private String url;
    private com.android.volley.Response.Listener<String> responseListener;
    private com.android.volley.Response.ErrorListener errorListener;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;
    private JSONObject jsonObject;
    private RecyclerView recyclerView;
    private QuickAdapterAR adapter;
    private ArrayList<Object> resultado = new ArrayList<>();
    private Integer positionItem = null;
    private String urlDelete;
    private String query;

    public ResponseAR(String php, final Context context, RecyclerView recyclerView) {
        this.url = Connection.getUrl() + php;
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public void run(final Map<String, String> params) {

        if (Connection.getUrl() != null) {

            responseListener = new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        jsonObject = new JSONObject(response);
                        boolean error = jsonObject.getBoolean("erro"); //Esse erro é o mesmo passado lá no php
                        query = jsonObject.getString("query");
                        if (!error) {
                            if (recyclerView != null) {
                                if (positionItem != null && urlDelete != null) { //Se chegou aqui, quer dizer que está apagando
                                    resultado.remove(resultado.get(positionItem));
                                    recyclerView.getAdapter().notifyItemRemoved(positionItem);
                                    positionItem = null;
                                    urlDelete = null;
                                } else { ///Se chegou aqui, quer dizer que vai retornar
                                    resultado.clear();
                                    String tipo = jsonObject.getString("tipo");
                                    if (tipo.equals("aluno_cadastrado")) {

                                        int qtd = jsonObject.getInt("qtd");
                                        if (qtd > 0) {
                                            for (int i = 0; i < qtd; i++) {
                                                JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                                Aluno aluno = new Aluno();
                                                aluno.setCpf(item.getString("cpf"));
                                                aluno.setNome(item.getString("nome"));
                                                aluno.setEndereco(item.getString("endereco"));
                                                aluno.setEmail(item.getString("email"));
                                                aluno.setSenha(item.getString("senha"));
                                                resultado.add(aluno);
                                            }
                                            adapter = new QuickAdapterAR(R.layout.layout_item_add_remove_recycler, resultado).setAdd_remove("remove");
                                            setUpAdapter();
                                        }
                                    } else  if (tipo.equals("aluno_nao_cadastrado")) {

                                        int qtd = jsonObject.getInt("qtd");
                                        if (qtd > 0) {
                                            for (int i = 0; i < qtd; i++) {
                                                JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                                Aluno aluno = new Aluno();
                                                aluno.setCpf(item.getString("cpf"));
                                                aluno.setNome(item.getString("nome"));
                                                aluno.setEndereco(item.getString("endereco"));
                                                aluno.setEmail(item.getString("email"));
                                                aluno.setSenha(item.getString("senha"));
                                                resultado.add(aluno);
                                            }
                                            adapter = new QuickAdapterAR(R.layout.layout_item_add_remove_recycler, resultado).setAdd_remove("add");
                                            setUpAdapter();
                                        }
                                    } else if (tipo.equals("disciplina_cadastrada")) {

                                        int qtd = jsonObject.getInt("qtd");
                                        if (qtd > 0) {
                                            for (int i = 0; i < qtd; i++) {
                                                JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                                Disciplina disciplina = new Disciplina();
                                                disciplina.setNome(item.getString("nome"));
                                                disciplina.setSigla_faculdade(item.getString("sigla_faculdade"));
                                                disciplina.setCodigo(item.getInt("codigo"));
                                                disciplina.setCargaHoraria(item.getInt("carga_horaria"));
                                                resultado.add(disciplina);
                                            }
                                            adapter = new QuickAdapterAR(R.layout.layout_item_add_remove_recycler, resultado).setAdd_remove("remove");
                                            setUpAdapter();
                                        }
                                    } else if (tipo.equals("disciplina_nao_cadastrada")) {

                                        int qtd = jsonObject.getInt("qtd");
                                        if (qtd > 0) {
                                            for (int i = 0; i < qtd; i++) {
                                                JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                                Disciplina disciplina = new Disciplina();
                                                disciplina.setNome(item.getString("nome"));
                                                disciplina.setSigla_faculdade(item.getString("sigla_faculdade"));
                                                disciplina.setCodigo(item.getInt("codigo"));
                                                disciplina.setCargaHoraria(item.getInt("carga_horaria"));
                                                resultado.add(disciplina);
                                            }
                                            adapter = new QuickAdapterAR(R.layout.layout_item_add_remove_recycler, resultado).setAdd_remove("add");
                                            setUpAdapter();
                                        }
                                    }

                                }


                            }

                        }
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Erro 2 " + e.getMessage() + "\n" + query + "\n" + response, Toast.LENGTH_LONG).show();
                    }

                }
            };

            errorListener = new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String motivo = error.getMessage();
                    Toast.makeText(context.getApplicationContext(), "Erro desconhecido: " + motivo,  Toast.LENGTH_LONG).show();
                }
            };
            if (urlDelete == null) {
                stringRequest = new StringRequest(
                        Request.Method.POST, url, responseListener, errorListener) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return params;
                    }
                };
            } else {
                stringRequest = new StringRequest(
                        Request.Method.POST, urlDelete, responseListener, errorListener) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return params;
                    }
                };

            }
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(context.getApplicationContext(), context.getApplicationContext().getString(R.string.IP_required), Toast.LENGTH_LONG);
        }
    }

    private void setUpAdapter() {

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                positionItem = position;
                Object item = resultado.get(position);
                Map<String, String> params = new HashMap<>();

                if (view.getId() == R.id.add) {
                    ////// adicionar
                    if (item instanceof Aluno) {
                        urlDelete = Connection.getUrl() + "registerturma_aluno.php";
                        Aluno aluno = (Aluno) item;
                        params.put("codigo_turma", String.valueOf(User.getTurma().getCodigo()));
                        params.put("cpf_aluno", aluno.getCpf());
                        run(params);
                        /////////////

                    } else if (item instanceof Disciplina) {
                        urlDelete = Connection.getUrl() + "registercurso_disciplina.php";
                        Disciplina disciplina = (Disciplina) item;
                        params.put("codigo_curso", String.valueOf(User.getCurso().getCodigo()));
                        params.put("codigo_disciplina", String.valueOf(disciplina.getCodigo()));
                        run(params);
                        /////////////

                    }
                    /////////////

                } else if (view.getId() == R.id.remove) {
                    ////// apagar
                    if (item instanceof Aluno) {
                        urlDelete = Connection.getUrl() + "deleteturma_aluno.php";
                        Aluno aluno = (Aluno) item;
                        params.put("codigo_turma", String.valueOf(User.getTurma().getCodigo()));
                        params.put("cpf_aluno", aluno.getCpf());
                        run(params);
                        /////////////

                    } else if (item instanceof Disciplina) {
                        urlDelete = Connection.getUrl() + "deletecurso_disciplina.php";
                        Disciplina disciplina = (Disciplina) item;
                        params.put("codigo_curso", String.valueOf(User.getCurso().getCodigo()));
                        params.put("codigo_disciplina", String.valueOf(disciplina.getCodigo()));
                        run(params);
                        /////////////

                    }
                    /////////////

                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
