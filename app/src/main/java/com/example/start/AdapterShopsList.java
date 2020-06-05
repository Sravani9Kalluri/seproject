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

public class AdapterShopsList extends RecyclerView.Adapter<AdapterShopsList.HolderShops> {
    private Context context;
    private ArrayList<ModelShopsList> shopsLists;

    public AdapterShopsList(Context context,ArrayList<ModelShopsList> shopsLists){
        this.context=context;
        this.shopsLists=shopsLists;

    }

    @NonNull
    @Override
    public AdapterShopsList.HolderShops onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_shop,parent,false);
        return new AdapterShopsList.HolderShops(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShopsList.HolderShops holder, int position) {
        final ModelShopsList model = shopsLists.get(position);
        String shop=model.getShop();
        String category=model.getCategory();


        holder.shopTv.setText(shop);
        holder.categoryTv.setText(category);

        holder.relative_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return shopsLists.size();
    }


    public class HolderShops extends RecyclerView.ViewHolder {

        private TextView categoryTv,shopTv;
        private RelativeLayout relative_select;

        public HolderShops(@NonNull View itemView) {
            super(itemView);

            shopTv=itemView.findViewById(R.id.shop_name);
            categoryTv=itemView.findViewById(R.id.shop_cat_disp);
            relative_select=itemView.findViewById(R.id.relative_select111);


        }
    }

}
