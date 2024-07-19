package com.proyect.petshop.QR

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class QRScanActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initiateQRScan()
    }

    private fun initiateQRScan() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanear código QR")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        result?.contents?.let { scannedData ->
            val intent = Intent().apply {
                putExtra("SCANNED_DATA", scannedData)
            }
            setResult(Activity.RESULT_OK, intent)
        } ?: run {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}
