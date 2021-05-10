package com.tola.easy_orders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.tola.easy_orders.Interface.ItemClickListener;
import com.tola.easy_orders.Models.Products;

public class MainDisplayActivity extends AppCompatActivity {

    private final List<Products> defaultProducts = new ArrayList<>();
    public TextView txtProduct, txtProductDescription, txtProductPrice;
    public ImageView imageViewer;
    public ItemClickListener itemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);
            imageViewer = (ImageView) findViewById(R.id.product_image);
            txtProduct = (TextView) findViewById(R.id.product_name);
            txtProductDescription = (TextView) findViewById(R.id.product_description);
            txtProductPrice = (TextView) findViewById(R.id.product_price);

//                defaultProducts.add(new Products("Dell Xs15","The best quality from USA","1300","","","","",""));
//                defaultProducts.add(new Products("Dell Xs13","The best quality from USA","2000","","","","",""));
//                defaultProducts.add(new Products("Dell Xs15","The best quality from USA","800","","","","",""));
//                defaultProducts.add(new Products("Dell Xs15","The best quality from USA","1600","","","","",""));
//                defaultProducts.add(new Products("Dell Xs15","The best quality from USA","700","","","","",""));
//                defaultProducts.add(new Products("Dell Xs15","The best quality from USA","960","","","","",""));

        for ( Products p: defaultProducts){
                txtProduct.setText(p.getPname());
                txtProductDescription.setText(p.getDescription());
                txtProductPrice.setText(p.getPrice());

        }









    }
}