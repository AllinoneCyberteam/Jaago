package com.example.dell.sports;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<sport> {
    private List<sport> sportsList;
    private Context mCtx;
    public ListViewAdapter( List<sport> sportsList,Context mCtx) {
        super(mCtx, R.layout.item_sports,sportsList);
        this.sportsList=sportsList;
        this.mCtx=mCtx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View listViewItem = inflater.inflate(R.layout.item_sports, null, true);
        TextView titleTextView= listViewItem.findViewById(R.id.titletextview);
        TextView descTextView= listViewItem.findViewById(R.id.desctextview);
        TextView atTextView= listViewItem.findViewById(R.id.attextview);
        TextView byTextView= listViewItem.findViewById(R.id.bytextview);
        ImageView imageView= listViewItem.findViewById(R.id.imageview);
        sport sport = sportsList.get(position);
        titleTextView.setText(sport.getTitle());
        descTextView.setText(sport.getDesc());
        atTextView.setText(sport.getPubAt());
        byTextView.setText(sport.getPubBy());
        //imageView.setIm(news.getTitle());

        boolean isPhoto = sport.getUrlToImage() != null;
        if (isPhoto) {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(imageView.getContext())
                    .load(sport.getUrlToImage())
                    .into(imageView);
        }
        return listViewItem;
    }
}
