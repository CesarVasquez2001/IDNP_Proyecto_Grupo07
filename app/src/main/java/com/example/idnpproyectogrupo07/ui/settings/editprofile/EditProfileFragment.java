package com.example.idnpproyectogrupo07.ui.settings.editprofile;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.R;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.database.User;
import com.example.idnpproyectogrupo07.databinding.FragmentEditProfileBinding;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Calendar;

public class EditProfileFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 99;
    private Uri imagePath;
    private Bitmap imageToStore;

    private EditProfileViewModel mViewModel;
    private FragmentEditProfileBinding binding;
    private int day, month, year;

    private DBUser dbUser;
    private User user;

    private EditText date, email, full_name;
    private AutoCompleteTextView genders;
    private ImageView editImage;
    private ShapeableImageView profile;
    private Button update;

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    private final String TAG = "EditProfileFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbUser = new DBUser(getActivity());
        dbUser.OpenDb();
        //user = dbUser.getPreference();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        int id_user = (int) getArguments().get("id_user");
        user = dbUser.getUser(id_user);

        genders = binding.editGender;
        date = binding.editDate;
        email = binding.editEmail;
        full_name = binding.editFullName;
        editImage = binding.navSettingEditProfileHeader.editProfilePictureButton;
        profile = binding.navSettingEditProfileHeader.profilePicture;
        update = binding.editButton;


        String[] gender = getResources().getStringArray(R.array.gender);
        //ArrayList<String> gender = new ArrayList<String>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item, gender);
        // DateFormat DD-MM-YY
        genders.setAdapter(arrayAdapter);

        email.setText(user.getEmail());
        full_name.setText(user.getFullname());
        genders.setText(user.getGender(), false);
        date.setText(user.getDate_of_birth());
        profile.setImageBitmap(user.getProfile_picture());
        if (imageToStore == null)
            imageToStore = user.getProfile_picture();

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtEmail = email.getText().toString();
                String txtFullname = full_name.getText().toString();
                String txtGender = genders.getText().toString();
                String txtDate = date.getText().toString();
                if (txtEmail.isEmpty() || txtFullname.isEmpty() || txtGender.isEmpty() || txtDate.isEmpty()) {
                    Toast.makeText(getContext(), "Complete all the required data", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean check = dbUser.check(txtEmail,user.getEmail());
                    Log.d("TAG", user.getEmail() + " - " + txtEmail);
                    if (check==false) {
                        User userUpdate = new User();
                        userUpdate.setId_user((int) user.getId_user());
                        userUpdate.setEmail(txtEmail);
                        userUpdate.setFullname(txtFullname);
                        userUpdate.setGender(txtGender);
                        userUpdate.setDate_of_birth(txtDate);
                        userUpdate.setProfile_picture(imageToStore);
                        boolean result = dbUser.updateUser(userUpdate);
                        if (result) {
                            Toast.makeText(getContext(), "Data update", Toast.LENGTH_SHORT).show();
                            dbUser.savePreference(userUpdate);
                            getActivity().onBackPressed();
                        } else {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Email already Exists", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }
                        , year, month, day);
                datePickerDialog.show();
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imagePath);
                profile.setImageBitmap(imageToStore);
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void chooseImage() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);

        } catch (Exception e) {
            Toast.makeText(getContext().getApplicationContext(), "CHOOSE IMAGE" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}