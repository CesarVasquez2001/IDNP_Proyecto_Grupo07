package com.example.idnpproyectogrupo07.ui.scan;

import android.graphics.Bitmap;
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
import androidx.fragment.app.FragmentResultListener;

import com.example.idnpproyectogrupo07.R;

public class ConfirmationRecycle extends Fragment {

    TextView nombrePlastico;
    TextView nombreCode;
    ImageView imageCamera;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.confirmation_detail_fragment,container,false);
                /*viewFragment.findViewById(R.id.nombre_plastic_confirmation).setText;
                nombreCode = view.findViewById(R.id.nombre_code_confirmation);
                imageCamera = view.findViewById(R.id.image_plastic_confirmation);*/
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View viewFragment, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(viewFragment, savedInstanceState);


        //Creacion objeto bundle para recibir objeto enviados
        getParentFragmentManager().setFragmentResultListener("dato0", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nombre_Plastic = result.getString("NombrePlasticConfirmation");
                //Bitmap image = result.get
                Log.e("depurando" ,"hb"+ nombre_Plastic);
                //Log.d("depurando", nombre_Code);
                ((TextView)viewFragment.findViewById(R.id.nombre_plastic_confirmation)).setText(nombre_Plastic);
                //((ImageView)viewFragment.findViewById(R.id.image_plastic_confirmation)).setImageBitmap();
            }
        });
        getParentFragmentManager().setFragmentResultListener("dato1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nombre_Code = result.getString("NombreCodeConfirmation");
                ((TextView)viewFragment.findViewById(R.id.nombre_code_confirmation)).setText(nombre_Code);
            }
        });

        getParentFragmentManager().setFragmentResultListener("dato2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nombre_Code = result.getString("NombreCodeConfirmation");
                ((TextView)viewFragment.findViewById(R.id.nombre_code_confirmation)).setText(nombre_Code);
            }
        });
    }
}
