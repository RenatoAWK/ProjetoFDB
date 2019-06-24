package com.bsi.fbd.minisiga.gui.adm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Faculdade;
import com.bsi.fbd.minisiga.modelo.Professor;
import com.bsi.fbd.minisiga.modelo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfessorEditAdm extends AppCompatActivity {
    private Professor professor;
    private Faculdade faculdade = (Faculdade) User.getCurrentUser();
    private com.bsi.fbd.minisiga.modelo.Response response;
    private com.google.android.material.textfield.TextInputLayout cpfLayout;
    private com.google.android.material.textfield.TextInputLayout nomeLayout;
    private com.google.android.material.textfield.TextInputLayout enderecoLayout;
    private com.google.android.material.textfield.TextInputLayout emailLayout;
    private com.google.android.material.textfield.TextInputLayout senhaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_edit_adm);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        professor = intent.getParcelableExtra("professor");

        cpfLayout = findViewById(R.id.cpfLayoutEditProf);
        nomeLayout = findViewById(R.id.nomeLayoutEditProf);
        enderecoLayout = findViewById(R.id.enderecoLayoutEditProf);
        emailLayout = findViewById(R.id.emailLayoutEditProf);
        senhaLayout = findViewById(R.id.senhaLayoutEditProf);

        cpfLayout.getEditText().setText(professor.getCpf());
        nomeLayout.getEditText().setText(professor.getNome());
        enderecoLayout.getEditText().setText(professor.getEndereco());
        emailLayout.getEditText().setText(professor.getEmail());
        senhaLayout.getEditText().setText(professor.getSenha());


        response = new com.bsi.fbd.minisiga.modelo.Response("editprofessor.php",this);

    }

    public void salvar(View view) {

        boolean valido = true;
        ArrayList list = new ArrayList<com.google.android.material.textfield.TextInputLayout>();
        list.add(cpfLayout);
        list.add(nomeLayout);
        list.add(enderecoLayout);
        list.add(emailLayout);
        list.add(senhaLayout);
        for (Object object : list) {
            com.google.android.material.textfield.TextInputLayout textInputLayout = (com.google.android.material.textfield.TextInputLayout) object;
            if (textInputLayout.getEditText().getText().toString().isEmpty()) {
                textInputLayout.setError(getApplicationContext().getString(R.string.required_field));
                valido = false;
            } else {
                textInputLayout.setError(null);
            }
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailLayout.getEditText().getText().toString().trim()).matches()) {
            emailLayout.setError(getApplicationContext().getString(R.string.invalid_email));
            valido = false;
        } else {
            emailLayout.setError(null);
        }


        if (valido) {
            Map<String, String> params = new HashMap<>();
            params.put("cpf_antigo", professor.getCpf());
            params.put("cpf", cpfLayout.getEditText().getText().toString().trim());
            params.put("nome", nomeLayout.getEditText().getText().toString().trim());
            params.put("endereco", enderecoLayout.getEditText().getText().toString().trim());
            params.put("senha", senhaLayout.getEditText().getText().toString().trim());
            params.put("email", emailLayout.getEditText().getText().toString().trim());
            params.put("sigla_faculdade", faculdade.getSigla());
            response.run(params);

        }
    }
}
