package com.example.myapplication;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.car.ui.toolbar.SearchConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "CMP354:";
    private EditText et_email, et_password;
    private FirebaseAuth mAuth;
    private Button login, signUp;
    public static Profiles profile = new Profiles();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //authentication
        mAuth = FirebaseAuth.getInstance();
        login = (Button) findViewById(R.id.buttonLogin);
        signUp = (Button) findViewById(R.id.buttonSignUpGoto);
        et_password = findViewById(R.id.editTextPassword);
        et_email = findViewById(R.id.editTextEmail);
//        prevents user from re-entering the app after logging out
        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                onDestroy();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void onClick_btn(View v)
    {
        if (v.getId()==R.id.buttonLogin) {

            if (et_password.getText().toString().isEmpty()) return;

            //Create a new signIn method which takes in an email address and password,
            // validates them, and then signs a user in with the signInWithEmailAndPassword method.
            mAuth.signInWithEmailAndPassword(et_email.getText().toString(), et_password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                assert user != null;
                                profile.setUID(mAuth.getCurrentUser().getUid());
                                profile.setEmail(mAuth.getCurrentUser().getEmail());
//                                profile.setUsername(user.getDisplayName());
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("email", user.getEmail());
                                startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        //----------------------------------------
        //Create a new signIn method which takes in an email address and password,
        // validates them, and then signs a user in with the signInWithEmailAndPassword method.
        else if (v.getId()==R.id.buttonSignUpGoto)
        {
            Intent sign = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(sign);

        }
    }

}
