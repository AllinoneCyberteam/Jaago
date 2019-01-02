package com.jaago.jaago;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SavedNews extends AppCompatActivity {
    List<News> newsList =new ArrayList<>();
    ArrayList<String> arrayList=new ArrayList<>();
    ListView listView;
    SQLiteDatabase mDatabase;
    private static final String DATABASE_NAME = "News.db";
    private SQLNews db;
    SavedViewAdapter adapter;
    SharedPref sharedPref;
    private int th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref=new SharedPref(this);
        if (sharedPref.loadNightModeState()==true) {

            th=0;
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
            th=1;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_news);
        try {
            mDatabase = openOrCreateDatabase(SavedNews.DATABASE_NAME, MODE_PRIVATE, null);
            db = new SQLNews(this);
            listView = findViewById(R.id.list);
            showSaved();
        }
        catch (Exception e){
            TextView textView=findViewById(R.id.nothing);
            textView.setVisibility(View.VISIBLE);
            textView.setText(e.toString());
        }
    }

    public void showSaved() {
        newsList=new ArrayList<>();
        arrayList.clear();
        adapter=new SavedViewAdapter(newsList,this,arrayList,th);
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT rowid,* FROM saved", null);
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                newsList.add(new News(
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getString(4),
                        cursorEmployees.getString(5),
                        cursorEmployees.getString(6)
                ));
                arrayList.add(cursorEmployees.getString(0));
            } while (cursorEmployees.moveToNext());
        }
        //closing the cursor
        cursorEmployees.close();

        //creating the adapter object
        adapter = new SavedViewAdapter(newsList,this,arrayList,th);

        //adding the adapter to listview
        listView.setAdapter(adapter);
    }
}
