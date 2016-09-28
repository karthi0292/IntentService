package com.service.intentservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by CIPL0233 on 9/28/2016.
 */

public class LocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.location.PROVIDERS_CHANGED")){
            Toast.makeText(context,"success",Toast.LENGTH_LONG).show();
        }
    }
}
