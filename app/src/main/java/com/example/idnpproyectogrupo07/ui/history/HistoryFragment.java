package com.example.idnpproyectogrupo07.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idnpproyectogrupo07.adapters.HistoryAdapter;
import com.example.idnpproyectogrupo07.classes.HistoryItems;
import com.example.idnpproyectogrupo07.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryFragment extends Fragment {

    HistoryAdapter historyAdapter;
    RecyclerView recyclerView;
    ArrayList<HistoryItems> historyItems;

    private final String pattern = "E, dd MMMM yyyy";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private String date;
    TextView tv_date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_history);
        historyItems = new ArrayList<>();

        loadItems();
        displayItems();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = simpleDateFormat.format(new Date());
        tv_date = view.findViewById(R.id.tv_date);
        tv_date.setText(date);
    }

    public void loadItems() {
        historyItems.add(new HistoryItems(R.drawable.shopping_bag, "Shopping bag", "Bags after shopping", "125g"));
        historyItems.add(new HistoryItems(R.drawable.bottle_of_water, "Bottle of water", "Plastic bottle", "287g"));
        historyItems.add(new HistoryItems(R.drawable.used_product, "Used product", "Plastic product packaging", "1000g"));
        historyItems.add(new HistoryItems(R.drawable.milk_bottle, "Drinks", "More plastic packaging", "100g"));
        historyItems.add(new HistoryItems(R.drawable.tea, "Disposable Drink", "Plastic packaging", "300g"));
        historyItems.add(new HistoryItems(R.drawable.coffee, "Coffee To Go", "More plastic packaging", "287g"));
        historyItems.add(new HistoryItems(R.drawable.iced_coffee, "Fast Drink", "Plastic packaging", "120g"));
    }

    public void displayItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyAdapter = new HistoryAdapter(getContext(), historyItems);
        recyclerView.setAdapter(historyAdapter);
    }
}