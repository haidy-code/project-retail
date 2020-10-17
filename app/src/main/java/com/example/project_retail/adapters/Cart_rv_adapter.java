package com.example.project_retail.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_retail.R;
import com.example.project_retail.models.ProductsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Cart_rv_adapter extends RecyclerView.Adapter<Cart_rv_adapter.Cartviewholder> {
    List<ProductsModel> productsList;
    Context context;
    OnIncButtonClicked onIncButtonClicked;
    OnDecButtonClicked onDecButtonClicked;
    public interface OnIncButtonClicked{
        void onincbuttonclicked(View view,int positioin);
    }
    public interface OnDecButtonClicked{
        void ondecbuttonclicked(View view,int positioin);
    }

    public Cart_rv_adapter(List<ProductsModel> productsList, Context context, OnIncButtonClicked onIncButtonClicked, OnDecButtonClicked onDecButtonClicked) {
        this.productsList = productsList;
        this.context = context;
        this.onIncButtonClicked = onIncButtonClicked;
        this.onDecButtonClicked = onDecButtonClicked;
    }

    @NonNull
    @Override
    public Cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_rv_item,parent,false);
        return new Cartviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cartviewholder holder, final int position) {
        ProductsModel getproducts=productsList.get(position);
        holder.cart_product_title.setText(getproducts.getTitle());
        holder.cart_product_details.setText(getproducts.getDetails());
        holder.cart_product_price.setText(getproducts.getFinalPriceText());
        holder.cart_quantity.setText(getproducts.getQuantity()+"");
        Glide.with(context).load(getproducts.getPhoto()).into(holder.cart_image);
        holder.cart_inc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onIncButtonClicked.onincbuttonclicked(view,position);
            }
        });
        holder.cart_dec_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDecButtonClicked.ondecbuttonclicked(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class Cartviewholder extends RecyclerView.ViewHolder{
ImageView cart_image;
TextView cart_product_title;
TextView cart_product_details;
TextView cart_product_price;
ImageButton cart_inc_btn;
ImageButton cart_dec_btn;
TextView cart_quantity;

        public Cartviewholder(@NonNull View itemView) {
            super(itemView);
            cart_image=itemView.findViewById(R.id.cart_iv);
            cart_product_title=itemView.findViewById(R.id.cart_product_title_tv);
            cart_product_details=itemView.findViewById(R.id.cart_product_details_tv);
            cart_product_price=itemView.findViewById(R.id.cart_product_price_tv);
            cart_inc_btn=itemView.findViewById(R.id.cart_inc_btn);
            cart_dec_btn=itemView.findViewById(R.id.cart_dec_btn);
            cart_quantity=itemView.findViewById(R.id.cart_quantity_tv);

        }
    }
}
