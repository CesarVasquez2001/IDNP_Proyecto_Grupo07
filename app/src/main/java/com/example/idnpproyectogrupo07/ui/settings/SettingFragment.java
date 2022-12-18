package com.example.idnpproyectogrupo07.ui.settings;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.database.User;
import com.example.idnpproyectogrupo07.databinding.FragmentSettingBinding;
import com.example.idnpproyectogrupo07.databinding.SettingHeaderBinding;


public class SettingFragment extends Fragment {

    private SettingViewModel mViewModel;
    private FragmentSettingBinding binding;
    private View root;

    private DBUser dbUser;
    private User user;
    private SettingHeaderBinding header;

    private TextView edit;
    private TextView change;

    //logica de inicializacion
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SettingViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        dbUser = new DBUser(getActivity());
        dbUser.OpenDb();
        user = dbUser.getPreference();

        return root;
    }

    // logica de creacion de los componentes
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        header = binding.settingHeader;

        header.textProfile.setText(user.getFullname());
        header.textEmail.setText(user.getEmail());
        header.imageView.setImageBitmap(user.getProfile_picture());

        edit = binding.textEditProfile;
        change = binding.textChangePassword;



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id_user", (int) user.getId_user());
                Navigation.findNavController(view).navigate(R.id.nav_setting_edit_profile,bundle);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id_user", (int) user.getId_user());
                Navigation.findNavController(view).navigate(R.id.nav_setting_change_password,bundle);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}