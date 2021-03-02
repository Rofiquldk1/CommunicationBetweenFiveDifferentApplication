package com.example.aidlserver;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

import SeparatePk.IRemoteServiceCallback;
import SeparatePk.aidlInterface;
import SeparatePk.IRemoteServiceCallback;
import SeparatePk.IRemoteServiceCallback;

public class MyService extends Service  {
    IRemoteServiceCallback iRemoteServiceCallback2;
    public MyService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }

    aidlInterface.Stub stubObject = new aidlInterface.Stub() {
        @Override
        public void trigger(IRemoteServiceCallback iRemoteServiceCallback) throws RemoteException {
            iRemoteServiceCallback2 = iRemoteServiceCallback;


            iRemoteServiceCallback2.feedBack("freeClient1");
        }

        @Override
        public void SendSMS(String phoneNumber, String message) throws RemoteException {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
            iRemoteServiceCallback2.sentStatus("Message Sent Successfully");
            iRemoteServiceCallback2.feedBack("freeClient1");
        }
    };

}