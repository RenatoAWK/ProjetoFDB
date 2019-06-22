package com.bsi.fbd.minisiga.gui.adm;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
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
import com.bsi.fbd.minisiga.modelo.Connection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlunoAdmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlunoAdmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlunoAdmFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private com.google.android.material.textfield.TextInputLayout cpfLayout;
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout enderecoLayout;
    private com.google.android.material.textfield.TextInputLayout emailLayout;
    private com.google.android.material.textfield.TextInputLayout senhaLayout;

    private RequestQueue requestQueue;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AlunoAdmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlunoAdmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlunoAdmFragment newInstance(String param1, String param2) {
        AlunoAdmFragment fragment = new AlunoAdmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            cpfLayout = getView().findViewById(R.id.cpfLayoutRegister);
            nomeLayout = getView().findViewById(R.id.nomeLayoutRegister);
            enderecoLayout = getView().findViewById(R.id.enderecoLayoutRegister);
            emailLayout = getView().findViewById(R.id.emailLayoutRegister);
            senhaLayout = getView().findViewById(R.id.senhaLayoutRegister);
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aluno_adm, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                textInputLayout.setError(getActivity().getApplicationContext().getString(R.string.required_field));
                valido = false;
            } else {
                textInputLayout.setError(null);
            }
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailLayout.getEditText().getText().toString().trim()).matches()){
            emailLayout.setError(getActivity().getApplicationContext().getString(R.string.invalid_email));
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
                                getActivity().getFragmentManager().popBackStack();
                                Toast.makeText(getActivity().getApplicationContext(), "Cadastro realizado", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "Erro 1", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getActivity().getApplicationContext(), "Erro 2 ", Toast.LENGTH_LONG).show();
                        }

                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String motivo = error.getMessage();
                        Toast.makeText(getActivity().getApplicationContext(), "Erro desconhecido: "+motivo, Toast.LENGTH_LONG).show();
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
                Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.IP_required), Toast.LENGTH_LONG);
            }

        }

    }
}
