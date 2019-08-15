package com.example.mygitsearchdrawer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mygitsearchdrawer.controller.RepositoriesDetailActivity;
import com.example.mygitsearchdrawer.model.Item;
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

public class FirebaseGithubViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView mGithubImageView;

    View mView;
    Context mContext;
    public FirebaseGithubViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindGithub(Item item) {
       //mageView userImageView = (ImageView) mView.findViewById(R.id.cover);
        mGithubImageView = (ImageView) mView.findViewById(R.id.cover);
        TextView nameTextView = (TextView) mView.findViewById(R.id.titleTxtView);
        TextView linkTextView = (TextView) mView.findViewById(R.id.githublink1);


        Picasso.get().load(item.getAvatarUrl()).into(mGithubImageView);

        nameTextView.setText(item.getLogin());
        linkTextView.setText(item.getHtmlUrl());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<Item> items = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GITHUB_USER). child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    items.add(snapshot.getValue(Item.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, RepositoriesDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(items));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
