package com.example.quickvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private TextView text_esqueci_senha;
    private androidx.appcompat.widget.AppCompatButton bt_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide(); // Para esconder a barra roxa feia
        IniciarComponentes();

        text_esqueci_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Esqueci_senha.class);
                startActivity(intent);
            }
        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Menu.class);
                startActivity(intent);
            }
        });
    }

    private void IniciarComponentes(){
        text_esqueci_senha = findViewById(R.id.text_esqueci_senha);
        bt_entrar = findViewById(R.id.bt_entrar);

    }

}