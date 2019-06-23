package com.bsi.fbd.minisiga.gui.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.bsi.fbd.minisiga.R;
import com.bsi.fbd.minisiga.modelo.Connection;

public class Network extends AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout ipLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ipLayout = findViewById(R.id.ipLayoutNetwork);
    }

    public void salvar(View view) {
        String ip = ipLayout.getEditText().getText().toString().trim();
        if (Patterns.IP_ADDRESS.matcher(ip).matches()){
            Connection.setIp(ip);
            Toast.makeText(getApplication(), "IP salvo",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            ipLayout.setError(getApplicationContext().getString(R.string.invalid_ip));
        }

    }
}
