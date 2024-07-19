package com.proyect.petshop.models

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.proyect.petshop.R

class InstruccionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruccion)
    }

    fun onRegresarClick(view: android.view.View) {
        // Finaliza la actividad actual y vuelve a la actividad anterior
        finish()
    }
}
