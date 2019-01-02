package com.jaago.jaago;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Sources extends AppCompatActivity {
    private  String JSON_URL ="https://newsapi.org/v2/sources?language=en&apiKey=eb524ac737c44081923e4bd0366af2b8";
    ListView listView;
    List<Source> source;
    private ArrayList<String> srcName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        listView = (ListView) findViewById(R.id.listview);
        source= new ArrayList<>();
        loadSrcList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Sources.this,MainActivity.class);
                intent.putExtra("Source",srcName.get(i));
                startActivity(intent);
            }
        });
    }

    private void loadSrcList() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray newsArray = obj.getJSONArray("sources");
                            for (int i = 0; i < newsArray.length(); i++) {
                                JSONObject newsObject = newsArray.getJSONObject(i);
                                String cat=newsObject.getString("category");
                                char a=cat.charAt(0);
                                cat=cat.replace(cat.charAt(0),Character.toUpperCase(a));
                                Source src=new Source(newsObject.getString("name"),newsObject.getString("country").toUpperCase(),cat,newsObject.getString("language").toUpperCase(),newsObject.getString("url"));
                                //urlNo.add(newsObject.getString("url"));
                                srcName.add(newsObject.getString("name"));
                                source.add(src);
                            }
                            SrcViewAdapter adapter = new SrcViewAdapter(source, getApplicationContext());
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
