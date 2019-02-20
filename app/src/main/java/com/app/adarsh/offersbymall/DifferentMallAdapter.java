package com.app.adarsh.offersbymall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class DifferentMallAdapter extends BaseAdapter {
    private ArrayList<Malls> mallList;
    private Context context;

    DifferentMallAdapter(Context context, String[] citymallList) {
        this.context = context;
        mallList = new ArrayList<Malls>();
        //Resources resources=context.getResources();
        String[] tempMallNames = citymallList;
        SharedPreferences bb = context.getSharedPreferences("mymall", 0);
        String name = bb.getString("names", "");
        if(name.equals("NEW DELHI"))
        {
            int[] mallImages = {R.drawable.ansaldelhi, R.drawable.dlfemporio, R.drawable.metrowalk
                    , R.drawable.selectcitywalk, R.drawable.tdi, R.drawable.v3s,R.drawable.ambience,
                    R.drawable.pacific, R.mipmap.westgatemall, R.mipmap.momentsmall, R.mipmap.starcitymall, R.drawable.citysquare};
            for (int i = 0; i < 12; i++) {
                Malls malls = new Malls(mallImages[i], tempMallNames[i]);
                mallList.add(malls);
            }

        }
        if(name.equals("GHAZIABAD"))
        {
            int[] mallImages = {R.drawable.shopprix, R.drawable.silvercity, R.drawable.mahagunmetro
                    , R.drawable.pacific, R.drawable.shipra, R.drawable.gaurcentral, R.mipmap.opulentmall,
                    R.mipmap.galaxiemall, R.mipmap.eastdelhimall, R.mipmap.markmall,R.drawable.parsvnath, R.mipmap.redmall};
            for (int i = 0; i < 12; i++) {
                Malls malls = new Malls(mallImages[i], tempMallNames[i]);
                mallList.add(malls);
            }

        }
        if(name.equals("GURGAON"))
        {
            int[] mallImages = {R.drawable.ambience, R.drawable.sahara, R.drawable.citycentre
                    , R.drawable.mgf, R.drawable.mega, R.drawable.central, R.mipmap.grandmall,
                    R.mipmap.jmdregentplaza, R.drawable.central,R.mipmap.vipulagora, R.mipmap.plazamall, R.mipmap.rahejamall};
            for (int i = 0; i < 12; i++) {
                Malls malls = new Malls(mallImages[i], tempMallNames[i]);
                mallList.add(malls);
            }

        }
        if(name.equals("NOIDA"))
        {
            int[] mallImages = {R.drawable.gip, R.drawable.dlf, R.drawable.wave
                    , R.drawable.centerstage, R.drawable.supertechshoprix,
                    R.drawable.sab, R.mipmap.spiceworldmall, R.mipmap.centrestagemall,
                    R.mipmap.ansalplaza};
            for (int i = 0; i < 9; i++) {
                Malls malls = new Malls(mallImages[i], tempMallNames[i]);
                mallList.add(malls);
            }

        }
        if(name.equals("FARIDABAD"))
        {
            int[] mallImages = {R.drawable.parsvnath, R.drawable.ef3, R.drawable.crownplaza
                    , R.drawable.eldeco, R.drawable.pristine, R.drawable.srs};
            for (int i = 0; i < 6; i++) {
                Malls malls = new Malls(mallImages[i], tempMallNames[i]);
                mallList.add(malls);
            }

        }
        if(name.equals("MEERUT"))
        {
            int[] mallImages = {R.drawable.pvs, R.drawable.melange, R.drawable.citysquare
                    , R.drawable.paradise, R.drawable.era, R.drawable.shopprixmall__copy};
            for (int i = 0; i < 6; i++) {
                //String s=DifferentMallAdapter.this.context.getResources().getResourceName(mallImages[i]);
                Malls malls = new Malls(mallImages[i], tempMallNames[i]);
                mallList.add(malls);
                //DifferentMallAdapter.this.context.getResources().getIdentifier(s.split(":")[1].split("/")[1],s.split(":")[1].split("/")[0],s.split(":")[0]);
            }

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
