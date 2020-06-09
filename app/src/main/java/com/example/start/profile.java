package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class profile extends AppCompatActivity {

    private ActionBar actionBar;
    CardView btn;
    SharedPreferences sp;
    TextInputEditText salary_edit;
    private String name;
    private EditText sname;
    private EditText email;
    private TextView dob11;
    private EditText ques;
    private EditText ans;
    String qq;
    DatePickerDialog.OnDateSetListener setListener;
    TextView from_date;
    CardView cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        /*Databasehelper db = new Databasehelper(this);




        List<List<String>> x;
        x = db.getCategories();
        List<String> cat = x.get(2);
        Toast.makeText(this,cat.get(3),Toast.LENGTH_SHORT).show();*/
        cardview = findViewById(R.id.save_edit_cd);
        sname=findViewById(R.id.name_edit);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        int salary = Integer.parseInt(sp.getString("com.start.salary","0"));
        salary_edit=findViewById(R.id.edit_salary);
        email=findViewById(R.id.email_edit);
        from_date=findViewById(R.id.dob);
        ques=findViewById(R.id.ques_edit);
        ans=findViewById(R.id.ans_edit);


        sname.setText(sp.getString("com.start.user_name","name"));
        salary_edit.setText(sp.getString("com.start.salary","salary"));
        email.setText(sp.getString("com.start.user_mail","email"));
        from_date.setText(sp.getString("com.start.user_dob","DOB"));
        ques.setText(sp.getString("com.start.question","question"));
        qq=sp.getString("com.start.question","question");
        ans.setText(sp.getString(qq,"answer"));

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Profile");

        Calendar calendar= Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(profile.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                from_date.setText(date);
            }
        };

        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(profile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;
                        from_date.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        /*btn.setOnClickListener(new View.OnClickListener() {
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
        });*/

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_st = sname.getText().toString();
                String email_st = email.getText().toString();
                String dob_st = from_date.getText().toString();
                String salary_st = salary_edit.getText().toString();
                String ques_st = ques.getText().toString();
                String ans_st = ans.getText().toString();
                 String oldemail = sp.getString("com.start.user_mail","");
                 String pswd = sp.getString(oldemail,"");
                if(!(name_st.equals("")) && !(email_st.equals("")) && !(dob_st.equals("")) && !(salary_st.equals("")) && !(ques_st.equals("")) && !(ans_st.equals("")))
                {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("com.start.user_name", name_st);
                    editor.putString("com.start.user_mail",email_st);
                    editor.putString("com.start.user_dob",dob_st);
                    editor.putString(email_st, pswd);
                    editor.putString(ques_st,ans_st);
                    editor.putString("com.start.question",ques_st);
                    editor.commit();


                    Intent intent = new Intent(profile.this, home.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(profile.this,"fill the blank info",Toast.LENGTH_LONG).show();
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

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(profile.this,home.class);
        startActivity(intent);
        finish();
    }
}


