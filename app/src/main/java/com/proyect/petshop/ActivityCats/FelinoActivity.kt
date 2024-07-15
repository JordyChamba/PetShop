package com.proyect.petshop.ActivityCats

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.proyect.petshop.R
import com.proyect.petshop.adapters.CarritoActivity
import com.proyect.petshop.models.InstruccionesActivity

class FelinoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_felino)

        // Botones y sus correspondientes ImageView
        val buttonAlimentos = findViewById<Button>(R.id.buttonAlimentos)
        val buttonZonaJuegos = findViewById<Button>(R.id.buttonZonaJuegos)
        val buttonVestimenta = findViewById<Button>(R.id.buttonVestimenta)
        val buttonInstrucciones = findViewById<Button>(R.id.buttonInstrucciones)
        val buttonCarrito = findViewById<Button>(R.id.buttonCarrito)
        val imageViewAlimentacion = findViewById<ImageView>(R.id.imageViewAlimentacion)
        val imageViewJuegos = findViewById<ImageView>(R.id.imageViewJuegos)
        val imageViewVestimenta = findViewById<ImageView>(R.id.imageViewVestimenta)
        val imageViewBook = findViewById<ImageView>(R.id.imageViewBook)
        val imageViewCarrito = findViewById<ImageView>(R.id.imageViewCarrito)
        val imageViewRegresar = findViewById<ImageView>(R.id.imageViewRegresar)

        // Listener para Alimentos
        val alimentosClickListener = View.OnClickListener {
            val intent = Intent(this@FelinoActivity, AlimentosActivity_cats::class.java)
            startActivity(intent)
        }
        buttonAlimentos.setOnClickListener(alimentosClickListener)
        imageViewAlimentacion.setOnClickListener(alimentosClickListener)

        // Listener para Zona de Juegos
        val juegosClickListener = View.OnClickListener {
            val intent = Intent(this@FelinoActivity, ZonaJuegosActivity_cats::class.java)
            startActivity(intent)
        }
        buttonZonaJuegos.setOnClickListener(juegosClickListener)
        imageViewJuegos.setOnClickListener(juegosClickListener)

        // Listener para Vestimenta
        val vestimentaClickListener = View.OnClickListener {
            val intent = Intent(this@FelinoActivity, VestimentaActivity_cats::class.java)
            startActivity(intent)
        }
        buttonVestimenta.setOnClickListener(vestimentaClickListener)
        imageViewVestimenta.setOnClickListener(vestimentaClickListener)

        // Listener para Instrucciones
        val instruccionesClickListener = View.OnClickListener {
            val intent = Intent(this@FelinoActivity, InstruccionesActivity::class.java)
            startActivity(intent)
        }
        buttonInstrucciones.setOnClickListener(instruccionesClickListener)
        imageViewBook.setOnClickListener(instruccionesClickListener)

        // Listener para Carrito
        val carritoClickListener = View.OnClickListener {
            val intent = Intent(this@FelinoActivity, CarritoActivity::class.java)
            startActivity(intent)
        }
        buttonCarrito.setOnClickListener(carritoClickListener)
        imageViewCarrito.setOnClickListener(carritoClickListener)

        // Listener para la imagen de regresar
        imageViewRegresar.setOnClickListener {
            finish() // Finalizar esta actividad y regresar a la actividad anterior
        }
    }
    // Método público para manejar el clic de la imagen de regresar
    fun onRegresarClick(view: View) {
        finish() // Finalizar esta actividad y regresar a la actividad anterior
    }

}
