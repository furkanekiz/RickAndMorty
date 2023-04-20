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
import androidx.fragment.app.FragmentTransaction;

import com.furkanekiz.rickandmorty.R;
import com.furkanekiz.rickandmorty.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private final String TAG = HomeFragment.class.getSimpleName();
    private static final boolean DEBUG = true;
    private FragmentManager manager;
    FragmentHomeBinding fragmentHomeBinding;
    View generalView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        generalView = fragmentHomeBinding.getRoot();
        manager = getChildFragmentManager();
        loadFragment(new CharacterFragment());
        return generalView;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction().setCustomAnimations(R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out);
        transaction.replace(R.id.fragMain, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();

    }

    private void Log(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }
}
