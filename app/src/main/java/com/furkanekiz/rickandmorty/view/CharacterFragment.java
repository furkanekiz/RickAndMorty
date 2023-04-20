package com.furkanekiz.rickandmorty.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.furkanekiz.rickandmorty.LocationItemClickListener;
import com.furkanekiz.rickandmorty.R;
import com.furkanekiz.rickandmorty.adapter.CharacterAdapter;
import com.furkanekiz.rickandmorty.adapter.LocationAdapter;
import com.furkanekiz.rickandmorty.databinding.FragmentCharacterBinding;
import com.furkanekiz.rickandmorty.model.character.CharacterResult;
import com.furkanekiz.rickandmorty.model.location.LocationResult;
import com.furkanekiz.rickandmorty.viewModel.CharacterItemViewModel;
import com.furkanekiz.rickandmorty.viewModel.LocationViewModel;

import java.util.ArrayList;


public class CharacterFragment extends Fragment {

    View generalView;
    ArrayList<LocationResult> locationResults = new ArrayList<>();
    ArrayList<CharacterResult> characterResults = new ArrayList<>();
    LocationItemClickListener locationItemClickListener;
    FragmentCharacterBinding fragmentCharacterBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCharacterBinding = FragmentCharacterBinding.inflate(inflater, container, false);
        generalView = fragmentCharacterBinding.getRoot();
        locationItemClickListener = (result) -> {
            fragmentCharacterBinding.rvLocations.post(() -> {
            });
            characterResults.clear();
            CharacterItemViewModel characterItemViewModel = new ViewModelProvider(this).get(CharacterItemViewModel.class);

            String id;
            for (String s : result.getResidents()) {
                String[] separated = s.split("character/");
                id = separated[1].trim();
                characterItemViewModel.getCharacterList(Integer.parseInt(id));
                characterItemViewModel.getServerSuccess().observe(getViewLifecycleOwner(), success -> {
                    characterResults = CharacterItemViewModel.resultArrayList;
                    CharacterAdapter characterAdapter = new CharacterAdapter(characterResults, R.layout.character_card);
                    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    fragmentCharacterBinding.rvCharacters.setLayoutManager(horizontalLayoutManagaer);
                    fragmentCharacterBinding.rvCharacters.setAdapter(characterAdapter);
                });
            }

        };
        return generalView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LocationViewModel locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        locationViewModel.getLocationList();
        locationViewModel.getServerSuccess().observe(getViewLifecycleOwner(), itemsuss -> {
            locationResults = LocationViewModel.resultArrayList;
            LocationAdapter itemAdapter = new LocationAdapter(locationResults, R.layout.location_card, locationItemClickListener);
            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            fragmentCharacterBinding.rvLocations.setLayoutManager(horizontalLayoutManagaer);
            fragmentCharacterBinding.rvLocations.setAdapter(itemAdapter);
        });

        super.onViewCreated(view, savedInstanceState);
    }

}

