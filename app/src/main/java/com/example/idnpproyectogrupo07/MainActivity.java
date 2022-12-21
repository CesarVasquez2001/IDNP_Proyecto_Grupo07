package com.example.idnpproyectogrupo07;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idnpproyectogrupo07.database.DBUser;
import com.example.idnpproyectogrupo07.classes.User;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idnpproyectogrupo07.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String SELECTED_ITEM = "arg_selected_item";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private static String TAG = "MainActivity";
    private NavController navController;

    private Bundle data;
    private DBUser dbUser;
    private User user;

    private View header;
    private ImageView nav_image;
    private TextView nav_full_name, nav_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolBar);

        drawer = binding.drawerLayout;
        navigationView = binding.navView;

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_overview, R.id.nav_home, R.id.nav_history, R.id.nav_scan, R.id.nav_education, R.id.nav_setting)
                .setOpenableLayout(drawer)
                .build();


        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);


// User
        //User user= (User) bundle.getSerializable("USER");
        //Log.d(TAG,"USUARIO RECIBIDO"+user);
        data = getIntent().getExtras();
        String email = data.getString("EMAIL");
        String password = data.getString("PASSWORD");
        // database datos iniciales
        dbUser = new DBUser(this);
        dbUser.OpenDb();
        user = dbUser.getUser(email, password);
        dbUser.savePreference(user);


        header = binding.navView.getHeaderView(0);
        nav_image = (ImageView) header.findViewById(R.id.image_view_header);
        nav_full_name = (TextView) header.findViewById(R.id.full_name_header);
        nav_email = (TextView) header.findViewById(R.id.email_header);
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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);


        user = dbUser.getUser((int) user.getId_user());

        nav_full_name.setText(user.getFullname());
        nav_email.setText(user.getEmail());
        nav_image.setImageBitmap(user.getProfile_picture());

        Menu menu = navigationView.getMenu();


        for (int i = 0; i < menu.size(); i++) {
            MenuItem m = menu.getItem(i);
            m.getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_right_24);
            if (m.isChecked() == true) {
                m.getActionView().findViewById(R.id.arrow_icon).setBackgroundResource(R.drawable.ic_baseline_chevron_left_24);

            }
        }
        ImageView exit = binding.navView.findViewById(R.id.exit_header);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.drawerLayout.isOpen()) {
                    binding.drawerLayout.close();
                }
            }
        });


        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean handled = NavigationUI.onNavDestinationSelected(item, navController);
        if (!handled) {
            switch (item.getItemId()) {
                case R.id.nav_signout:
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Logout");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    break;
            }
        }
        drawer.closeDrawer(GravityCompat.START);

        return handled;
    }


    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_scan);
        fragment.onActivityResult(requestCode, resultCode, data);
        }*/
}
