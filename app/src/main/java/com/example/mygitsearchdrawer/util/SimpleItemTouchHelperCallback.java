package com.example.mygitsearchdrawer.util;

import androidx.recyclerview.widget.ItemTouchHelper;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback{
    private final ItemTouchHelperAdapter mAdapter;
    //  This constructor takes an ItemTouchHelperAdapter parameter. When implemented in
    //  FirebaseRestaurantListAdapter, the ItemTouchHelperAdapter instance will pass the gesture event back to the
    //  Firebase adapter where we will define what occurs when an item is moved or dismissed.

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter){
        mAdapter = adapter;
    }
    //  The method below informs the ItemTouchHelperAdapter that drag gestures are enabled.
    //  We could also disable drag gestures by returning 'false'.

    @Override
    public boolean isLongPressDragEnabled(){
        return true;
    }



}
