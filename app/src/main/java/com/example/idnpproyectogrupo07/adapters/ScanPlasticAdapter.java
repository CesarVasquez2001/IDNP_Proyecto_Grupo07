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
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;

import java.util.ArrayList;

public class ScanPlasticAdapter extends RecyclerView.Adapter<ScanPlasticAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<ScanItemsPlastic> model;

    //listener
    private View.OnClickListener listener;


    public ScanPlasticAdapter(Context context, ArrayList<ScanItemsPlastic> model){

        this.inflater = LayoutInflater.from(context);
        this.model = model;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_plasticos, parent, false);
        view.setOnClickListener(this); //onclick

        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre();
        String placeholder = model.get(position).getPlaceholder();
        int imagen = model.get(position).getImagenid();
        holder.nombres.setText(nombre);
        holder.placeholder.setText(placeholder);
        holder.imagen.setImageResource(imagen);


    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null) listener.onClick(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombres, placeholder;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres=itemView.findViewById(R.id.nombre_plastic);
            placeholder =itemView.findViewById(R.id.referencia_plastic);
            imagen = itemView.findViewById(R.id.image_plastic);
        }
    }

}
