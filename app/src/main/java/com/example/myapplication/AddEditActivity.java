package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class AddEditActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView titleTV;
    private EditText nameET;
    private EditText gpuET;
    private EditText cpuET;
    private EditText ramET;
    private EditText caseET;
    private EditText motherET;
    private EditText priceET;
    private EditText psuET;
    private EditText hddET;
    private EditText ssdET;
    private EditText minPrice;
    private EditText maxPrice;
    private Button addButton;
    private Button cancelButton;
    private TableRow searchRow;
    private TableRow priceRow;
    //    private Button saveButton;
    //    private ImageButton uploadButton;
    private FirebaseFirestore db;
    private int pcID;
    private int num = 0;
    private int pos = 0;
    Computers items = new Computers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        titleTV = findViewById(R.id.textViewMain);
        nameET = findViewById(R.id.editTextPcName);
        gpuET = findViewById(R.id.editTextgpu);
        cpuET = findViewById(R.id.editTextCPU);
        ramET = findViewById(R.id.editTextRAM);
        caseET = findViewById(R.id.editTextCase);
        motherET = findViewById(R.id.editTextboard);
        priceET = findViewById(R.id.editTextPrice);
        psuET = findViewById(R.id.editTextPower);
        hddET = findViewById(R.id.editTextHdd);
        ssdET = findViewById(R.id.editTextSSD);
        minPrice = findViewById(R.id.editTextPriceMin);
        maxPrice = findViewById(R.id.editTextPriceMax);
        addButton = findViewById(R.id.buttonSAVE);
        searchRow = findViewById(R.id.searchRow);
        priceRow = findViewById(R.id.priceRow);

        //listeners
        addButton.setOnClickListener(this);
        cancelButton = findViewById(R.id.buttonCANCEL);
        cancelButton.setOnClickListener(this);

        db = MainActivity.db;
        Intent intent = getIntent();
        num = intent.getIntExtra("number", 0);
        pos = intent.getIntExtra("position", 0);
        searchRow.setVisibility(View.GONE);
        priceRow.setVisibility(View.VISIBLE);

        if (num == 1) {
            items = MainActivity.ItemsList.get(pos);
            titleTV.setText("EDIT LISTING");
            db.collection("profiles").whereEqualTo("UID", items.getSellerID()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Profiles temp = new Profiles();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            temp = document.toObject(Profiles.class);
                            break;
                        }
                        nameET.setText(items.getName());
                        gpuET.setText(items.getGpu());
                        cpuET.setText(items.getCpu());
                        ramET.setText(items.getRam());
                        caseET.setText(items.getPcase());
                        motherET.setText(items.getMotherboard());
                        psuET.setText(items.getPowersupply());
                        hddET.setText(items.getHdd());
                        ssdET.setText(items.getSsd());
                        priceET.setText(items.getPrice());
                    }
                }
            });
        } else if (num == 4) {
            titleTV.setText("SEARCH");
            addButton.setText("SEARCH");
            searchRow.setVisibility(View.VISIBLE);
            priceRow.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSAVE) {
            //ADD/EDIT TO DATABASE
            if (num == 1) {
                CollectionReference computers = db.collection("computers");
                Computers item =
                        new Computers(cpuET.getText().toString(), gpuET.getText().toString(), ramET.getText().toString(), caseET.getText().toString(),
                                motherET.getText().toString(), psuET.getText().toString(), hddET.getText().toString(), ssdET.getText().toString(),
                                priceET.getText().toString(), nameET.getText().toString(), LoginActivity.profile.getUID(), items.getPcID());
                computers.document(items.getPcID()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Intent notificationIntent = new Intent(getApplicationContext(), ItemActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // create the pending intent
                        int flags = PendingIntent.FLAG_IMMUTABLE;
                        PendingIntent pendingIntent =
                                PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, flags);//<--------

                        // create the variables for the notification
                        CharSequence contentTitle = "Computify";

                        CharSequence tickerText = "New computers available";
                        CharSequence contentText = "Your listing has been created";

                        NotificationChannel notificationChannel =
                                new NotificationChannel("Channel_ID", "My Notifications", NotificationManager.IMPORTANCE_HIGH);

                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.createNotificationChannel(notificationChannel);


                        // create the notification and set its data
                        Notification notification = new NotificationCompat
                                .Builder(getApplicationContext(), "Channel_ID")
                                .setSmallIcon(R.drawable.computer)
                                .setTicker(tickerText)
                                .setContentTitle(contentTitle)
                                .setContentText(contentText)
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true)
                                .setChannelId("Channel_ID")
                                .build();

                        final int NOTIFICATION_ID = 4; //cannot be 0
                        manager.notify(NOTIFICATION_ID, notification);


                        computers.document(items.getPcID()).set(item);
                        Intent temp = new Intent(AddEditActivity.this, MainActivity.class);
                        temp.putExtra("number", num);
                        startActivity(temp);
                    }
                });

            } else if (num == 4) {
                CollectionReference computers = db.collection("computers");
                Computers item =
                        new Computers(cpuET.getText().toString(), gpuET.getText().toString(), ramET.getText().toString(), caseET.getText().toString(),
                                motherET.getText().toString(), psuET.getText().toString(), hddET.getText().toString(), ssdET.getText().toString(),
                                priceET.getText().toString(), nameET.getText().toString(), null, null);
                ArrayList<Computers> tempList = new ArrayList<>();
                Log.d("TEST", "onClick: " + item.toString());
                for (Computers x : MainActivity.ItemsList) {
                    if (item.equals(x) && (maxPrice.getText().toString().isEmpty() || (Integer.parseInt(maxPrice.getText().toString()) >= Integer.parseInt(x.getPrice()))) && (minPrice.getText().toString().isEmpty() || (Integer.parseInt(maxPrice.getText().toString()) >= Integer.parseInt(x.getPrice())))){
                        tempList.add(x);
                    }
                    Log.d("TEST", "onClick: " + x.toString());
                }
                MainActivity.SearchList = tempList;
                Intent temp = new Intent(AddEditActivity.this, MainActivity.class);
                temp.putExtra("number", num);
                startActivity(temp);

            } else {
                if (nameET.getText().toString().isEmpty() || priceET.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(),"Error: please input a name and a price!", Toast.LENGTH_LONG).show();
                } else {
                    CollectionReference computers = db.collection("computers");
                    Computers item =
                            new Computers(cpuET.getText().toString(), gpuET.getText().toString(), ramET.getText().toString(), caseET.getText().toString(),
                                    motherET.getText().toString(), psuET.getText().toString(), hddET.getText().toString(), ssdET.getText().toString(),
                                    priceET.getText().toString(), nameET.getText().toString(), LoginActivity.profile.getUID(), "");
                    computers.add(item).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("TAG", "DocumentSnapshot written with ID: " + documentReference.getId());
                            computers.document(documentReference.getId()).update("pcID", documentReference.getId());
                            Intent temp = new Intent(AddEditActivity.this, MainActivity.class);
                            startActivity(temp);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error adding document", e);
                        }
                    });
                }
            }
        } else if (v.getId() == R.id.buttonCANCEL) {
            Intent temp = new Intent(AddEditActivity.this, MainActivity.class);
            startActivity(temp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
//        menu.getItem(1).setVisible(false);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {

            Intent HomeIntent = new Intent(AddEditActivity.this, MainActivity.class);
            startActivity(HomeIntent);

        }
        if (item.getItemId() == R.id.addlisting) {
            Intent AddIntent = new Intent(AddEditActivity.this, AddEditActivity.class);

            startActivity(AddIntent);
        }
        if (item.getItemId() == R.id.viewlisting) {

            Intent ViewIntent = new Intent(AddEditActivity.this, MainActivity.class);

            ViewIntent.putExtra("number", 1);

            startActivity(ViewIntent);

        }
        if (item.getItemId() == R.id.purchases) {

            Intent ViewIntent = new Intent(AddEditActivity.this, MainActivity.class);

            ViewIntent.putExtra("number", 2);

            startActivity(ViewIntent);

        }
        if (item.getItemId() == R.id.profile) {

            //TBA WHEN THE PROFILE ACTIVITY IS CREATED

            Intent ProfileIntent = new Intent(AddEditActivity.this, ProfileActivity.class);
            startActivity(ProfileIntent);

        }
        if (item.getItemId() == R.id.about) {

            //TBA WHEN ABOUT ACTIVITY IS CREATED
            Intent HomeIntent = new Intent(AddEditActivity.this, AboutActivity.class);
            startActivity(HomeIntent);

        }
        if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();

            Intent HomeIntent = new Intent(AddEditActivity.this, LoginActivity.class);
            startActivity(HomeIntent);

        }
        return super.onOptionsItemSelected(item);
    }
}

