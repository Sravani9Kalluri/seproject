package com.example.start;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class addcategory extends AppCompatActivity {

    CardView addcat;
    Toolbar toolbar;
    private ActionBar actionBar;
    FloatingActionButton actionButton;
    private ArrayList<ModelCategoriesList> modelCategoriesLists;
    private AdapterCategories adapterCategories  ;
    private RecyclerView usersRv;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcategory);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Categories");

        usersRv=findViewById(R.id.usersRv11);
        actionButton = findViewById(R.id.fab_btn);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        usersRv.setLayoutManager(llm);
        usersRv.setAdapter(adapterCategories);
        loadInfo();


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addcategory.this,createCategory.class);
                startActivity(intent);

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

    private void loadInfo(){
        modelCategoriesLists=new ArrayList<>();
        List<List<String>> x;
        Databasehelper db = new Databasehelper(this);
        x = db.getCategories();
        List<String> cat = x.get(0);
        List<String> limit = x.get(1);
        List<String> amount = x.get(2);
        for (int i=0;i<cat.size();i++){

            ModelCategoriesList model = new ModelCategoriesList(cat.get(i),Double.parseDouble(limit.get(i)),Double.parseDouble(amount.get(i)));
            modelCategoriesLists.add(model);
            adapterCategories=new AdapterCategories(addcategory.this,modelCategoriesLists);
            usersRv.setAdapter(adapterCategories);
        }

    }
//
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
