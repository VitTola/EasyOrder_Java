package com.tola.easy_orders.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tola.easy_orders.R;

import com.tola.easy_orders.Interface.ItemClickListener;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProduct, txtProductDescription, txtProductPrice;
    public ImageView imageViewer;
    public ItemClickListener itemClickListener;

    public ProductViewHolder(View itemView) {
        super(itemView);
        imageViewer = (ImageView) itemView.findViewById(R.id.product_image);
        txtProduct = (TextView) itemView.findViewById(R.id.product_name);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price);
    }
    public void setItemClickListener(ItemClickListener listener){
        this.itemClickListener = listener;
    }

    @Override
    public void onClick(View v) {

    }
}
