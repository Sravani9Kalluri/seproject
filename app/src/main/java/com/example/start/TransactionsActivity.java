package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class TransactionsActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private ArrayList<ModelTransactions> transactions;
    private AdapterTransactions adapterTransactions  ;
    private RecyclerView usersRv;
    Button rangebtn;
    private TextView dateDisp;
    private String from_date,to_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Intent iin=getIntent();
        Bundle b=iin.getExtras();
        if(b!=null){
            from_date=(String)b.get("date1");
            to_date=(String)b.get("date2");
        }

        /*from_date=getIntent().getStringExtra("date1");
        to_date=getIntent().getStringExtra("date2");*/

        Toast.makeText(TransactionsActivity.this, from_date+","+to_date, Toast.LENGTH_SHORT).show();

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(from_date+" - "+to_date);

        usersRv=findViewById(R.id.transRv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        usersRv.setLayoutManager(llm);
        usersRv.setAdapter(adapterTransactions);

        loadInfo();



    }

    private void loadInfo(){
        //transactions=new ArrayList<>();
        List<List<String>> x;
        Databasehelper db = new Databasehelper(this);
        x = db.gettransactions(from_date,to_date);
        Log.e("x", String.valueOf(x));
        if(x.size() == 0){
            Toast.makeText(TransactionsActivity.this,"No transactions to show", Toast.LENGTH_SHORT).show();
        }

        /*List<String> amt = x.get(0);
        List<String> shop = x.get(1);
        List<String> cat = x.get(2);
        List<String> date = x.get(3);

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
        }*/

    }
    //
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
