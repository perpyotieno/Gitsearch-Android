package com.example.mygitsearchdrawer.controller;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;

import com.example.mygitsearchdrawer.R;
import com.example.mygitsearchdrawer.model.GithubPagerAdapter;
import com.example.mygitsearchdrawer.model.Item;

import org.parceler.Parcels;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepositoriesDetailActivity extends AppCompatActivity{
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private GithubPagerAdapter adapterViewPager;
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_detail);
        ButterKnife.bind(this);

        items = Parcels.unwrap(getIntent().getParcelableExtra("mItems"));
        int startingPosition = getIntent().getIntExtra("i", 0);
        adapterViewPager = new GithubPagerAdapter(getSupportFragmentManager(), items);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
