package com.example.quickvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

public class Configuracoes extends AppCompatActivity {

    private Spinner opcoes_idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        getSupportActionBar().hide();
        InicializarComponentes();

        opcoes_idioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position == 1){
                    SelecionarIdioma("de");
                    startActivity(new Intent(Configuracoes.this, Menu.class));
                } else if(position == 2) {
                    SelecionarIdioma("es");
                    startActivity(new Intent(Configuracoes.this, Menu.class));
                } else if(position == 3) {
                    SelecionarIdioma("fr");
                    startActivity(new Intent(Configuracoes.this, Menu.class));
                } else if(position == 4) {
                    SelecionarIdioma("en");
                    startActivity(new Intent(Configuracoes.this, Menu.class));
                } else if(position == 5) {
                    SelecionarIdioma("pt");
                    startActivity(new Intent(Configuracoes.this, Menu.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void InicializarComponentes(){
        opcoes_idioma = findViewById(R.id.opcoes_idioma);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.lista_idiomas, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        opcoes_idioma.setAdapter(arrayAdapter);
    }

    public void SelecionarIdioma(String linguagem){
        Locale localidade = new Locale(linguagem);
        Locale.setDefault(localidade);
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = localidade;
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }

}