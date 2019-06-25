package com.bsi.fbd.minisiga.modelo;

import android.app.Activity;
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
import com.bsi.fbd.minisiga.gui.adm.AlunoDetailAdm;
import com.bsi.fbd.minisiga.gui.adm.AlunoEditAdm;
import com.bsi.fbd.minisiga.gui.adm.BlocoDetailAdm;
import com.bsi.fbd.minisiga.gui.adm.BlocoEditAdm;
import com.bsi.fbd.minisiga.gui.adm.MainBlocoActivity;
import com.bsi.fbd.minisiga.gui.adm.ProfessorDetailAdm;
import com.bsi.fbd.minisiga.gui.adm.ProfessorEditAdm;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private String url;
    private com.android.volley.Response.Listener<String> responseListener;
    private com.android.volley.Response.ErrorListener errorListener;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Context context;
    private JSONObject jsonObject;
    private RecyclerView recyclerView;
    private QuickAdapter adapter;
    private ArrayList<Object> resultado = new ArrayList<>();
    private Integer positionItem = null;
    private String urlDelete;
    private String query;



    public Response(String php, final Context context) {
        this.url = Connection.getUrl() + php;
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }
    public Response(String php, final Context context, RecyclerView recyclerView){
        this.url = Connection.getUrl() + php;
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public void run(final Map<String, String> params){

        if (Connection.getUrl() != null) {

            responseListener = new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        jsonObject = new JSONObject(response);
                        boolean error = jsonObject.getBoolean("erro"); //Esse erro é o mesmo passado lá no php
                        query = jsonObject.getString("query");
                        if (!error) {
                            if (recyclerView != null){
                                if (positionItem != null && urlDelete != null){ //Se chegou aqui, quer dizer que está apagando
                                    resultado.remove(resultado.get(positionItem));
                                    recyclerView.getAdapter().notifyItemRemoved(positionItem);
                                    positionItem = null;
                                    urlDelete = null;
                                } else { ///Se chegou aqui, quer dizer que vai retornar

                                    String tipo = jsonObject.getString("tipo");
                                    if (tipo.equals("bloco")){

                                        int qtd = jsonObject.getInt("qtd");
                                        if (qtd > 0){
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

                                    } else if (tipo.equals("sala")){

                                        int qtd = jsonObject.getInt("qtd");
                                        if (qtd > 0){
                                            for (int i = 0; i < qtd ; i++) {
                                                JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                                                Sala sala = new Sala();
                                                sala.setNumero(item.getInt("numero"));
                                                sala.setTipo(item.getString("tipo"));
                                                sala.setArea(item.getString("area"));
                                                sala.setSiglaFaculdade(item.getString("sigla_faculdade"));
                                                sala.setCodigoBloco(item.getInt("codigo_bloco"));
                                                resultado.add(sala);
                                            }
                                            adapter = new QuickAdapter(R.layout.layout_item_recycler, resultado);
                                            setUpAdapter();
                                        }

                                    } else if (tipo.equals("curso")){

                                        int qtd = jsonObject.getInt("qtd");
                                        if (qtd > 0){
                                            for (int i = 0; i < qtd ; i++) {
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
                                            }
                                            adapter = new QuickAdapter(R.layout.layout_item_recycler, resultado);
                                            setUpAdapter();
                                        }

                                    }


                                }


                            } else {
                                Toast.makeText(context.getApplicationContext(), "Operação realizada", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(context.getApplicationContext(), "Erro 1", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Erro 2 "+e.getMessage()+"\n"+query+"\n"+response, Toast.LENGTH_LONG).show();
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
            if (urlDelete == null){
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
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ////// mandar para próxima tela
                Object item = resultado.get(position);
                Intent intent ;
                if (item instanceof Bloco){
                    intent = new Intent(context, MainBlocoActivity.class);
                    intent.putExtra("bloco",(Bloco) item);
                    User.setBloco((Bloco) item);
                    context.startActivity(intent);
                }


            }
        });

        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                ////// mandar pra tela de detalhes
                Object item = resultado.get(position);
                Intent intent;
                if (item instanceof Bloco) {
                    intent = new Intent(context, BlocoDetailAdm.class);
                    intent.putExtra("bloco", (Bloco) item);
                    context.startActivity(intent);
                } else if (item instanceof Aluno) {
                    intent = new Intent(context, AlunoDetailAdm.class);
                    intent.putExtra("aluno", (Aluno) item);
                    context.startActivity(intent);
                } else if (item instanceof Professor) {
                    intent = new Intent(context, ProfessorDetailAdm.class);
                    intent.putExtra("professor", (Professor) item);
                    context.startActivity(intent);
                } else if (item instanceof Sala) {
                    //intent = new Intent(context, ProfessorDetailAdm.class);
                    //intent.putExtra("professor",(Professor) item);
                    //context.startActivity(intent);
                } else if (item instanceof Curso) {
                    //intent = new Intent(context, ProfessorDetailAdm.class);
                    //intent.putExtra("professor",(Professor) item);
                    //context.startActivity(intent);
                }

                /////////////

                return false;
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                positionItem = position;
                Object item = resultado.get(position);
                Map<String, String> params = new HashMap<>();
                Faculdade faculdade = (Faculdade) User.getCurrentUser();

                if (view.getId() == R.id.edit){
                    Intent intent;
                    ////// mandar pra tela de edição
                    if (item instanceof Bloco){
                        intent = new Intent(context, BlocoEditAdm.class);
                        intent.putExtra("bloco",(Bloco)item);
                        context.startActivity(intent);
                    } else if (item instanceof Aluno){
                        intent = new Intent(context, AlunoEditAdm.class);
                        intent.putExtra("aluno",(Aluno)item);
                        context.startActivity(intent);
                    } else if (item instanceof Professor){
                        intent = new Intent(context, ProfessorEditAdm.class);
                        intent.putExtra("professor",(Professor) item);
                         context.startActivity(intent);
                    } else if (item instanceof Sala){
                        //intent = new Intent(context, ProfessorEditAdm.class);
                        //intent.putExtra("professor",(Professor) item);
                        //context.startActivity(intent);
                    } else if (item instanceof Curso){
                        //intent = new Intent(context, ProfessorEditAdm.class);
                        //intent.putExtra("professor",(Professor) item);
                        //context.startActivity(intent);
                    }
                    /////////////

                } else {
                    ////// apagar
                    if (item instanceof Bloco){
                        urlDelete = Connection.getUrl() + "deletebloco.php";
                        Bloco bloco = (Bloco) item;
                        params.put("sigla_faculdade",faculdade.getSigla());
                        params.put("codigo",String.valueOf(bloco.getCodigo()));
                        run(params);
                    } else if (item instanceof Aluno){
                        urlDelete = Connection.getUrl() + "deletealuno.php";
                        Aluno aluno = (Aluno) item;
                        params.put("sigla_faculdade",faculdade.getSigla());
                        params.put("cpf",aluno.getCpf());
                        run(params);
                    } else if (item instanceof Professor) {
                        urlDelete = Connection.getUrl() + "deleteprofessor.php";
                        Professor professor = (Professor) item;
                        params.put("sigla_faculdade", faculdade.getSigla());
                        params.put("cpf", professor.getCpf());
                        run(params);
                    } else if (item instanceof Sala) {
                        //urlDelete = Connection.getUrl() + "deleteprofessor.php";
                        //Professor professor = (Professor) item;
                        //params.put("sigla_faculdade", faculdade.getSigla());
                        //params.put("cpf", professor.getCpf());
                        //run(params);
                    } else if (item instanceof Curso) {
                        //urlDelete = Connection.getUrl() + "deleteprofessor.php";
                        //Professor professor = (Professor) item;
                        //params.put("sigla_faculdade", faculdade.getSigla());
                        //params.put("cpf", professor.getCpf());
                        //run(params);

                    }
                    /////////////

                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
