package hu.mobilalk.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import hu.mobilalk.phoneshop.R;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginActivity.class.getName();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        EditText emailET = findViewById(R.id.editTextEmail);
        EditText passwordET = findViewById(R.id.editTextPassword);
        try{
            String email = emailET.getText().toString();
            String pwd = passwordET.getText().toString();

            mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(this, task -> {
                if(task.isSuccessful()){
                    startShopping();
                } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(LoginActivity.this, "Hibás email formátum", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Nincs ilyen felhasználó!", Toast.LENGTH_LONG).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(LoginActivity.this, "Nem lehet üres a beviteli mező", Toast.LENGTH_LONG).show();
        }




    }

    private void startShopping() {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

    public void signup(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}