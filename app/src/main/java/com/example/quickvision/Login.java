package com.example.quickvision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextView text_esqueci_senha;
    private Button bt_entrar;
    private EditText edit_email, edit_senha;
    private ProgressBar progressbar;


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
            public void onClick(View v) {

                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, R.string.erro_preenchimento,Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else{
                    AutenticarUsuario(v);
                }

            }
        });
    }

    /*=============================================================================
    Pega o preenchimento dos campos e-mail e senha e verifica se existe no Firebase
    E realiza as trativas de erros também
    =============================================================================*/
    private  void AutenticarUsuario(View view){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressbar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaMenu();
                        }
                    }, 3000);
                } else{
                    String erro;
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e){
                        erro = String.valueOf(R.string.erro_senha);
                    } catch (FirebaseAuthUserCollisionException e){
                        erro = String.valueOf(R.string.erro_cadastrar_user);
                    } catch (FirebaseAuthInvalidCredentialsException |
                            FirebaseAuthInvalidUserException e){
                        erro = String.valueOf(R.string.erro_email);
                    } catch (Exception e){
                        erro = "Erro " + e;
                    }
                    Snackbar snackbar = Snackbar.make(view, erro,Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }

    /*========================================================================
    Para cair diretamento na tela de menu caso o usuario esteja autenticado
    =========================================================================*/
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if(usuarioAtual != null){
            TelaMenu();
        }
    }

    private void TelaMenu(){
        Intent intent = new Intent(Login.this, Menu.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        text_esqueci_senha = findViewById(R.id.text_esqueci_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        progressbar = findViewById(R.id.progressbar);
    }

}