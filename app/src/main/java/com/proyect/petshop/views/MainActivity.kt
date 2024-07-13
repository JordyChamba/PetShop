package com.proyect.petshop.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.proyect.petshop.R
import com.proyect.petshop.activityDogs.CaninoActivity
import com.proyect.petshop.ActivityCats.FelinoActivity
import com.proyect.petshop.ActivityBirds.BirdActivity
import com.proyect.petshop.models.InstruccionesActivity
import com.proyect.petshop.adapters.CarritoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showWelcomeDialog()

        // Find all buttons and image views
        val buttonCanino = findViewById<Button>(R.id.buttonCanino)
        val imageViewPerro = findViewById<ImageView>(R.id.imageViewPerro)
        val buttonGatuno = findViewById<Button>(R.id.buttonGatuno)
        val imageViewGato = findViewById<ImageView>(R.id.imageViewGato)
        val buttonAves = findViewById<Button>(R.id.buttonAves)
        val imageViewAves = findViewById<ImageView>(R.id.imageViewAves)
        val buttonInstrucciones = findViewById<Button>(R.id.buttonInstrucciones)
        val imageViewBook = findViewById<ImageView>(R.id.imageViewBook)
        val buttonCarrito = findViewById<Button>(R.id.buttonCarrito)
        val imageViewCarrito = findViewById<ImageView>(R.id.imageViewCarrito)

        // Set click listeners for Perro
        val perroClickListener = {
            Log.d("MainActivity", "Perro clicked")
            val intent = Intent(this, CaninoActivity::class.java)
            startActivity(intent)
        }
        buttonCanino.setOnClickListener { perroClickListener() }
        imageViewPerro.setOnClickListener { perroClickListener() }

        // Set click listeners for Gato
        val gatoClickListener = {
            Log.d("MainActivity", "Gato clicked")
            val intent = Intent(this, FelinoActivity::class.java)
            startActivity(intent)
        }
        buttonGatuno.setOnClickListener { gatoClickListener() }
        imageViewGato.setOnClickListener { gatoClickListener() }

        // Set click listeners for Aves
        val avesClickListener = {
            Log.d("MainActivity", "Aves clicked")
            val intent = Intent(this, BirdActivity::class.java)
            startActivity(intent)
        }
        buttonAves.setOnClickListener { avesClickListener() }
        imageViewAves.setOnClickListener { avesClickListener() }

        // Set click listeners for Instrucciones
        val instruccionesClickListener = {
            Log.d("MainActivity", "Instrucciones clicked")
            val intent = Intent(this, InstruccionesActivity::class.java)
            startActivity(intent)
        }
        buttonInstrucciones.setOnClickListener { instruccionesClickListener() }
        imageViewBook.setOnClickListener { instruccionesClickListener() }

        // Set click listeners for Carrito
        val carritoClickListener = {
            Log.d("MainActivity", "Carrito clicked")
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
        buttonCarrito.setOnClickListener { carritoClickListener() }
        imageViewCarrito.setOnClickListener { carritoClickListener() }
    }

    private fun showWelcomeDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_content, null)
        builder.setView(dialogView)
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
