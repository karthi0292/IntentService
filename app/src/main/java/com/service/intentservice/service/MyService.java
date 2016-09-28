package com.service.intentservice.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;

import com.service.intentservice.receiver.LocationReceiver;

public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LocationReceiver locationReceiver=new LocationReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.location.PROVIDERS_CHANGED");
                registerReceiver(locationReceiver, filter);
            }
        };
        handler.postDelayed(runnable, 1000);

        //Start Sticky will trigger the service, suppose if the service is stopped by OS due to running out of memory
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
