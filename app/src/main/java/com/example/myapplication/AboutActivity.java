package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
            Intent HomeIntent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(HomeIntent);
        }
        if (item.getItemId() == R.id.addlisting) {
            Intent AddIntent = new Intent(AboutActivity.this, AddEditActivity.class);
            startActivity(AddIntent);
        }
        if (item.getItemId() == R.id.viewlisting) {
            Intent ViewIntent = new Intent(AboutActivity.this, MainActivity.class);
            ViewIntent.putExtra("number", 1);
            startActivity(ViewIntent);
        }
        if (item.getItemId() == R.id.purchases) {
            Intent ViewIntent = new Intent(AboutActivity.this, MainActivity.class);
            ViewIntent.putExtra("number", 2);
            startActivity(ViewIntent);
        }
        if (item.getItemId() == R.id.profile) {
            //TBA WHEN THE PROFILE ACTIVITY IS CREATED
            Intent ProfileIntent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(ProfileIntent);
        }
        if (item.getItemId() == R.id.about) {
            //TBA WHEN ABOUT ACTIVITY IS CREATED
            Intent HomeIntent = new Intent(AboutActivity.this, AboutActivity.class);
            startActivity(HomeIntent);
        }
        if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();
            Intent HomeIntent = new Intent(AboutActivity.this, LoginActivity.class);
            startActivity(HomeIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}