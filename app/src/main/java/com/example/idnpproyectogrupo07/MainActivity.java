package com.example.idnpproyectogrupo07;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.databinding.MenuImageBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
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

    private  NavigationView navigationView;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolBar);
        /*
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        drawer = binding.drawerLayout;
        navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations

        ;

        //Toast toast=Toast. makeText(getApplicationContext(), selectedItem.getTitle(),Toast. LENGTH_SHORT);
        //toast.show();




        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_overview  , R.id.nav_home, R.id.nav_history, R.id.nav_scan,R.id.nav_education,R.id.nav_signout,R.id.nav_setting)
                .setOpenableLayout(drawer)
                .build();
        navigationView.setNavigationItemSelectedListener(item -> {
            // do stuff

            return true;
        });


        /*
        for (int i = 0;i<navigationView.getMenu().size();i++){
            navigationView.getMenu().getItem(i).setActionView(R.layout.menu_image);
        }
*/
        //Color.parseColor("#FF000000")



        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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
        if (menu.isChecked()==true){
            menu.getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_left_24);
        }
        if(menu.isChecked()==false)
            menu.getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_right_24);
*/



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


}