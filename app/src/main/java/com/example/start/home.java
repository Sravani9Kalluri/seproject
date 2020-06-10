package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class home extends AppCompatActivity {
    CardView showcard;
    CardView profilecard;
    CardView setcard;
    SharedPreferences sp;
    //hello for commit
    private long backPressedTime;
    private Toast backToast;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        String salary = sp.getString("com.start.salary","0");
        if(salary == "0"){
            Toast.makeText(home.this,"Enter salary",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(home.this,profile.class);
            startActivity(intent);
        }
        profilecard = findViewById(R.id.profilecard);
        setcard = findViewById(R.id.setcard);
        showcard= findViewById(R.id.showcard);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");


        setcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,Settings.class);
                startActivity(intent);

            }
        });

        profilecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,profile.class);
                startActivity(intent);

            }
        });

        showcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,showanalysis.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_sign_out) {
            // do something
            Intent i = new Intent(home.this, login.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        if(backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();;
            return;
        }
        else{
            backToast=Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
        /*Intent intent = new Intent(home.this,home.class);
        startActivity(intent);
        finish();*/
    }


}
