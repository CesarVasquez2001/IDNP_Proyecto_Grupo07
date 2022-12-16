package com.example.idnpproyectogrupo07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.classes.ScanCodePlastic;
import com.example.idnpproyectogrupo07.database.DBCode;
import com.example.idnpproyectogrupo07.database.DBHelper;
import com.example.idnpproyectogrupo07.database.DBType;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.database.User;
import com.example.idnpproyectogrupo07.databinding.ActivityLoginBinding;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private @NonNull
    ActivityLoginBinding binding;
    private DBUser dbUser;
    private DBCode dbCode;
    private DBType dbType;


    private  Button buttonSignIn;
    private TextView txtSignUp;
    private EditText txtEmail,txtPassword;

    private ImageView imageView;
    private static String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Inicializar datos de codigo y tipo si se entra por primera vez a la app
        dbCode = new DBCode(this);
        dbCode.OpenDb();
        Log.d(TAG,"SIZE CODE: " + dbCode.getSize() );

        dbType = new DBType(this);
        dbType.OpenDb();
        Log.d(TAG,"SIZE CODE: " + dbType.getSize() );

        //Database
        dbUser = new DBUser(this);
        dbUser.OpenDb();


        txtEmail=binding.loginEmail;
        txtPassword=binding.loginPassword;
        txtSignUp = binding.loginSignUp;
        buttonSignIn = binding.buttonSignIn;

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                if (email.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Files can't be blank", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemailpass = dbUser.checkEmailPassword(email,password);
                    User user= dbUser.loginUser(email,password);

                    if (checkemailpass==true){
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("EMAIL",user.getEmail());
                        intent.putExtra("PASSWORD",user.getPassword());

                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                    clean();
                }

            }
        });

    }

    private void clean(){
        txtEmail.setText("");
        txtPassword.setText("");
    }
}