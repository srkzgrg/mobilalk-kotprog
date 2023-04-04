package hu.mobilalk.phoneshop.Adapters;


import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hu.mobilalk.phoneshop.Activities.ShopActivity;
import hu.mobilalk.phoneshop.Models.Product;
import hu.mobilalk.phoneshop.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {
    private static final String LOG_TAG = ProductAdapter.class.getName();

    // Member variables.
    private ArrayList<Product> mShopingItemData = new ArrayList<>();
    private ArrayList<Product> mShopingItemDataAll = new ArrayList<>();
    private Context mContext;
    private int lastPosition = -1;


    public ProductAdapter(Context context, ArrayList<Product> itemsData) {
        this.mShopingItemData = itemsData;
        this.mShopingItemDataAll = itemsData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.product_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Product currentItem = mShopingItemData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentItem);


        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
           holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mShopingItemData.size();
    }


    /**
     * RecycleView filter
     * **/
    @Override
    public Filter getFilter() {
        return shopingFilter;
    }

    private Filter shopingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Product> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mShopingItemDataAll.size();
                results.values = mShopingItemDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Product item : mShopingItemDataAll) {
                    if(item.getModel().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mShopingItemData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mPriceText;
        private ImageView mItemImage;
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.productModel);
            mInfoText = itemView.findViewById(R.id.productBrand);
            mItemImage = itemView.findViewById(R.id.itemImage);
            mPriceText = itemView.findViewById(R.id.price);
        }

        void bindTo(Product currentItem){
            mTitleText.setText(currentItem.getModel());
            mInfoText.setText(currentItem.getMarka());
            mPriceText.setText(Integer.toString(currentItem.getAr()));

            // Load the images into the ImageView using the Glide library.
            //Glide.with(mContext).load("R.drawable."+ currentItem.getImage_url()).into(mItemImage);

            itemView.findViewById(R.id.add_to_cart).setOnClickListener(view -> {
                try {
                    ((ShopActivity)mContext).addToCart(currentItem);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


}
