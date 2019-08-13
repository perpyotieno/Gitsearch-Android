package com.example.mygitsearchdrawer.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback{
    private final ItemTouchHelperAdapter mAdapter;
    //  This constructor takes an ItemTouchHelperAdapter parameter. When implemented in
    //  FirebaseRestaurantListAdapter, the ItemTouchHelperAdapter instance will pass the gesture event back to the
    //  Firebase adapter where we will define what occurs when an item is moved or dismissed.

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter){
        mAdapter = adapter;
    }
    @Override
    public boolean isLongPressDragEnabled(){
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int dragFlags =ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        if(source.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }
}
