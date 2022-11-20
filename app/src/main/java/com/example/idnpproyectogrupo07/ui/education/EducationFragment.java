package com.example.idnpproyectogrupo07.ui.education;

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
import com.example.idnpproyectogrupo07.databinding.FragmentEducationBinding;

public class EducationFragment extends Fragment {

    private EducationViewModel mViewModel;
    private FragmentEducationBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        EducationViewModel slideshowViewModel =
                new ViewModelProvider(this).get(EducationViewModel.class);

        binding = FragmentEducationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.educationText;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

