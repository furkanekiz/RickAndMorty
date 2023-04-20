package com.furkanekiz.rickandmorty.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.furkanekiz.rickandmorty.R;
import com.furkanekiz.rickandmorty.databinding.ActivityMainBinding;
import com.furkanekiz.rickandmorty.utils.AppSettings;

public class MainActivity extends AppCompatActivity {
    private AppSettings mAppSettings;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        mAppSettings = new AppSettings(this);
        Glide.with(this)
                .load(R.raw.gif)
                .into(activityMainBinding.imgSplash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String txtMessage;
        if (mAppSettings.isFirstAPKRun()) {
            txtMessage = getString(R.string.string_welcome) + " " + getString(R.string.string_let_s_start);
            activityMainBinding.txtSplashBtn.setText(txtMessage);
        } else {
            txtMessage = getString(R.string.string_hello) + " " + getString(R.string.string_let_s_start);
            activityMainBinding.txtSplashBtn.setText(txtMessage);
        }
        activityMainBinding.txtSplashBtn.setOnClickListener(click -> {
            Intent intent = new Intent(this, HomeActivty.class);
            startActivity(intent);
            finish();
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mAppSettings.isFirstAPKRun()) {
            mAppSettings.saveFirstAPKRun(false);
        }
        finish();
    }

}