package com.example.medve.flagsquizapp.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medve.flagsquizapp.Model.RankingFamousPlaces;
import com.example.medve.flagsquizapp.Model.RankingFlags;
import com.example.medve.flagsquizapp.R;

import java.util.List;


    public class CustomAdapterFamousPlaces extends BaseAdapter {

    private Context context;
    private List<RankingFamousPlaces> lstRaniking;

    public CustomAdapterFamousPlaces(Context context, List<RankingFamousPlaces> lstRaniking) {
        this.context = context;
        this.lstRaniking = lstRaniking;
    }

    @Override
    public int getCount() {
        return lstRaniking.size();
    }

    @Override
    public Object getItem(int position) {
        return lstRaniking.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row, null);

        ImageView imgTop = (ImageView) view.findViewById(R.id.imgTop);
        TextView txtTop = (TextView) view.findViewById(R.id.txtTop);

        if (position == 0)//top1
            imgTop.setImageResource(R.drawable.top1);
        else if (position == 1)//top2
            imgTop.setImageResource(R.drawable.top2);
        else //top3
            imgTop.setImageResource(R.drawable.top3);

        txtTop.setText(String.format("%.1f",lstRaniking.get(position).getScore()));
        return view;
    }
}
