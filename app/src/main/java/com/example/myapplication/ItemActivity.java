package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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


        TextViewname.setText(item.getName());
        TextViewgpu.setText(item.getGpu());
        TextViewcpu.setText(item.getCpu());
        TextViewram.setText(item.getRam());
        TextViewcase.setText(item.getPcase());
        TextViewmother.setText(item.getMotherboard());
        TextViewpsu.setText(item.getPowersupply());
        TextViewhdd.setText(item.getHdd());
        TextViewssd.setText(item.getSsd());
        TextViewSeller.setText(item.getSellerID());

    }
}