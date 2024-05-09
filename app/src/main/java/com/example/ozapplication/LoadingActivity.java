package com.example.ozapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {


    public static ArrayList<User> users = new ArrayList<>(); //"static" data array for using from other activities
    boolean firstDownload = true;
    public static User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        //constant listener for every updating of database and data array
        //when firstDownload jump to LoginActivity
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(LoadingActivity.this, "onDataChange", Toast.LENGTH_SHORT).show();
                Log.d("LoadingActivity", "onDataChange");
                users.clear();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User u = userSnapshot.getValue(User.class);
                    users.add(u);
                }
                //Toast.makeText(LoadingActivity.this, users.size() + "", Toast.LENGTH_SHORT).show();
                if (firstDownload) //when firstDownload jump to LoginActivity
                {
                    firstDownload = false;
                    startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoadingActivity.this, "error reading from firebase", Toast.LENGTH_LONG).show();
            }
        });
    }



    //static (used from other activities) function for getting the logged in user ("current user")
    //if there is no logged in user returning null
    //todo: fix
    public static User getCurrentUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser current = mAuth.getCurrentUser();
        String userId = current.getUid();
        for (User user : users) {
            if (user.userId.equals(userId))
                return user;
        }
        return null;
    }

}