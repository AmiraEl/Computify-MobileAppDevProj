package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView itemsListView;
    static FirebaseFirestore db;
    public static ArrayList<Computers> ItemsList = new ArrayList<>();
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsListView = findViewById(R.id.listViewItems);
        itemsListView.setOnItemClickListener(this);
        db = FirebaseFirestore.getInstance();
        Intent checker = getIntent();
        number = checker.getIntExtra("number", 0);
        UpdateDisplay();
    }

    private void UpdateDisplay() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        ItemsList.clear();
        Log.d("TAG", "here");

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
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detail;
        switch (number){
            case 1: //listings
                detail = new Intent(MainActivity.this, AddEditActivity.class);
                detail.putExtra("position", position);
                detail.putExtra("number", number);
                startActivity(detail);
                break;
//            case 2: //purchases
//                detail = new Intent(MainActivity.this, ItemActivity.class);
//                detail.putExtra("position", position);
//                detail.putExtra("number", number);
//                startActivity(detail);
//                break;
            default:
                detail = new Intent(MainActivity.this, ItemActivity.class);
                detail.putExtra("position", position);
                detail.putExtra("number", number);
                startActivity(detail);
                break;
        }


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
        if(item.getItemId() == R.id.home){

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

            Intent ProfileIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(ProfileIntent);

        }
        if (item.getItemId() == R.id.about) {


            //TBA WHEN ABOUT ACTIVITY IS CREATED
            Intent HomeIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(HomeIntent);

        }
        if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();

            Intent HomeIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(HomeIntent);

        }


        return super.onOptionsItemSelected(item);
    }
}