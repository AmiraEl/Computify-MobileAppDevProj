package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsListView = findViewById(R.id.listViewItems);
        itemsListView.setOnItemClickListener(this);
        UpdateDisplay();
    }

    private void UpdateDisplay() {

        // get the items for the feed
//        ArrayList<RSSItem> items = feed.getAllItems();

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data =
                new ArrayList<>();
        //TODO:
//        for (Computers item : ) {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("name", "");
//            data.add(map);
//        }
        //
        //
        // create the resource, from, and to variables
        int resource = R.layout.activity_item;
        String[] from = {"name"};
        int[] to = {R.id.textViewNamein, R.id.textViewPrice};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}