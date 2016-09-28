package com.service.intentservice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.service.intentservice.R;
import com.service.intentservice.service.MyIntentService;
import com.service.intentservice.service.MyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  Intent intent=new Intent(this,MyIntentService.class);
       // startService(intent);


        Intent serviceIntent=new Intent(this, MyService.class);
        startService(serviceIntent);
    }
}
