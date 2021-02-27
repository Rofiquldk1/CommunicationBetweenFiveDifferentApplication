package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import SeparatePackage.aidlInterface;

public class MainActivity extends AppCompatActivity {
    private aidlInterface aidlObject,aidlObject2;
    int result;
    private Button btn_add,btn_mul,btn_sub;
    private EditText editTextfirstNum,editTextSecondNum;
    private TextView textViewResult,textViewAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Toast.makeText(MainActivity.this,"Action Send to server",Toast.LENGTH_SHORT).show();
                textViewAction.setText("Action Send to server");
                int firstNum = Integer.valueOf(editTextfirstNum.getText().toString());
                int secondNum = Integer.valueOf(editTextSecondNum.getText().toString());
                try {
                    result = aidlObject.calculateData(firstNum,secondNum,0);
                    Toast.makeText(MainActivity.this,"Action received from server",Toast.LENGTH_SHORT).show();
                    textViewAction.setText("Action received from server");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                textViewResult.setText("Result "+result);
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Action Send to server",Toast.LENGTH_SHORT).show();
                textViewAction.setText("Action Send to server");
                int firstNum = Integer.valueOf(editTextfirstNum.getText().toString());
                int secondNum = Integer.valueOf(editTextSecondNum.getText().toString());
                try {
                    result = aidlObject.calculateData(firstNum,secondNum,1);
                    Toast.makeText(MainActivity.this,"Action received from server",Toast.LENGTH_SHORT).show();
                    textViewAction.setText("Action received from server");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                textViewResult.setText("Result "+result);
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Action Send to server2",Toast.LENGTH_SHORT).show();
                textViewAction.setText("Action Send to server2");
                int firstNum = Integer.valueOf(editTextfirstNum.getText().toString());
                int secondNum = Integer.valueOf(editTextSecondNum.getText().toString());
                try {
                    result = aidlObject2.calculateData(firstNum,secondNum,2);
                    Toast.makeText(MainActivity.this,"Action received from server2",Toast.LENGTH_SHORT).show();
                    textViewAction.setText("Action received from server2");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                textViewResult.setText("Result "+result);
            }
        });

        bindToAIDLService();
        bindToAIDLService2();

    }

    private void bindToAIDLService() {
        Intent aidlServiceIntent = new Intent("connect_to_aidl_service");
        bindService(implicitIntentToExplicitIntent(aidlServiceIntent,this),serviceConnectionObject,BIND_AUTO_CREATE);
    }

    private void bindToAIDLService2() {
        Intent aidlServiceIntent2 = new Intent("connect_to_aidl_service2");
        bindService(implicitIntentToExplicitIntent(aidlServiceIntent2,this),serviceConnectionObject2,BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnectionObject = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidlObject = SeparatePackage.aidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    ServiceConnection serviceConnectionObject2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidlObject2 = SeparatePackage.aidlInterface.Stub.asInterface(iBinder);
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
}