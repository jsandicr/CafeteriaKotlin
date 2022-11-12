package com.jsandi.cafeteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.NonNull
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.jsandi.cafeteria.data.Product

class PrincipalActivity : AppCompatActivity() {

    RecyclerView recyclerView;
    DatabaseReference database;
    ProductAdapter adapter;
    ArrayList<Product> list;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        recyclerView = findViewById(R.id.productList)
        database = FirebaseDatabase.getInstance().getReference("Products");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ProductAdapter(this, list);
        recyclerView.setAdapter(adapter);
        database.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    list.add(product)
                }
                adapter.notifyDataSetChanged();
            }

            @
        })
    }
}