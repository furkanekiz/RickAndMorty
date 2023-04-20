package com.furkanekiz.rickandmorty.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.furkanekiz.rickandmorty.RickAndMortApp;
import com.furkanekiz.rickandmorty.model.character.CharacterResult;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CharacterItemViewModel extends ViewModel {
    private static final String TAG = CharacterItemViewModel.class.getSimpleName();
    private static final boolean DEBUG = true;
    public MutableLiveData<Boolean> itemSuccess;
    public MutableLiveData<Boolean> itemFailed;
    public MutableLiveData<Boolean> serverSuccess;
    public static ArrayList<CharacterResult> resultArrayList = new ArrayList<>();

    public CharacterItemViewModel() {
        itemSuccess = new MutableLiveData<>();
        itemFailed = new MutableLiveData<>();
        serverSuccess = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getServerSuccess() {
        return serverSuccess;
    }

    public void getCharacterList(int id) {
        RickAndMortApp.getApiService().getCharacter(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CharacterResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log("CharacterList onSubscribe");
                        resultArrayList.clear();

                    }

                    @Override
                    public void onNext(CharacterResult list) {
                        Log("CharacterList onNext: " + id + "     " + list.getName());
                        resultArrayList.add(list);

                        itemSuccess.setValue(false);
                        itemFailed.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log("CharacterList onError" + e.getMessage());
                        itemFailed.setValue(true);
                        itemFailed.setValue(false);
                    }

                    @Override
                    public void onComplete() {
                        Log("CharacterList onComplete");
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
