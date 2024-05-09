package com.example.ozapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText etEmail, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        etEmail=findViewById(R.id.etEmail);
        etPass=findViewById(R.id.etPass);
    }


    //if user is already signed in (non-null) jump to StartActivity
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            LoadingActivity.currentUser = LoadingActivity.getCurrentUser(); //update currentUser
            startActivity(new Intent(LoginActivity.this,StartActivity.class));
            //finish();
        }
    }

    public void Login(View view) {
        Log.d("Tag", "Login");
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        Toast.makeText(this, email+" "+pass, Toast.LENGTH_SHORT).show();
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LoadingActivity.currentUser = LoadingActivity.getCurrentUser(); //update currentUser
                            /* we can update currentUser also by email:
                            for (User user : LoadingActivity.users) {
                                if (user.email.equals(email))
                                    LoadingActivity.currentUser = user;
                            }*/
                            startActivity(new Intent(LoginActivity.this,StartActivity.class));
                        } else {
                            Log.d("Tag", "sign-in failed");
                            Toast.makeText(LoginActivity.this, "sign-in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void Registration(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }

}