package com.app.adarsh.offersbymall;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cityMallAdapter extends BaseAdapter {
    private ArrayList<Malls> mallList;
    private Context context;

    cityMallAdapter(Context context) {
        this.context = context;
        mallList = new ArrayList<Malls>();
        Resources resources = context.getResources();
        String[] tempMallNames = resources.getStringArray(R.array.cityMall_name);
        int[] mallImages = {R.drawable.delhi_ambience, R.drawable.delhi_ansalplaza, R.drawable.delhi_crossriver
                , R.drawable.delhi_dlfemporio, R.drawable.delhi_pacific, R.drawable.delhi_selectcitywalk};
        for (int i = 0; i < 6; i++) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        Picasso.with(context).load(tempMalls.imageId).centerCrop().resize(350, 350).into(viewHolder.MallView);
        viewHolder.textView.setText(tempMalls.MallName);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategorySelectionActiivity.class);
                intent.putExtra("mallname", tempMalls.MallName);
                Log.d("CodeKamp", tempMalls.MallName);
                context.startActivity(intent);
            }
        });
        return row;
    }
}
