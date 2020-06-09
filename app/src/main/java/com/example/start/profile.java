package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class profile extends AppCompatActivity {

    private ActionBar actionBar;
    CardView btn;
    SharedPreferences sp;
    TextInputEditText salary_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        /*Databasehelper db = new Databasehelper(this);

        List<List<String>> x;
        x = db.getCategories();
        List<String> cat = x.get(2);
        Toast.makeText(this,cat.get(3),Toast.LENGTH_SHORT).show();*/

        btn=findViewById(R.id.save_edit_cd);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        int salary = Integer.parseInt(sp.getString("com.start.salary","0"));
        salary_edit=findViewById(R.id.edit_salary);


        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Profile");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                String salary_1 = salary_edit.getText().toString();
                if(!(salary_1.equals("0"))){
                    editor.putString("com.start.salary",salary_1);
                    editor.commit();
                    Toast.makeText(profile.this,"Enter salary",Toast.LENGTH_LONG).show();
                }
                else{

                    Intent intent=new Intent(profile.this,home.class);
                    startActivity(intent);
                }
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
            Intent i = new Intent(profile.this, login.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void display_name(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(profile.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_popup,null);
//        EditText changename = (EditText)mView.findViewById(R.id.changename);
        Button cancel = (Button)mView.findViewById(R.id.cancel_btn);
        Button ok = (Button)mView.findViewById(R.id.ok_btn);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
        alertDialog.show();

    }

    public void display_sal(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(profile.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_popup,null);
//        EditText changename = (EditText)mView.findViewById(R.id.changename);
        Button cancel = (Button)mView.findViewById(R.id.cancel_btn);
        Button ok = (Button)mView.findViewById(R.id.ok_btn);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
        alertDialog.show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}


