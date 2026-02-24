package com.example.bingoarg;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

public class ConfiguracionActivity extends AppCompatActivity {

    private Switch switchSonido;
    private SeekBar seekBarVelocidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        // Botón volver
        ImageButton btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> finish());

        // Switch de sonido
        switchSonido = findViewById(R.id.switchSonido);
        switchSonido.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Aquí controlaremos el audio cuando conectemos AudioPlayer
        });

        // SeekBar de velocidad
        seekBarVelocidad = findViewById(R.id.seekBarVelocidad);
        // Cargar velocidad guardada
        SharedPreferences prefs = getSharedPreferences("bingo_config", MODE_PRIVATE);
        int velocidadGuardada = prefs.getInt("velocidad", 2);
        seekBarVelocidad.setProgress(velocidadGuardada);
        seekBarVelocidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Guardamos la velocidad elegida en SharedPreferences
                SharedPreferences prefs = getSharedPreferences("bingo_config", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("velocidad", progress);
                editor.apply();
            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}