package com.example.idnpproyectogrupo07.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idnpproyectogrupo07.HistoryItems;
import com.example.idnpproyectogrupo07.MyAdapter;
import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.databinding.FragmentHistoryBinding;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private ArrayList<HistoryItems> historyItems;
    private String[] itemsNames;
    private int[] itemsImage;
    private String[] itemsDescriptions;
    private String[] itemsStatus;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        HistoryViewModel slideshowViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        init();

        recyclerView = view.findViewById(R.id.recycler_view_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        MyAdapter myAdapter = new MyAdapter(getContext(), historyItems);

        // Todo: Fix this (close Fragment and redirect to main activity)
//        recyclerView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();
    }

    private void init() {
        historyItems = new ArrayList<>();

        itemsImage = new int[] {
                R.drawable.coffee,
                R.drawable.coffee,
                R.drawable.coffee
        };

        itemsNames = new String[] {
                "Coffee",
                "Water",
                "Bug"
        };

        itemsDescriptions = new String[] {
                "Coffee Text",
                "Water Text",
                "Bug Text"
        };

        itemsStatus = new String[] {
                "Activated",
                "Deactivated",
                "Activated"
        };



        for(int i = 0; i < itemsNames.length; i++) {
            HistoryItems items = new HistoryItems(itemsImage[i], itemsNames[i], itemsDescriptions[i], itemsStatus[i]);
            historyItems.add(items);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}