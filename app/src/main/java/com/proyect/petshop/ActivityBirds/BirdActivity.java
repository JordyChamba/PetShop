package com.proyect.petshop.ActivityBirds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.proyect.petshop.R;
import com.proyect.petshop.activityDogs.CaninoActivity;
import com.proyect.petshop.models.InstruccionesActivity;

public class BirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aves);

        // Botones y sus correspondientes ImageView
        Button buttonAlimentos = findViewById(R.id.buttonAlimentos);
        Button buttonZonaJuegos = findViewById(R.id.buttonZonaJuegos);
        Button buttonVestimenta = findViewById(R.id.buttonVestimenta);
        Button buttonInstrucciones = findViewById(R.id.buttonInstrucciones);
        Button buttonCarrito = findViewById(R.id.buttonCarrito);
        View imageViewAlimentacion = findViewById(R.id.imageViewAlimentacion);
        View imageViewJuegos = findViewById(R.id.imageViewJuegos);
        View imageViewVestimenta = findViewById(R.id.imageViewVestimenta);
        View imageViewBook = findViewById(R.id.imageViewBook);

        // Listener para Alimentos
        View.OnClickListener alimentosClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, AlimentosActivity_birds.class);
                startActivity(intent);
            }
        };
        buttonAlimentos.setOnClickListener(alimentosClickListener);
        imageViewAlimentacion.setOnClickListener(alimentosClickListener);

        // Listener para Zona de Juegos
        View.OnClickListener juegosClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, ZonaJuegosActivity_birds.class);
                startActivity(intent);
            }
        };
        buttonZonaJuegos.setOnClickListener(juegosClickListener);
        imageViewJuegos.setOnClickListener(juegosClickListener);

        // Listener para Vestimenta
        View.OnClickListener vestimentaClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, VestimentaActivity_birds.class);
                startActivity(intent);
            }
        };
        buttonVestimenta.setOnClickListener(vestimentaClickListener);
        imageViewVestimenta.setOnClickListener(vestimentaClickListener);

        // Listener para Instrucciones
        View.OnClickListener instruccionesClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdActivity.this, InstruccionesActivity.class);
                startActivity(intent);
            }
        };
        buttonInstrucciones.setOnClickListener(instruccionesClickListener);
        imageViewBook.setOnClickListener(instruccionesClickListener);

        // Listener para la imagen de regresar
        ImageView imageViewRegresar = findViewById(R.id.imageViewRegresar);
        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finalizar esta actividad y regresar a la anterior en el stack
            }
        });
    }

    // Método para manejar el clic del botón de regresar
    public void onRegresarClick(View view) {
        finish(); // Finalizar esta actividad y regresar a la anterior en el stack
    }

}
