package com.furkanekiz.rickandmorty.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.furkanekiz.rickandmorty.databinding.ActivityHomeActivtyBinding;
import com.furkanekiz.rickandmorty.utils.AppSettings;

public class HomeActivty extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static Context context;
    ActivityHomeActivtyBinding homeActivtyBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeActivtyBinding = ActivityHomeActivtyBinding.inflate(getLayoutInflater());
        View view = homeActivtyBinding.getRoot();
        setContentView(view);
        AppSettings mAppSettings = new AppSettings(this);
        mAppSettings.saveFirstAPKRun(false);
        context = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        openMainMenu();
    }

    private void openMainMenu() {
        fragmentManager = getSupportFragmentManager();
        HomeFragment mainMenuFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(android.R.id.content, mainMenuFragment).commitAllowingStateLoss();

    }
}