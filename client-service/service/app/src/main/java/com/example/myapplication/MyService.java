package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public abstract class MyService extends Service {
    public MyService() {
    }
    public void onCreate(){
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mstub;
    }


    public IMyAidlInterface.Stub mstub = new IMyAidlInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public int minus(int a, int b) throws RemoteException {
            return a-b;
        }

        @Override
        public int multiply(int a, int b) throws RemoteException {
            return a*b;
        }

        @Override
        public int divide(int a, int b) throws RemoteException {
            return a/b;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
}

