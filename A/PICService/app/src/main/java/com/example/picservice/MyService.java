package com.example.picservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private final String Tag="Service";
    public MyService() {
    }

    @Override
    public void onCreate(){
        Log.e(Tag,"onCreate()被调用");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(Tag,"onStartCommmand()被调用");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(Tag,"onBind()被调用");
        return mBinder;
    }

    AInterface.Stub mBinder = new AInterface.Stub() {

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public int sub(int a, int b) throws RemoteException {
            return a-b;
        }

        @Override
        public int mul(int a, int b) throws RemoteException {
            return a*b;
        }

        @Override
        public int div(int a, int b) throws RemoteException {
            return a/b;
        }

    };
}