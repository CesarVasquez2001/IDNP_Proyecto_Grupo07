package com.example.idnpproyectogrupo07.ui.scan;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        binding = FragmentScanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.scanText;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}