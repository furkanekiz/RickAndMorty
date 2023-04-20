package com.furkanekiz.rickandmorty.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.furkanekiz.rickandmorty.RickAndMortApp;
import com.furkanekiz.rickandmorty.model.location.LocationList;
import com.furkanekiz.rickandmorty.model.location.LocationResult;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LocationViewModel extends ViewModel {
    private static final String TAG = LocationViewModel.class.getSimpleName();
    private static final boolean DEBUG = true;
    public MutableLiveData<Boolean> itemSuccess;
    public MutableLiveData<Boolean> itemFailed;
    public MutableLiveData<Boolean> serverSuccess;
    public static ArrayList<LocationResult> resultArrayList = new ArrayList<>();

    public LocationViewModel() {
        itemSuccess = new MutableLiveData<>();
        itemFailed = new MutableLiveData<>();
        serverSuccess = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getServerSuccess() {
        return serverSuccess;
    }

    public void getLocationList() {
        RickAndMortApp.getApiService().getLocation()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LocationList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log("LocationList onSubscribe");
                    }

                    @Override
                    public void onNext(LocationList list) {
                        Log("LocationList onNext: " );
                        resultArrayList.addAll(list.getResults());
                        itemSuccess.setValue(false);
                        itemFailed.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log("LocationList onError" + e.getMessage());
                        itemFailed.setValue(true);
                        itemFailed.setValue(false);
                    }

                    @Override
                    public void onComplete() {
                        Log("LocationList onComplete");
                        serverSuccess.setValue(true);

                    }
                });
    }


    private void Log(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }
}
