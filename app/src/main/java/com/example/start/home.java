package com.example.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class home extends AppCompatActivity {
    CardView showcard;
    CardView profilecard;
    CardView setcard;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        int salary = sp.getInt("salary",0);
        if(salary == 0){
            Intent intent=new Intent(home.this,Settings.class);
            startActivity(intent);
            finish();
        }
        profilecard = findViewById(R.id.profilecard);
        setcard = findViewById(R.id.setcard);
        showcard= findViewById(R.id.showcard);


        setcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,Settings.class);
                startActivity(intent);
                finish();
            }
        });

        profilecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,profile.class);
                startActivity(intent);
                finish();
            }
        });

        showcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,showanalysis.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
