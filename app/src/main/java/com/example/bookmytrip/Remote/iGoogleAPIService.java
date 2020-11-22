package com.example.bookmytrip.Remote;

import com.example.bookmytrip.Model.Myplaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface iGoogleAPIService {
    @GET
    Call<Myplaces> getNearbyPlaces(@Url String url);
}
