package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button button;

    private FirebaseAuth mAuth;

    //Make sure to check email and password (empty and null)


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, "Already in", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        button = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance(); // initializing instance
    }

    public void onRegister(View view){
        final String myEmail = email.getText().toString();
        final String myPassword = password.getText().toString();
        mAuth.createUserWithEmailAndPassword(myEmail, myPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "Created user with email", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });



    }

    public void onLogin(View view) {
        final String myEmail = email.getText().toString();
        final String myPassword = password.getText().toString();

        mAuth.signInWithEmailAndPassword(myEmail, myPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userId = user.getUid().toString();
                            Toast.makeText(MainActivity.this, "Auth Success", Toast.LENGTH_SHORT).show();
                            Log.i("User", "User: " + user.toString());
                            Log.i("User", "User: " + userId);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Signout", Toast.LENGTH_SHORT).show();
    }
}













