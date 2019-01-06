package com.example.nani.jagoo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.ResponseCache;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityhealth extends AppCompatActivity {
    private ArrayList<String> urlno= new ArrayList<>();
    private WebView webView;
    ListView listview;
    List<Healthnews> healthlist;
    SearchView search;
    String coun="";
    private String JSON_URL= "https://newsapi.org/v2/everything?sources=medical-news-today&apiKey=60bef10eeedd46ce8a60b0b99859da23";
    private AdapterView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityhealth);
              /*  webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);*/
        /* webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });*/
    String[] arraySpinner= new String[]{
            "Countries","UAE","Argentina","Austria","Australia","India"
    };
        final Spinner s =(Spinner)findViewById(R.id.spinner);
        final ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i == 1)
                    coun = "ae";
                else if (i == 2)
                    coun = "ar";
                else if (i == 3)
                    coun = "at";
                else if (i == 4)
                    coun = "au";
                else
                    coun = "in";

                JSON_URL = "https://newsapi.org/v2/top-headlines?sources=medical-news-today&apiKey=60bef10eeedd46ce8a60b0b99859da23";
                healthlist=new ArrayList<>();
                urlno.clear();
                ListViewAdapter adapter;
                adapter=new ListViewAdapter(healthlist, getApplicationContext());
                listview.setAdapter(adapter);
                loadhealthlist();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        search=findViewById(R.id.srch);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               JSON_URL="https://newsapi.org/v2/everthing?q="+s+"&apiKey=60bef10eeedd46ce8a60b0b99859da23";
                healthlist= new ArrayList<>();
                urlno.clear();
                ListViewAdapter adapter;
                adapter = new ListViewAdapter(healthlist, getApplicationContext());
                listView.setAdapter(adapter);
                loadhealthlist();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        listview = (ListView) findViewById(R.id.listview);
        healthlist= new ArrayList<>();
        loadhealthlist();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri = Uri.parse(urlno.get(i));
                Intent intent = new Intent(MainActivityhealth.this,Deschealth.class);
                intent.putExtra("url",urlno.get(i));
                startActivity(intent);
                // webView.loadUrl(String.valueOf(uri));


            }
        });

}
    private void loadhealthlist(){

        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.pb);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest= new StringRequest(Request.Method.GET,JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progressBar.setVisibility(View.GONE);
                try
                {
                    JSONObject obj=new JSONObject(response);
                    JSONArray newsArray=obj.getJSONArray("articles");
                    for(int i=0;i<newsArray.length();i++)
                    {
                        JSONObject newsObject=newsArray.getJSONObject(i);
                        Healthnews healthnews=new Healthnews(newsObject.getString("title"),newsObject.getString("urlToImage"),newsObject.getString("description"),newsObject.getString("publishedAt"),"V ANANTH","");
                        urlno.add(newsObject.getString("url"));
                        healthlist.add(healthnews);
                    }
                    ListViewAdapter adapter=new ListViewAdapter(healthlist,getApplicationContext());
                    listview.setAdapter(adapter);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}