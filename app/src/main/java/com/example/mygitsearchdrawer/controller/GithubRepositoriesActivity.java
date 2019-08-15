package com.example.mygitsearchdrawer.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mygitsearchdrawer.ItemAdapter;
import com.example.mygitsearchdrawer.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;



import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygitsearchdrawer.api.Client;
import com.example.mygitsearchdrawer.api.Service;
import com.example.mygitsearchdrawer.model.Item;
import com.example.mygitsearchdrawer.model.ItemResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepositoriesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView Disconnected;
    private Item item;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repositories);
        initViews();
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(GithubRepositoriesActivity.this, "Github users refreshed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();

    }

    private void loadJSON() {
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<List<Item>> call = apiService.getItems("language:java+location:kenya");

            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    List<Item> items = response.body();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    for(Item watchman: items){
                        Log.d("username-----------", watchman.getLogin());
                        Log.d("image--------------", watchman.getAvatarUrl());
                        Log.d("url----------------", watchman.getHtmlUrl());
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    Log.d("GithubRepositories", t.getMessage());
                    Toast.makeText(GithubRepositoriesActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}




