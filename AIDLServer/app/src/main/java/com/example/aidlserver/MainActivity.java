package com.example.aidlserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static MainActivity INSTANCE;
    public TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.action);
        INSTANCE = this;
    }

    public static MainActivity getActivityInstance()
    {
        return INSTANCE;
    }

    public void update()
    {
        Toast.makeText(MainActivity.this,"Action received from client and return back to client",
                Toast.LENGTH_SHORT).show();
        tv.setText("Action received from client and return back to client");
    }


}