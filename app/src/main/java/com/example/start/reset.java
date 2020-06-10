package com.example.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class reset extends AppCompatActivity {
    EditText newps;
    EditText confirmnew;
    CardView cardreset;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        newps=findViewById(R.id.newreset);
        confirmnew = findViewById(R.id.confirmnew);
        cardreset = findViewById(R.id.cardreset);

        cardreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sp.getString("com.start.user_mail",null);
                String newps1 = newps.getText().toString();
                String confirmnew1 = confirmnew.getText().toString();
                if(newps1.equals(confirmnew1)){
                    if(newps1.length() == 4){
                        SharedPreferences.Editor editor = sp.edit();
                       editor.putString(username,newps1);
                       editor.commit();
                        Intent intent = new Intent(reset.this,login.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getBaseContext(), "password should be 4 digits", Toast.LENGTH_SHORT).show();
                    }}
                 else{
                        Toast.makeText(getBaseContext(), "password and confirm password are not same", Toast.LENGTH_SHORT).show();
                    }


            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(reset.this,login.class);
        startActivity(intent);
        finish();
    }
}
