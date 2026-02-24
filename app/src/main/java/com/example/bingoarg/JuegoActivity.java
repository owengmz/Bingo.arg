package com.example.bingoarg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

public class JuegoActivity extends AppCompatActivity {

    private TextView tvUltimoNumero;
    private TextView tvContador;
    private RecyclerView recyclerViewNumeros;
    private Button btnPlay;
    private Button btnStop;

    private BingoManager bingoManager;
    private BingoAdapter bingoAdapter;
    private Handler handler;
    private Runnable runnable;

    private boolean jugando = false;
    private boolean pausado = false;
    private RecyclerView recyclerViewSalidos;
    private SalidosAdapter salidosAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        tvUltimoNumero = findViewById(R.id.tvUltimoNumero);
        tvContador = findViewById(R.id.tvContador);
        recyclerViewNumeros = findViewById(R.id.recyclerViewNumeros);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);

        bingoManager = new BingoManager();
        bingoAdapter = new BingoAdapter(this);
        handler = new Handler();

        recyclerViewNumeros.setLayoutManager(new GridLayoutManager(this, 10)); // 10 columnas para números del 1 al 90
        recyclerViewNumeros.setAdapter(bingoAdapter);

        ImageButton btnConfiguracion = findViewById(R.id.btnConfiguracion);
        btnConfiguracion.setOnClickListener(v -> {
            Intent intent = new Intent(JuegoActivity.this, ConfiguracionActivity.class);
            startActivity(intent);
        });

        // Botón Play/Pausa
        btnPlay.setOnClickListener(v -> {
            if (!jugando && !pausado) {
                // Iniciar juego
                jugando = true;
                btnPlay.setText("⏸ Pausa");
                iniciarSorteo();
            } else if (jugando && !pausado) {
                // Pausar juego
                pausado = true;
                jugando = false;
                handler.removeCallbacks(runnable);
                btnPlay.setText("▶ Play");
            } else if (pausado) {
                // Reanudar juego
                pausado = false;
                jugando = true;
                btnPlay.setText("⏸ Pausa");
                iniciarSorteo();
            }
        });

        // Botón Stop con confirmación
        btnStop.setOnClickListener(v -> {
            jugando = false;
            handler.removeCallbacks(runnable);
            Intent intent = new Intent(JuegoActivity.this, ConfirmarStopActivity.class);
            startActivity(intent);
        });
        recyclerViewSalidos = findViewById(R.id.recyclerViewSalidos);
        salidosAdapter = new SalidosAdapter(this);
        recyclerViewSalidos.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerViewSalidos.setAdapter(salidosAdapter);
    }
// Metodo para obtener la velocidad desde SharedPreferences
    private int obtenerVelocidad() {
        SharedPreferences prefs = getSharedPreferences("bingo_config", MODE_PRIVATE);
        int progreso = prefs.getInt("velocidad", 1);
        switch (progreso) {
            case 0:
                return 5000; // lento
            case 1:
                return 4000; // normal
            case 2:
                return 3000; // Rapido

            default:
                return 4000;
        }
    }

    private void iniciarSorteo() {
        runnable = new Runnable() {
            @Override
            public void run() {
                if (!jugando) return;

                int numero = bingoManager.generadorDeNumero();

                if (numero == -1) {
                    jugando = false;
                    finDelJuego();
                    return;
                }

                tvUltimoNumero.setText(String.valueOf(numero));
                int cantidadSalidos = bingoManager.getNumeroSalido().size();
                tvContador.setText("Sorteados: " + cantidadSalidos + "/90");
                bingoAdapter.actualizarNumero(numero, bingoManager.getNumeroSalido());
                salidosAdapter.actualizarSalidos(bingoManager.getNumeroSalido()); // ← acá adentro
                handler.postDelayed(this, obtenerVelocidad());
            }
        };
        handler.post(runnable);
    }

    private void finDelJuego() {
        // Aquí navegaremos a la pantalla de fin del juego
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}