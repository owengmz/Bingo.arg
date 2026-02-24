package com.example.bingoarg;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;

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
        seekBarVelocidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 0=muy lento, 1=lento, 2=normal, 3=rápido, 4=muy rápido
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