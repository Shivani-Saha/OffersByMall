package com.app.adarsh.offersbymall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.adarsh.offersbymall.R;

import com.example.adarsh.offersbymall.R;
import com.firebase.client.Firebase;

import java.util.List;

import static com.example.adarsh.offersbymall.R.drawable.shop;
import static com.google.android.gms.analytics.internal.zzy.e;

public class MyOfferRecyclerViewAdapter extends RecyclerView.Adapter<MyOfferRecyclerViewAdapter.CustomViewHolder> {

    private List<OfferDetails> feedItemList;
    private Context mContext;

    public MyOfferRecyclerViewAdapter(Context context, List<OfferDetails> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_layout, viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        OfferDetails feedItem = feedItemList.get(i);

        customViewHolder.shopname.setText(feedItem.getShopName());

        View view = LayoutInflater.from(mContext).inflate(R.layout.offer,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.img);
        if(feedItem.getOffer_image().equals("")) {
            imageView.setImageResource(R.mipmap.homelifestyle);
        }else{
            try {
                byte[] imageAsBytes1 = Base64.decode(feedItem.getOffer_image().getBytes(), Base64.DEFAULT);
                imageView.setImageBitmap(
                        BitmapFactory.decodeByteArray(imageAsBytes1, 0, imageAsBytes1.length)
                );
            }catch (Exception e){
                imageView.setImageResource(R.mipmap.homelifestyle);
            }
        }
        TextView offer=(TextView)view.findViewById(R.id.offerName);
        offer.setText(feedItem.getOffer());

        View view1 = LayoutInflater.from(mContext).inflate(R.layout.offer, null);
        ImageView imageView1=(ImageView)view1.findViewById(R.id.img);

        if(feedItem.getOffer2_image().equals("")){
            imageView1.setImageResource(R.mipmap.homelifestyle);
        }else {
            try{
                byte[] imageAsBytes2 = Base64.decode(feedItem.getOffer2_image().getBytes(), Base64.DEFAULT);
                imageView1.setImageBitmap(
                        BitmapFactory.decodeByteArray(imageAsBytes2, 0, imageAsBytes2.length)
                );
            }catch (Exception e){
                imageView1.setImageResource(R.mipmap.homelifestyle);
            }
        }

        TextView offer1=(TextView)view1.findViewById(R.id.offerName);
        offer1.setText(feedItem.getOffer2());

        View view2 = LayoutInflater.from(mContext).inflate(R.layout.offer, null);
        ImageView imageView2=(ImageView)view2.findViewById(R.id.img);

        if(feedItem.getOffer3_image().equals("")){
            imageView2.setImageResource(R.mipmap.homelifestyle);
        }else {
            try {
                byte[] imageAsBytes3 = Base64.decode(feedItem.getOffer3_image().getBytes(), Base64.DEFAULT);
                imageView2.setImageBitmap(
                        BitmapFactory.decodeByteArray(imageAsBytes3, 0, imageAsBytes3.length)
                );
            }catch (Exception e){
            imageView2.setImageResource(R.mipmap.homelifestyle);
        }
        }

        TextView offer2=(TextView)view2.findViewById(R.id.offerName);
        offer2.setText(feedItem.getOffer3());

        customViewHolder.ll.removeAllViews();
        customViewHolder.ll.addView(view);
        customViewHolder.ll.addView(view1);
        customViewHolder.ll.addView(view2);

    }



    @Override
    public int getItemCount()
    {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout ll;
        protected TextView shopname;
        public CustomViewHolder(View view) {
            super(view);
            this.ll=(LinearLayout) view.findViewById(R.id.ll);
            this.shopname= (TextView) view.findViewById(R.id.mShopName);
        }
    }
}
