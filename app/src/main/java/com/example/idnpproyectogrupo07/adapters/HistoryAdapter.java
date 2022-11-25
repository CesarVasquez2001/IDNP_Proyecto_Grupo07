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
import com.example.idnpproyectogrupo07.classes.HistoryItems;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    // (1)
    ArrayList<HistoryItems> model;
    LayoutInflater inflater;

    // (2)
    public HistoryAdapter(Context context, ArrayList<HistoryItems> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }


    // (3)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item_history, parent, false);

        return new ViewHolder(view);
    }

    // (5)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = model.get(position).getName();
        String description = model.get(position).getDescription();
        String status = model.get(position).getStatus();
        int image = model.get(position).getImage();

        holder.name.setText(name);
        holder.description.setText(description);
        holder.status.setText(status);
        holder.image.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    // (4)
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, status;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewItemName);
            description = itemView.findViewById(R.id.textViewItemDescription);
            status = itemView.findViewById(R.id.textViewItemStatus);

            image = itemView.findViewById(R.id.image_view_history_item);
        }
    }
}
