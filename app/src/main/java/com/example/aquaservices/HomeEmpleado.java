package com.example.aquaservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aquaservices.models.Usuario;
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

import java.util.HashMap;
import java.util.Map;

public class HomeEmpleado extends AppCompatActivity {

    // Elements
    private Button buttonRegContadores;
    private Button buttonRegDeposito;
    private Button buttonRegVisita;
    // Modelos
    private Map<String, String> mapUsuario;
    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_empleado);

        // Vistas
        buttonRegContadores = findViewById(R.id.buttonRegContadores);
        buttonRegDeposito = findViewById(R.id.buttonRegDeposito);
        buttonRegVisita = findViewById(R.id.buttonRegVisita);
        buttonRegContadores.setOnClickListener(listener);
        buttonRegDeposito.setOnClickListener(listener);
        buttonRegVisita.setOnClickListener(listener);
        // Vars
        mAuth = mAuth.getInstance();
        mapUsuario = new HashMap<>();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().
                child("usuarios").child(mAuth.getCurrentUser().getUid());

        obtenerInfoUsuario(userRef);


    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonRegContadores:
                    startActivity(new Intent(HomeEmpleado.this, QRCodeReader.class));
                    break;
                case R.id.buttonRegDeposito:
                    Toast.makeText(HomeEmpleado.this, "Deposito", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonRegVisita:
                    Toast.makeText(HomeEmpleado.this, "Visita", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_home_empleado, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeEmpleado.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void obtenerInfoUsuario(DatabaseReference userRef) {
        AlertDialog dialogGetData = Globals.infoAlertDialog(HomeEmpleado.this,
                "Obteniendo información...");
        dialogGetData.setCancelable(false);
        dialogGetData.show();
        userRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    DataSnapshot dataSnapshot = task.getResult();
                    mapUsuario = (HashMap<String, String>) dataSnapshot.getValue();
                    setTitle("Usuario: "+mapUsuario.get("nombre"));
                    dialogGetData.dismiss();
                } else {
                    Log.e("firebase", "Error getting data", task.getException());
                    Toast.makeText(HomeEmpleado.this,"Error getting data"+ task.getException(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}