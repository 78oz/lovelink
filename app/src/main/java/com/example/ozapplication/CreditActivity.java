package com.example.ozapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreditActivity extends AppCompatActivity {

    TextView tv;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    String st, all="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        tv = findViewById(R.id.tv);

        is=getResources().openRawResource(R.raw.credit);
        isr=new InputStreamReader(is);
        br=new BufferedReader(isr);

        try {

            while ((st=br.readLine())!=null)
                all+=st+"\n";

            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        tv.setText(all);

    }
}