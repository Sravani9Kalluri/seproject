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

public class AdapterTransactions extends RecyclerView.Adapter<AdapterTransactions.HolderTransactions>{
    private Context context;
    private ArrayList<ModelTransactions> transactions;

    public AdapterTransactions(Context context,ArrayList<ModelTransactions> transactions){
        this.context=context;
        this.transactions=transactions;

    }

    @NonNull
    @Override
    public HolderTransactions onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_transaction,parent,false);
        return new HolderTransactions(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTransactions holder, int position) {
        final ModelTransactions model = transactions.get(position);

        double amount=Double.parseDouble(model.getAmount());
        String shop=model.getShop();
        String category=model.getCategory();
        String date=model.getDate();
        final String id=model.getId();

        holder.amountTv.setText(Double.toString(amount));
        holder.shopTv.setText(shop);
        holder.categoryTv.setText(category);
        holder.dateTv.setText(date);

        /*holder.relative_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,EditShopActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return transactions.size();
    }



    public class HolderTransactions extends RecyclerView.ViewHolder {

        private TextView amountTv,shopTv,categoryTv,dateTv;
        private RelativeLayout relative_select;
        private FloatingActionButton actionButton;

        public HolderTransactions(@NonNull View itemView) {
            super(itemView);

            amountTv=itemView.findViewById(R.id.trans_amt);
            shopTv=itemView.findViewById(R.id.act_trans_shop);
            categoryTv=itemView.findViewById(R.id.act_trans_cat);
            dateTv=itemView.findViewById(R.id.act_trans_date);
            relative_select=itemView.findViewById(R.id.relative_select);
            actionButton=itemView.findViewById(R.id.des_check_btn);

        }
    }
}
