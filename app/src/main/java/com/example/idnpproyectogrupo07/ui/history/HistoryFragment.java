package com.example.idnpproyectogrupo07.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idnpproyectogrupo07.adapters.HistoryAdapter;
import com.example.idnpproyectogrupo07.classes.HistoryItems;
import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.classes.Plastic;
import com.example.idnpproyectogrupo07.classes.User;
import com.example.idnpproyectogrupo07.database.DBPlastic;
import com.example.idnpproyectogrupo07.database.DBUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class HistoryFragment extends Fragment implements HistoryAdapter.ItemClickListener{

    HistoryAdapter historyAdapter;
    RecyclerView recyclerView;
    ArrayList<Plastic> historyItems;
    TextView tvHistoryEmpty;
    TextView tvHistoryEmptyHint;
    ImageView ivHistoryEmpty;
    private Calendar date1;

        private final String pattern = "E, dd MMMM yyyy";
        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private String date;
    TextView tv_date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_history);

        tvHistoryEmpty = view.findViewById(R.id.tv_history_empty);
        tvHistoryEmptyHint = view.findViewById(R.id.tv_history_empty_hint);
        ivHistoryEmpty = view.findViewById(R.id.iv_history_empty);
        historyItems = new ArrayList<>();

        loadItems();

        if (historyItems.size() > 0) {
            tvHistoryEmpty.setVisibility(View.INVISIBLE);
            ivHistoryEmpty.setVisibility(View.INVISIBLE);
            tvHistoryEmptyHint.setVisibility(View.INVISIBLE);

            displayItems();
        }



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void loadItems() {
        DBUser dbUser = new DBUser(getContext());
        dbUser.OpenDb();
        User user = dbUser.getPreference();

        DBPlastic dbPlastic = new DBPlastic(getContext());
        dbPlastic.OpenDb();

        ArrayList<Plastic> plastics = dbPlastic.getAllPlastic((int) user.getId_user());

        Collections.reverse(plastics);
        historyItems = plastics;
    }

    public void displayItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyAdapter = new HistoryAdapter(getContext(), historyItems,this);
        recyclerView.setAdapter(historyAdapter);
    }

    @Override
    public void onItemClick(Plastic plastic) {
        Bundle bundle = new Bundle();
        bundle.putInt("plastic",plastic.getId_plastic());
        Navigation.findNavController(getView()).navigate(R.id.historyDetailFragment,bundle);
    }
}