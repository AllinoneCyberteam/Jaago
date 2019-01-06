package com.example.dell.sports;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
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

import static android.media.CamcorderProfile.get;

    public class sports extends AppCompatActivity {
    private ArrayList<String> urlNo =new ArrayList<>();
    private WebView webView;
    private String JSON_URL ="https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=0a75fe2756384846ac66f29d4fe1385e";
    ListView listview;
    List<sport> sportslist;
     String coun="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        /* webView =(WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);*/
         /*webView.setWebViewClient(new webViewClient(){
            @Override
                    public boolean shouldOverrideUrlLoading(webView view,String url) {
                view.loadUrl(url);
                return true;
            }
         });*/
    String[] arraySpinner = new String[] {
            "Countries","United Arab Emirates (UAE)","Argentina","Austria","Australia","India"
    };
    final Spinner s = (Spinner) findViewById(R.id.countries);
    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    s.setAdapter(adapter);
    s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
            if(i==1)
                coun="ae";
            else if(i==2)
                coun="ar";
            else if(i==3)
                coun="at";
            else if(i==4)
                coun="au";
            else
                coun="in";
            JSON_URL ="https://newsapi.org/v2/top-headlines?sources=bbc-sport?country="+coun+"&apiKey=0a75fe2756384846ac66f29d4fe1385e";
            sportslist=new ArrayList<>();
            urlNo.clear();
            ListViewAdapter adapter =new ListViewAdapter(sportslist, getApplicationContext());
            listview.setAdapter((ListAdapter) adapter);
            loadsportslist();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
                    }


    });
    listview =(ListView) findViewById(R.id.listview);
    sportslist = new ArrayList<>();
    loadsportslist();
    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
            Uri uri = Uri.parse(urlNo.get(i));
        Intent intent = new Intent(sports.this,DescSports.class);
        intent.putExtra("url",urlNo.get(i));
        startActivity(intent);
        //webView.loadUrl(String.valueOf(uri));
        }
    });
        }
        private void loadsportslist() {
        final ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,JSON_URL,new Response.Listener<String>()
        {
            @Override
                    public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try{
                    JSONObject obj= new JSONObject(response);
                    JSONArray sportsArray=obj.getJSONArray("articles");
                    for(int i=0;i<sportsArray.length();i++){
                        JSONObject sportsObject=sportsArray .getJSONObject(i);
                    sport sport = new sport(sportsObject.getString("title"),sportsObject.getString("urlToImage"),sportsObject.getString("description"),
                    sportsObject.getString("publishedAt"),"sushmithavk","");
                    urlNo.add(sportsObject.getString("url"));
                    sportslist.add(sport);
                    }
                    ListViewAdapter adapter =new ListViewAdapter(sportslist,getApplicationContext());
                    listview.setAdapter((ListAdapter) adapter);
                    }catch (JSONException e)
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
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                       });
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    }