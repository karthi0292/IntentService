package com.service.intentservice.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.service.intentservice.R;
import com.service.intentservice.adapter.GridViewAdapter;
import com.service.intentservice.common.Constants;
import com.service.intentservice.interfaces.CallBack;
import com.service.intentservice.receiver.DownloadReceiver;
import com.service.intentservice.service.MyIntentService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallBack {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=(GridView)findViewById(R.id.gridview);


        //Inorder to receive values from IntentService class
        DownloadReceiver downloadReceiver = new DownloadReceiver(new Handler());
        downloadReceiver.setReceiver(this);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra(Constants.DOWNLOAD_COMPLETE_RECEIVER, downloadReceiver);
        startService(intent);


        //  Intent serviceIntent=new Intent(this, MyService.class);
        // startService(serviceIntent);
    }

    //Callback from downloadCompleteReceiver
    @Override
    public void trigger(Bundle data) {
        if (data != null) {

            //Update UI once Intent service is completed
            ArrayList<String> filePathList=data.getStringArrayList(Constants.FILE_PATH_LIST);
            GridViewAdapter gridViewAdapter=new GridViewAdapter(filePathList,this);
            gridView.setAdapter(gridViewAdapter);
        }

    }
}
