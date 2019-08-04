package com.example.mygitsearchdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mygitsearchdrawer.controller.GithubRepositoriesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.frequently_searched) Button mFrequentlySearchedButton;
    @BindView(R.id.savedUsersButton) Button mSavedUsersButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");

        ButterKnife.bind(this);

        mFrequentlySearchedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, GithubRepositoriesActivity.class);
                startActivity(intent);
            }
        });
        mSavedUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, SavedUserListActivity.class);
                startActivity(intent);
            }
        });

    }
}
