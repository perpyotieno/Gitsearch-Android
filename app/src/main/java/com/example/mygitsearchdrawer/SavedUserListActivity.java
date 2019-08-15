package com.example.mygitsearchdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mygitsearchdrawer.adapters.FirebaseGithubListAdapter;
import com.example.mygitsearchdrawer.model.Item;
import com.example.mygitsearchdrawer.util.OnStartDragListener;
import com.example.mygitsearchdrawer.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedUserListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mGithubReference;
    private FirebaseGithubListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
  //  private FirebaseRecyclerAdapter<Item, FirebaseGithubViewHolder> mFirebaseAdapter;


   // private DragStartHelper.OnDragStartListener mOnStartDragListener;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repositories);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();

    }
    private void setUpFirebaseAdapter() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_GITHUB_USER)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);


//        mGithubReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_GITHUB_USER)
//                .child(uid);

        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(query, Item.class)
                        .build();

        mFirebaseAdapter = new FirebaseGithubListAdapter(options,
                query, this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

//                @Override
//                protected void onBindViewHolder(@NonNull FirebaseGithubViewHolder firebaseGithubViewHolder, int position, @NonNull Item item) {
//                    firebaseGithubViewHolder.bindGithub(item);
//                }
//
//            @NonNull
//            @Override
//            public FirebaseGithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repositories_list_item_drag, parent, false);
//                return new FirebaseGithubViewHolder(view);
//            }
//        };
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//    }

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
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.stopListening();
    }


}

