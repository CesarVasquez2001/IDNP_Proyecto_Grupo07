package com.example.idnpproyectogrupo07.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.classes.Plastic;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;
import com.example.idnpproyectogrupo07.database.DBCode;
import com.example.idnpproyectogrupo07.database.DBPlastic;
import com.example.idnpproyectogrupo07.database.DBType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ItemClickListener itemClickListener;
    // (1)
    ArrayList<Plastic> model;
    LayoutInflater inflater;
    Context context;
    // (2)
    public HistoryAdapter(Context context, ArrayList<Plastic> model,ItemClickListener itemClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        this.context = context;
        this.itemClickListener = itemClickListener;
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
        DBCode dbCode = new DBCode(this.context);
        DBType dbType = new DBType(this.context);
        dbCode.OpenDb();
        dbType.OpenDb();
        ScanItemsPlastic scanItemsPlastic = dbType.getType(model.get(position).getId_type_column());
        ScanCodePlastic scanCodePlastic = dbCode.getCode(model.get(position).getId_code_column());

        String status = model.get(position).getDate_plastic();
        Bitmap image = model.get(position).getPlastic_picture();

        holder.name.setText(scanItemsPlastic.getNombre());
        holder.description.setText(scanCodePlastic.getNombre());
        holder.status.setText(status);
        holder.image.setImageBitmap(image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(model.get(position));
            }
        });
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

    public interface ItemClickListener{
        public void onItemClick(Plastic plastic);
    }
}
