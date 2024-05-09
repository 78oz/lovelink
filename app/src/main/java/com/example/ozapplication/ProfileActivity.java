package com.example.ozapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    ImageView iv;
    Spinner spGender;
    EditText etAge;
    String[] valuesGender = {"male", "female", "other"};
    ArrayAdapter adGender;
    Bitmap imageBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        iv = findViewById(R.id.iv);
        etAge = findViewById(R.id.etAge);
        spGender = findViewById(R.id.spGender);
        adGender = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, valuesGender);
        spGender.setAdapter(adGender);
    }

    public void next(View view) {
        //0 - check if all params / pic are fine
        if (imageBitmap==null || etAge.getText().toString().equals(""))
        {
            Toast.makeText(this, "missing details...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //1 - creating userParam arrayList (by creating each parameter and adding to arraylist)
            ArrayList<Parameter> userParam = new ArrayList<>();
            //gender
            userParam.add(new Parameter("gender", spGender.getSelectedItem().toString()));
            //age
            userParam.add(new Parameter("age", etAge.getText().toString()));
            userParam.add(new Parameter("age", etAge.getText().toString()));
            //2 - adding userParam arrayList to user
            LoadingActivity.currentUser.userParam = userParam;
            //3 - upload imageBitmap picture to storage firebase at "pictures/userId"
            String userId = LoadingActivity.currentUser.userId;
            //Toast.makeText(this, "Uploading image...", Toast.LENGTH_SHORT).show();
            HandleImage.UploadingImage(imageBitmap, this, "pictures", "pic"+userId);
            //4 - next jump
            startActivity(new Intent(ProfileActivity.this, PrefActivity.class));
        }
    }

    public void takePic(View view) { //writing Ad pictures to firebase storage
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 123);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Picture into ImageView
        super.onActivityResult(requestCode, resultCode, data); //was add to fix error
        if (requestCode == 123 && resultCode == RESULT_OK) {
            //decode the data to imageBitmap
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            iv.setImageBitmap(imageBitmap);
        }

    }
}