package com.jaago.jaago;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SavedViewAdapter extends ArrayAdapter<News> {
    private List<News> newsList;
    private Context mCtx;
    private  ArrayList<String> ids;
    private int th;
    public SavedViewAdapter( List<News> newsList,Context mCtx,ArrayList<String> ids,int th) {
        super(mCtx, R.layout.item_saved_news,newsList);
        this.newsList=newsList;
        this.mCtx=mCtx;
        this.ids=ids;
        this.th=th;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View listViewItem = inflater.inflate(R.layout.item_saved_news, null, true);
        TextView titleTextView= (TextView) listViewItem.findViewById(R.id.titletextview);
        TextView descTextView= (TextView) listViewItem.findViewById(R.id.desctextview);
        TextView atTextView= (TextView) listViewItem.findViewById(R.id.attextview);
        TextView byTextView= (TextView) listViewItem.findViewById(R.id.bytextview);
        ImageView imageView= (ImageView) listViewItem.findViewById(R.id.imageview);
        ImageButton imageButton= (ImageButton) listViewItem.findViewById(R.id.share);
        RelativeLayout relativeLayout=listViewItem.findViewById(R.id.newsrel);
        if(th==0){
            titleTextView.setTextColor(Color.WHITE);
            descTextView.setTextColor(Color.WHITE);
            atTextView.setTextColor(Color.WHITE);
            byTextView.setTextColor(Color.WHITE);
            titleTextView.setBackgroundColor(Color.GRAY);
            descTextView.setBackgroundColor(Color.GRAY);
            atTextView.setBackgroundColor(Color.GRAY);
            byTextView.setBackgroundColor(Color.GRAY);
            imageView.setBackgroundColor(Color.GRAY);
            relativeLayout.setBackgroundColor(Color.GRAY);
        }
        else
        {
            titleTextView.setTextColor(Color.BLACK);
            descTextView.setTextColor(Color.BLACK);
            atTextView.setTextColor(Color.BLACK);
            byTextView.setTextColor(Color.BLACK);
        }
        final ImageButton imageButton1= (ImageButton) listViewItem.findViewById(R.id.delete);
        final News news = newsList.get(position);
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
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"News");
                i.putExtra(android.content.Intent.EXTRA_TEXT, String.valueOf(news.getNewsurl()));
                getContext().startActivity(Intent.createChooser(i,"Share via"));
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imageButton1.setBackgroundResource(R.drawable.saveb);
                Toast.makeText(getContext(),"Deleted",Toast.LENGTH_LONG).show();
                SQLNews db;
                db=new SQLNews(getContext());
                db.deleteNews(ids.get(position));
                if (mCtx instanceof SavedNews) {
                    ((SavedNews) mCtx).showSaved();
                }


            }
        });
        return listViewItem;
    }
}

