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
import com.example.idnpproyectogrupo07.classes.HistoryItemsPrev;

import java.util.ArrayList;

/* TODO: DELETE*/

public class MyAdapterPrev extends RecyclerView.Adapter<MyAdapterPrev.MyViewHolder> {

    Context context;
    ArrayList<HistoryItemsPrev> historyItems;

    public MyAdapterPrev(Context context, ArrayList<HistoryItemsPrev> historyItems) {
        this.context = context;
        this.historyItems = historyItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item_history, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HistoryItemsPrev items = historyItems.get(position);
        holder.nameHistoryItem.setText(items.image);
        holder.nameHistoryItem.setText(items.name);
        holder.descriptionHistoryItem.setText(items.description);
        holder.statusHistoryItem.setText(items.status);
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageHistoryItem;
        TextView nameHistoryItem;
        TextView descriptionHistoryItem;
        TextView statusHistoryItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageHistoryItem = itemView.findViewById(R.id.image_view_history_item);
            nameHistoryItem = imageHistoryItem.findViewById(R.id.textViewItemName);
            descriptionHistoryItem = imageHistoryItem.findViewById(R.id.textViewItemDescription);
            statusHistoryItem = imageHistoryItem.findViewById(R.id.textViewItemStatus);

        }
    }
}
