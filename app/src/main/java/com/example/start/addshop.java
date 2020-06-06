package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class addshop extends AppCompatActivity {

    private ActionBar actionBar;
    String cats[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshop);
        Databasehelper db = new Databasehelper(this);
        List<List<String>> x;
        x = db.getCategories();
        List<String> cat = x.get(0);
        cats=new String[cat.size()];
        for(int i=0;i<cat.size();i++){
            cats[i]=cat.get(i);
        }

        Toast.makeText(this,cat.get(0),Toast.LENGTH_SHORT).show();
        Spinner spinner = (Spinner) findViewById(R.id.setcategory);

       ArrayAdapter<String> adp=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,cats);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(adp);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Add Shop");

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


}
