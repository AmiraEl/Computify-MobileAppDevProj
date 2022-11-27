package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private String TAG = "CMP354:";
    private EditText et_email, et_password, et_username;
    private FirebaseAuth mAuth;
    private Button login, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //authentication
        mAuth = FirebaseAuth.getInstance();
        login = (Button) findViewById(R.id.buttonLoginGoto);
        signUp = (Button) findViewById(R.id.buttonSignUp);
        et_password = findViewById(R.id.editTextPassword1);
        et_email = findViewById(R.id.editTextEmail1);
        et_username = findViewById(R.id.editTextUserName);
    }

    public void onClick_btn(View v)
    {
        if (et_password.getText().toString().isEmpty() || et_email.getText().toString().isEmpty()) return;
        if (v.getId()==R.id.buttonSignUp) {

            //Create a new signIn method which takes in an email address and password,
            // validates them, and then signs a user in with the signInWithEmailAndPassword method.

            mAuth.createUserWithEmailAndPassword(et_email.getText().toString(), et_password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "Create User With Email : success\nPlease login",
                                        Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        //----------------------------------------
        //Create a new signIn method which takes in an email address and password,
        // validates them, and then signs a user in with the signInWithEmailAndPassword method.
        else if (v.getId()==R.id.buttonLoginGoto)
        {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);

        }
    }

}