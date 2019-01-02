package com.jaago.jaago;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class SQLiteDatabaseHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Sources";
    private static final String TABLE_NAME = "src";
    private static final String KEY_ID = "id";
    private static final String Name="name";
    private static final String Country="country";
    private static final String Category="category";
    private static final String Language="language";
    private static final String Srcurl="url";

    private static final String[] COLUMNS = { Name, Country, Category,Language,Srcurl};
    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE src ( "
                + "name TEXT, "
                + "country TEXT, " + "category TEXT , " + "language TEXT , " + "url TEXT)";

        db.execSQL(CREATION_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public void deleteOne(Source source) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name = ?", new String[] { String.valueOf(source.getName()) });
        db.close();
    }
    public List<Source> allPlayers() {

        List<Source> players = new LinkedList<Source>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Source source = null;

        if (cursor.moveToFirst()) {
            do {
                source = new Source();
                source.setName(cursor.getString(0));
                source.setCountry(cursor.getString(1));
                source.setCategory(cursor.getString(2));
                source.setLanguage(cursor.getString(3));
                source.setSrcurl(cursor.getString(4));
                players.add(source);
            } while (cursor.moveToNext());
        }

        return players;
    }

    public void addSource(Source source) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name, source.getName());
        values.put(Country, source.getCategory());
        values.put(Category, source.getCategory());
        values.put(Language, source.getLanguage());
        values.put(Srcurl, source.getSrcurl());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

}
