package com.example.mygitsearchdrawer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mygitsearchdrawer.controller.RepositoriesDetail;
import com.example.mygitsearchdrawer.model.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items = new ArrayList<>();
    private Context context;
    private  int i;
    public static final String TAG = "ItemAdapter";

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int i) {
        holder.bindItem(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cover) ImageView cover;
        @BindView(R.id.titleTxtView) TextView title;


        public void bindItem(Item item){
            Picasso.get().load(item.getAvatarUrl()).into(cover);
            title.setText(item.getLogin());
        }

        private Context mContext;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            mContext = view.getContext();
            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    Intent intent = new Intent(context, RepositoriesDetail.class);
                    intent.putExtra("i", i);
                    intent.putExtra("mItems", Parcels.wrap(items));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

//                    if (pos != RecyclerView.NO_POSITION) {
//                        Item clickedDataItem = items.get(pos);
//                        Intent intent = new Intent(context, RepositoriesDetail.class);
//                        intent.putExtra("login", items.get(pos).getLogin());
//                        intent.putExtra("html_url", items.get(pos).getHtmlUrl());
//                        intent.putExtra("avatar_url", items.get(pos).getAvatarUrl());
//                        intent.putExtra("followers", items.get(pos).getFollowers());
//                        intent.putExtra("following", items.get(pos).getFollowing());
//                        intent.putExtra("repositories", items.get(pos).getRepositories());
//
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getLogin(), Toast.LENGTH_LONG).show();
//                    }
                }

            });
        }
    }
}







