package com.service.intentservice.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.os.ResultReceiver;

import com.service.intentservice.common.Constants;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    public static final int STATUS_FINISHED = 1;

    public MyIntentService() {
        super("MyIntentService");
    }

    private Bundle bundle;

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            bundle = new Bundle();
            ResultReceiver downloadCompleteReceiver = intent.getParcelableExtra(Constants.DOWNLOAD_COMPLETE_RECEIVER);
            ArrayList<String> filePathList = new ArrayList<>();
            ArrayList<String> listUrls = new ArrayList<>();

            listUrls.add("https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/profle-256.png");
            listUrls.add("https://cdn3.iconfinder.com/data/icons/luchesa-vol-9/128/Home-256.png");
            listUrls.add("https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/computer-256.png");
            listUrls.add("https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/calendar-256.png");
            listUrls.add("https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/car-256.png");
            listUrls.add(" https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/chat-256.png");

            //Run in background if the app is in active mode.It never run if the app is killed
            for (int i = 0; i < listUrls.size(); i++) {
                try {
                    URL url = new URL(listUrls.get(i));
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    String[] urls = listUrls.get(i).split("/");

                    File fileDir = new File(Environment.getExternalStorageDirectory() + "/" + Constants.SERVICE_IMAGES + "/");
                    if (!fileDir.exists())
                        fileDir.mkdir();
                    File file = new File(fileDir, urls[urls.length - 1]);

                    filePathList.add(file.toString());

                    byte data[] = new byte[1024];

                    InputStream in = new BufferedInputStream(url.openStream());
                    FileOutputStream fileOutputStream = new FileOutputStream(file);

                    if (urlConnection.getResponseCode() == 200) {
                        while ((in.read(data)) != -1)
                            fileOutputStream.write(data);
                    }
                    in.close();
                    fileOutputStream.close();
                    fileOutputStream.flush();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bundle.putStringArrayList(Constants.FILE_PATH_LIST, filePathList);
            downloadCompleteReceiver.send(STATUS_FINISHED, bundle);
        }
    }
}
