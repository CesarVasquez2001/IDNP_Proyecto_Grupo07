package com.example.idnpproyectogrupo07.ui.scan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.idnpproyectogrupo07.database.DBCode;

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

    /*referencias para comunicar fragements

    Activity actividad;
    iComunicaFragments interfaceCommunicationFragments;*/


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
        View view = inflater.inflate(R.layout.fragment_code_list, container, false);
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
    public void cargarLista() {

        DBCode dbCode = new DBCode(getActivity());
        dbCode.OpenDb();
        listaCodes =  dbCode.getAllCode();
        /*
        listaCodes.add(new ScanCodePlastic("PET Plastic", "e.g. Clear bottles, pots, trays and punnets", R.drawable.code1));
        listaCodes.add(new ScanCodePlastic("HDPE Plastic", "e.g. Milk jugs, shampoo, chemical and detergent bottles ", R.drawable.code2));
        listaCodes.add(new ScanCodePlastic("PVC Plastic", "e.g. Cosmetic containers and household fittings ", R.drawable.code3));
        listaCodes.add(new ScanCodePlastic("LDPE Plastic", "e.g. Flexible lids, plastic drycleaner covers, clining film ", R.drawable.code4));
        listaCodes.add(new ScanCodePlastic("PP Plastic", "e.g. Single pots, tubs, ready-meal trays and rigid caps ", R.drawable.code5));
        listaCodes.add(new ScanCodePlastic("PS Plastic", "e.g. Multipack yogurt snap pots and video games cases ", R.drawable.code6));
        listaCodes.add(new ScanCodePlastic("Other Plastic", "e.g. Nets, PLA, clear CD cases and acrylic ", R.drawable.code7));
        */
    }

    public void mostrarLista() {
        recyclerViewItemsCodes.setLayoutManager(new LinearLayoutManager(getContext()));
        scanCodeAdapter = new ScanCodeAdapter(getContext(), listaCodes);
        recyclerViewItemsCodes.setAdapter(scanCodeAdapter);
        ;

        scanCodeAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // imagen tomada desde el fragment de scan
                Bitmap bitmapimage = getArguments().getParcelable("BitmapImage");
                int type = getArguments().getInt("id_type_column");

                String nombre = listaCodes.get(recyclerViewItemsCodes.getChildAdapterPosition(view)).getNombre();
                Toast.makeText(getContext(), "Selecciono" + nombre, Toast.LENGTH_SHORT).show();
                //interfaceCommunicationFragments.sendCodes(listaCodes.get(recyclerViewItemsCodes.getChildAdapterPosition(view)));
                Bundle datos = new Bundle();
                datos.putInt("id_code_column", listaCodes.get(recyclerViewItemsCodes.getChildAdapterPosition(view)).getId_code());
                datos.putInt("id_type_column",type);
                datos.putParcelable("BitmapImage",bitmapimage);

                Navigation.findNavController(view).navigate(R.id.confirmationRecycleFragment,datos);
            }
        });
    }
    /*
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.actividad = (Activity) context;
            interfaceCommunicationFragments = (iComunicaFragments) this.actividad;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }*/
}