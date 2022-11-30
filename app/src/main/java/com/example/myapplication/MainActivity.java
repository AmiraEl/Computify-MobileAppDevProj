package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView itemsListView;
    static FirebaseFirestore db;
    private ArrayList<Computers> ItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsListView = findViewById(R.id.listViewItems);
        itemsListView.setOnItemClickListener(this);
        db = FirebaseFirestore.getInstance();
        UpdateDisplay();
    }

    private void UpdateDisplay() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        Log.d("TAG", "here");

        db.collection("computers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        data.add((HashMap<String, Object>) document.getData());
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        ItemsList.add(document.toObject(Computers.class));

                    }

                    for(Computers comp : ItemsList){
                        Log.d("TAG","here");
                        HashMap<String,String> map = new HashMap<String, String>();
                        map.put("name", comp.getName());
                        map.put("price", comp.getPrice());
                        data.add(map);
                    }
//        // create the resource, from, and to variables
                    int resource = R.layout.list_item;
                    String[] from = {"name", "price"};
                    int[] to = {R.id.textViewNamein, R.id.textViewPrice};
//        String[] from = {"powersupply", "sellerID", "ssd", "price", "motherboard", "name", "pcase", "cpu", "hdd", "gpu", "ram"};

                    // create and set the adapter
                    SimpleAdapter adapter =
                            new SimpleAdapter(getApplicationContext(), data, resource, from, to);
                    itemsListView.setAdapter(adapter);
                }
            }
        });
        // create a List of Map<String, ?> objects



    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent =
    }
}