package com.example.idnpproyectogrupo07.ui.history;

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

import com.example.idnpproyectogrupo07.adapters.HistoryAdapter;
import com.example.idnpproyectogrupo07.classes.HistoryItems;
import com.example.idnpproyectogrupo07.classes.HistoryItemsPrev;
import com.example.idnpproyectogrupo07.adapters.MyAdapterPrev;
import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.databinding.FragmentHistoryBinding;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    HistoryAdapter historyAdapter;
    RecyclerView recyclerView;
    ArrayList<HistoryItems> historyItems;

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

    public void loadItems() {
        historyItems.add(new HistoryItems(R.drawable.coffee, "Coffee 1", "Strong Coffee", "125"));
        historyItems.add(new HistoryItems(R.drawable.coffee, "Coffee 2", "Medium Coffee", "287"));
        historyItems.add(new HistoryItems(R.drawable.coffee, "Coffee 3", "Light Coffee", "50"));
        historyItems.add(new HistoryItems(R.drawable.coffee, "Coffee 4", "Coffee without sugar", "10"));
    }

    public void displayItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyAdapter = new HistoryAdapter(getContext(), historyItems);
        recyclerView.setAdapter(historyAdapter);
    }

    /* TODO: DELETE*/
    //    private FragmentHistoryBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//
//
//        HistoryViewModel slideshowViewModel =
//                new ViewModelProvider(this).get(HistoryViewModel.class);
//
//        binding = FragmentHistoryBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }

}


//package com.example.idnpproyectogrupo07.ui.slideshow;
//
//        import android.os.Bundle;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.TextView;
//
//        import androidx.annotation.NonNull;
//        import androidx.annotation.Nullable;
//        import androidx.fragment.app.Fragment;
//        import androidx.lifecycle.ViewModelProvider;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import com.example.idnpproyectogrupo07.classes.HistoryItemsPrev;
//        import com.example.idnpproyectogrupo07.adapters.MyAdapterPrev;
//        import com.example.idnpproyectogrupo07.R;
//        import com.example.idnpproyectogrupo07.databinding.FragmentHistoryBinding;
//
//        import java.util.ArrayList;
//
//public class HistoryFragment extends Fragment {
//
//    private FragmentHistoryBinding binding;
//    private ArrayList<HistoryItemsPrev> historyItems;
//    private String[] itemsNames;
//    private int[] itemsImage;
//    private String[] itemsDescriptions;
//    private String[] itemsStatus;
//    private RecyclerView recyclerView;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//
//
//        HistoryViewModel slideshowViewModel =
//                new ViewModelProvider(this).get(HistoryViewModel.class);
//
//        binding = FragmentHistoryBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        init();
//
//        recyclerView = view.findViewById(R.id.recycler_view_history);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//        MyAdapterPrev myAdapter = new MyAdapterPrev(view.getContext(), historyItems);
//
//        // Todo: Fix this (close Fragment and redirect to main activity)
//        recyclerView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();
//    }
//
//    private void init() {
//        historyItems = new ArrayList<>();
//
//        itemsImage = new int[] {
//                R.drawable.coffee,
//                R.drawable.coffee,
//                R.drawable.coffee
//        };
//
//        itemsNames = new String[] {
//                "Coffee",
//                "Water",
//                "Bug"
//        };
//
//        itemsDescriptions = new String[] {
//                "Coffee Text",
//                "Water Text",
//                "Bug Text"
//        };
//
//        itemsStatus = new String[] {
//                "Activated",
//                "Deactivated",
//                "Activated"
//        };
//
//        for(int i = 0; i < itemsNames.length; i++) {
//            HistoryItemsPrev items = new HistoryItemsPrev(itemsImage[i], itemsNames[i], itemsDescriptions[i], itemsStatus[i]);
//            historyItems.add(items);
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}