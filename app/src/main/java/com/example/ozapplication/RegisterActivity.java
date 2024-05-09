package com.example.ozapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText etName, etPhone, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Reg(View view) {
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        //1 - write to firebase authentication:
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "registration...", Toast.LENGTH_SHORT).show();
                            //2 - write to firebase database:
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            String userId = currentUser.getUid();
                            //DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users/"+userId);
                            //DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users").child("userId");
                            User user = new User(userId, name, phone, email, password);
                            //myRef.setValue(user);
                            LoadingActivity.currentUser = user; //update currentUser
                            startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}