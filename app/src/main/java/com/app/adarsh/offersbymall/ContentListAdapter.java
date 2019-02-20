package com.app.adarsh.offersbymall;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContentListAdapter extends RecyclerView.Adapter<contentViewHolder> {
    private Context context;
    private ProgressDialog mProgress;
    private String ownerName, shopno, offers;
    // Uri ur= Uri.parse("https://firebasestorage.googleapis.com/v0/b/offersbymall-93fb1.appspot.com/o/Shop+Photo%2F458?alt=media&token=c721f5e4-ec2e-4654-acd4-07f2875bd57c");

    private ArrayList<ContentDetailList> contentDetailLists = new ArrayList<>();
    private StorageReference mStorage = FirebaseStorage.getInstance().getReference();

    public ContentListAdapter(Context context, ArrayList<ContentDetailList> contentDetailLists) {
        Log.d("CodeKamp", "adapter1");
        this.context = context;
        this.contentDetailLists = contentDetailLists;
        Log.d("CodeKamp", "inAdapter:" + contentDetailLists.get(0).uri);
        mProgress = new ProgressDialog(context);
        mProgress.setMessage("Loading Image...");
        mProgress.show();
    }

    @Override
    public contentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.content_info, parent, false);
        Log.d("CodeKamp", "view created");


        return new contentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(contentViewHolder holder, int position) {

        Log.d("CodeKamp", "OnBInd:" + contentDetailLists.get(0).shopName);
        Uri uri = Uri.parse(contentDetailLists.get(position).uri);
        ownerName = contentDetailLists.get(position).ownerName;
        shopno = contentDetailLists.get(position).ShopNo;
        offers = contentDetailLists.get(position).offer;


        holder.mShopName.setText(contentDetailLists.get(position).shopName);
        //  holder.mContact.setText(contentDetailLists.get(position).ShopNo);
        holder.moffer.setText("OFFER : " + offers);
        holder.moffer.setVisibility(View.GONE);
        final TextView textOffer = holder.moffer;

        Picasso.with(context).load(uri).centerCrop().resize(160, 200).into(holder.imageView);
        if (holder.imageView != null)
            mProgress.dismiss();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textOffer.isShown()) {
                    textOffer.setVisibility(View.GONE);
                } else {
                    textOffer.setVisibility(View.VISIBLE);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        Log.d("CodeKamp", "count:" + contentDetailLists.size());
        return contentDetailLists.size();
    }
}
