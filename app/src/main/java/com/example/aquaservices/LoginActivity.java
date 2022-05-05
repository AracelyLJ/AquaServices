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
                    startLogin(editTextTextEmailAddress.getText().toString(), editTextTextPassword.getText().toString());
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
            startActivity(new Intent(LoginActivity.this, HomeEmpleado.class));
        }
    }

    public void startLogin(String email, String password) {
        Toast.makeText(this, "iniciar sesion", Toast.LENGTH_SHORT).show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            AlertDialog welDialog = Globals.infoAlertDialog(LoginActivity.this, "Iniciando sesión...");
                            welDialog.show();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success signin", "signInWithEmail:success");
                            startActivity(new Intent(LoginActivity.this, HomeEmpleado.class));
                            welDialog.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Fail signin", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}