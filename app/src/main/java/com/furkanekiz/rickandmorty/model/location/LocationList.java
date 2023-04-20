package com.furkanekiz.rickandmorty.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationList {

@SerializedName("info")
@Expose
private Info info;
@SerializedName("results")
@Expose
private List<LocationResult> results;

public Info getInfo() {
return info;
}

public void setInfo(Info info) {
this.info = info;
}

public List<LocationResult> getResults() {
return results;
}

public void setResults(List<LocationResult> results) {
this.results = results;
}

}