package com.app.adarsh.offersbymall;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;

public class contentViewHolder extends RecyclerView.ViewHolder {
    public TextView mShopName, moffer;
    public ImageView imageView;
     Button button;

    public contentViewHolder(View itemView) {
        super(itemView);
        Log.d("CodeKamp", "viewholder started");
        moffer = (TextView) itemView.findViewById(R.id.offerid);
        mShopName = (TextView) itemView.findViewById(R.id.fetchedShopName);
        imageView = (ImageView) itemView.findViewById(R.id.fetchedImage);


        Log.d("CodeKamp", "ViewHolde end");
    }
}
