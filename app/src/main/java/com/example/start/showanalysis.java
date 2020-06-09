package com.example.start;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

public class showanalysis extends AppCompatActivity {

    private ActionBar actionBar;
    private ArrayList<ModelCategoriesList> modelCategoriesLists;
    private AdapterAnalysisCategories adapterAnalysisCategories  ;
    private RecyclerView catRv;
    ExtendedFloatingActionButton actionButton111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showanalysis);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> Series = new BarGraphSeries<>(data());
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(labels());
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        Series.setSpacing(60);
        Series.setDrawValuesOnTop(true);
        Series.setValuesOnTopColor(Color.RED);
        graph.addSeries(Series);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Analysis");

        catRv=findViewById(R.id.catRv);
        actionButton111=findViewById(R.id.transactions_btn);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        catRv.setLayoutManager(llm);
        catRv.setAdapter(adapterAnalysisCategories);
        loadInfo();

        actionButton111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(showanalysis.this,RangeActivity.class);
                startActivity(intent);

            }
        });
    }
    private DataPoint[] data(){
        List<List<String>> x;
        Databasehelper db = new Databasehelper(this);
        x = db.getCategories();
        List<String> amount = x.get(2);
        int n = amount.size();
        DataPoint[] values = new DataPoint[n];
        for(int i = 0;i < n;i++){
            DataPoint v = new DataPoint(i + 1,Integer.parseInt(amount.get(i)));
            values[i] = v;
        }
        return values;
    }
    private String[] labels(){
        List<List<String>> x;
        Databasehelper db = new Databasehelper(this);
        x = db.getCategories();
        List<String> cat = x.get(0);
        int n = cat.size();
        String[] res = new String[n];
        for(int i = 0;i<n;i++){
            int end = 6;
            if(end > cat.get(i).length() ){
                end = cat.get(i).length() ;
            }
            res[i] = cat.get(i).substring(0,end);
        }
        return res;
    }

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
            adapterAnalysisCategories=new AdapterAnalysisCategories(showanalysis.this,modelCategoriesLists);
            catRv.setAdapter(adapterAnalysisCategories);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_sign_out) {
            // do something
            Intent i = new Intent(showanalysis.this, login.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(showanalysis.this,home.class);
        startActivity(intent);
        finish();
    }
}
