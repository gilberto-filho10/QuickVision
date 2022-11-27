package com.example.quickvision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Agendar extends AppCompatActivity {

    private EditText edit_nome, edit_cpf, edit_telefone, edit_obs, edit_data, edit_hora;
    private RadioButton rb_visitante, rb_terceiro;
    private Button bt_salvar, bt_cancelar;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*=====================================
                Validando os preenchimentos dos campos
                ======================================*/
                String nome = edit_nome.getText().toString();
                String cpf = edit_cpf.getText().toString();
                String data = edit_data.getText().toString();
                String hora = edit_hora.getText().toString();

                if (nome.isEmpty() || cpf.isEmpty() || data.isEmpty() || hora.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, R.string.erro_preenchimento, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                if (rb_visitante.isChecked() || rb_terceiro.isChecked()){
                    SalvarAgendamento();
                } else {
                    Snackbar snackbar = Snackbar.make(v, R.string.erro_preenchimento, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }

            }
        });

        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agendar.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void SalvarAgendamento(){
        String nome = edit_nome.getText().toString();
        String cpf = edit_cpf.getText().toString();
        String telefone = edit_telefone.getText().toString();
        String[] tipo = {"Visitante", "Prestador"};
        String obs = edit_obs.getText().toString();
        String data = edit_data.getText().toString();
        String hora = edit_hora.getText().toString();
        String sucesso = String.valueOf(R.string.salvo_agendamento);
        String falha = String.valueOf(R.string.falha_agendamento);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> agendamento = new HashMap<>();
        agendamento.put("Nome", nome);
        agendamento.put("CPF", cpf);
        agendamento.put("Telefone", telefone);
        agendamento.put("Observações", obs);
        agendamento.put("Data", data);
        agendamento.put("Hora", hora);
        if (rb_visitante.isChecked()) {
            agendamento.put("Tipo", tipo[0]);
        } else {
            agendamento.put("Tipo", tipo[1]);
        }

        //userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Agendamentos").document(nome);
        documentReference.set(agendamento).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("db", "Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error", "Failure" + e.toString());
            }
        });

        /* https://youtu.be/-y4EMB2pWEc */
        Intent telaQrcode = new Intent(getApplicationContext(), QRcode.class);
        Bundle info = new Bundle();

        info.putString("Nome", nome);
        info.putString("CPF", cpf);
        info.putString("Telefone", telefone);
        info.putString("Observações", obs);
        info.putString("Data", data);
        info.putString("Hora", hora);
        info.putStringArray("Tipo", tipo);

        telaQrcode.putExtras(info);
        startActivity(telaQrcode);

    }

    private void IniciarComponentes(){
        edit_nome = findViewById(R.id.edit_nome);
        edit_cpf = findViewById(R.id.edit_cpf);
        edit_telefone = findViewById(R.id.edit_telefone);
        edit_obs = findViewById(R.id.edit_obs);
        edit_data = findViewById(R.id.edit_data);
        edit_hora = findViewById(R.id.edit_hora);
        rb_visitante = findViewById(R.id.rb_visitante);
        rb_terceiro = findViewById(R.id.rb_terceiro);
        bt_salvar = findViewById(R.id.bt_salvar);
        bt_cancelar = findViewById(R.id.bt_cancelar);

    }
}