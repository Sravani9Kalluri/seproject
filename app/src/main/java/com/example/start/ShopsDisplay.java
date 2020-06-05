package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShopsDisplay extends AppCompatActivity {
    private ActionBar actionBar;
    FloatingActionButton actionButton;
    private ArrayList<ModelShopsList> modelShopsLists;
    private AdapterShopsList adapterShops  ;
    private RecyclerView usersRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_display);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Shops");

        usersRv=findViewById(R.id.usersRv111);
        actionButton = findViewById(R.id.fab_btn111);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        usersRv.setLayoutManager(llm);
        usersRv.setAdapter(adapterShops);
        loadInfo();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShopsDisplay.this,addshop.class);
                startActivity(intent);

            }
        });

    }

    private void loadInfo(){
        modelShopsLists=new ArrayList<>();
        List<List<String>> x;
        Databasehelper db = new Databasehelper(this);
        x = db.getshops();
        List<String> shop = x.get(0);
        List<String> cat = x.get(1);
        for (int i=0;i<cat.size();i++){

            ModelShopsList model = new ModelShopsList(shop.get(i),cat.get(i));
            modelShopsLists.add(model);
            adapterShops=new AdapterShopsList(ShopsDisplay.this,modelShopsLists);
            usersRv.setAdapter(adapterShops);
        }

    }
    //
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
