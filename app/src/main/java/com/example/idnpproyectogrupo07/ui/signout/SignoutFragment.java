package com.example.idnpproyectogrupo07.ui.signout;

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
import com.example.idnpproyectogrupo07.databinding.FragmentSignoutBinding;
import com.example.idnpproyectogrupo07.ui.scan.ScanViewModel;

public class SignoutFragment extends Fragment {

    private SignoutViewModel mViewModel;
    private FragmentSignoutBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        SignoutViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SignoutViewModel.class);

        binding = FragmentSignoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.signoutText;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}