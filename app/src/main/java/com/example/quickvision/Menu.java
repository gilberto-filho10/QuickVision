package com.example.quickvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    Button bt_agendar, bt_consulta, bt_entrada, bt_logout, bt_config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide(); // Para esconder a barra roxa feia
        IniciarComponentes();

        bt_agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Agendar.class);
                startActivity(intent);
            }
        });

        bt_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Consultar.class);
                startActivity(intent);
                finish();
            }
        });

        bt_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Configuracoes.class);
                startActivity(intent);
                finish();
            }
        });

        /*=============================================================================
        Botão de logout, verifica se existe uma sessão ativa no app e caso sim, ele
        finaliza ela e manda para a tela de login
        =============================================================================*/
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Menu.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void IniciarComponentes(){
        bt_agendar = findViewById(R.id.bt_agendar);
        bt_consulta = findViewById(R.id.bt_consulta);
        bt_entrada = findViewById(R.id.bt_entrada);
        bt_logout = findViewById(R.id.bt_logout);
        bt_config = findViewById(R.id.bt_config);
    }
}