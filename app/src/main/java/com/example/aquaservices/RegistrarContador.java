package com.example.aquaservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aquaservices.admin_activities.ActivitySucursales;
import com.example.aquaservices.models.Maquina;
import com.example.aquaservices.models.Sucursal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrarContador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contador);

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            Toast.makeText(this, extras.getString("id_maquina"), Toast.LENGTH_SHORT).show();
            confirm_maq_exists(extras.getString("id_maquina"));
        }else{
            Toast.makeText(this, "EXTRAS VACÍO", Toast.LENGTH_SHORT).show();
        }

    }

    public void confirm_maq_exists(String maquina) {
        DatabaseReference maqRef = FirebaseDatabase.getInstance().getReference("maquinas");
        maqRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean band = false;
                for (DataSnapshot ds: snapshot.getChildren()) {
                    if(ds.getKey().equals(maquina)){
                        band = true;
                    }
                }
                if(!band){
                    Globals.confirmAlertDialog(RegistrarContador.this,
                            "No estás conectado a internet o la máquina que intentas registrar " +
                                    "no existe en la base de datos.").show();
                } else {
                    get_data_to_register(maquina);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegistrarContador.this, "Falló lectura de datos.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void get_data_to_register(String maquina) {
        DatabaseReference maqRef = FirebaseDatabase.getInstance().getReference("maquinas")
                .child(maquina);
        maqRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, String> mapMaq = new HashMap<>();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    mapMaq.put(ds.getKey(), ds.getValue().toString());
                }
                load_machine_data(mapMaq);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegistrarContador.this, "Falló lectura de datos.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void load_machine_data(Map<String, String> maq){
        Maquina maquina = new Maquina(maq.get("id"), maq.get("contador"),
                maq.get("sucursal"), maq.get("tipo"));
        Toast.makeText(this, maquina.toString()                        , Toast.LENGTH_SHORT).show();
    }


}