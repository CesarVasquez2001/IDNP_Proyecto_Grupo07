package com.example.idnpproyectogrupo07.ui.education;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.adapters.EducationAdapter;
import com.example.idnpproyectogrupo07.classes.EducationItems;

import com.example.idnpproyectogrupo07.database.DBEducation;


import java.util.ArrayList;

public class EducationFragment extends Fragment {

    EducationAdapter educationAdapter;
    RecyclerView recyclerViewEducation;
    ArrayList<EducationItems> listEducation;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_education, container, false);
        recyclerViewEducation = view.findViewById(R.id.recyclerViewEducation);
        listEducation = new ArrayList<>();
        cargarLista();
        mostrarLista();
        return view;
    }



    private void cargarLista() {
        DBEducation dbEducation = new DBEducation(getActivity());
        dbEducation.OpenDb();
         listEducation = dbEducation.getAllCode();

    }
    private void mostrarLista() {
        recyclerViewEducation.setLayoutManager(new LinearLayoutManager(getContext()));
        educationAdapter = new EducationAdapter(getContext(),listEducation);
        recyclerViewEducation.setAdapter(educationAdapter);
    }

}

