package com.service.intentservice.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

import com.service.intentservice.interfaces.CallBack;

/**
 * Created by CIPL0233 on 9/29/2016.
 */

public class DownloadReceiver extends ResultReceiver {
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    private CallBack callback;
    private static final int STATUS_FINISHED = 1;

    public DownloadReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(CallBack callbackReceiver) {
        callback = callbackReceiver;
    }

    //Callback from MyIntentService
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (resultCode == STATUS_FINISHED) {
            if (callback != null)
                callback.trigger(resultData);
        }
    }
}
