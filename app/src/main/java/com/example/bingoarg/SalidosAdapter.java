package com.example.bingoarg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SalidosAdapter extends RecyclerView.Adapter<SalidosAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> numerosSalidos;

    public SalidosAdapter(Context context) {
        this.context = context;
        this.numerosSalidos = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumeroSalido;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNumeroSalido = itemView.findViewById(R.id.tvNumeroSalido);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_numero_salido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Mostramos los números en orden inverso, el último salido primero
        int numero = numerosSalidos.get(numerosSalidos.size() - 1 - position);
        holder.tvNumeroSalido.setText(String.valueOf(numero));
    }

    @Override
    public int getItemCount() {
        return numerosSalidos.size();
    }

    public void actualizarSalidos(ArrayList<Integer> salidos) {
        this.numerosSalidos = salidos;
        notifyDataSetChanged();
    }
}