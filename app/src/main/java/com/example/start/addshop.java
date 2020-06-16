package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addshop extends AppCompatActivity {

    private ActionBar actionBar;
    String cats[];
    CardView cardview;
    private Spinner spinner;
    private EditText sname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshop);

        cardview = findViewById(R.id.savenewshop);
        spinner=findViewById(R.id.setcategory);
        sname=findViewById(R.id.sname);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("New Shop");



        final Databasehelper db = new Databasehelper(this);
        List<List<String>> x;
        x = db.getCategories();
        final List<String> cat = x.get(0);
        cats=new String[cat.size()];


        List<String> cats1=new ArrayList<>();
        cats1.add(0,"Set category to");
        for(int i=0;i<cat.size();i++){
            cats1.add(i+1,cat.get(i));
        }
        final int[] pos = {0};
        ArrayAdapter<String> dataAdapter;
        dataAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,cats1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Change Category"))
                {
                    //null value
                    Toast.makeText(parent.getContext(), "Selected null value", Toast.LENGTH_SHORT).show();
                }
                else{
                    String item=parent.getItemAtPosition(position).toString();
                    pos[0] =position;

                    Toast.makeText(parent.getContext(), "Selected"+item, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selected null value11", Toast.LENGTH_SHORT).show();
            }
        });

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname_st=sname.getText().toString();
                Intent intent = new Intent(addshop.this,ShopsDisplay.class);
                if(sname.equals("")){
                    Toast.makeText(addshop.this,"enter shop name", Toast.LENGTH_SHORT).show();
                }
                else if(pos[0] ==0){
                    Toast.makeText(addshop.this,"Select valid category", Toast.LENGTH_SHORT).show();

                }
                else{
                    Log.e("cat",cat.get(pos[0] - 1));
                    db.addnewshop(sname_st,cat.get(pos[0] - 1));
                    Toast.makeText(addshop.this,"updated successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
        });



        /*cats=new String[cat.size()];
        cats[0]="Select category";
        for(int i=0;i<cat.size();i++){
            cats[i+1]=cat.get(i);
        }

        Toast.makeText(this,cat.get(0),Toast.LENGTH_SHORT).show();
        Spinner spinner = (Spinner) findViewById(R.id.setcategory);

       ArrayAdapter<String> adp=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,cats);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(adp);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Add Shop");*/

        // Create an ArrayAdapter using the string array and a default spinner layout
       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.selectcategory_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(addshop.this,ShopsDisplay.class);
        startActivity(intent);
        finish();
    }
}
