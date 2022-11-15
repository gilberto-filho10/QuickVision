package com.example.quickvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.BitSet;


public class QRcode extends AppCompatActivity {

    private ImageView img_qrcode;
    private Button bt_compartilhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_compartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Compartilhar();
            }
        });

        /* https://youtu.be/RYSkbFJbXz0 */
        Intent ReceberTelaQrcode = getIntent();
        Bundle info = ReceberTelaQrcode.getExtras();

        if(info != null){
            String nome = info.getString("Nome");
            String cpf = info.getString("CPF");
            String telefone = info.getString("Telefone");
            String obs = info.getString("Observações");
            String data = info.getString("Data");
            String hora = info.getString("Hora");
            String[] tipo = info.getStringArray("Tipo");

        }
        /* Gerando GR Code */


    }

    private void Compartilhar(){
        /* https://www.youtube.com/watch?v=4MDeqMU1H_U */
        if(img_qrcode.getDrawable() != null){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/png");

            BitmapDrawable drawable = (BitmapDrawable) img_qrcode.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);

            String path = MediaStore.Images.Media.insertImage
                    (getContentResolver(), bitmap, "Enviar", null);

            Uri uri = Uri.parse(path);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(intent, "Enviar para"));

        } else {
            Toast.makeText(getBaseContext(),R.string.falha_compartilhar,Toast.LENGTH_LONG).show();
        }
    }

    private void IniciarComponentes(){
        img_qrcode = findViewById(R.id.img_qrcode);
        bt_compartilhar = findViewById(R.id.bt_compartilhar);
    }
}