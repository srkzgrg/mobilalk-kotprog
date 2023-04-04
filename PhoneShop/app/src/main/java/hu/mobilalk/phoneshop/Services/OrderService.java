package hu.mobilalk.phoneshop.Services;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
        mItems.add(order);

    }
}
