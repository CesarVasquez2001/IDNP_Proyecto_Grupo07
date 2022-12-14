package com.example.idnpproyectogrupo07.ui.scan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.adapters.ScanCodeAdapter;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CodeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodeListFragment extends Fragment {

    ScanCodeAdapter scanCodeAdapter;
    RecyclerView recyclerViewItemsCodes;
    ArrayList<ScanCodePlastic> listaCodes;


    public CodeListFragment() {
        // Required empty public constructor
    }

    public static CodeListFragment newInstance(String param1, String param2) {
        CodeListFragment fragment = new CodeListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_code_list,container,false);
        recyclerViewItemsCodes = view.findViewById(R.id.recycler_view_codes);
        listaCodes = new ArrayList<>();
        //cargarlista
        cargarLista();
        //mostrarlista
        mostrarLista();
        return view;
    }

    /*botella, wrapper,packet,plastic bag
    , plastic tub, plastic tube, blister,  pouch, cramshell ,plastic jug, spray bottle, pump bottle ,plastic jar ,
    aplicator, plastic case, unknown plastic/*
     */
    public void cargarLista(){

        listaCodes.add(new ScanCodePlastic("PET Plastic","e.g. Clear bottles, pots, trays and punnets", R.drawable.code1));
        listaCodes.add(new ScanCodePlastic("HDPE Plastic","e.g. Milk jugs, shampoo, chemical and detergent bottles ", R.drawable.code2));
        listaCodes.add(new ScanCodePlastic("PVC Plastic","e.g. Cosmetic containers and household fittings ", R.drawable.code3));
        listaCodes.add(new ScanCodePlastic("LDPE Plastic","e.g. Flexible lids, plastic drycleaner covers, clining film ", R.drawable.code4));
        listaCodes.add(new ScanCodePlastic("PP Plastic","e.g. Single pots, tubs, ready-meal trays and rigid caps ", R.drawable.code5));
        listaCodes.add(new ScanCodePlastic("PS Plastic","e.g. Multipack yogurt snap pots and video games cases ", R.drawable.code6));
        listaCodes.add(new ScanCodePlastic("Other Plastic","e.g. Nets, PLA, clear CD cases and acrylic ", R.drawable.code7));
    }

    public void mostrarLista(){
        recyclerViewItemsCodes.setLayoutManager(new LinearLayoutManager(getContext()));
        scanCodeAdapter = new ScanCodeAdapter(getContext(), listaCodes);
        recyclerViewItemsCodes.setAdapter(scanCodeAdapter);;

        scanCodeAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = listaCodes.get(recyclerViewItemsCodes.getChildAdapterPosition(view)).getNombre();
                Toast.makeText(getContext(), "Selecciono"+nombre, Toast.LENGTH_SHORT).show();
                //Navigation.findNavController(view).navigate(R.id.action_nav_plastic_list_to_codeListFragment);
            }
        });
    }
}