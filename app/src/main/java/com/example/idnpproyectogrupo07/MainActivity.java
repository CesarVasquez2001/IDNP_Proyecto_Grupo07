package com.example.idnpproyectogrupo07;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.database.DBHelper;
import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.database.User;
import com.example.idnpproyectogrupo07.databinding.MenuImageBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idnpproyectogrupo07.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
    private static final String SELECTED_ITEM = "arg_selected_item";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private static String TAG = "MainActivity";

    private Bundle data;
    private DBUser dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // User
        //User user= (User) bundle.getSerializable("USER");
        //Log.d(TAG,"USUARIO RECIBIDO"+user);
        data=getIntent().getExtras();
        String email =data.getString("EMAIL");
        String password =data.getString("PASSWORD");



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolBar);

        drawer = binding.drawerLayout;
        navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_overview  , R.id.nav_home, R.id.nav_history, R.id.nav_scan,R.id.nav_education,R.id.nav_signout,R.id.nav_setting)
                .setOpenableLayout(drawer)
                .build();
        navigationView.setNavigationItemSelectedListener(item -> {
            return true;
        });
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // database
        dbUser = new DBUser(this);
        dbUser.OpenDb();
        User user = dbUser.loginUser(email,password);

        View header = binding.navView.getHeaderView(0);
        ImageView nav_image = (ImageView) header.findViewById(R.id.image_view_header);
        TextView nav_full_name= (TextView) header.findViewById(R.id.full_name_header);
        TextView nav_email= (TextView) header.findViewById(R.id.email_header);

        nav_full_name.setText(user.getFullname());
        nav_email.setText(user.getEmail());
        nav_image.setImageBitmap(user.getProfile_picture());

    }

    /*
Menu contextual
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer,menu);
        MenuCompat.setGroupDividerEnabled(menu,true);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        Menu menu = navigationView.getMenu();
        int id=0;
        for (int i=0;i<menu.size();i++){
            MenuItem m=menu.getItem(i);
            m.getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_right_24);
            if (m.isChecked()==true){
                m.getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_left_24);
            }
        }
        ImageView exit = binding.navView.findViewById(R.id.exit_header);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.drawerLayout.isOpen()){
                    binding.drawerLayout.close();
                }
            }
        });

    /*
            Toast toast=Toast. makeText(getApplicationContext(),"dad"+id,Toast. LENGTH_SHORT);
            toast.show();

            if (navigationView.getMenu().getItem(0).isChecked()==true)
                navigationView.getMenu().getItem(0).getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_left_24);
            if (navigationView.getMenu().getItem(1).isChecked()==true)
                navigationView.getMenu().getItem(1).getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_left_24);
             if (navigationView.getMenu().getItem(2).isChecked()==true)
                navigationView.getMenu().getItem(2).getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_left_24);


            if (navigationView.getMenu().getItem(0).isChecked()==false)
                navigationView.getMenu().getItem(0).getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_right_24);
             if (navigationView.getMenu().getItem(1).isChecked()==false)
                navigationView.getMenu().getItem(1).getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_right_24);
             if (navigationView.getMenu().getItem(2).isChecked()==false)
                navigationView.getMenu().getItem(2).getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_right_24);
    */


        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_scan);
        fragment.onActivityResult(requestCode, resultCode, data);
        }*/
    }
