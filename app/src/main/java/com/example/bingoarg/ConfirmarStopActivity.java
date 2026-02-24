package com.example.bingoarg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmarStopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_stop);

        // Hace que el fondo sea transparente para ver el juego detras
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button btnFinalizar = findViewById(R.id.btnFinalizar);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        btnFinalizar.setOnClickListener(v -> {
            // Navegar a fin del juego
            Intent intent = new Intent(ConfirmarStopActivity.this, FinJuegoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnCancelar.setOnClickListener(v -> {
            // Volver al juego
            finish();
        });
    }
}