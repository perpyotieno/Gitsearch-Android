package com.example.mygitsearchdrawer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mygitsearchdrawer.controller.RepositoriesDetailActivity;
import com.example.mygitsearchdrawer.model.Item;
import com.example.mygitsearchdrawer.util.ItemTouchHelperViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseGithubViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    public ImageView mGithubImageView;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public FirebaseGithubViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }

    public void bindGithub(Item item) {

        mGithubImageView = (ImageView) mView.findViewById(R.id.cover);
        TextView nameTextView = (TextView) mView.findViewById(R.id.titleTxtView);
        TextView linkTextView = (TextView) mView.findViewById(R.id.githublink1);


        Picasso.get().load(item.getAvatarUrl()).into(mGithubImageView);

        nameTextView.setText(item.getLogin());
        linkTextView.setText(item.getHtmlUrl());

    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }

}
