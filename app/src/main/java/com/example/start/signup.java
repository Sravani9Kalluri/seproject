package com.example.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.DatePickerDialog;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    CardView cardview;
    SharedPreferences sp;
    TextView textlogin;
    EditText name;
    EditText email;
    EditText password;
    EditText confirmpassword;
    TextView dob;
    //ImageView dateclick;
    EditText ques;
    EditText ans;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        cardview = findViewById(R.id.cardview);
        textlogin = findViewById(R.id.textlogin);
        name = findViewById(R.id.name);
        email = findViewById(R.id.gmail);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        dob = findViewById(R.id.dob);
        //dateclick = findViewById(R.id.dateclick);
        ques = findViewById(R.id.ques);
        ans = findViewById(R.id.ans);

        textlogin.setPaintFlags(textlogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        if(sp.getString("com.start.user_mail",null) != null){
            Toast.makeText(signup.this,"you are already registered with " + sp.getString("com.start.user_mail",null),Toast.LENGTH_LONG).show();
        }

        Calendar calendar= Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(signup.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                dob.setText(date);
            }
        };

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;
                        dob.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String cpassword = confirmpassword.getText().toString();
                String dob1 = dob.getText().toString();
                String ques1 = ques.getText().toString();
                String ans1 = ans.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                if(!(name1.equals("")) && !(email1.equals("")) && !(password1.equals("")) && !(cpassword.equals("")) && !(dob1.equals(""))&&!(ques1.equals("")) && !(ans1.equals(""))){
                if(name1.matches("[a-zA-z\\s]+")){
                if(checkvalidmail(email)) {
                    if(password1.equals(cpassword)){
                        if(password1.length() == 4){

                        editor.putString("com.start.user_name", name1);
                        editor.putString("com.start.user_mail", email1);
                        editor.putString("com.start.user_dob", dob1);
                        editor.putString(email1, password1);
                        editor.putString(ques1,ans1);
                        editor.putString("com.start.question",ques1);
                        editor.commit();
                        Intent intent = new Intent(signup.this,home.class);
                        startActivity(intent);
                        finish();}
                        else{
                            Toast.makeText(signup.this,"password should contain minimum 4 digits",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(signup.this,"password and confirm password are not same",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(signup.this,"enter valid email",Toast.LENGTH_LONG).show();
                }
                }
                else{
                    Toast.makeText(signup.this,"enter a valid name!",Toast.LENGTH_LONG).show();
                }

            }
            else{
                Toast.makeText(signup.this,"complete the form!",Toast.LENGTH_LONG).show();
            }}
        });

        textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,login.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private boolean checkvalidmail(EditText email){
        String check = email.getText().toString();

        if(!check.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(check).matches()){
            return true;
        }else {
            return false;
        }
    }
}