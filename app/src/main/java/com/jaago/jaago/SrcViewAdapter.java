package com.jaago.jaago;

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

public class SrcViewAdapter extends ArrayAdapter<Source> {
    private List<Source> srcList;
    private Context mCtx;
    public SrcViewAdapter( List<Source> srcList,Context mCtx) {
        super(mCtx, R.layout.item_source,srcList);
        this.srcList=srcList;
        this.mCtx=mCtx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View listViewItem = inflater.inflate(R.layout.item_source, null, true);
        TextView nameTextView= (TextView) listViewItem.findViewById(R.id.srcnameview);
        TextView catTextView= (TextView) listViewItem.findViewById(R.id.srccatview);
        TextView lngTextView= (TextView) listViewItem.findViewById(R.id.srclngview);
        TextView cnTextView= (TextView) listViewItem.findViewById(R.id.srccntview);
        //ImageView imageView= listViewItem.findViewById(R.id.imageview);
        Source source = srcList.get(position);
        nameTextView.setText(source.getName());
        catTextView.setText(source.getCategory());
        lngTextView.setText(source.getLanguage());
        cnTextView.setText(source.getCountry());
        //imageView.setIm(news.getTitle());

        /*boolean isPhoto = source.getImgurl() != null;
        if (isPhoto) {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(imageView.getContext())
                    .load(source.getImgurl())
                    .into(imageView);
        }*/
        return listViewItem;
    }
}
