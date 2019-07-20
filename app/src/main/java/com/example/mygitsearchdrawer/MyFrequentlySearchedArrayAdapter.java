package com.example.mygitsearchdrawer;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyFrequentlySearchedArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mFsearches;
    private String[] mNumberOfRepositories;

    public MyFrequentlySearchedArrayAdapter(Context mContext, int resource, String[] mFsearches, String[] mNumberOfRepositories) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mFsearches = mFsearches;
        this.mNumberOfRepositories = mNumberOfRepositories;
    }
    @Override
    public Object getItem(int position) {
        String Fsearches = mFsearches[position];
        String NumberOfRepositories = mNumberOfRepositories[position];
        return String.format("%s \nNumber of Repositories: %s", Fsearches, NumberOfRepositories);
    }

    @Override
    public int getCount() {
        return mFsearches.length;
    }
}
