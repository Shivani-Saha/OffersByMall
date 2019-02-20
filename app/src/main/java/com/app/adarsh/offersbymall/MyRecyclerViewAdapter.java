package com.app.adarsh.offersbymall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {

    private List<UserDe> feedItemList;
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, List<UserDe> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        UserDe feedItem = feedItemList.get(i);



        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getName()));
        customViewHolder.textView2.setText(Html.fromHtml(feedItem.getCity()));
        customViewHolder.textView3.setText(Html.fromHtml(feedItem.getShopNo()));
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        protected TextView textView2;
        protected TextView textView3;
        public CustomViewHolder(View view) {
            super(view);
            this.textView2 = (TextView) view.findViewById(R.id.textView2);
            this.textView3 = (TextView) view.findViewById(R.id.textView3);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }
}
