package com.example.idnpproyectogrupo07.ui.history;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.classes.Plastic;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;
import com.example.idnpproyectogrupo07.database.DBCode;
import com.example.idnpproyectogrupo07.database.DBPlastic;
import com.example.idnpproyectogrupo07.database.DBType;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.databinding.FragmentConfirmationRecycleBinding;
import com.example.idnpproyectogrupo07.databinding.FragmentHistoryDetailBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;


public class HistoryDetailFragment extends Fragment {
    private FragmentHistoryDetailBinding binding;
    ImageView image_scan;
    TextView date;
    DBCode dbCode;
    DBType dbType;
    DBPlastic dbPlastic;
    DBUser dbUser;

    Plastic plastic;
    ScanItemsPlastic scanItemsPlastic;
    ScanCodePlastic scanCodePlastic;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = FragmentHistoryDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbType = new DBType(getContext());
        dbCode = new DBCode(getContext());
        dbPlastic = new DBPlastic(getContext());
        dbUser = new DBUser(getContext());

        dbType.OpenDb();
        dbCode.OpenDb();
        dbPlastic.OpenDb();
        dbUser.OpenDb();

        int id_plastic = getArguments().getInt("plastic");

        plastic = dbPlastic.getPlastic(id_plastic);

        scanItemsPlastic = dbType.getType(plastic.getId_type_column());
        scanCodePlastic = dbCode.getCode(plastic.getId_code_column());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);
        image_scan = binding.imagePlasticConfirmation;
        image_scan.setImageBitmap(plastic.getPlastic_picture());

        date = binding.plasticDate;
        date.setText(plastic.getDate_plastic());

        // TYPE
        ImageView type = binding.plasticType.imageViewHistoryItem;
        type.setImageResource(scanItemsPlastic.getImagenid());

        TextView type_name = binding.plasticType.textViewItemName;
        type_name.setText(scanItemsPlastic.getNombre());

        TextView type_description = binding.plasticType.textViewItemDescription;
        type_description.setText(scanItemsPlastic.getPlaceholder());

        binding.plasticType.textViewItemStatus.setText("");
        // CODE
        ImageView code = binding.plasticCode.imageViewHistoryItem;
        code.setImageResource(scanCodePlastic.getImagenId());

        TextView code_name = binding.plasticCode.textViewItemName;
        code_name.setText(scanCodePlastic.getNombre());

        TextView code_description = binding.plasticCode.textViewItemDescription;
        code_description.setText(scanCodePlastic.getPlaceholder());

        binding.plasticCode.textViewItemStatus.setText("");

        binding.plasitcTitleAmount.setText("Amount: "+ plastic.getAmount_plastic()+"");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}