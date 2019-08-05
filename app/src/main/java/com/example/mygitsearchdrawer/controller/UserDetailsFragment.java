package com.example.mygitsearchdrawer.controller;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygitsearchdrawer.Constants;
import com.example.mygitsearchdrawer.R;
import com.example.mygitsearchdrawer.model.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.user_image_header)
    ImageView mImageLabel;
    @BindView(R.id.username)
    TextView mNameLabel;
    @BindView(R.id.link)
    TextView mGithubLink;
    @BindView(R.id.saveGithubButton)
    Button mSaveGithubButton;

    private Item items;

    public static UserDetailsFragment newInstance(Item itemArrayList) {
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

        mSaveGithubButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mSaveGithubButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference githubRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_GITHUB_USER)
            .child(uid);
            DatabaseReference pushRef = githubRef.push();

            String pushId = pushRef.getKey();
            items.setPushId(pushId);
            pushRef.setValue(items);



            githubRef.push().setValue(items);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        }


    }
}