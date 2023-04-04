package hu.mobilalk.phoneshop.Services;

import android.annotation.SuppressLint;
import android.telecom.Call;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import hu.mobilalk.phoneshop.Models.Cart;
import hu.mobilalk.phoneshop.Adapters.CartAdapter;
import hu.mobilalk.phoneshop.Models.Product;

public class CartService {
    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final CollectionReference mItems = mFirestore.collection("Cart");


    //CREATE
    @SuppressLint("NotifyDataSetChanged")
    public void addProductToCart(String userid, Product product, int mennyiseg){
        int osszar = mennyiseg * product.getAr();
        Cart cart = new Cart(userid, product, osszar, mennyiseg);
        mItems.add(cart);
    }

    //UPDATE
    public void updateCartByUserAndProductId(String userid, String productid, Callback callback ){
        ArrayList<Cart> carts = new ArrayList<>();
        mItems.whereEqualTo("userid", userid).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Cart item = document.toObject(Cart.class);
                if(item.getTermek().getId().equals(productid)){
                    carts.add(item);
                    mItems.document(document.getId()).update("mennyiseg", item.getMennyiseg() + 1);
                    mItems.document(document.getId()).update("osszar", (item.getMennyiseg() + 1) * item.getTermek().getAr());
                }
            }
            callback.onSuccess(carts);
        });
    }

    //READ
    public void getKosarMennyiseg(String userid, CallbackMennnyiseg callback){
        final int[] mennyiseg = {0};
        mItems.whereEqualTo("userid", userid).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                Cart item = document.toObject(Cart.class);
                mennyiseg[0] +=item.getMennyiseg();
            }
            callback.onSuccess(mennyiseg[0]);
        });
    }

    //READ
    @SuppressLint("NotifyDataSetChanged")
    public void getKosar(ArrayList<Cart> mProducts, String userid, CallbackUpdate updt){
        mItems.whereEqualTo("userid", userid).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Cart item = document.toObject(Cart.class);
                mProducts.add(item);
            }
            updt.onSuccess(true);
        });
    }

    public void getVegosszegByUserId(String userid, CallbackVegosszeg callback){
        final int[] vegosszeg = {0};
        mItems.whereEqualTo("userid", userid).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                Cart item = document.toObject(Cart.class);
                vegosszeg[0] +=item.getOsszar();
            }
            callback.onSuccess(vegosszeg[0]);
        });
    }

    public void removeCart(String userid){
        mItems.whereEqualTo("userid", userid).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document: queryDocumentSnapshots){
                mItems.document(document.getId()).delete();
            }
        });
    }

    public void plusItemByProductId(String userid, String productid, CallbackUpdate upt){
        int a = 0;
        mItems.whereEqualTo("userid", userid).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document: queryDocumentSnapshots){
                Cart item = document.toObject(Cart.class);
                if(item.getTermek().getId().equals(productid)){
                    item.setMennyiseg(item.getMennyiseg() + 1);
                    item.setOsszar(item.getMennyiseg() * item.getTermek().getAr());
                    Log.i("tesztKosar", String.valueOf(item.getMennyiseg()));
                    mItems.document(document.getId()).update("mennyiseg", item.getMennyiseg());
                    mItems.document(document.getId()).update("osszar", item.getOsszar());
                }
            }
            upt.onSuccess(true);
        });
    }

    public void minusItemByProductId(String userid, String productid, CallbackUpdate upt){
        int a = 0;
        mItems.whereEqualTo("userid", userid).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document: queryDocumentSnapshots){
                Cart item = document.toObject(Cart.class);
                if(item.getTermek().getId().equals(productid)){
                    item.setMennyiseg(item.getMennyiseg() - 1);
                    if(item.getMennyiseg() <= 0){
                        mItems.document(document.getId()).delete();
                    }else{
                        item.setOsszar(item.getMennyiseg() * item.getTermek().getAr());
                        mItems.document(document.getId()).update("mennyiseg", item.getMennyiseg());
                        mItems.document(document.getId()).update("osszar", item.getOsszar());
                    }
                }
            }
            upt.onSuccess(true);
        });
    }
    public interface Callback {
        void onSuccess(List<Cart> prod);
    }
    public interface CallbackMennnyiseg {
        void onSuccess(int mennyiseg);
    }

    public interface CallbackVegosszeg {
        void onSuccess(int vegosszeg);
    }

    public interface CallbackUpdate{
        void onSuccess(boolean a);
    }

}







