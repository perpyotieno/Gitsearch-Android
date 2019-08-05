package com.example.mygitsearchdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mygitsearchdrawer.model.Item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedUserListActivity extends AppCompatActivity {
    private DatabaseReference mGithubReference;
    private FirebaseRecyclerAdapter<Item, FirebaseGithubViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repositories);
        ButterKnife.bind(this);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mGithubReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_GITHUB_USER)
                .child(uid);

        setUpFirebaseAdapter();

    }
    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(mGithubReference, Item.class)
                        .build();

            mFirebaseAdapter = new FirebaseRecyclerAdapter<Item, FirebaseGithubViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull FirebaseGithubViewHolder firebaseGithubViewHolder, int position, @NonNull Item item) {
                    firebaseGithubViewHolder.bindGithub(item);
                }

            @NonNull
            @Override
            public FirebaseGithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
                return new FirebaseGithubViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
    }

