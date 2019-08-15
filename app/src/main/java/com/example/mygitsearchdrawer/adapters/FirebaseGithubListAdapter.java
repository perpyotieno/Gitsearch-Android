package com.example.mygitsearchdrawer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.DragStartHelper;

import com.example.mygitsearchdrawer.FirebaseGithubViewHolder;
import com.example.mygitsearchdrawer.R;
import com.example.mygitsearchdrawer.controller.RepositoriesDetailActivity;
import com.example.mygitsearchdrawer.model.Item;
import com.example.mygitsearchdrawer.util.ItemTouchHelperAdapter;
import com.example.mygitsearchdrawer.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirebaseGithubListAdapter extends FirebaseRecyclerAdapter<Item, FirebaseGithubViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Item> mItems = new ArrayList<>();



    public FirebaseGithubListAdapter(FirebaseRecyclerOptions<Item> options, Query ref,
                                     OnStartDragListener onStartDragListener,
                                     Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;


        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mItems.add(dataSnapshot.getValue(Item.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseGithubViewHolder firebaseGithubViewHolder, int position, @NonNull Item item) {
        firebaseGithubViewHolder.bindGithub(item);
        firebaseGithubViewHolder.mGithubImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(firebaseGithubViewHolder);
                }
                return false;
            }
        });


        firebaseGithubViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RepositoriesDetailActivity.class);
                intent.putExtra("position", firebaseGithubViewHolder.getAdapterPosition());
                intent.putExtra("items", Parcels.wrap(mItems));
                mContext.startActivity(intent);
            }
        });

    }


    @NonNull
    @Override
    public FirebaseGithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repositories_list_item_drag, parent, false);
        return new FirebaseGithubViewHolder(view);
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);

        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    @Override
    public void stopListening() {
        super.stopListening(); mRef.removeEventListener(mChildEventListener); }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        getRef(position).removeValue();

    }
    private void setIndexInFirebase() {
        for (Item item : mItems) {
            int index = mItems.indexOf(item);
            DatabaseReference ref = getRef(index);
            item.setIndex(Integer.toString(index));
            ref.setValue(item);
        }
    }


}
