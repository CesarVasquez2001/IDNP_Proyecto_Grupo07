package com.example.idnpproyectogrupo07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<HistoryItems> historyItems;

    public MyAdapter(Context context, ArrayList<HistoryItems> historyItems) {
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
        HistoryItems items = historyItems.get(position);
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
