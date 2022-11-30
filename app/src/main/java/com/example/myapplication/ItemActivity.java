package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class ItemActivity extends AppCompatActivity {
    private TextView TextViewname;
    private TextView TextViewgpu;
    private TextView TextViewcpu;
    private TextView TextViewram;
    private TextView TextViewcase;
    private TextView TextViewmother;
    private TextView TextViewpsu;
    private TextView TextViewhdd;
    private TextView TextViewssd;
    private TextView TextViewSeller;
    private TextView TextViewPrice;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        int pos = getIntent().getIntExtra("position", -1);
        Computers item = MainActivity.ItemsList.get(pos);

        TextViewname = findViewById(R.id.textViewName);
        TextViewgpu = findViewById(R.id.TextViewGPU);
        TextViewcpu = findViewById(R.id.TextViewCPU);
        TextViewram = findViewById(R.id.textViewRAM);
        TextViewcase = findViewById(R.id.textViewCase);
        TextViewmother = findViewById(R.id.TextViewboard);
        TextViewpsu = findViewById(R.id.TextViewPower);
        TextViewhdd = findViewById(R.id.TextViewHdd);
        TextViewssd = findViewById(R.id.TextViewSSD);
        TextViewSeller = findViewById(R.id.TextViewSeller);
        TextViewPrice = findViewById(R.id.TextViewPrice);
        this.db = MainActivity.db;


        db.collection("profiles").whereEqualTo("UID", item.getSellerID()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Profiles temp = new Profiles();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        temp = document.toObject(Profiles.class);
                        break;
                    }
                    TextViewname.setText(item.getName());
                    TextViewgpu.setText(item.getGpu());
                    TextViewcpu.setText(item.getCpu());
                    TextViewram.setText(item.getRam());
                    TextViewcase.setText(item.getPcase());
                    TextViewmother.setText(item.getMotherboard());
                    TextViewpsu.setText(item.getPowersupply());
                    TextViewhdd.setText(item.getHdd());
                    TextViewssd.setText(item.getSsd());
                    TextViewPrice.setText(item.getPrice());
                    TextViewSeller.setText(temp.getEmail());

                }
            }
        });
    }
}