package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddEditActivity extends AppCompatActivity {

//    private TextView nameTV;
//    private TextView gpuTV;
//    private TextView cpuTV;
//    private TextView ramTV;
//    private TextView caseTV;
//    private TextView motherTV;
//    private TextView psuTV;
//    private TextView hddTV;
//    private TextView ssdTV;
    private TextView titleTV;

    private EditText nameET;
    private EditText gpuET;
    private EditText cpuET;
    private EditText ramET;
    private EditText caseET;
    private EditText motherET;
    private EditText psuET;
    private EditText hddET;
    private EditText ssdET;

    private Button addButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        titleTV = findViewById(R.id.textViewMain);

        nameET = findViewById(R.id.);
        gpuET = findViewById(R.id.);
        cpuET = findViewById(R.id.);
        ramET = findViewById(R.id.);
        caseET = findViewById(R.id.);
        motherET = findViewById(R.id.);
        psuET = findViewById(R.id.);
        hddET = findViewById(R.id.);
        ssdET = findViewById(R.id.);
    }
}