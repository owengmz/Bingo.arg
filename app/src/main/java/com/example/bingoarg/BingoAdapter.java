package com.example.bingoarg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BingoAdapter extends RecyclerView.Adapter<BingoAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Integer> numerosSalidos;
    private int ultimoNumero;

    // Constructor - recibe el contexto y la lista de números salidos
    public BingoAdapter(Context context) {
        this.context = context;
        this.numerosSalidos = new ArrayList<>();
        this.ultimoNumero = -1;
    }

    // ViewHolder - sostiene la referencia al TextView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumero;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.tvNumero);
        }
    }

    // Crea el ViewHolder inflando el item_numero.xml
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_numero, parent, false);
        return new ViewHolder(view);
    }

    // Pinta cada número según su estado
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int numero = position + 1; // posición 0 = número 1
        holder.tvNumero.setText(String.valueOf(numero));

        if (numero == ultimoNumero) {
            // Último número sorteado - púrpura intenso
            holder.tvNumero.setBackgroundColor(0xFFa436f2);
            holder.tvNumero.setTextColor(0xFFFFFFFF);
        } else if (numerosSalidos.contains(numero)) {
            // Número sorteado - púrpura suave
            holder.tvNumero.setBackgroundColor(0x66a436f2);
            holder.tvNumero.setTextColor(0xFFFFFFFF);
        } else {
            // Número no sorteado - gris oscuro
            holder.tvNumero.setBackgroundColor(0xFF1a0f22);
            holder.tvNumero.setTextColor(0xFF475569);
        }
    }

    // Le dice al RecyclerView cuántos items hay
    @Override
    public int getItemCount() {
        return 90;
    }

    // Metodo para actualizar cuando sale un número nuevo
    public void actualizarNumero(int numero, ArrayList<Integer> salidos) {
        this.ultimoNumero = numero;
        this.numerosSalidos = salidos;
        notifyDataSetChanged(); // le avisa al RecyclerView que redibuje
    }
}