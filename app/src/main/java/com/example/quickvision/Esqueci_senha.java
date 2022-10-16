package com.example.quickvision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Esqueci_senha extends AppCompatActivity {

    EditText edit_confirmar_email, edit_recuperar_email;
    Button bt_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        getSupportActionBar().hide(); // Para esconder a barra roxa feia
        IniciarComponentes();

        bt_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecuperarSenha();

            }
        });

    }

    private void RecuperarSenha(){
        String email = edit_recuperar_email.getText().toString().trim();
        String confir_email = edit_confirmar_email.getText().toString().trim();

        if (confir_email != email){
            Toast.makeText(getBaseContext(), "Os campos de e-mail não são iguais",Toast.LENGTH_LONG).show();

        } else if(email.isEmpty() || confir_email.isEmpty()){
            Toast.makeText(getBaseContext(), "Os campos de e-mails não podem estar vazios",
                    Toast.LENGTH_LONG).show();

        };

        FirebaseAuth.getInstance().sendPasswordResetEmail("").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getBaseContext(), "E-mail com dados para recuparação da senha enviado",
                        Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(), "Erro " +e,Toast.LENGTH_LONG).show();
            }
        });
    }


    private void IniciarComponentes(){
        edit_confirmar_email = findViewById(R.id.edit_confirmar_email);
        edit_recuperar_email = findViewById(R.id.edit_recuperar_email);
        bt_enviar = findViewById((R.id.bt_enviar));

    }

}