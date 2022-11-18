package com.example.quickvision;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Consultar extends AppCompatActivity {

    Button bt_pesquisar, bt_voltar;
    EditText edit_pesquisar;
    TextView text_nome, text_data, text_hora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        getSupportActionBar().hide();
        IniciarComponentes();

        bt_pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultarAgendamento();
            }
        });

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Consultar.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void ConsultarAgendamento(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("Agendamentos").document(edit_pesquisar.getText().toString());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null){
                    text_nome.setText(documentSnapshot.getString("Nome"));
                    text_data.setText(documentSnapshot.getString("Data"));
                    text_hora.setText(documentSnapshot.getString("Hora"));
                }
            }
        });
    }


    private void IniciarComponentes() {
        bt_pesquisar = findViewById(R.id.bt_pesquisar);
        edit_pesquisar = findViewById(R.id.edit_pesquisar);
        text_nome = findViewById(R.id.text_nome);
        text_data = findViewById(R.id.text_data);
        text_hora = findViewById(R.id.text_hora);
        bt_voltar = findViewById(R.id.bt_voltar);
    }
}