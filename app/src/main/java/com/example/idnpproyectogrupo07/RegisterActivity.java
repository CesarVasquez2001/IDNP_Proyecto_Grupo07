package com.example.idnpproyectogrupo07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.idnpproyectogrupo07.databinding.ActivityLoginBinding;
import com.example.idnpproyectogrupo07.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private @NonNull ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*
        setContentView(R.layout.activity_login);
        */
        Button button = binding.buttonRegister;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });  }
}