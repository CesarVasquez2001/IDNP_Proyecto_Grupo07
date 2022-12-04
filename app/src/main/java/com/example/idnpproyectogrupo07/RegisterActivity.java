package com.example.idnpproyectogrupo07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.database.User;
import com.example.idnpproyectogrupo07.databinding.ActivityLoginBinding;
import com.example.idnpproyectogrupo07.databinding.ActivityRegisterBinding;

import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private @NonNull
    ActivityRegisterBinding binding;
    private EditText txtDate, txtEmail, txtPassword, txtConfirmPassword, txtFullname, txtGender;
    private TextView txtSignIn;
    private Button buttonRegister;
    private int day, month, year;
    private DBUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*
        setContentView(R.layout.activity_login);
        */

        String[] gender = getResources().getStringArray(R.array.gender);
        //ArrayList<String> gender = new ArrayList<String>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, gender);
        // DateFormat DD-MM-YY
        binding.registerGender.setAdapter(arrayAdapter);

        txtSignIn = binding.registerLogin;

        txtEmail = (EditText) binding.registerEmail;
        txtPassword = (EditText) binding.registerPassword;
        txtConfirmPassword = (EditText) binding.registerConfirmPassword;
        txtFullname = (EditText) binding.registerFullname;
        txtGender = (EditText) binding.registerGender;
        txtDate = (EditText) binding.registerDateOfBirth;

        buttonRegister = (Button) binding.buttonRegisterContinue;

        dbUser = new DBUser(getApplicationContext());
        dbUser.OpenDb();

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }
                        , day, month, year);
                datePickerDialog.show();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();
                String fullname = txtFullname.getText().toString();
                String gender = txtGender.getText().toString();
                String date = txtDate.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullname.isEmpty() || gender.isEmpty() || date.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Files can't be blank", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirmPassword)) {
                        Boolean checkemail = dbUser.checkEmail(email);
                        if (checkemail == false) {
                            User user = new User(fullname, email, password, gender, date);
                            Long insert = dbUser.insertUser(user);
                            if(insert>0){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                                clean();
                                /*
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                */

                                finish();

                            }else{
                                Toast.makeText(getApplicationContext(),"Registered Failed",Toast.LENGTH_SHORT).show();

                            }

                        }else{
                            Toast.makeText(getApplicationContext(),"User already Exists",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Passwords are not matching",Toast.LENGTH_SHORT).show();

                    }
                }



            }
        });
    }
    private void clean(){
        txtEmail.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        txtFullname.setText("");
        txtGender.setText("");
        txtDate.setText("");
    }
}