package com.example.mygitsearchdrawer.model;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mygitsearchdrawer.controller.UserDetailsFragment;

import java.util.ArrayList;

public class GithubPagerAdapter  extends FragmentPagerAdapter {
    private ArrayList<Item> items;

    public GithubPagerAdapter(FragmentManager fm, ArrayList<Item> mItems) {
        super(fm);
        items = mItems;
    }

    @Override
    public Fragment getItem(int i) {
        return UserDetailsFragment.newInstance(items.get(i));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return items.get(i).getLogin();
    }
}
