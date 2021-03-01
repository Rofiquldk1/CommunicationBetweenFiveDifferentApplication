package com.example.aidlclient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;
import SeparatePk.aidlInterface;
import SeparatePk.IRemoteServiceCallback;

public class MainActivity extends AppCompatActivity {
    private aidlInterface aidlObject,aidlObject2,aidlObject3,aidlObject4,aidlObject5;
    int result1=0,result2=0,result3=0,result4=0,result5=0;
    private Button btn_add,btn_mul,btn_sub;
    private EditText editTextfirstNum,editTextSecondNum;
    private TextView textViewResult,textViewAction;
    String ts,ts1,ts2,ts3,ts4,ts5;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        btn_add = findViewById(R.id.btn_add);
        btn_mul = findViewById(R.id.btn_mul);
        btn_sub = findViewById(R.id.btn_sub);
        editTextfirstNum = findViewById(R.id.edit_text_first_num);
        editTextSecondNum = findViewById(R.id.edit_text_2nd_num);
        textViewResult = findViewById(R.id.text_result);
        textViewAction = findViewById(R.id.action);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"Action Send to server",Toast.LENGTH_SHORT).show();
                textViewAction.setText("Action Send to server");
                int firstNum = Integer.valueOf(editTextfirstNum.getText().toString());
                int secondNum = Integer.valueOf(editTextSecondNum.getText().toString());
                try {
                    Long tsLong0 = System.currentTimeMillis();
                    ts = tsLong0.toString();
                    databaseHelper.addInfo("1","controller","signal sent to clients",ts);

                    Log.d("260","Clicked");
                    aidlObject.trigger(stubObjectR);
                    aidlObject2.trigger(stubObjectR);
                    aidlObject3.trigger(stubObjectR);
                    aidlObject4.trigger(stubObjectR);
                    aidlObject5.trigger(stubObjectR);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"Action Send to server",Toast.LENGTH_SHORT).show();
                textViewAction.setText("Action Send to server");
                int firstNum = Integer.valueOf(editTextfirstNum.getText().toString());
                int secondNum = Integer.valueOf(editTextSecondNum.getText().toString());
                textViewResult.setText("Result "+result1+" "+result2+" "+result3+" "+result4+" "+result5);
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewAction.setText("Action Send to server2");
                int firstNum = Integer.valueOf(editTextfirstNum.getText().toString());
                int secondNum = Integer.valueOf(editTextSecondNum.getText().toString());
                textViewResult.setText("Result "+result1+" "+result2+" "+result3+" "+result4+" "+result5);
            }
        });

        bindToAIDLService();
        bindToAIDLService2();
        bindToAIDLService3();
        bindToAIDLService4();
        bindToAIDLService5();
    }

    private void bindToAIDLService5() {
        Intent aidlServiceIntent = new Intent("connect_to_aidl_service5");
        bindService(implicitIntentToExplicitIntent(aidlServiceIntent,this),serviceConnectionObject5,BIND_AUTO_CREATE);
    }

    private void bindToAIDLService4() {
        Intent aidlServiceIntent = new Intent("connect_to_aidl_service4");
        bindService(implicitIntentToExplicitIntent(aidlServiceIntent,this),serviceConnectionObject4,BIND_AUTO_CREATE);
    }

    private void bindToAIDLService3() {
        Intent aidlServiceIntent = new Intent("connect_to_aidl_service3");
        bindService(implicitIntentToExplicitIntent(aidlServiceIntent,this),serviceConnectionObject3,BIND_AUTO_CREATE);
    }

    private void bindToAIDLService2() {
        Intent aidlServiceIntent = new Intent("connect_to_aidl_service2");
        bindService(implicitIntentToExplicitIntent(aidlServiceIntent,this),serviceConnectionObject2,BIND_AUTO_CREATE);
    }

    private void bindToAIDLService() {
        Intent aidlServiceIntent = new Intent("connect_to_aidl_service");
        bindService(implicitIntentToExplicitIntent(aidlServiceIntent,this),serviceConnectionObject,BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnectionObject = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidlObject = aidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    ServiceConnection serviceConnectionObject2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidlObject2 = aidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    ServiceConnection serviceConnectionObject3 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidlObject3 = aidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    ServiceConnection serviceConnectionObject4 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidlObject4 = aidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    ServiceConnection serviceConnectionObject5 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidlObject5 = aidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    public Intent implicitIntentToExplicitIntent(Intent implicitIntent, Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

    IRemoteServiceCallback stubObjectR = new IRemoteServiceCallback.Stub() {

        @Override
        public void feedBack(String msg) throws RemoteException {
            if(msg.equalsIgnoreCase("freeClient5")){
                Long tsLong0 = System.currentTimeMillis();
                ts = tsLong0.toString();
                databaseHelper.addInfo("2","client5","this is client 5",ts);
            }
            if(msg.equalsIgnoreCase("freeClient2")){
                Long tsLong0 = System.currentTimeMillis();
                ts = tsLong0.toString();
                databaseHelper.addInfo("3","client2","this is client 2",ts);
            }
            if(msg.equalsIgnoreCase("freeClient4")){
                Long tsLong0 = System.currentTimeMillis();
                ts = tsLong0.toString();
                databaseHelper.addInfo("4","client4","this is client 4",ts);
            }
            if(msg.equalsIgnoreCase("freeClient1")){
                Long tsLong0 = System.currentTimeMillis();
                ts = tsLong0.toString();
                databaseHelper.addInfo("5","client1","this is client 1",ts);
            }
            if(msg.equalsIgnoreCase("freeClient3")){
                Long tsLong0 = System.currentTimeMillis();
                ts = tsLong0.toString();
                databaseHelper.addInfo("6","client3","this is client 3",ts);
            }
            Log.d("260",msg);
        }
    };

}