package com.example.nani.jagoo;

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
 import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Healthnews> {
    private List<Healthnews> newsList;
    private Context mCtx;
    public ListViewAdapter( List<Healthnews> newsList,Context mCtx) {
        super(mCtx, R.layout.news_helalth,newsList);
        this.newsList=newsList;
        this.mCtx=mCtx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View listViewItem = inflater.inflate(R.layout.news_helalth, null, true);
        TextView titleTextView= listViewItem.findViewById(R.id.titletextview);
        TextView descTextView= listViewItem.findViewById(R.id.desctextview);
        TextView atTextView= listViewItem.findViewById(R.id.attextview);
        TextView byTextView= listViewItem.findViewById(R.id.bytextview);
        ImageView imageView= listViewItem.findViewById(R.id.imageview);
        Healthnews news = newsList.get(position);
        titleTextView.setText(news.getTitle());
        descTextView.setText(news.getDesc());
        atTextView.setText(news.getPubAt());
        byTextView.setText(news.getPubBy());
        //imageView.setIm(news.getTitle());

        boolean isPhoto = news.getImgurl() != null;
        if (isPhoto) {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(imageView.getContext())
                    .load(news.getImgurl())
                    .into(imageView);
        }
        return listViewItem;
    }
}
