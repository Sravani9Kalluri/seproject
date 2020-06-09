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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditShopActivity extends AppCompatActivity {

    private ActionBar actionBar;
    String cats[];
    CardView cardview1;
    CardView cardview2;
    private String shop;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);

        cardview1 = findViewById(R.id.savenewshop);
        cardview2 = findViewById(R.id.remove_new_shop);
        spinner=findViewById(R.id.setcategory);

        Intent iin=getIntent();
        Bundle b=iin.getExtras();
        if(b!=null){
           shop=(String)b.get("shopname");
        }

        final Databasehelper db = new Databasehelper(this);
        List<List<String>> x;
        x = db.getCategories();
        final List<String> cat = x.get(0);
        cats=new String[cat.size()];


        List<String> cats1=new ArrayList<>();
        cats1.add(0,"Change category to");
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
                    //update db using shop,position and item values
                    Toast.makeText(parent.getContext(), "Selected"+item, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selected null value11", Toast.LENGTH_SHORT).show();
            }
        });

       /* cats[0]="Change category to";
        for(int i=0;i<cat.size();i++){
            cats[i+1]=cat.get(i);
        }

        Toast.makeText(this,cat.get(0),Toast.LENGTH_SHORT).show();
        Spinner spinner = (Spinner) findViewById(R.id.setcategory);

        ArrayAdapter<String> adp=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,cats);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);*/

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(shop);

        cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditShopActivity.this,ShopsDisplay.class);
                if(pos[0] ==0){
                    Toast.makeText(EditShopActivity.this,"Select valid category", Toast.LENGTH_SHORT).show();

                }
                else{
                    Log.e("cat",cat.get(pos[0] - 1));
                    db.editshop(shop,cat.get(pos[0] - 1));
                    Toast.makeText(EditShopActivity.this,"updated successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
        });


        cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditShopActivity.this,ShopsDisplay.class);
                    db.deleteshop(shop);
                    Toast.makeText(EditShopActivity.this,"removed successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(EditShopActivity.this,ShopsDisplay.class);
        startActivity(intent);
        finish();
    }
}

