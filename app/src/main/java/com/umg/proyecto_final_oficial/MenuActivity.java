package com.umg.proyecto_final_oficial;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MenuActivity extends AppCompatActivity {

    private Button btnPlatos, btnBebidas, btnGaleria, btnEmpresa, btnMapa, btnPedido, btnUnete;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_menu);

        // Inicializar cada botón y asignar su listener por separado
        btnPlatos = findViewById(R.id.btnPlatos);
        btnPlatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PlatosActivity.class));
            }
        });

        btnBebidas = findViewById(R.id.btnBebidas);
        btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BebidasActivity.class));
            }
        });

        btnGaleria = findViewById(R.id.btnGaleria);
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GaleriaActivity.class));
            }
        });

        btnEmpresa = findViewById(R.id.btnEmpresa);
        btnEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmpresaActivity.class));
            }
        });

        btnMapa = findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/7SwQmteznwtxKAnw6")));
            }
        });

        btnPedido = findViewById(R.id.btnPedido);
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toNumber = "502" + "58103302";
                Intent sendIntent = new Intent(Intent.ACTION_MAIN);
                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(toNumber) + "@s.whatsapp.net");
                startActivity(sendIntent);
            }
        });

        btnUnete = findViewById(R.id.btnUnete);
        btnUnete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
            }
        });
    }


}

