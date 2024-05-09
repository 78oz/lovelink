package com.example.ozapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toast.makeText(this, LoadingActivity.currentUser+"", Toast.LENGTH_SHORT).show();
    }

    public void check_match(View view) {
        double machScore = LoadingActivity.users.get(0).Match(LoadingActivity.users.get(1));
        Toast.makeText(this, machScore+"", Toast.LENGTH_SHORT).show();
    }

    //region menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Add menu
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout) // identify item pressed
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(StartActivity.this, LoginActivity.class));
            finish();
        }
        if(item.getItemId() == R.id.guide) // identify item pressed
        {
            //Intent intent = new Intent(this, GuideActivity.class);
            //startActivity(intent);
        }
        if(item.getItemId() == R.id.credit) // identify item pressed
        {
            //Intent intent = new Intent(this, CreditActivity.class);
            //startActivity(intent);
        }
        return true;
    }

    public void show_all(View view) {
        startActivity(new Intent(StartActivity.this, ShowAllActivity.class));
    }
    //endregion
}