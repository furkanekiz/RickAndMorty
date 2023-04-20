package com.furkanekiz.rickandmorty.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.furkanekiz.rickandmorty.databinding.FragmentDetailBinding;
import com.furkanekiz.rickandmorty.model.character.CharacterResult;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DetailFragment extends Fragment {
    private final String TAG = DetailFragment.class.getSimpleName();
    private static final boolean DEBUG = true;
    View generalView;
    FragmentDetailBinding fragmentDetailBinding;
    CharacterResult characterResult = new CharacterResult();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false);
        generalView = fragmentDetailBinding.getRoot();
        StringBuilder s = new StringBuilder();
        Bundle bundle = getArguments();
        if (bundle != null) {
            characterResult = bundle.getParcelable("characterItemDetail");

            String id;
            for (String integer : characterResult.getEpisode()) {
                String[] separated = integer.split("episode/");
                id = separated[1].trim();
                s.append(id).append(",");
            }
        }


        fragmentDetailBinding.txtDetailTitle.setText(characterResult.getName());
        fragmentDetailBinding.txtStatus.setText(characterResult.getStatus());
        fragmentDetailBinding.txtSpecy.setText(characterResult.getSpecies());
        fragmentDetailBinding.txtGender.setText(characterResult.getGender());
        fragmentDetailBinding.txtOrigin.setText(characterResult.getOrigin().getName());
        fragmentDetailBinding.txtLocation.setText(characterResult.getLocation().getName());
        fragmentDetailBinding.txtEpisodes.setText(s.deleteCharAt(s.length() - 1).toString());
        String initialStringDate = characterResult.getCreated();
        convertDate(initialStringDate);
        Glide.with(this).load(characterResult.getImage()).into(fragmentDetailBinding.imgDetailLogo);
        fragmentDetailBinding.imgDetailLeft.setOnClickListener(view -> {
            FragmentManager fm = getParentFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        });

        return generalView;
    }

    private void convertDate(String initialStringDate) {
        Locale us = new Locale("US");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", us);
        try {
            Date date = format.parse(initialStringDate);
            assert date != null;
            String stringDate = new SimpleDateFormat("dd MMM, yyyy hh:mm:ss", us).format(date);
            fragmentDetailBinding.txtCreatedAt.setText(stringDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void Log(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }
}
