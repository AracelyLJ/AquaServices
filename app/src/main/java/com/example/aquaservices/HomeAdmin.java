package com.example.aquaservices;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aquaservices.admin_activities.ActivitySucursales;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HomeAdmin extends AppCompatActivity {

    // Elements
    private Button buttonSucursales;
    private Button buttonUsuarios;
    private Button buttonMaquinas;
    // Modelos
    // private Map<String, String> mapUsuario;
    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        // Vistas
        buttonSucursales = findViewById(R.id.buttonSucursales);
        buttonUsuarios = findViewById(R.id.buttonUsuarios);
        buttonMaquinas = findViewById(R.id.buttonMaquinas);
        buttonSucursales.setOnClickListener(listener);
        buttonUsuarios.setOnClickListener(listener);
        buttonMaquinas.setOnClickListener(listener);
        // Vars
        mAuth = mAuth.getInstance();
        // mapUsuario = new HashMap<>();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().
                child("usuarios").child(mAuth.getCurrentUser().getUid());
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonSucursales:
                    startActivity(new Intent(HomeAdmin.this, ActivitySucursales.class));
                    break;
                case R.id.buttonUsuarios:
                    Toast.makeText(HomeAdmin.this, "Usuarios", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonMaquinas:
                    Toast.makeText(HomeAdmin.this, "Maquinas", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(HomeAdmin.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}