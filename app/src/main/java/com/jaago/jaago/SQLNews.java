package com.jaago.jaago;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class SQLNews extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "News.db";
    private static final String TABLE_NAME = "saved";
    private static final String KEY_ID = "id";
    private static final   String Title= "title";
    private static final  String Imgurl= "img";
    private static final String Desc= "descr";
    private static final  String PubAt= "pubat";
    private static final  String PubBy= "pubby";
    private static final String Newsurl= "url";
    private static final String ROW_ID= "rowid,*";


    private static final String[] COLUMNS = { Title, Imgurl, Desc,PubAt,PubBy,Newsurl};
    public SQLNews(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE IF NOT EXISTS saved ( "
                + "title TEXT, "
                + "img TEXT, " + "descr TEXT , " + "pubat TEXT , " + "pubby TEXT,"+"url TEXT)";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public List<News> allNews() {

        List<News> news = new LinkedList<News>();
        String query = "SELECT "+ROW_ID+" FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        News news1 = null;

        if (cursor.moveToFirst()) {
            do {
                news1 = new News();
                news1.setTitle(cursor.getString(1));
                news1.setImgurl(cursor.getString(2));
                news1.setDesc(cursor.getString(3));
                news1.setPubAt(cursor.getString(4));
                news1.setPubBy(cursor.getString(5));
                news1.setNewsurl(cursor.getString(6));
                news1.setNewsid(cursor.getString(0));
                news.add(news1);
            } while (cursor.moveToNext());
        }

        return news;
    }
    public void addNews(News news) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Title, news.getTitle());
        values.put(Imgurl, news.getImgurl());
        values.put(Desc, news.getDesc());
        values.put(PubAt, news.getPubAt());
        values.put(PubBy, news.getPubBy());
        values.put(Newsurl, news.getNewsurl());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }
    public void  deleteNews(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"rowid=?",new String[]{id});
        db.close();
    }
}
