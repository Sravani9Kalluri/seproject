package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TransactionsActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private ArrayList<ModelTransactions> transactions;
    private AdapterTransactions adapterTransactions  ;
    private RecyclerView usersRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Transactions");

        usersRv=findViewById(R.id.transRv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        usersRv.setLayoutManager(llm);
        usersRv.setAdapter(adapterTransactions);
        loadInfo();

    }

    private void loadInfo(){
        transactions=new ArrayList<>();
        /*List<List<String>> x;
        Databasehelper db = new Databasehelper(this);
        x = db.getshops();
        List<String> amt = x.get(0);
        List<String> shop = x.get(1);
        List<String> cat = x.get(2);
        List<String> date = x.get(3);*/

        List<String> amt = null;
        List<String> shop = null;
        List<String> cat = null;
        List<String> date = null;
        amt.add("60");
        amt.add("600");
        amt.add("ABC");
        amt.add("XYZ");
        amt.add("Food");
        amt.add("Bills");
        amt.add("13/5/2020");
        amt.add("10/5/2020");
        for (int i=0;i<cat.size();i++){

            ModelTransactions model = new ModelTransactions(Double.parseDouble(amt.get(i)),shop.get(i),cat.get(i),date.get(i));
            transactions.add(model);
            adapterTransactions=new AdapterTransactions(TransactionsActivity.this,transactions);
            usersRv.setAdapter(adapterTransactions);
        }

    }
    //
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
