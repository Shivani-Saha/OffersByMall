package com.app.adarsh.offersbymall;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adarsh.offersbymall.R;
import com.firebase.client.Firebase;

import java.util.List;

public class MyRecyclerViewAdaptera extends RecyclerView.Adapter<MyRecyclerViewAdaptera.CustomViewHolder> {

    private List<Vendorlist> feedItemList;
    private Context mContext;
    private  Firebase mRootRef;
    String shop;
    String mall;
    String category;
    MyRecyclerViewAdaptera(String mall)
{
    this.mall=mall;
}

    public MyRecyclerViewAdaptera(Context context, List<Vendorlist> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowa, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        SharedPreferences bb = mContext.getSharedPreferences("my_prefs", 0);
        mall = bb.getString("mall", "");
        SharedPreferences b = mContext.getSharedPreferences("my", 0);
      category= b.getString("category", "");


        Toast.makeText(mContext, mall, Toast.LENGTH_SHORT).show();

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Vendorlist feedItem = feedItemList.get(i);
 shop=feedItem.getShopName();
        //Setting text view title
        customViewHolder.name.setText(Html.fromHtml(feedItem.getName()));
        customViewHolder.offer.setText(Html.fromHtml(feedItem.getOffer()));
        customViewHolder.shopno.setText(Html.fromHtml(feedItem.getShopNo()));
        customViewHolder.shopname.setText(Html.fromHtml(feedItem.getShopName()));
        customViewHolder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootRef = new Firebase("https://offersbymall-93fb1.firebaseio.com/ApprovedVendor");
                mRootRef.child(mall).child(category).setValue(feedItemList);
                Toast.makeText(mContext,"This mall is successfully approved", Toast.LENGTH_SHORT).show();


            }
        });
    }



    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected Button b;
        protected TextView name;
        protected TextView offer;
        protected TextView shopno;
        protected TextView shopname;
        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name);
            this.offer = (TextView) view.findViewById(R.id.offer);
            this.shopname= (TextView) view.findViewById(R.id.shopname);
            this.shopno = (TextView) view.findViewById(R.id.shopno);
            this.b = (Button) view.findViewById(R.id.b);
        }
    }
}
