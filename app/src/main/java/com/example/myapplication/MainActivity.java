package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, TextView.OnEditorActionListener {
    private ListView itemsListView;
    static FirebaseFirestore db;
    public static ArrayList<Computers> ItemsList = new ArrayList<>();
    public static ArrayList<Computers> SearchList = new ArrayList<>();
    int number;
    private ImageButton searchButton;
    private EditText searchET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchET = findViewById(R.id.editTextSearch);
        itemsListView = findViewById(R.id.listViewItems);
        searchButton = findViewById(R.id.imageButton);
        itemsListView.setOnItemClickListener(this);
        searchButton.setOnClickListener(this);
        db = FirebaseFirestore.getInstance();
        Intent checker = getIntent();
        number = checker.getIntExtra("number", 0);
        UpdateDisplay();
    }

    private void UpdateDisplay() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        ItemsList.clear();
        switch (number) {
            case 1:  // listings
                db.collection("computers").whereEqualTo("sellerID", LoginActivity.profile.getUID()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                        data.add((HashMap<String, Object>) document.getData());
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                ItemsList.add(document.toObject(Computers.class));

                            }
                            for (Computers comp : ItemsList) {
                                Log.d("TAG", "here");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("name", comp.getName());
                                map.put("price", comp.getPrice());
                                data.add(map);
                            }
//        // create the resource, from, and to variables
                            int resource = R.layout.list_item;
                            String[] from = {"name", "price"};
                            int[] to = {R.id.textViewNamein, R.id.textViewPrice};
                            // create and set the adapter
                            SimpleAdapter adapter =
                                    new SimpleAdapter(getApplicationContext(), data, resource, from, to);
                            itemsListView.setAdapter(adapter);
                        }
                    }
                });
                break;
            case 2:  //purchases
                db.collection("purchases").whereEqualTo("sellerID", LoginActivity.profile.getUID()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                        data.add((HashMap<String, Object>) document.getData());
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                ItemsList.add(document.toObject(Computers.class));
                            }
                            for (Computers comp : ItemsList) {
                                Log.d("TAG", "here");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("name", comp.getName());
                                map.put("price", comp.getPrice());
                                data.add(map);
                            }
//        // create the resource, from, and to variables
                            int resource = R.layout.list_item;
                            String[] from = {"name", "price"};
                            int[] to = {R.id.textViewNamein, R.id.textViewPrice};
                            // create and set the adapter
                            SimpleAdapter adapter =
                                    new SimpleAdapter(getApplicationContext(), data, resource, from, to);
                            itemsListView.setAdapter(adapter);
                        }
                    }
                });
                break;
            case 4: //Searching
                for (Computers comp : SearchList) {
                    Log.d("TAG", "here");
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("name", comp.getName());
                    map.put("price", comp.getPrice());
                    data.add(map);
                }
//        // create the resource, from, and to variables
                int resource = R.layout.list_item;
                String[] from = {"name", "price"};
                int[] to = {R.id.textViewNamein, R.id.textViewPrice};
                // create and set the adapter
                SimpleAdapter adapter =
                        new SimpleAdapter(getApplicationContext(), data, resource, from, to);
                itemsListView.setAdapter(adapter);
                break;
            default:
                db.collection("computers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                        data.add((HashMap<String, Object>) document.getData());
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                ItemsList.add(document.toObject(Computers.class));
                            }
                            for (Computers comp : ItemsList) {
                                Log.d("TAG", "here");
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("name", comp.getName());
                                map.put("price", comp.getPrice());
                                data.add(map);
                            }
                            // create the resource, from, and to variables
                            int resource = R.layout.list_item;
                            String[] from = {"name", "price"};
                            int[] to = {R.id.textViewNamein, R.id.textViewPrice};
                            // create and set the adapter
                            SimpleAdapter adapter =
                                    new SimpleAdapter(getApplicationContext(), data, resource, from, to);
                            itemsListView.setAdapter(adapter);
                        }
                    }
                });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detail;
        switch (number) {
            case 1: //listings
                detail = new Intent(MainActivity.this, AddEditActivity.class);
                detail.putExtra("position", position);
                detail.putExtra("number", number);
                startActivity(detail);
                break;
            default:
                detail = new Intent(MainActivity.this, ItemActivity.class);
                detail.putExtra("position", position);
                detail.putExtra("number", number);
                startActivity(detail);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent searchPage = new Intent(MainActivity.this, AddEditActivity.class);
        searchPage.putExtra("number", 4);
        startActivity(searchPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
//        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent HomeIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(HomeIntent);
        }
        if (item.getItemId() == R.id.addlisting) {
            Intent AddIntent = new Intent(MainActivity.this, AddEditActivity.class);
            startActivity(AddIntent);
        }
        if (item.getItemId() == R.id.viewlisting) {
            Intent ViewIntent = new Intent(MainActivity.this, MainActivity.class);
            ViewIntent.putExtra("number", 1);
            startActivity(ViewIntent);
        }
        if (item.getItemId() == R.id.purchases) {
            Intent ViewIntent = new Intent(MainActivity.this, MainActivity.class);
            ViewIntent.putExtra("number", 2);
            startActivity(ViewIntent);
        }
        if (item.getItemId() == R.id.profile) {
            //TBA WHEN THE PROFILE ACTIVITY IS CREATED
            Intent ProfileIntent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(ProfileIntent);
        }
        if (item.getItemId() == R.id.about) {
            //TBA WHEN ABOUT ACTIVITY IS CREATED
            Intent HomeIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(HomeIntent);
        }
        if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();
            Intent HomeIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(HomeIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Log.d("TAG", "onClick: searching...");
        ArrayList<Computers> templist = new ArrayList<>();
        if (v.getId() == R.id.imageButton) {
            if (!searchET.getText().toString().isEmpty())
                for (Computers x : ItemsList) {
                    String temp = x.toString();
                    String search = searchET.getText().toString();
                    if (temp.contains(search)) {
                        templist.add(x);
                    }
                }
            ItemsList = templist;
            UpdateDisplay();
        }
        return false;
    }
}