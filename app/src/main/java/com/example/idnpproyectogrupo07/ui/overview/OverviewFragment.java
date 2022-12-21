package com.example.idnpproyectogrupo07.ui.overview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.idnpproyectogrupo07.classes.Plastic;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;
import com.example.idnpproyectogrupo07.classes.User;
import com.example.idnpproyectogrupo07.database.DBCode;
import com.example.idnpproyectogrupo07.database.DBPlastic;
import com.example.idnpproyectogrupo07.database.DBType;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.databinding.FragmentOverviewBinding;
import com.example.idnpproyectogrupo07.ui.overview.Graphs.GraficoBarrasVista;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OverviewFragment extends Fragment {
    ArrayList<Plastic> plastics;
    ArrayList<ScanCodePlastic> codes;
    ArrayList<ScanItemsPlastic> types;
    User user;
    DBCode dbCode;
    DBPlastic dbPlastic;
    DBUser dbUser;
    DBType dbType;
    private FragmentOverviewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dbUser = new DBUser(getContext());
        dbUser.OpenDb();
        user = dbUser.getPreference();

        dbPlastic = new DBPlastic(getContext());
        dbPlastic.OpenDb();

        dbCode = new DBCode(getContext());
        dbCode.OpenDb();
        dbType = new DBType(getContext());
        dbType.OpenDb();

        types = dbType.getAllType();
        codes = dbCode.getAllCode();
        plastics = dbPlastic.getAllPlastic((int) user.getId_user());


        binding = FragmentOverviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.graficoBarrasVista.setTitulo("Items escaneados por codigo");
        binding.graficoBarrasVista.setLeyenda("Numero de escaneos");
        ArrayList<String> tagsCodes = new ArrayList<String>();
        ArrayList<Double> amountCodes = new ArrayList<Double>();

        for (int i = 0; i < codes.size(); i++) {
            tagsCodes.add(String.valueOf(codes.get(i).getNombre()));
            amountCodes.add((double) dbPlastic.getSizeCodeUser((int) user.getId_user(), codes.get(i).getId_code()));

        }
        Log.d("TAG", tagsCodes.toString());
        Log.d("TAG", amountCodes.toString());

        binding.graficoBarrasVista.setListTags(tagsCodes);
        binding.graficoBarrasVista.setListValues(amountCodes);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}