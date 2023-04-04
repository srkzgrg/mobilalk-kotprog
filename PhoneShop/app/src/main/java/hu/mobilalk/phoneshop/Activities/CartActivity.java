package hu.mobilalk.phoneshop.Activities;

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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import hu.mobilalk.phoneshop.Models.Cart;
import hu.mobilalk.phoneshop.Adapters.CartAdapter;
import hu.mobilalk.phoneshop.R;
import hu.mobilalk.phoneshop.Services.CartService;
import hu.mobilalk.phoneshop.Services.OrderService;

public class CartActivity extends AppCompatActivity {

    private static final String LOG_TAG = ShopActivity.class.getName();
    private FirebaseUser user;
    private int gridNumber = 1;
    private FrameLayout badge;
    private TextView countTextView;

    private RecyclerView mRecyclerView;
    private ArrayList<Cart> mProducts;
    private CartAdapter mAdapter;

    EditText cimEditText;
    EditText telefonEditText;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(
                this, gridNumber));
        mProducts = new ArrayList<>();
        mAdapter = new CartAdapter(this, mProducts);
        mRecyclerView.setAdapter(mAdapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            Log.d(LOG_TAG, "Authenticated user!");
            Log.i("Teszt", user.getUid());
            new CartService().getKosar(mProducts, user.getUid(), upt ->{ mAdapter.notifyDataSetChanged();});
        } else {
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }

        cimEditText = findViewById(R.id.editTextAdress);
        telefonEditText = findViewById(R.id.editTextPhone);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getKosar(){
        mProducts.clear();
        new CartService().getKosar(mProducts, user.getUid(), upt->{ mAdapter.notifyDataSetChanged();});

    }

    public void rendeles(View view) {
        String cim = cimEditText.getText().toString();
        String telefon = telefonEditText.getText().toString();
        new CartService().getVegosszegByUserId(user.getUid(), cb -> {
            new OrderService().addRendeles(user.getUid(), mProducts, cb, cim, telefon);
        });
        new CartService().removeCart(user.getUid());

    }

    public void plusItem(Cart currentItem) {
        new CartService().plusItemByProductId(user.getUid(), currentItem.getTermek().getId(), upt -> {getKosar();});
    }

    public void minusItem(Cart currentItem) {
        new CartService().minusItemByProductId(user.getUid(), currentItem.getTermek().getId(), upt -> {getKosar();});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_button:
                Log.d(LOG_TAG, "Logout clicked!");
                FirebaseAuth.getInstance().signOut();
                finish();
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