package com.example.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    Button categorybtn;
    Button shopbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

         categorybtn= findViewById(R.id.categorybtn);
         shopbtn=findViewById(R.id.shopbtn);

        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Settings.this,addcategory.class);
                startActivity(intent);
                finish();
            }
        });
        shopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Settings.this,addshop.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}
