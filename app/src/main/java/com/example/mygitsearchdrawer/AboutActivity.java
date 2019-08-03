package com.example.mygitsearchdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mygitsearchdrawer.controller.GithubRepositoriesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements  View.OnClickListener{
    @BindView(R.id.frequently_searched) Button mFrequentlySearchedButton;
    @BindView(R.id.search_repos) Button mSearchUserButton;
    @BindView(R.id.edit_username) EditText mEnterUsernameEditText;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");

        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSearchUserButton.setOnClickListener(this);


        mFrequentlySearchedButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View view) {
                if (view == mSearchUserButton) {
                    String username = mEnterUsernameEditText.getText().toString();
                    addToSharedPreferences(username);
                    Intent intent = new Intent(AboutActivity.this, UserListActivity.class);
                    startActivity(intent);
                }

                if (view == mFrequentlySearchedButton) {
                Intent intent = new Intent(AboutActivity.this, GithubRepositoriesActivity.class);
                startActivity(intent);
            }
        }
    private void addToSharedPreferences(String username) {
        mEditor.putString(Constants.PREFERENCES_USER_KEY, username).apply();
    }

    }
