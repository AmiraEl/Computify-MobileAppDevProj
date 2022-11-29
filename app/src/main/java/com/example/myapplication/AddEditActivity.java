package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddEditActivity extends AppCompatActivity implements View.OnClickListener {

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

    static FirebaseFirestore db;

    private int pcID;

    private FirebaseAuth auth;



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
        psuET = findViewById(R.id.editTextPower);
        hddET = findViewById(R.id.editTextHdd);
        ssdET = findViewById(R.id.editTextSSD);

        addButton = findViewById(R.id.buttonSAVE);
        cancelButton = findViewById(R.id.buttonCANCEL);

        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);



        db = FirebaseFirestore.getInstance();


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSAVE){

            //ADD/EDIT TO DATABASE

            CollectionReference computers = db.collection("computers");



            computers.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                     List<String> o = (List<String>) task.getResult().getDocuments().get(task.getResult().size() -1).get("pcID");
                      pcID = Integer.parseInt(o.get(o.size()-1));
                      pcID++;

                        Toast.makeText(getApplicationContext(), Integer.toString(pcID), Toast.LENGTH_LONG).show();
                        Log.d("ABOOK", Integer.toString(pcID));


                        Map<String, Object> computer = new HashMap<>();
                        computer.put("pcID", pcID);
                        computer.put("UID", LoginActivity.profile.getUID());
                        computer.put("name", nameET.getText().toString());
                        computer.put("gpu", gpuET.getText().toString());
                        computer.put("cpu", cpuET.getText().toString());
                        computer.put("ram", ramET.getText().toString());
                        computer.put("case", caseET.getText().toString());
                        computer.put("mother", motherET.getText().toString());
                        computer.put("psu", psuET.getText().toString());
                        computer.put("hdd", hddET.getText().toString());
                        computer.put("ssd", ssdET.getText().toString());

                        computers.document(Integer.toString(pcID)).set(computer);

                    }
                }
            });





        }
        else if(v.getId() == R.id.buttonCANCEL){
            //RETURNNNNNNNNNNNNNN
        }
    }


}