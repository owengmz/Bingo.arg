package com.example.bingoarg;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Título con dos colores
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        SpannableString titulo = new SpannableString("Bingo.arg");

        // Pinta solo el punto (posición 5) en púrpura
        titulo.setSpan(new ForegroundColorSpan(Color.parseColor("#a436f2")), 5, 6,  // posición del punto en "bingo.arg"
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvTitulo.setText(titulo);

        // Botones (los conectamos a sus pantallas después)
        Button btnEmpezar = findViewById(R.id.btnEmpezar);
        Button btnConfiguracion = findViewById(R.id.btnConfiguracion);
        TextView tvReglas = findViewById(R.id.tvReglas);

        btnEmpezar.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, JuegoActivity.class);
            startActivity(intent1);
            // Aquí navegaremos a la pantalla de juego , NOTA ANTES DE INICIALIZARLA FIJARSE SI ESTA CREADA EN ANDORID MANIFEST
        });

        btnConfiguracion.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConfiguracionActivity.class);
            startActivity(intent);
            // Aquí navegaremos a configuración
        });

        tvReglas.setOnClickListener(v -> {
            // Aquí navegaremos a reglas
        });
    }
}
