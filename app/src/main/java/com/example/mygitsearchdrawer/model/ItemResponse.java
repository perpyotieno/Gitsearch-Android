package com.example.mygitsearchdrawer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {
    @SerializedName("Items")
    @Expose
    private List<Item> Items;

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        this.Items = items;
    }
}
