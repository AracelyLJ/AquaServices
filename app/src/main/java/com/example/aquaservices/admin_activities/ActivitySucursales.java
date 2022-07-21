package com.example.aquaservices.admin_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaservices.HomeAdmin;
import com.example.aquaservices.HomeEmpleado;
import com.example.aquaservices.LoginActivity;
import com.example.aquaservices.R;
import com.example.aquaservices.models.Sucursal;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private ImageView btnAddSucursal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales);
        this.setTitle("SUCURSALES");

        // Views
        btnAddSucursal = findViewById(R.id.btnAddSucursal);
        btnAddSucursal.setOnClickListener(listener);

        // Implementacion
        sucsRef = FirebaseDatabase.getInstance().getReference("sucursales");
        sucsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                fill_layout_sucursales(sucursales);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivitySucursales.this, "Falló lectura de datos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

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
                startActivity(new Intent(ActivitySucursales.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v.getId()==R.id.btnAddSucursal) {
                Toast.makeText(ActivitySucursales.this,
                        "Agregar sucursal aún no disponible", Toast.LENGTH_SHORT).show();
            } else {
                infoDialog(Integer.parseInt(v.getId()+""));
            }
        }
    };

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

    /**
     * TODO: Hacer una vista para el diálogo que va a:
     * Obtener información y mostrarla
     * botón de editar
     * botón de borrar
     * diseño
     */
    public void infoDialog(Integer idSucursal) {
        final Integer[] idg = {idSucursal};
        sucsRef = FirebaseDatabase.getInstance().getReference("sucursales");
        sucsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id="", nombre="", ubicacion="", tipoRenta="", montoRenta="";
                for (DataSnapshot ds: snapshot.getChildren()) {
                    id = ds.child("id").getValue().toString();
                    nombre = ds.child("nombre").getValue().toString();
                    ubicacion = ds.child("ubicacion").getValue().toString();
                    tipoRenta = ds.child("tipo_renta").getValue().toString();
                    montoRenta = ds.child("monto_renta").getValue().toString();
                    if (Integer.parseInt(id)==idg[0]) break;
                }
                String info = "id: "+id+"\n"+"nombre: "+nombre+"\n"+"ubicacion: "+ubicacion+"\n"+
                        "tipoRenta: "+tipoRenta+"\n"+"montoRenta: "+montoRenta;
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySucursales.this);
                builder.setMessage(info)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                builder.create().show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivitySucursales.this, "Falló lectura de datos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}