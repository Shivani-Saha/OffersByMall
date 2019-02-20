package com.app.adarsh.offersbymall;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MallsAdapter extends BaseAdapter {
    private ArrayList<Malls> mallList;
    private Context context;
    String[] newdelhiNames;
    String[] gaziabadNames;
    String[] gurgaonNames;
    String[] noidaNames;
    String[] faridabadNames;
    String[] meerutNames;

    private Bundle mBundle = new Bundle();

    MallsAdapter(Context context) {
        this.context = context;
        mallList = new ArrayList<Malls>();
        Resources resources = context.getResources();

     newdelhiNames = resources.getStringArray(R.array.newdelhi_malls);
      gaziabadNames = resources.getStringArray(R.array.ghaziabad_malls);
       gurgaonNames = resources.getStringArray(R.array.gurgaon_mall);
      noidaNames = resources.getStringArray(R.array.noida_malls);
     faridabadNames = resources.getStringArray(R.array.faridabad_malls);
       meerutNames = resources.getStringArray(R.array.meerut_malls);


        String[] tempMallNames = resources.getStringArray(R.array.Malls_name);
        int[] mallImages = {R.drawable.newdelhimall, R.drawable.gaziabad, R.drawable.gurgaonmall, R.drawable.noidamall
                , R.drawable.faridabadmall, R.drawable.meerutmall};
        for (int i = 0; i <6 ; i++) {
            Malls malls = new Malls(mallImages[i], tempMallNames[i]);
            mallList.add(malls);
        }
    }

    @Override
    public int getCount() {
        return mallList.size();
    }

    @Override
    public Object getItem(int position) {
        return mallList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView MallView;
        TextView textView;

        ViewHolder(View v) {
            MallView = (ImageView) v.findViewById(R.id.imageview);
            textView = (TextView) v.findViewById(R.id.textview);
        }

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = null;
        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_mall, parent, false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }
        final Malls tempMalls = mallList.get(position);
        Picasso.with(context).load(tempMalls.imageId).centerCrop().resize(360, 400).into(viewHolder.MallView);
        viewHolder.textView.setText(tempMalls.MallName);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    mBundle.putStringArray("allahabad", newdelhiNames);
                    Intent intent = new Intent(context, DifferentMallsActivity.class);
                    intent.putExtras(mBundle);
                    intent.putExtra("names", tempMalls.MallName);
                    context.startActivity(intent);
                }
                if (position == 1) {
                    mBundle.putStringArray("allahabad", gaziabadNames);
                    Intent intent = new Intent(context, DifferentMallsActivity.class);
                    intent.putExtras(mBundle);
                    intent.putExtra("names", tempMalls.MallName);
                    context.startActivity(intent);
                }
                if (position == 2) {
                    mBundle.putStringArray("allahabad",gurgaonNames);
                    Intent intent = new Intent(context, DifferentMallsActivity.class);
                    intent.putExtras(mBundle);
                    intent.putExtra("names", tempMalls.MallName);
                    context.startActivity(intent);
                }
                if (position == 3) {

                    mBundle.putStringArray("allahabad", noidaNames);
                    Intent intent = new Intent(context, DifferentMallsActivity.class);
                    intent.putExtras(mBundle);
                    intent.putExtra("names", tempMalls.MallName);
                    context.startActivity(intent);
                }
                if (position == 4) {
                    mBundle.putStringArray("allahabad",faridabadNames);
                    Intent intent = new Intent(context, DifferentMallsActivity.class);
                    intent.putExtras(mBundle);
                    intent.putExtra("names", tempMalls.MallName);
                    context.startActivity(intent);
                }
                if (position == 5) {
                    mBundle.putStringArray("allahabad",meerutNames);
                    Intent intent = new Intent(context, DifferentMallsActivity.class);
                    intent.putExtras(mBundle);
                    intent.putExtra("names", tempMalls.MallName);
                    context.startActivity(intent);
                }


            }
        });
        return row;
    }
}
