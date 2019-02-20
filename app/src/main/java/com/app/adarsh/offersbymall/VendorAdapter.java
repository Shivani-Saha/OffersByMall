package com.app.adarsh.offersbymall;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adarsh.offersbymall.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.media.CamcorderProfile.get;
import static com.google.android.gms.analytics.internal.zzy.a;
import static com.google.android.gms.analytics.internal.zzy.e;
import static com.google.android.gms.analytics.internal.zzy.i;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.MyViewHolder>{

    private List<VendorInfo> array;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, shopName, shopNum,contact,mallName;
        public Button btn;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.ownerName);
            shopName = (TextView) view.findViewById(R.id.shopName);
            shopNum = (TextView) view.findViewById(R.id.shopNo);
            contact = (TextView) view.findViewById(R.id.contact);
            mallName = (TextView) view.findViewById(R.id.mallName);
            btn = (Button) view.findViewById(R.id.btn);

        }
    }


    public VendorAdapter(List<VendorInfo> arr,Context mContext) {
        this.array = arr;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vendor_list, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        VendorInfo movie = array.get(position);
        holder.name.setText(movie.getOwner());
        holder.shopName.setText(movie.getShop_name());
        holder.shopNum.setText(movie.getShop_no());
        holder.mallName.setText(movie.getMall_name());
        holder.contact.setText(movie.getContact());
        final MyViewHolder h=holder;

        final String id=movie.getId();
        final int pos=position;

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VendorActivity va=(VendorActivity)mContext;
                va.getApproval(id,pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    void removeEntry(int pos){
        array.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeRemoved(pos,array.size());
        if(array.size()==0){
            VendorActivity v=(VendorActivity)mContext;
            v.emptyList();
        }
    }

}
