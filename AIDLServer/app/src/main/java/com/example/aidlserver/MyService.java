package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import SeparatePackage.aidlInterface;

public class MyService extends Service {
    public MyService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }
    
    SeparatePackage.aidlInterface.Stub stubObject = new SeparatePackage.aidlInterface.Stub() {
        @Override
        public int calculateData(int firstValue, int secondValue, int operationType) throws RemoteException {
            switch (operationType){
                case 0 :
                    return firstValue+secondValue;
                case 1:
                    return firstValue*secondValue;
                default:
                    return firstValue+secondValue;
            }
        }
    };

}