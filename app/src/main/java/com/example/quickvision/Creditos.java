package com.example.quickvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Creditos extends AppCompatActivity {

    Button bt_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
        getSupportActionBar().hide();
        IniciarComponentes();

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Creditos.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void IniciarComponentes(){
        bt_voltar = findViewById(R.id.bt_voltar);
    }
}


