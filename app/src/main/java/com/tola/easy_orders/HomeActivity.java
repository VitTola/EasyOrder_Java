package com.tola.easy_orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tola.easy_orders.Models.Products;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private final List<Products> defaultProducts = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerview_product);

        defaultProducts.add(new Products("Dell Xs15","The best quality from USA","1300",R.drawable.microsoft,"","","",""));
        defaultProducts.add(new Products("Dell Xs13","The best quality from USA","2000",R.drawable.apple1,"","","",""));
        defaultProducts.add(new Products("Dell Xs15","The best quality from USA","800",R.drawable.hp,"","","",""));
        defaultProducts.add(new Products("Dell Xs15","The best quality from USA","1600",R.drawable.microsoft,"","","",""));
        defaultProducts.add(new Products("Dell Xs15","The best quality from USA","700",R.drawable.accer,"","","",""));
        defaultProducts.add(new Products("Dell Xs15","The best quality from USA","960",R.drawable.apple1,"","","",""));

        MyAdapter myAdapter = new MyAdapter(this,defaultProducts);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}