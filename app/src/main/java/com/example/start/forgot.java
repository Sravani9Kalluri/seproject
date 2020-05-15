package com.example.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgot extends AppCompatActivity {
    TextView question;
    EditText answer;
    CardView card;
    SharedPreferences sp;
    String ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        question = findViewById(R.id.forques);
        answer = findViewById(R.id.forans);
        card = findViewById(R.id.card);
        ques =  sp.getString("com.start.question","complete the signup process");
        question.setText(ques);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String ans_user = answer.getText().toString();
               String ans_act = sp.getString(ques,null);
               if(ans_user.equals(ans_act) && ans_user != ""){
                   Intent intent = new Intent(forgot.this,reset.class);
                   startActivity(intent);

               }
               else{
                   Toast.makeText(getBaseContext(), "wrong answer", Toast.LENGTH_SHORT).show();
               }
            }
        });

    }

}
