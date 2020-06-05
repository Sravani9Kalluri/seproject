package com.example.start;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.amountTv.setText(Double.toString(amount));
        holder.limitTv.setText(Double.toString(limit));

        holder.relative_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesLists.size();
    }

    public class HolderCategories extends RecyclerView.ViewHolder {

        private TextView categoryTv,limitTv,amountTv;
        private RelativeLayout relative_select;

        public HolderCategories(@NonNull View itemView) {
            super(itemView);

            categoryTv=itemView.findViewById(R.id.category_name);
            amountTv=itemView.findViewById(R.id.act_cat_amount);
            limitTv=itemView.findViewById(R.id.act_cat_limit);
            relative_select=itemView.findViewById(R.id.relative_select);

        }
    }
}




