package hu.mobilalk.phoneshop.Adapters;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.TextView;


import java.util.ArrayList;

import hu.mobilalk.phoneshop.Activities.CartActivity;
import hu.mobilalk.phoneshop.Models.Cart;
import hu.mobilalk.phoneshop.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{


    // Member variables.
    private ArrayList<Cart> mShopingItemData = new ArrayList<>();
    private Context mContext;
    private int lastPosition = -1;

    public CartAdapter(Context context, ArrayList<Cart> itemsData) {
        this.mShopingItemData = itemsData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.cart_list, parent, false));
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Cart currentItem = mShopingItemData.get(position);

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
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mPriceText;
        private TextView mMennyisegText;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.productModel);
            mInfoText = itemView.findViewById(R.id.productBrand);
            mPriceText = itemView.findViewById(R.id.price);
            mMennyisegText = itemView.findViewById(R.id.mennyiseg);
        }

        void bindTo(Cart currentItem){
            mTitleText.setText(currentItem.getTermek().getModel());
            mInfoText.setText(currentItem.getTermek().getMarka());
            mPriceText.setText(String.valueOf(currentItem.getOsszar()) + " Ft");
            mMennyisegText.setText(String.valueOf(currentItem.getMennyiseg()) + " db");

            itemView.findViewById(R.id.plus_cart).setOnClickListener(view -> {
                ((CartActivity)mContext).plusItem(currentItem);
            });
            itemView.findViewById(R.id.minus_cart).setOnClickListener(view -> {
                ((CartActivity)mContext).minusItem(currentItem);
            });
        }
    }
}
