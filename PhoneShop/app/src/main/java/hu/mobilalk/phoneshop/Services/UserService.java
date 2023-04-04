package hu.mobilalk.phoneshop.Services;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import hu.mobilalk.phoneshop.Models.User;

public class UserService {
    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final CollectionReference mItems = mFirestore.collection("User");


    public void addUser(User user){
        this.mItems.add(user);
    }
}
