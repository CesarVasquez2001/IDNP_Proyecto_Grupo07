package com.example.idnpproyectogrupo07.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;


import java.util.ArrayList;

public class ScanCodeAdapter extends RecyclerView.Adapter<ScanCodeAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<ScanCodePlastic> codes;

    //listener
    private View.OnClickListener listener;

    public ScanCodeAdapter(Context context, ArrayList<ScanCodePlastic> codes) {
        this.inflater = LayoutInflater.from(context);
        this.codes = codes;

    }

    @NonNull
    @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_codes, parent, false);
            view.setOnClickListener(this);
            return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = codes.get(position).getNombre();
        String placeholder = codes.get(position).getPlaceholder();
        int imagen = codes.get(position).getImagenId();
        holder.nombres.setText(nombre);
        holder.placeholder.setText(placeholder);
        holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return codes.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null) listener.onClick(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombres, placeholder;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.nombre_codes);
            placeholder = itemView.findViewById(R.id.referencia_codes);
            imagen = itemView.findViewById(R.id.image_codes);
        }
    }
}
