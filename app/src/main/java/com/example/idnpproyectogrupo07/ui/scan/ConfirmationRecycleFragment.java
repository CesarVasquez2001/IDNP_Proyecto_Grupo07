package com.example.idnpproyectogrupo07.ui.scan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.classes.Plastic;
import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;
import com.example.idnpproyectogrupo07.classes.ScanItemsPlastic;
import com.example.idnpproyectogrupo07.classes.User;
import com.example.idnpproyectogrupo07.database.DBCode;
import com.example.idnpproyectogrupo07.database.DBPlastic;
import com.example.idnpproyectogrupo07.database.DBType;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.databinding.FragmentConfirmationRecycleBinding;
import com.example.idnpproyectogrupo07.databinding.RecyclerViewItemHistoryBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;


public class ConfirmationRecycleFragment extends Fragment {


    private FragmentConfirmationRecycleBinding binding;
    ImageView image_scan;
    TextView date;

    DBCode dbCode;
    DBType dbType;
    DBPlastic dbPlastic;
    DBUser dbUser;
    Bitmap bitmapimage;
    ScanItemsPlastic scanItemsPlastic;
    ScanCodePlastic scanCodePlastic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentConfirmationRecycleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbType = new DBType(getContext());
        dbCode = new DBCode(getContext());
        dbPlastic = new DBPlastic(getContext());
        dbUser = new DBUser(getContext());

        dbType.OpenDb();
        dbCode.OpenDb();
        dbPlastic.OpenDb();
        dbUser.OpenDb();

        bitmapimage = getArguments().getParcelable("BitmapImage");
        int id_type_column = getArguments().getInt("id_type_column");
        int id_code_column = getArguments().getInt("id_code_column");

        scanItemsPlastic = dbType.getType(id_type_column);
        scanCodePlastic = dbCode.getCode(id_code_column);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        image_scan = binding.imagePlasticConfirmation;
        image_scan.setImageBitmap(bitmapimage);

        date = binding.plasticDate;
        Calendar calendar = Calendar.getInstance();
        date.setText(DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH).format(calendar.getTime()));

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

        //BUTTON
        Button cancel = binding.cancel;
        Button scrap = binding.recyclingButton;

        //AMOUNT
        EditText amount = binding.plasticAmount;
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_confirmationRecycleFragment_to_nav_scan);
            }
        });
        scrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fill in the amount", Toast.LENGTH_SHORT).show();
                }else{
                    Plastic plastic = new Plastic();
                    plastic.setDate_plastic(date.getText().toString());
                    plastic.setAmount_plastic(Integer.parseInt(amount.getText().toString()));
                    plastic.setPlastic_picture(bitmapimage);
                    plastic.setId_code_column(scanCodePlastic.getId_code());
                    plastic.setId_type_column(scanItemsPlastic.getId_type());
                    User user = dbUser.getPreference();
                    plastic.setId_user_column((int) user.getId_user());
                    dbPlastic.insertPlastic(plastic);
                    Navigation.findNavController(view).navigate(R.id.action_confirmationRecycleFragment_to_nav_scan);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}