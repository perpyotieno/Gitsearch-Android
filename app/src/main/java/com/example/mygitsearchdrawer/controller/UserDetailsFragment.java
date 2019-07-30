package com.example.mygitsearchdrawer.controller;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mygitsearchdrawer.R;
import com.example.mygitsearchdrawer.model.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment {
    @BindView(R.id.user_image_header) ImageView mImageLabel;
    @BindView(R.id.username) TextView mNameLabel;
    @BindView(R.id.link) TextView mGithubLink;
//    @BindView(R.id.followers) TextView mFollowers;
//    @BindView(R.id.following) TextView mFollowing;
//    @BindView(R.id.repositories) TextView mRepositories;

    private Item items;

    public static UserDetailsFragment newInstance(Item itemArrayList){
        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("itemArrayList", Parcels.wrap(itemArrayList));
        userDetailsFragment.setArguments(args);
        return userDetailsFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = Parcels.unwrap(getArguments().getParcelable("itemArrayList"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(items.getAvatarUrl()).into(mImageLabel);

        mNameLabel.setText(items.getLogin());
        mGithubLink.setText(items.getHtmlUrl());
//        mFollowers.setText(Integer.toString(items.getFollowers()));
//        mFollowing.setText(Integer.toString(items.getFollowing()));
//        mRepositories.setText(Integer.toString(items.getRepositories()));

        return view;
    }




}
