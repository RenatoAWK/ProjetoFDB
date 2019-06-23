package com.bsi.fbd.minisiga.gui.adm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Bloco;
import com.bsi.fbd.minisiga.modelo.Connection;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.QuickAdapter;
import com.bsi.fbd.minisiga.modelo.User;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlocoAdmFragment extends Fragment {

    private RequestQueue requestQueue;
    private ArrayList<Bloco> resultado = new ArrayList<>();
    private RecyclerView recyclerView;
    private QuickAdapter quickAdapter;
    private Context context;
    private View view;

    public BlocoAdmFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bloco_adm, container, false);
        recyclerView = view.findViewById(R.id.blocoRecyclerAdm);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        popular();
        return view;
    }

    private void setUpAdapter() {
        quickAdapter = new QuickAdapter(R.layout.layout_item_recycler, resultado);
        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ////// mandar pra tela de detalhes
            }
        });

        quickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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
        recyclerView.setAdapter(quickAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void popular() {
        requestQueue = Volley.newRequestQueue(getContext());

        String url = Connection.getUrl() + "getblocos.php";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("erro"); //Esse erro é o mesmo passado lá no php
                    if (!error) {
                        int qtd = jsonObject.getInt("qtd");
                        for (int i = 0; i < qtd ; i++) {
                            JSONObject item = jsonObject.getJSONObject(String.valueOf(i));
                            Bloco bloco = new Bloco();
                            bloco.setSiglaFaculdade(item.getString("sigla_faculdade"));
                            bloco.setArea(item.getString("area"));
                            bloco.setCodigo(Integer.parseInt(item.getString("codigo")));
                            bloco.setTipo(item.getString("tipo"));
                            resultado.add(bloco);
                        }
                        setUpAdapter();

                    } else {
                        Toast.makeText(context, "Erro 1", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "Erro 2 ", Toast.LENGTH_LONG).show();
                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String motivo = error.getMessage();
                Toast.makeText(context, "Erro desconhecido: " + motivo, Toast.LENGTH_LONG).show();
            }
        };

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                Faculdade faculdade = (Faculdade) User.getCurrentUser();
                params.put("sigla_faculdade", faculdade.getSigla());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}
