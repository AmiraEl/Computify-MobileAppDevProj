package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.HashMap;
import java.util.List;
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
    private Button addButton;
    private Button cancelButton;
    private Button saveButton;
    private ImageButton uploadButton;
    private FirebaseFirestore db;
    private int pcID;
    private int num = 0;
    private int pos = 0;
    Computers items = new Computers();



    // Uri indicates, where the image will be picked from
    private Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;


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
        addButton = findViewById(R.id.buttonSAVE);
        cancelButton = findViewById(R.id.buttonCANCEL);
        saveButton = findViewById(R.id.buttonSelect);
        uploadButton = findViewById(R.id.imageButtonUpload);
        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        db = MainActivity.db;

        Intent intent = getIntent();
        num = intent.getIntExtra("number", 0);
        pos = intent.getIntExtra("position", 0);

        if (num == 1) {
            items = MainActivity.ItemsList.get(pos);
            titleTV.setText("Edit Listing");
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
        }else if (num == 4){
            titleTV.setText("Search");
            addButton.setText("Search");
        }

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                UploadImage();
            }
        });
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
                        computers.document(items.getPcID()).set(item);
                        Intent temp = new Intent(AddEditActivity.this, MainActivity.class);
                        temp.putExtra("number", num);
                        startActivity(temp);
                    }
                });

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

            Intent ProfileIntent = new Intent(AddEditActivity.this, MainActivity.class);
            startActivity(ProfileIntent);

        }
        if (item.getItemId() == R.id.about) {

            //TBA WHEN ABOUT ACTIVITY IS CREATED
            Intent HomeIntent = new Intent(AddEditActivity.this, MainActivity.class);
            startActivity(HomeIntent);

        }
        if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();

            Intent HomeIntent = new Intent(AddEditActivity.this, LoginActivity.class);
            startActivity(HomeIntent);

        }


        return super.onOptionsItemSelected(item);
    }

    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);

            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void UploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(getApplicationContext(),
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(getApplicationContext(),
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }
}

}