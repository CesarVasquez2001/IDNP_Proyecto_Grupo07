package com.example.idnpproyectogrupo07.ui.scan;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.databinding.FragmentScanBinding;
import com.example.idnpproyectogrupo07.ui.scan.ScanViewModel;

public class ScanFragment extends Fragment {

    private ScanViewModel mViewModel;
    private FragmentScanBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        ScanViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ScanViewModel.class);
        abrirCamara();
        binding = FragmentScanBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        final ImageView imageView = binding.ImageViewScan;
        slideshowViewModel.
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), imageView::setImageResource);
        return root;


    }
    private void abrirCamara(){
        Intent intent = new Intent(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        //if(intent.resolveActivity(getPackageManager()) !=null ){
        startActivityForResult(intent, 1);
        //}

    }
/*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }


    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}