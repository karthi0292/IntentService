package com.service.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

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

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> listUrls = new ArrayList<>();
            listUrls.add("http://www.freepngimg.com/download/audi/1-audi-r8-png-image.png");
            listUrls.add("http://www.freepngimg.com/download/audi/2-black-r8-audi-png-car-image.png");
            listUrls.add("http://www.freepngimg.com/download/audi/3-audi-png-car-image.png");
            listUrls.add("http://www.freepngimg.com/download/audi/4-red-r8-audi-png-car-image.png");
            listUrls.add("http://www.freepngimg.com/download/audi/5-audi-png-car-image.png");
            listUrls.add("http://www.freepngimg.com/download/audi/7-audi-png-car-image.png");
            listUrls.add("http://www.freepngimg.com/download/audi/5-audi-png-car-image.png");

            //Run in background if the app is in active mode.It never run if the app is killed
            for (int i = 0; i < listUrls.size(); i++) {
                try {
                    URL url = new URL(listUrls.get(i));
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    String[] urls = listUrls.get(i).split("/");

                    File fileDir = new File(Environment.getExternalStorageDirectory() + "/" + "IntentService Images" + "/");
                    if (!fileDir.exists())
                        fileDir.mkdir();
                    File file = new File(fileDir, urls[urls.length - 1]);

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
        }
    }
}
