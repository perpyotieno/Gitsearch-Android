package com.example.mygitsearchdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {
    private Button mSearchReposButton;
    private EditText mUsernameEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUsernameEditText = view.findViewById(R.id.edit_username);

        view.findViewById(R.id.search_repos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searched = mUsernameEditText.getText().toString();

                Intent intent = new Intent(getActivity(), FrequentlySearchedActivity.class);
                intent.putExtra("searched",searched);
                startActivity(intent);
            }
        });
    }
}
