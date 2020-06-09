package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class createCategory extends AppCompatActivity {

    private ActionBar actionBar;
    private EditText new_cat;
    private EditText new_limit;
    CardView cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Create Category");

        new_cat=findViewById(R.id.newcat);
        new_limit=findViewById(R.id.new_limit1);
        cardview=findViewById(R.id.savenewcat1);
        final Databasehelper db = new Databasehelper(this);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createCategory.this,addcategory.class);
                String limit_st = new_limit.getText().toString();
                String cat_st = new_cat.getText().toString();
                if(cat_st.equals("")){
                    Toast.makeText(createCategory.this,"Category name is empty", Toast.LENGTH_SHORT).show();
                }
                else if(limit_st.equals("")){
                    Toast.makeText(createCategory.this,"limit field is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addnewcat(cat_st,limit_st);
                    Toast.makeText(createCategory.this,"created successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }


            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
