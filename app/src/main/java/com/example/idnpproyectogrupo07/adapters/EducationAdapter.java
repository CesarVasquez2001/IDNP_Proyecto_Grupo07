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
import com.example.idnpproyectogrupo07.classes.EducationItems;
import com.example.idnpproyectogrupo07.ui.education.EducationInterfaceInfo;


import java.util.ArrayList;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<EducationItems> codes;


    //listener
    private View.OnClickListener listener;

    //Interface Education information
    private final EducationInterfaceInfo educationInterfaceInfo;

    public EducationAdapter(Context context, ArrayList<EducationItems> codes, EducationInterfaceInfo educationInterfaceInfo) {
        this.inflater = LayoutInflater.from(context);
        this.codes = codes;
        this.educationInterfaceInfo = educationInterfaceInfo;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_education, parent, false);
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
    public void onClick(View v) {
        if (listener != null) listener.onClick(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombres, placeholder;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.nombre_education);
            placeholder = itemView.findViewById(R.id.referencia_education);
            imagen = itemView.findViewById(R.id.image_education);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(educationInterfaceInfo != null){
                        int pos = getAdapterPosition();
                        if(pos !=RecyclerView.NO_POSITION ){
                            educationInterfaceInfo.onItemClick(pos);
                        }

                    }
                }
            });*/
        }
    }
}
