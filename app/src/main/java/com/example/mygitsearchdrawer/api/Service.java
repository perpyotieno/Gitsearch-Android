package com.example.mygitsearchdrawer.api;

import com.example.mygitsearchdrawer.model.Item;
import com.example.mygitsearchdrawer.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("/users")
    Call<List<Item>> getItems(@Query("q") String query);
}
