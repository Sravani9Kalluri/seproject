package com.example.start;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.HolderCategories>{
    private Context context;
    private ArrayList<ModelCategoriesList> categoriesLists;

    public AdapterCategories(Context context,ArrayList<ModelCategoriesList> categoriesLists){
        this.context=context;
        this.categoriesLists=categoriesLists;

    }

    @NonNull
    @Override
    public HolderCategories onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.list_category,parent,false);
        return new HolderCategories(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategories holder, int position) {
        final ModelCategoriesList model = categoriesLists.get(position);
        String category=model.getCategory();
        double limit=Double.parseDouble(model.getLimit());
        double amount=Double.parseDouble(model.getAmount());

        holder.categoryTv.setText(category);
        holder.limitTv.setText(Double.toString(limit));

        /*holder.relative_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,EditCategoryActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesLists.size();
    }



    public class HolderCategories extends RecyclerView.ViewHolder {

        private TextView categoryTv,limitTv;
        private RelativeLayout relative_select;
        private FloatingActionButton actionButton;

        public HolderCategories(@NonNull View itemView) {
            super(itemView);

            categoryTv=itemView.findViewById(R.id.category_name);
            limitTv=itemView.findViewById(R.id.act_cat_limit);
            relative_select=itemView.findViewById(R.id.relative_select);
            actionButton=itemView.findViewById(R.id.des_check_btn);

        }
    }
}




