package com.tola.easy_orders;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tola.easy_orders.Models.Products;
import com.tola.easy_orders.ViewHolder.ProductViewHolder;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ProductViewHolder> {


    List<Products> products ;
    Context context;

    public MyAdapter(Context context, List<Products> defaultProducts) {
        this.products = defaultProducts;
        this.context = context;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main_display,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txtProduct.setText(products.get(position).getPname());
        holder.txtProductPrice.setText(products.get(position).getPrice());
        holder.txtProductDescription.setText(products.get(position).getDescription());
        holder.imageViewer.setImageResource(R.drawable.apple1);


    }

    @Override
    public int getItemCount() {
        return products.size() ;
    }
}
