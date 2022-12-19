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
import com.example.idnpproyectogrupo07.adapters.ScanPlasticAdapter;
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;
import com.example.idnpproyectogrupo07.database.DBType;
import com.example.idnpproyectogrupo07.databinding.FragmentPlasticListBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlasticListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlasticListFragment extends Fragment {

    ScanPlasticAdapter scanPlasticAdapter;
    RecyclerView recyclerViewItemsPlastic;
    ArrayList<ScanItemsPlastic> listaPlastic;


    public PlasticListFragment() {
        // Required empty public constructor
    }

    public static PlasticListFragment newInstance(String param1, String param2) {
        PlasticListFragment fragment = new PlasticListFragment();
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
        View view = inflater.inflate(R.layout.fragment_plastic_list, container, false);
        recyclerViewItemsPlastic = view.findViewById(R.id.recyclerViewPlastic);
        listaPlastic = new ArrayList<>();
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

        DBType dbType = new DBType(getActivity());
        dbType.OpenDb();
        listaPlastic =  dbType.getAllType();

        /*
        listaPlastic.add(new ScanItemsPlastic("Botella de Plastico", "e.g. agua, jugos, gaseosas, etc", R.drawable.plastic));
        listaPlastic.add(new ScanItemsPlastic("Envoltorio de Plastico", "e.g. dulces, caramelos, goma de mascar, etc ", R.drawable.wrapper));
        listaPlastic.add(new ScanItemsPlastic("Bolsa de Plastico", "e.g. supermercado, ......, ...., etc ", R.drawable.bag));
        listaPlastic.add(new ScanItemsPlastic("Pote de Plastico", "e.g. mantequilla, helados, nutela, etc ", R.drawable.tub));
        listaPlastic.add(new ScanItemsPlastic("Tubo de Plastico", "e.g. crema dental, crema de manos, etc ", R.drawable.pastadental));
        listaPlastic.add(new ScanItemsPlastic("Paquete de Blisters", "e.g. pastillas, juguetes , usbs, etc ", R.drawable.antihistamines));
        listaPlastic.add(new ScanItemsPlastic("Funda de Plastico", "e.g. comida de mascota, popcorn microondas, etc ", R.drawable.pouch));
        listaPlastic.add(new ScanItemsPlastic("Taper de Plastico", "e.g. fruta, ensaladas, comidas, etc ", R.drawable.cramshell));
        listaPlastic.add(new ScanItemsPlastic("Galonera de Plastico", "e.g. yogurt, limpiador, leche, etc ", R.drawable.galonera));
        listaPlastic.add(new ScanItemsPlastic("Botella de Spray", "e.g. desinfectante, limpiador de superficies, etc ", R.drawable.spray));
        listaPlastic.add(new ScanItemsPlastic("Surtidor de Plastico", "e.g. alcohol en gel, jabon, lociones, etc ", R.drawable.pump));
        listaPlastic.add(new ScanItemsPlastic("Bote de Plastico", "e.g. suplementos, nutella, etc ", R.drawable.potegrande));
        listaPlastic.add(new ScanItemsPlastic("Aplicador de Plastico", "e.g. desodorante, balsamo labial, etc ", R.drawable.deodorant));
        listaPlastic.add(new ScanItemsPlastic("Caja de Plastico", "e.g. videojuegos, DVD, etc ", R.drawable.game));
        listaPlastic.add(new ScanItemsPlastic("Plastico Desconocido", "e.g. Si no conoces el plastico pero puedes dar mas detalles del mismo, etc ", R.drawable.unknown));
        */
    }

    public void mostrarLista() {
        recyclerViewItemsPlastic.setLayoutManager(new LinearLayoutManager(getContext()));
        scanPlasticAdapter = new ScanPlasticAdapter(getContext(), listaPlastic);
        recyclerViewItemsPlastic.setAdapter(scanPlasticAdapter);


        scanPlasticAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // imagen tomada desde el fragment de scan
                Bitmap bitmapimage = getArguments().getParcelable("BitmapImage");


                String nombreCodeConfirmation = listaPlastic.get(recyclerViewItemsPlastic.getChildAdapterPosition(view)).getNombre();
                Toast.makeText(getContext(), "Selecciono" + nombreCodeConfirmation, Toast.LENGTH_SHORT).show();

                Bundle datos = new Bundle();
                datos.putInt("id_type_column", listaPlastic.get(recyclerViewItemsPlastic.getChildAdapterPosition(view)).getId_type());
                datos.putParcelable("BitmapImage",bitmapimage);

                Navigation.findNavController(view).navigate(R.id.code_list,datos);
            }
        });
    }

}