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

public class Products_rv_adapter extends RecyclerView.Adapter<Products_rv_adapter.productsviewholder> {
    private List<ProductsModel> productsList;
    Context context;
    private OnProductClickListener onProductClickListener;
    private OnAddProductClickListener onAddProductClickListener;
    public interface OnProductClickListener {
        void onProductClick(View view, int position);
    }
    public interface OnAddProductClickListener {
        void onAddProductClick(View view, int position);
    }

    public Products_rv_adapter(List<ProductsModel> productsList, Context context, OnProductClickListener onProductClickListener, OnAddProductClickListener onAddProductClickListener) {
        this.productsList = productsList;
        this.context = context;
        this.onProductClickListener = onProductClickListener;
        this.onAddProductClickListener = onAddProductClickListener;
    }

    @NonNull
    @Override
    public productsviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.products_rv_item,parent,false);
        productsviewholder productsviewholder=new productsviewholder(view);
        return productsviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final productsviewholder holder, int position) {
ProductsModel productsModel=productsList.get(position);
holder.title.setText(productsModel.getTitle());
holder.decription.setText(productsModel.getDetails());
holder.price.setText(productsModel.getFinalPriceText());
Glide.with(context).load(productsModel.getPhoto()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onProductClickListener.onProductClick(view, holder.getAdapterPosition());
            }
        });
        holder.add_to_cart_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddProductClickListener.onAddProductClick(view,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class productsviewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView decription;
        TextView price;
        ImageButton add_to_cart_botton;
    public productsviewholder(@NonNull View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.product_iv);
        title=itemView.findViewById(R.id.product_title_tv);
        decription=itemView.findViewById(R.id.product_description_tv);
        price=itemView.findViewById(R.id.product_price_tv);
        add_to_cart_botton=itemView.findViewById(R.id.add_product_btn);

    }
}
}
