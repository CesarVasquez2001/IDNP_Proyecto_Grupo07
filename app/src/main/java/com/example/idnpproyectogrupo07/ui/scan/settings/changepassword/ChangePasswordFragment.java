package com.example.idnpproyectogrupo07.ui.scan.settings.changepassword;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.classes.User;
import com.example.idnpproyectogrupo07.databinding.FragmentChangePasswordBinding;

public class ChangePasswordFragment extends Fragment {

    private ChangePasswordViewModel mViewModel;
    private FragmentChangePasswordBinding binding;

    private EditText current_password, password, confirm_password;
    private Button change_password;

    private DBUser dbUser;
    private User user;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbUser = new DBUser(getActivity());
        dbUser.OpenDb();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int id_user = (int) getArguments().get("id_user");
        user = dbUser.getUser(id_user);

        current_password = binding.editCurrentPassword;
        password = binding.editNewPassword;
        confirm_password = binding.editConfirmNewPassword;

        change_password = binding.editUpdatePassword;

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current = current_password.getText().toString();
                String new_password = password.getText().toString();
                String confirm = confirm_password.getText().toString();


                if (current.isEmpty()||new_password.isEmpty()||confirm.isEmpty()){
                    Toast.makeText(getContext(), "Complete all the required data", Toast.LENGTH_SHORT).show();
                }else{
                    if (current.equals(user.getPassword())){
                        if (new_password.equals(confirm)){
                            User userUpdate = dbUser.getUser((int) user.getId_user());

                            userUpdate.setPassword(new_password);
                            boolean result = dbUser.updateUserPassword(userUpdate);
                            if (result) {
                                Toast.makeText(getContext(), "Data update", Toast.LENGTH_SHORT).show();
                                dbUser.savePreference(userUpdate);
                                getActivity().onBackPressed();
                            } else {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(getContext(), "The current password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


}