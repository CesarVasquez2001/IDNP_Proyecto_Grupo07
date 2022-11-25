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

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.databinding.FragmentScanBinding;
import com.example.idnpproyectogrupo07.ui.scan.ScanViewModel;

public class ScanFragment extends Fragment {

    private static final int CAMERA_REQUEST_CODE = 1999;
    private ScanViewModel mViewModel;
    private FragmentScanBinding binding;
    ImageView imageView;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /*ScanViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ScanViewModel.class);
        binding = FragmentScanBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        //binding.

        //imageView =(ImageView) root.findViewById(R.id.ImageViewScan);
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), imageView::setImageResource);
        //return root;
        */
        binding = FragmentScanBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void abrirCamara() {
        Intent intent = new Intent(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        //if(intent.resolveActivity(getPackageManager()) !=null ){
        startActivityForResult(intent, 1);
        //}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Log.e("Test Err!","a");
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imgBitmap);

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            abrirCamara();
            //binding.ImageViewScan.setImageBitmap(imageView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}