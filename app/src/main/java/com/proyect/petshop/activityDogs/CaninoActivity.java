package com.proyect.petshop.activityDogs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.proyect.petshop.R;
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.adapters.CartSingleton;
import com.proyect.petshop.models.InstruccionesActivity;

public class CaninoActivity extends AppCompatActivity {
    private TextView textViewNotificationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canino);

        // Botones y sus correspondientes ImageView
        Button buttonAlimentos = findViewById(R.id.buttonAlimentos);
        ImageView imageViewAlimentacion = findViewById(R.id.imageViewAlimentacion);
        Button buttonZonaJuegos = findViewById(R.id.buttonZonaJuegos);
        ImageView imageViewJuegos = findViewById(R.id.imageViewJuegos);
        Button buttonVestimenta = findViewById(R.id.buttonVestimenta);
        ImageView imageViewVestimenta = findViewById(R.id.imageViewVestimenta);
        Button buttonInstrucciones = findViewById(R.id.buttonInstrucciones);
        ImageView imageViewBook = findViewById(R.id.imageViewBook);
        Button buttonCarrito = findViewById(R.id.buttonCarrito);
        ImageView imageViewCarrito = findViewById(R.id.imageViewCarrito);
        textViewNotificationCount = findViewById(R.id.cartItemCount);

        // Listener para Alimentos
        View.OnClickListener alimentosClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaninoActivity.this, AlimentosActivity_dogs.class);
                startActivity(intent);
            }
        };
        buttonAlimentos.setOnClickListener(alimentosClickListener);
        imageViewAlimentacion.setOnClickListener(alimentosClickListener);

        // Listener para Zona de Juegos
        View.OnClickListener juegosClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaninoActivity.this, ZonaJuegosActivity_dogs.class);
                startActivity(intent);
            }
        };
        buttonZonaJuegos.setOnClickListener(juegosClickListener);
        imageViewJuegos.setOnClickListener(juegosClickListener);

        // Listener para Vestimenta
        View.OnClickListener vestimentaClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaninoActivity.this, VestimentaActivity_dogs.class);
                startActivity(intent);
            }
        };
        buttonVestimenta.setOnClickListener(vestimentaClickListener);
        imageViewVestimenta.setOnClickListener(vestimentaClickListener);

        // Listener para Instrucciones
        View.OnClickListener instruccionesClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaninoActivity.this, InstruccionesActivity.class);
                startActivity(intent);
            }
        };
        buttonInstrucciones.setOnClickListener(instruccionesClickListener);
        imageViewBook.setOnClickListener(instruccionesClickListener);

        // Listener para Carrito
        View.OnClickListener carritoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaninoActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        };
        buttonCarrito.setOnClickListener(carritoClickListener);
        imageViewCarrito.setOnClickListener(carritoClickListener);
        // Listener para la imagen de regresar
        ImageView imageViewRegresar = findViewById(R.id.imageViewRegresar);

        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finalizar esta actividad y regresar a la anterior en el stack
            }
        });
        // Actualizar el contador de notificaciones
        updateNotificationCount();
    }

    // Método para manejar el clic del botón de regresar
    public void onRegresarClick(View view) {
        finish(); // Finalizar esta actividad y regresar a la anterior en el stack
    }

    // Método para actualizar el contador de notificaciones
    private void updateNotificationCount() {
        int count = CartSingleton.getInstance().getCartItemCount();

            textViewNotificationCount.setVisibility(View.VISIBLE);
            textViewNotificationCount.setText(String.valueOf(count));

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualizar el contador cuando la actividad vuelve a ser visible
        updateNotificationCount();
    }

}
