package com.example.mygitsearchdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.DragStartHelper;

import com.example.mygitsearchdrawer.FirebaseGithubViewHolder;
import com.example.mygitsearchdrawer.R;
import com.example.mygitsearchdrawer.model.Item;
import com.example.mygitsearchdrawer.util.ItemTouchHelperAdapter;
import com.example.mygitsearchdrawer.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class FirebaseGithubListAdapter extends FirebaseRecyclerAdapter<Item, FirebaseGithubViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;



    public FirebaseGithubListAdapter(FirebaseRecyclerOptions<Item> options,
                                     DatabaseReference ref,
                                     OnStartDragListener onStartDragListener,
                                     Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
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
    }

    @NonNull
    @Override
    public FirebaseGithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repositories_list_item_drag, parent, false);
        return new FirebaseGithubViewHolder(view);
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();

    }


}
