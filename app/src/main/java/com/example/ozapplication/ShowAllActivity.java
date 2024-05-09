package com.example.ozapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowAllActivity extends AppCompatActivity {

    ListView lv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        lv=findViewById(R.id.lv);

        ArrayAdapter<User> adp = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1, LoadingActivity.users);
        lv.setAdapter(adp);
    }
}