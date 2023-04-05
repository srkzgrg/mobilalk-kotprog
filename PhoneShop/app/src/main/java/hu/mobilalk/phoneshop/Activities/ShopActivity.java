package hu.mobilalk.phoneshop.Activities;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import hu.mobilalk.phoneshop.Models.Order;
import hu.mobilalk.phoneshop.Models.Product;
import hu.mobilalk.phoneshop.Adapters.ProductAdapter;
import hu.mobilalk.phoneshop.R;
import hu.mobilalk.phoneshop.Services.CartService;
import hu.mobilalk.phoneshop.Services.ProductService;

public class ShopActivity extends AppCompatActivity {

    private static final String LOG_TAG = ShopActivity.class.getName();
    private FirebaseUser user;
    private int gridNumber = 1;
    private FrameLayout badge;
    private TextView countTextView;

    private RecyclerView mRecyclerView;
    private ArrayList<Product> mProducts;
    private ProductAdapter mAdapter;
    private int kosarmennyiseg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.kosarmennyiseg = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(
                this, gridNumber));
        mProducts = new ArrayList<>();
        mAdapter = new ProductAdapter(this, mProducts);
        mRecyclerView.setAdapter(mAdapter);

        //autentikació
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            Log.d(LOG_TAG, "Authenticated user!");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }

        new ProductService().listProducts(mProducts, mAdapter);

        updateKosarIcon();
    }


    public void addToCart(Product currentItem) throws InterruptedException {
        new CartService().updateCartByUserAndProductId(user.getUid(),currentItem.getId(), prod1 -> {
            if(prod1.size() == 0){
                new CartService().addProductToCart(user.getUid(), currentItem, 1);
            }
            updateKosarIcon();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.shop_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(LOG_TAG, s);
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_button:
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.cart:
                Intent cartIntent = new Intent(this, CartActivity.class);
                startActivity(cartIntent);
                return true;
            case R.id.rendelesek:
                Intent orderIntent = new Intent(this, OrderActivity.class);
                startActivity(orderIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem alertMenuItem = menu.findItem(R.id.cart);
        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();

        badge = (FrameLayout) rootView.findViewById(R.id.view_alert_red_circle);
        countTextView = (TextView) rootView.findViewById(R.id.view_alert_count_textview);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }


    public void updateKosarIcon() {
        new CartService().getKosarMennyiseg(user.getUid(), mennyiseg -> {
            this.kosarmennyiseg = mennyiseg;
            if (0 < kosarmennyiseg) {
                countTextView.setText(String.valueOf(kosarmennyiseg));
            } else {
                countTextView.setText("");
            }
            badge.setVisibility((kosarmennyiseg > 0) ? VISIBLE : GONE);
        });
    }

}