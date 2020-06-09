package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class EditCategoryActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private String category;
    CardView cardview;
    private EditText new_limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Edit Limit");

        cardview = findViewById(R.id.savenewlimit);
        new_limit=findViewById(R.id.new_limit);


        Intent iin=getIntent();
        Bundle b=iin.getExtras();
        if(b!=null){
            category=(String)b.get("category");
        }
        Toast.makeText(EditCategoryActivity.this,category, Toast.LENGTH_SHORT).show();
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditCategoryActivity.this,addcategory.class);
                String limit_st = new_limit.getText().toString();
                if(limit_st.equals("")){
                    Toast.makeText(EditCategoryActivity.this,"enter a limit", Toast.LENGTH_SHORT).show();

                }
                else{
                    //function to update limit in db
                    Toast.makeText(EditCategoryActivity.this,"updated successfully", Toast.LENGTH_SHORT).show();
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
