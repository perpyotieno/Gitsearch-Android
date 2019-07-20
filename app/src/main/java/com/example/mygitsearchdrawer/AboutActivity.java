package com.example.mygitsearchdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {
    private Button mFrequentlySearchedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mFrequentlySearchedButton =(Button) findViewById(R.id.frequently_searched);

        mFrequentlySearchedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, FrequentlySearchedActivity.class);
                startActivity(intent);
            }
        });

    }
}
