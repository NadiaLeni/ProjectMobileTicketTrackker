package com.example.projectmobile3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.projectmobile3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_myticket, R.id.navigation_account, R.id.fragmentRegister, R.id.fragmentProfile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navView.getChildAt(0);
//        for (int i = 0; i < menuView.getChildCount(); i++) {
//            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
//            View icon = item.findViewById(com.google.android.material.R.id.icon);
//            if (icon instanceof ImageView) {
//                ImageView imageView = (ImageView) icon;
//                imageView.setColorFilter(ContextCompat.getColor(this, R.color.light_pink), android.graphics.PorterDuff.Mode.SRC_IN);
//            }
//        }
    }

}