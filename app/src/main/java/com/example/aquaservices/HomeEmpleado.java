package com.example.aquaservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeEmpleado extends AppCompatActivity {

    private Button buttonSignOut;
    // Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;
    private DataSnapshot dataSnapshot;

    public static String dataSnapshotUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_empleado);


        buttonSignOut = findViewById(R.id.button);
        mAuth = mAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(mAuth.getCurrentUser().getUid());

        // Aquí realizar el progress dialog
        usersRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    Toast.makeText(HomeEmpleado.this,"Error getting data"+ task.getException(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    dataSnapshot = task.getResult();
                    Toast.makeText(HomeEmpleado.this, "asdf"+dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeEmpleado.this, LoginActivity.class));
            }
        });
    }
}