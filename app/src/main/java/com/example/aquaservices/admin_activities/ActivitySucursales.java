package com.example.aquaservices.admin_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaservices.HomeAdmin;
import com.example.aquaservices.R;
import com.example.aquaservices.models.Sucursal;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

public class ActivitySucursales extends AppCompatActivity {

    private DatabaseReference sucsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales);
        this.setTitle("SUCURSALES");

        sucsRef = FirebaseDatabase.getInstance().getReference("sucursales");
        sucsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object val = snapshot.child("001").getValue();
                List<Sucursal> sucursales = new ArrayList<>();
                String id, nombre, ubicacion, tipoRenta, montoRenta;
                for (DataSnapshot ds: snapshot.getChildren()) {
                    id = ds.child("id").getValue().toString();
                    nombre = ds.child("nombre").getValue().toString();
                    ubicacion = ds.child("ubicacion").getValue().toString();
                    tipoRenta = ds.child("tipo_renta").getValue().toString();
                    montoRenta = ds.child("monto_renta").getValue().toString();
                    sucursales.add(new Sucursal(id,nombre,ubicacion,tipoRenta,montoRenta));
                    }
                Toast.makeText(ActivitySucursales.this, sucursales.get(0).getNombre(), Toast.LENGTH_SHORT).show();
                fill_layout_sucursales(sucursales);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivitySucursales.this, "Falló lectura de datos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fill_layout_sucursales(List<Sucursal> sucursales) {

        ViewGroup layout = findViewById(R.id.elemento_sucursal);
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.layout_elemento_sucursal;

        for(Sucursal s: sucursales) {
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(id, null, false);

            TextView txtId = linearLayout.findViewById(R.id.txtId);
            TextView txtNombre= linearLayout.findViewById(R.id.txtNombre);
            ImageView imgPreview = linearLayout.findViewById(R.id.imgPreview);
            imgPreview.setId(Integer.parseInt(s.getId()));
            txtId.setText(s.getId());
            txtNombre.setText(s.getNombre());
            imgPreview.setOnClickListener(listener);

            layout.addView(linearLayout);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(ActivitySucursales.this, v.getId()+"", Toast.LENGTH_SHORT).show();
            // Aquí ver cómo se le hará para obtener los datos de la sucursal cuando se dé click al icono
        }
    };

}