package com.example.aquaservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.nfc.Tag;
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
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextTextEmailAddress, editTextTextPassword;
    private Button buttonLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.loginButton);

        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextEmailAddress.getText().toString().matches("")){
                    editTextTextEmailAddress.setError("Campo requerido.");
                } else if (editTextTextPassword.getText().toString().matches("")){
                    editTextTextPassword.setError("Campo requerido.");
                } else {
                    startLoginProcess(editTextTextEmailAddress.getText().toString(), editTextTextPassword.getText().toString());
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            goToHomeActivity();
        }
    }


    public void startLoginProcess(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success signin", "signInWithEmail:success");
                            goToHomeActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Fail sign in", "signInWithEmail:failure", task.getException());
                            AlertDialog al = Globals.confirmAlertDialog(LoginActivity.this,
                                    "Email o contraseña erróneos. Intente de nuevo.");
                            al.show();
                        }
                    }
                });
    }

    public void goToHomeActivity() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().
                child("usuarios").child(mAuth.getCurrentUser().getUid());
        usersRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    DataSnapshot dataSnapshot = task.getResult();
                    Map<String, String> mapUsuario = (HashMap<String, String>) dataSnapshot.getValue();
                    if (mapUsuario.get("rol").equals("admin")) {
                        startActivity(new Intent(LoginActivity.this, HomeAdmin.class));
                    } else {
                        startActivity(new Intent(LoginActivity.this, HomeEmpleado.class));
                    }
                } else {
                    Log.e("firebase", "Error getting data", task.getException());
                    Toast.makeText(LoginActivity.this,"Error getting data"+ task.getException(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}