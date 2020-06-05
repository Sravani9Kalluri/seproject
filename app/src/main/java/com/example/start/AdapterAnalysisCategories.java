package com.example.start;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAnalysisCategories extends RecyclerView.Adapter<AdapterAnalysisCategories.HolderAnalysis> {
    private Context context;
    private ArrayList<ModelCategoriesList> categoriesLists;

    public AdapterAnalysisCategories(Context context,ArrayList<ModelCategoriesList> categoriesLists){
        this.context=context;
        this.categoriesLists=categoriesLists;

    }

    @NonNull
    @Override
    public HolderAnalysis onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.list_analysis_category,parent,false);
        return new HolderAnalysis(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAnalysis holder, int position) {
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

    public class HolderAnalysis extends RecyclerView.ViewHolder{

        private TextView categoryTv,limitTv,amountTv;
        private RelativeLayout relative_select;

        public HolderAnalysis(@NonNull View itemView) {
            super(itemView);

            categoryTv=itemView.findViewById(R.id.category_name1);
            amountTv=itemView.findViewById(R.id.act_cat_amount1);
            limitTv=itemView.findViewById(R.id.act_cat_limit1);
            relative_select=itemView.findViewById(R.id.relative_select1);
        }
    }

}
