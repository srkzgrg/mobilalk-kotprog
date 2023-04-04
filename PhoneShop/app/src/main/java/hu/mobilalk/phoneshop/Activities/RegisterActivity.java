package hu.mobilalk.phoneshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import hu.mobilalk.phoneshop.Models.User;
import hu.mobilalk.phoneshop.R;
import hu.mobilalk.phoneshop.Services.UserService;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();

    //FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

    EditText userEmailEditText;
    EditText nameEditText;
    EditText passwordEditText;
    EditText repasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");

        userEmailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        repasswordEditText = findViewById(R.id.editTextPassword2);
        nameEditText = findViewById(R.id.editTextName);
    }

    public void register(View view) {
        String email = userEmailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String repassword = repasswordEditText.getText().toString();
        String name = nameEditText.getText().toString();

        if (!password.equals(repassword)) {
            //TODO a két jelszó nem egyezik
        }else {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        Log.d(LOG_TAG, "Sikeres regisztráció");
                        User user = new User(user_id, email, name);
                        new UserService().addUser(user);
                        startShopping();
                    } else {
                        Log.d(LOG_TAG, "User was't created successfully:", task.getException());
                        Toast.makeText(RegisterActivity.this, "A regisztráció sikertelen volt:", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void startShopping() {

    }

    public void login(View view) {
        finish();
    }
}