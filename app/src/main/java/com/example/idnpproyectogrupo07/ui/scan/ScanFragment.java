package com.example.idnpproyectogrupo07.ui.scan;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Session2Command;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.databinding.FragmentScanBinding;
import com.example.idnpproyectogrupo07.ui.scan.ScanViewModel;
import com.google.android.material.navigation.NavigationView;

public class ScanFragment extends Fragment {

    private static final int CAMERA_REQUEST_CODE = 1999;
    private ScanViewModel mViewModel;
    ImageView imageView;
    Button btnplastic;
    RecyclerView recyclerView;
    View view;

    @NonNull
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        abrirCamara();
        return inflater.inflate(R.layout.fragment_scan, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button= view.findViewById(R.id.btnplastic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.plastic_list);
            }
        });
    }


    private void abrirCamara() {
        Intent intent = new Intent(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        //if(intent.resolveActivity(getPackageManager()) !=null ){
        startActivityForResult(intent, 1);
        //}
    }


    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Log.e("Test Err!","a");
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imgBitmap);

        }
    }*/


}