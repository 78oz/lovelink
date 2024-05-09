package com.example.ozapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PrefActivity extends AppCompatActivity {

    EditText etMinAge, etMaxAge;
    CheckBox cbGender, cbMinAge, cbMaxAge;
    Spinner spGender;
    String[] valuesGender={"male","female","other"};
    ArrayAdapter adGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);

        spGender=findViewById(R.id.spGender);
        adGender=new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, valuesGender);
        spGender.setAdapter(adGender);

        etMinAge = findViewById(R.id.etMinAge);
        etMaxAge = findViewById(R.id.etMaxAge);
        cbGender = findViewById(R.id.cbGender);
        cbMinAge = findViewById(R.id.cbMinAge);
        cbMaxAge = findViewById(R.id.cbMaxAge);

    }

    public void save(View view) {
        //1 - creating userPref arrayList
        ArrayList<Parameter> userPref = new ArrayList<>();
        //gender
        userPref.add(new Parameter("gender", spGender.getSelectedItem().toString(), cbGender.isChecked()));
        //MinAge
        userPref.add(new Parameter("minAge", etMinAge.getText().toString(), cbMinAge.isChecked()));
        //MaxAge
        userPref.add(new Parameter("maxAge", etMaxAge.getText().toString(), cbMaxAge.isChecked()));
        //2 - adding userPref arrayList to user
        LoadingActivity.currentUser.userPref = userPref;
        //3 - writing user to database
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users/"+LoadingActivity.currentUser.userId);
        myRef.setValue(LoadingActivity.currentUser);
        //4 - next jump
        startActivity(new Intent(PrefActivity.this, StartActivity.class));

    }
}