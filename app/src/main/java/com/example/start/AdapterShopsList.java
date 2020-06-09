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
        final String shop=model.getShop();
        final String category=model.getCategory();


        holder.shopTv.setText(shop);
        holder.categoryTv.setText(category);

        /*holder.relative_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        holder.actionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, EditShopActivity.class);
                intent.putExtra("shopname",shop);
                context.startActivity(intent);
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
        private FloatingActionButton actionButton1;

        public HolderShops(@NonNull View itemView) {
            super(itemView);

            shopTv=itemView.findViewById(R.id.shop_name);
            categoryTv=itemView.findViewById(R.id.shop_cat_disp);
            relative_select=itemView.findViewById(R.id.relative_select111);
            actionButton1=itemView.findViewById(R.id.des_check_btn1);

        }
    }

}
