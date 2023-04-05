package hu.mobilalk.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import hu.mobilalk.phoneshop.Adapters.CartAdapter;
import hu.mobilalk.phoneshop.Adapters.OrderAdapter;
import hu.mobilalk.phoneshop.Models.Cart;
import hu.mobilalk.phoneshop.Models.Order;
import hu.mobilalk.phoneshop.Notafications.NotaficationConfig;
import hu.mobilalk.phoneshop.R;
import hu.mobilalk.phoneshop.Services.CartService;
import hu.mobilalk.phoneshop.Services.OrderService;

public class OrderActivity extends AppCompatActivity {

    private FirebaseUser user;
    private int gridNumber = 1;

    private RecyclerView mRecyclerView;
    private ArrayList<Order> mProducts;
    private OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(
                this, gridNumber));
        mProducts = new ArrayList<>();
        mAdapter = new OrderAdapter(this, mProducts);
        mRecyclerView.setAdapter(mAdapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            new OrderService().getRendelesByUserID(mProducts, user.getUid(), upt ->{ mAdapter.notifyDataSetChanged();});
        } else {
            finish();
        }
    }
}