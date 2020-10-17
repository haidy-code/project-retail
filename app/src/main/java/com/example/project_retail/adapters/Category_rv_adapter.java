package com.example.project_retail.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_retail.R;
import com.example.project_retail.models.CategoryModel;
import com.example.project_retail.models.ProductsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Category_rv_adapter extends RecyclerView.Adapter<Category_rv_adapter.categoriesviewholder> {
    private List<CategoryModel> categoryList;
    Context context;

    public Category_rv_adapter(List<CategoryModel> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public categoriesviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv_item,parent,false);
      categoriesviewholder categoriesviewholder=new categoriesviewholder(view);
        return categoriesviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull categoriesviewholder holder, int position) {
        CategoryModel categoryModel=categoryList.get(position);
        Glide.with(context).load(categoryModel.getCategoryphoto()).into(holder.categoryimage);
        holder.categoryname.setText(categoryModel.getCategoryname());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class categoriesviewholder extends RecyclerView.ViewHolder{
        ImageView categoryimage;
        TextView categoryname;

        public categoriesviewholder(@NonNull View itemView) {
            super(itemView);
            categoryimage=itemView.findViewById(R.id.category_iv);
            categoryname=itemView.findViewById(R.id.category_name_tv);
        }
    }
}
