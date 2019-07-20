package com.example.mygitsearchdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FrequentlySearchedActivity extends AppCompatActivity {
//    private  TextView mTextView;
    private ListView mListView;
    private String[] fsearches = new String[]{
            "perpyotieno","ArnoldOduma","EdithKhalivinzwa","AliceMawia", "NellyWanjiru","ShiroLeah","JoyWambui"};
    private String[] fnumberOfRepositories = new String[]{
            "Number of Repositories: 10","Number of Repositories: 30","Number of Repositories: 1","Number of Repositories: 34", "Number of Repositories: 70","Number of Repositories: 5","Number of Repositories: 1"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequently_searched);

        mListView = (ListView) findViewById(R.id.listView);
        MyFrequentlySearchedArrayAdapter adapter = new MyFrequentlySearchedArrayAdapter(this, android.R.layout.simple_list_item_1, fsearches, fnumberOfRepositories);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fsearch = ((TextView)view).getText().toString();
                Toast.makeText(FrequentlySearchedActivity.this, fsearch, Toast.LENGTH_LONG).show();
            }
        });
    }
}
