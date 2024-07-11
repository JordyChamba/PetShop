package com.proyect.petshop.ActivityBirds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.proyect.petshop.R;
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.models.InstruccionesActivity;
import com.proyect.petshop.views.MainActivity;

public class BirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aves);

        Button buttonAlimentos = findViewById(R.id.buttonAlimentos);
        Button buttonZonaJuegos = findViewById(R.id.buttonZonaJuegos);
        Button buttonVestimenta = findViewById(R.id.buttonVestimenta);
        Button buttonInstrucciones = findViewById(R.id.buttonInstrucciones);
        Button buttonCarrito = findViewById(R.id.buttonCarrito);
        Button buttonHome = findViewById(R.id.buttonHome);

        buttonAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, AlimentosActivity_birds.class);
                startActivity(intent);
            }
        });

        buttonZonaJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, ZonaJuegosActivity_birds.class);
                startActivity(intent);
            }
        });

        buttonVestimenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, VestimentaActivity_birds.class);
                startActivity(intent);
            }
        });

        buttonInstrucciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, InstruccionesActivity.class);
                startActivity(intent);
            }
        });

        buttonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Limpiar todas las actividades en la parte superior de MainActivity
                startActivity(intent);
            }
        });
    }
}
