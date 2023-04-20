package com.furkanekiz.rickandmorty;



import com.furkanekiz.rickandmorty.model.character.CharacterResult;
import com.furkanekiz.rickandmorty.model.location.LocationList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RickAndMortyApi {
    @GET("location")
    Observable<LocationList> getLocation();

    @GET("character/{id}")
    Observable<CharacterResult> getCharacter(@Path("id") int id);

}
