package com.example.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;


import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},1000);

        }
        Intent intent = new Intent(this, service.class);
        startService(intent);

        Timer timer =new Timer();

        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                Intent intent=new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();

            }
        }, 2000);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode ==  1000){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted!",Toast.LENGTH_SHORT).show();
            } else{

                Toast.makeText(this,"Permisssion not granted",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


}