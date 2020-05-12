package com.example.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    CardView btnl;
    //TextView textrl1;
    TextView textl;
    EditText usernl;
    EditText userpl;
    TextView forgot;
    TextView logintext;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sp = PreferenceManager.getDefaultSharedPreferences(this);

        btnl= findViewById(R.id.btnl);
        //textrl1 = findViewById(R.id.textrl1);
        textl = findViewById(R.id.textl);
        usernl = findViewById(R.id.usernl);
        userpl = findViewById(R.id.userpl);
        forgot = findViewById(R.id.forgot);
        logintext = findViewById(R.id.logintext);

        textl.setPaintFlags(textl.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forgot.setPaintFlags(forgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        logintext.setPaintFlags(logintext .getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //textrl1.setVisibility(View.GONE);

        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textrl1.setVisibility(View.VISIBLE);

                String username = usernl.getText().toString();
                String password = userpl.getText().toString();
                String rlpassword = sp.getString(username, "null");
                if (password.equals(rlpassword)) {

                    Intent intent = new Intent(login.this, home.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "user not found!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);
                finish();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,forgot.class);
                startActivity(intent);
                finish();
            }
        });


    }




}