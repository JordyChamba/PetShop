package com.proyect.petshop.QR

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import java.util.EnumMap

object QRCodeHelper {

    fun generateQRBitmap(data: String, qrCodeSize: Int): Bitmap? {
        val hintMap = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
        hintMap[EncodeHintType.MARGIN] = 4 // Margen del código QR

        val writer = QRCodeWriter()
        try {
            val bitMatrix: BitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }

            return bmp
        } catch (e: WriterException) {
            e.printStackTrace()
            // Manejar la excepción según tu lógica
            return null
        }
    }
}
