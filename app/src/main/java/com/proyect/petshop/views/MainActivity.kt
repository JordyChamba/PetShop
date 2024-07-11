package com.proyect.petshop.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
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

        val buttonCanino = findViewById<Button>(R.id.buttonCanino)
        val buttonGatuno = findViewById<Button>(R.id.buttonGatuno)
        val buttonAves = findViewById<Button>(R.id.buttonAves)
        val buttonInstrucciones = findViewById<Button>(R.id.buttonInstrucciones)
        val buttonCarrito = findViewById<Button>(R.id.buttonCarrito)

        buttonCanino.setOnClickListener {
            Log.d("MainActivity", "buttonCanino clicked")
            val intent = Intent(this, CaninoActivity::class.java)
            startActivity(intent)
        }

        buttonGatuno.setOnClickListener {
            Log.d("MainActivity", "buttonGatuno clicked")
            val intent = Intent(this, FelinoActivity::class.java)
            startActivity(intent)
        }

        buttonAves.setOnClickListener {
            Log.d("MainActivity", "buttonAves clicked")
            val intent = Intent(this, BirdActivity::class.java)
            startActivity(intent)
        }

        buttonInstrucciones.setOnClickListener {
            Log.d("MainActivity", "buttonInstrucciones clicked")
            val intent = Intent(this, InstruccionesActivity::class.java)
            startActivity(intent)
        }

        buttonCarrito.setOnClickListener {
            Log.d("MainActivity", "buttonCarrito clicked")
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
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
