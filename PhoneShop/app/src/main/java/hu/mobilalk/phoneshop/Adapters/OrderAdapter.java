package hu.mobilalk.phoneshop.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import hu.mobilalk.phoneshop.Activities.CartActivity;
import hu.mobilalk.phoneshop.Models.Cart;
import hu.mobilalk.phoneshop.Models.Order;
import hu.mobilalk.phoneshop.R;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{


    // Member variables
    private ArrayList<Order> mShopingItemData = new ArrayList<>();
    private Context mContext;
    private int lastPosition = -1;

    public OrderAdapter(Context context, ArrayList<Order> itemsData) {
        this.mShopingItemData = itemsData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new OrderAdapter.ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.order_list, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Order currentItem = mShopingItemData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentItem);


        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_down);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mShopingItemData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mOrderID;
        private TextView mOrderPrice;
        private TextView mOrderDate;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mOrderID = itemView.findViewById(R.id.orderID);
            mOrderPrice = itemView.findViewById(R.id.orderPrice);
            mOrderDate = itemView.findViewById(R.id.orderDate);
        }

        void bindTo(Order currentItem){
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            String asd = sfd.format(new Date(currentItem.getDatum().getTime()));
            Log.i("teszt", asd);
            mOrderID.setText(String.valueOf(currentItem.getId()));
            mOrderPrice.setText(String.valueOf(currentItem.getVegosszeg()) + " Ft");
            mOrderDate.setText(asd);
        }
    }
}
