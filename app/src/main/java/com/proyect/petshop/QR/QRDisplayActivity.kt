package com.proyect.petshop.QR

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.proyect.petshop.R

class QRDisplayActivity : AppCompatActivity() {

    private lateinit var qrBitmap: Bitmap
    private var clienteContador: Int = 0 // Contador del cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_display)

        // Recuperar los datos del Intent
        val clienteNombre = intent.getStringExtra("CLIENTE_NOMBRE") ?: "Nombre del cliente"
        val clienteCedula = intent.getStringExtra("CLIENTE_CEDULA") ?: "Cédula del cliente"
        val clienteTelefono = intent.getStringExtra("CLIENTE_TELEFONO") ?: "Teléfono del cliente"
        val clienteDireccion = intent.getStringExtra("CLIENTE_DIRECCION") ?: "Dirección del cliente"
        val clienteBanco = intent.getStringExtra("CLIENTE_BANCO") ?: "Banco del cliente"
        val clienteNumeroBanco = intent.getStringExtra("CLIENTE_NUMERO_BANCO") ?: "Número de tarjeta del cliente"
        val valorTotalCompra = intent.getStringExtra("TOTAL_PRECIO") ?: "0.00" // Recupera el valor total de la compra

        // Incrementar el contador de cliente
        clienteContador++

        // Concatenar los títulos con los datos del cliente
        val datosCliente = "Número de cliente: $clienteContador\n" +
                "Nombre del cliente: $clienteNombre\n" +
                "Cédula del cliente: $clienteCedula\n" +
                "Teléfono del cliente: $clienteTelefono\n" +
                "Dirección del cliente: $clienteDireccion\n" +
                "Banco del cliente: $clienteBanco\n" +
                "Número de tarjeta del cliente: $clienteNumeroBanco\n" +
                "Valor total de la compra: $valorTotalCompra\n" +
                "Pase a pagar a caja el cliente número $clienteContador"

        // Generar el código QR basado en los datos del cliente
        val qrCodeSize = resources.getDimensionPixelSize(R.dimen.qr_code_size)
        qrBitmap = QRCodeHelper.generateQRBitmap(datosCliente, qrCodeSize) ?: return

        // Mostrar el código QR en un ImageView
        val qrImageView = findViewById<ImageView>(R.id.qrImageView)
        qrImageView.setImageBitmap(qrBitmap)

        // Configurar el clic en el ImageView para iniciar el escaneo
        qrImageView.setOnClickListener {
            startQRScan()
        }
    }

    private fun startQRScan() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanea el código QR del cliente")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IntentIntegrator.REQUEST_CODE && resultCode == RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            result?.contents?.let {
                // Aquí puedes manejar los datos escaneados del QR
                val scannedData = it
                Toast.makeText(this, "Datos escaneados: $scannedData", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // Método para manejar el clic en los botones e imagen de regresar
    fun onRegresarClick(view: View) {
        finish()
    }
}
