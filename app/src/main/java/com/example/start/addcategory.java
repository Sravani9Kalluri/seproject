package com.example.start;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

public class addcategory extends AppCompatActivity {

    CardView addcat;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcategory);

        addcat = findViewById(R.id.addcat1);
        addcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addcategory.this,createCategory.class);
                startActivity(intent);
                finish();
            }
        });
        /*toolbar = (Toolbar)findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
*/
    }


    //@Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id=item.getItemId();
//
//        if(id==R.id.home){
//            this.finish();
//        }
//        return super.onOptionsItemSelected(item);
//        }
//
}
