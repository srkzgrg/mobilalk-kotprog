package hu.mobilalk.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.order_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_button:
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(this, LoginActivity.class);
                startActivity(login);
                return true;
            case R.id.bag:
                Intent intent = new Intent(this, ShopActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}