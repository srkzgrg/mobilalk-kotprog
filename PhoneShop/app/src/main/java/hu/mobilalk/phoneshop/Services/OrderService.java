package hu.mobilalk.phoneshop.Services;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;

import hu.mobilalk.phoneshop.Models.Cart;
import hu.mobilalk.phoneshop.Models.Order;

public class OrderService {
    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

    private final CollectionReference mItems = mFirestore.collection("Order");


    public void addRendeles(String userid, ArrayList<Cart> kosarak, int vegosszeg, String cim, String telefon){
        Date date = new Date();
        Order order = new Order(vegosszeg, kosarak, userid, date, cim, telefon);
        mItems.add(order).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                mItems.document(documentReference.getId()).update("id", documentReference.getId());
            }
        });

    }

    public void getRendelesByUserID(ArrayList<Order> mProducts, String userid, CallbackUpdate updt){
        mItems.whereEqualTo("user_id", userid).limitToLast(5).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Order item = document.toObject(Order.class);
                Log.i("Teszt", String.valueOf(item));
                mProducts.add(item);
            }
            updt.onSuccess(true);
        });
    }

    public interface CallbackUpdate{
        void onSuccess(boolean a);
    }
}
