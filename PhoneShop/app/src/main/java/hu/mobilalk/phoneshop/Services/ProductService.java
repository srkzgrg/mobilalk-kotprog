package hu.mobilalk.phoneshop.Services;


import android.annotation.SuppressLint;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import hu.mobilalk.phoneshop.Models.Product;
import hu.mobilalk.phoneshop.Adapters.ProductAdapter;


public class ProductService {
    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final CollectionReference mItems = mFirestore.collection("Product");

    @SuppressLint("NotifyDataSetChanged")
    public void listProducts(ArrayList<Product> mProducts, ProductAdapter mAdapter){
        mItems.orderBy("model", Query.Direction.DESCENDING).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Product item = document.toObject(Product.class);
                mProducts.add(item);
            }

            // Notify the adapter of the change.
            mAdapter.notifyDataSetChanged();
        });
    }




}
