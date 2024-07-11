package com.proyect.petshop.ActivityCats;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.proyect.petshop.R
import com.proyect.petshop.adapters.CarritoActivity
import com.proyect.petshop.models.InstruccionesActivity
import com.proyect.petshop.views.MainActivity

class FelinoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_felino)

        // Aquí puedes agregar cualquier lógica adicional que desees
        val buttonAlimentos = findViewById<Button>(R.id.buttonAlimentos)
        val buttonZonaJuegos = findViewById<Button>(R.id.buttonZonaJuegos)
        val buttonVestimenta = findViewById<Button>(R.id.buttonVestimenta)
        val buttonInstrucciones = findViewById<Button>(R.id.buttonInstrucciones);
        val buttonCarrito = findViewById<Button>(R.id.buttonCarrito);
        val buttonHome = findViewById<Button>(R.id.buttonHome);

        buttonAlimentos.setOnClickListener {
            val intent = Intent(
                this@FelinoActivity,
                AlimentosActivity_cats::class.java
            )
            startActivity(intent)
        }

        buttonZonaJuegos.setOnClickListener {
            val intent = Intent(
                this@FelinoActivity,
                ZonaJuegosActivity_cats::class.java
            )
            startActivity(intent)
        }

        buttonVestimenta.setOnClickListener {
            val intent = Intent(
                this@FelinoActivity,
                VestimentaActivity_cats::class.java
            )
            startActivity(intent)
        }

        buttonInstrucciones.setOnClickListener {
            val intent = Intent(
                this@FelinoActivity,
                InstruccionesActivity::class.java
            )
            startActivity(intent)
        }

        buttonCarrito.setOnClickListener {
            val intent = Intent(
                this@FelinoActivity,
                CarritoActivity::class.java
            )
            startActivity(intent)
        }

        buttonHome.setOnClickListener {
            val intent = Intent(
                this@FelinoActivity,
                MainActivity::class.java
            )
            startActivity(intent)
        }
    }
}
