package com.proyect.petshop.models

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.proyect.petshop.R
import com.proyect.petshop.adapters.CartSingleton
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class PDFActivity : AppCompatActivity() {
    private lateinit var textViewTotal: TextView
    private lateinit var tableLayout: TableLayout
    private lateinit var buttonDownload: Button
    private lateinit var pdfFile: File

    private var clienteNombre: String? = null
    private var clienteCedula: String? = null
    private var clienteDireccion: String? = null
    private var clienteTelefono: String? = null
    private var clienteBanco: String? = null
    private var clienteNumeroBanco: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        textViewTotal = findViewById(R.id.textViewTotal)
        tableLayout = findViewById(R.id.tableLayoutCart)
        buttonDownload = findViewById(R.id.buttonDownload)

        val products = CartSingleton.getInstance().getCartItems()
        val totalPrice = CartSingleton.getInstance().calculateTotal()

        // Formatear el precio total a dos decimales
        val formattedTotal = String.format("%.2f", totalPrice)
        textViewTotal.text = "Total: $$formattedTotal"

        populateTable(products)

        buttonDownload.setOnClickListener {
            generatePDF(products, totalPrice)
        }

        // Configurar el ImageView de regresar
        findViewById<ImageView>(R.id.imageViewRegresar).setOnClickListener {
            finish() // Finaliza la actividad actual
        }
        // Configurar el Button de regresar
        findViewById<Button>(R.id.buttonRegresar).setOnClickListener {
            finish() // Finaliza la actividad actual
        }


        clienteNombre = intent.getStringExtra("CLIENTE_NOMBRE") ?: "No especificado"
        clienteCedula = intent.getStringExtra("CLIENTE_CEDULA") ?: "No especificado"
        clienteDireccion = intent.getStringExtra("CLIENTE_DIRECCION") ?: "No especificado"
        clienteTelefono = intent.getStringExtra("CLIENTE_TELEFONO") ?: "No especificado"
        clienteBanco = intent.getStringExtra("CLIENTE_BANCO") ?: "----"
        clienteNumeroBanco = intent.getStringExtra("CLIENTE_NUMERO_BANCO") ?: "----"

        findViewById<TextView>(R.id.textViewClienteNombre).text = clienteNombre
        findViewById<TextView>(R.id.textViewClienteCedula).text = clienteCedula
        findViewById<TextView>(R.id.textViewClienteDireccion).text = clienteDireccion
        findViewById<TextView>(R.id.textViewClienteTelefono).text = clienteTelefono
        findViewById<TextView>(R.id.textViewClienteBanco).text = clienteBanco
        findViewById<TextView>(R.id.textViewClienteNumeroBanco).text = clienteNumeroBanco

    }

    private fun populateTable(products: List<Product>) {
        // Agregar una fila para los títulos
        val titleRow = TableRow(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
        }

        val titleName = TextView(this).apply {
            text = "Producto"
            setPadding(8, 8, 8, 8)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.LTGRAY)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f)
        }

        val titlePrice = TextView(this).apply {
            text = "Precio"
            setPadding(8, 8, 8, 8)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.LTGRAY)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)
        }

        val titleQuantity = TextView(this).apply {
            text = "Cantidad"
            setPadding(8, 8, 8, 8)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.LTGRAY)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)
        }

        val titleSubtotal = TextView(this).apply {
            text = "Subtotal"
            setPadding(8, 8, 8, 8)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.LTGRAY)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)
        }

        titleRow.addView(titleName)
        titleRow.addView(titlePrice)
        titleRow.addView(titleQuantity)
        titleRow.addView(titleSubtotal)

        tableLayout.addView(titleRow)

        // Agregar un separador entre los títulos y los productos
        val titleSeparator = View(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                2 // Ajuste del grosor del separador
            )
            setBackgroundColor(Color.BLACK)
        }
        tableLayout.addView(titleSeparator)

        for (product in products) {
            val tableRow = TableRow(this).apply {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
            }

            val productName = TextView(this).apply {
                text = product.nombre
                setPadding(8, 8, 8, 8)
                maxLines = 3
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f)
            }

            val productPrice = TextView(this).apply {
                text = "$${product.precio}"
                setPadding(8, 8, 8, 8)
                gravity = Gravity.CENTER
                maxLines = 1
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)
            }

            val productQuantity = TextView(this).apply {
                text = product.cantidad.toString()
                setPadding(8, 8, 8, 8)
                gravity = Gravity.CENTER
                maxLines = 1
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)
            }

            val productSubtotal = TextView(this).apply {
                text = "$${product.precio * product.cantidad}"
                setPadding(8, 8, 8, 8)
                gravity = Gravity.CENTER
                maxLines = 1
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)
            }

            tableRow.addView(productName)
            tableRow.addView(productPrice)
            tableRow.addView(productQuantity)
            tableRow.addView(productSubtotal)

            tableLayout.addView(tableRow)

            // Agregar un separador entre cada producto
            val productSeparator = View(this).apply {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    1 // Ajuste del grosor del separador
                )
                setBackgroundColor(Color.GRAY)
            }
            tableLayout.addView(productSeparator)
        }
    }


    private fun generatePDF(products: List<Product>, totalPrice: Double) {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // Tamaño A4: 595x842 puntos
        var page: PdfDocument.Page? = null
        var canvas: Canvas? = null
        val paint = Paint()

        // Load the logo
        val logoBitmap = BitmapFactory.decodeResource(resources, R.drawable.logover)

        // Define the desired width and height for the scaled logo
        val desiredWidth = pageInfo.pageWidth.toInt() /5 // Adjust as needed
        val desiredHeight = pageInfo.pageHeight.toInt() / 7 // Adjust as needed

        // Scale the logo to the desired size
        val scaledLogo = Bitmap.createScaledBitmap(logoBitmap, desiredWidth, desiredHeight, true)

        // Coordinates to position the image at the top-left corner
        val topRightX = pageInfo.pageWidth - scaledLogo.width.toFloat()
        val topLeftY = 0f

        val logoPaint = Paint().apply {
            alpha = 30 // Opacity of the logo (0-255, where 0 is completely transparent and 255 is completely opaque)
        }

// Draw the scaled logo at the top-left corner of the page
        canvas?.drawBitmap(scaledLogo, topRightX, topLeftY, logoPaint)


        // Coordenadas iniciales para el contenido
        var yPosition = 150f
        var productListStartY = yPosition


        // Dibujar las filas de la tabla con datos de productos
        for (product in products) {
            val productNameLines = splitProductName(product.nombre)

            // Calcular la altura necesaria para la celda según el número de líneas
            val lineHeight = 20f // Espacio entre líneas
            val maxLines = 2 // Máximo dos líneas por celda
            val cellHeight = lineHeight * maxLines

            // Determinar la altura necesaria para el nombre del producto
            val productNameHeight = lineHeight * productNameLines.size
            val rowHeight = maxOf(cellHeight, productNameHeight)

            // Verificar si es necesario iniciar una nueva página
            if (page == null || yPosition + rowHeight > pageInfo.pageHeight - 50) {
                page?.let {
                    document.finishPage(it)
                }
                page = document.startPage(pageInfo)
                canvas = page?.canvas
                yPosition = 100f // Reiniciar la posición vertical en la nueva página

                // Repetir el encabezado y cualquier contenido fijo en cada nueva página
                paint.color = Color.RED
                paint.textSize = 20f
                val title = "Factura de compra"
                val titleWidth = paint.measureText(title)
                val startX = (pageInfo.pageWidth - titleWidth) / 2
                canvas?.drawText(title, startX, yPosition, paint)
                yPosition += 10

                paint.color = Color.BLACK
                paint.textSize = 10f
                canvas?.drawText("R.U.C.: 1785456858542", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("No: 0000452", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Fecha: 17/07/2024 --- 14:25PM", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawLine(50f, yPosition, 550f, yPosition, paint)
                yPosition += 10

                val title1 ="Datos del cliente"
                val titleWidth1 = paint.measureText(title1)
                val startX1 = (pageInfo.pageWidth - titleWidth1) / 2
                canvas?.drawText(title1, startX1, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Nombre del Cliente: $clienteNombre", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Cédula: $clienteCedula", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Telefono: $clienteTelefono", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Direccion: $clienteDireccion", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Banco: $clienteBanco", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Numero de la Tarjeta: $clienteNumeroBanco", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawLine(50f, yPosition, 550f, yPosition, paint)
                yPosition += 10

                val title2 ="Datos del Local"
                val titleWidth2 = paint.measureText(title2)
                val startX2 = (pageInfo.pageWidth - titleWidth2) / 2
                canvas?.drawText(title2, startX2, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Nombre del local: PatApp", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Dirección del Local: Av. Patria y 10 de Agosto", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Teléfono: 0978451524568 o 02-458745214", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawText("Correo: PatApp@hotmail.com", 50f, yPosition, paint)
                yPosition += 10
                canvas?.drawLine(50f, yPosition, 550f, yPosition, paint)
                // Dibujar las cabeceras de la tabla en cada nueva página
                yPosition += 30

                // Draw a rectangle for the "Factura" section
                paint.textSize = 10f
                paint.color = Color.BLUE
                val title3 ="Facturacion"
                val titleWidth3 = paint.measureText(title3)
                val startX3 = (pageInfo.pageWidth - titleWidth3) / 2
                canvas?.drawText(title3, startX3, yPosition, paint)
                yPosition += 10

                paint.color = Color.GRAY // Adjust color as needed
                paint.style = Paint.Style.STROKE
                // Encabezado de la tabla
                canvas?.drawRect(50f, yPosition, pageInfo.pageWidth - 50f, yPosition + 30f, paint)
                paint.color = Color.RED
                paint.textSize = 10f
                canvas?.drawText("Nombre", 70f, yPosition + 30, paint)
                canvas?.drawText("Precio", 410f, yPosition + 30, paint)
                canvas?.drawText("Cantidad", 450f, yPosition + 30, paint)
                canvas?.drawText("Subtotal", 500f, yPosition + 30, paint)
                paint.color = Color.BLACK
                yPosition += 50f
            }
            // Dibujar la línea debajo de las etiquetas y el producto
            paint.color = Color.BLACK
            paint.style = Paint.Style.STROKE
            canvas?.drawRect(
                RectF(
                    50f,
                    yPosition,
                    (pageInfo.pageWidth - 50).toFloat(),
                    yPosition + rowHeight
                ), paint
            )
            paint.style = Paint.Style.FILL
            // Dibujar los datos del producto en la tabla
            paint.color = Color.BLACK
            paint.textSize = 10f
            canvas?.drawText(product.nombre, 70f, yPosition, paint)
            canvas?.drawText("$${product.precio}", 411f, yPosition, paint)
            canvas?.drawText("${product.cantidad}", 470f, yPosition, paint)
            canvas?.drawText("$${product.precio * product.cantidad}", 505f, yPosition, paint)
            yPosition += rowHeight // Avanzar la posición vertical
        }
        canvas?.drawBitmap(scaledLogo, 0f, 0f, logoPaint)


        // Dibujar el marco alrededor del total
        paint.style = Paint.Style.STROKE
        canvas?.drawRect(
            RectF(
                50f,
                yPosition + 20,
                (pageInfo.pageWidth - 50).toFloat(),
                yPosition + 40
            ), paint
        )
        paint.style = Paint.Style.FILL

        // Dibujar el total al final
        paint.color = Color.RED
        paint.textSize = 12f
        canvas?.drawText(
            "Total: $$totalPrice",
            (pageInfo.pageWidth - paint.measureText("Total: $$totalPrice")) / 2,
            yPosition + 30,
            paint

        )

        // Mostrar mensaje de agradecimiento
        paint.color = Color.BLUE
        paint.textSize = 14f
        val thanksMessage = "Gracias por su compra"
        val startX4 = (pageInfo.pageWidth - paint.measureText(thanksMessage)) / 2
        canvas?.drawText(thanksMessage, startX4, yPosition + 70, paint)


        // Terminar la página actual
        page?.let {
            document.finishPage(it)
        }

        // Guardar el archivo PDF en almacenamiento externo
        pdfFile = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "factura.pdf")
        try {
            document.writeTo(FileOutputStream(pdfFile))
            Toast.makeText(this, "PDF generado correctamente", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al generar PDF", Toast.LENGTH_SHORT).show()
        }

        // Cerrar el documento
        document.close()

        // Abrir el archivo PDF generado
        openGeneratedPDF()
    }

    private fun openGeneratedPDF() {
        // Abrir el archivo PDF generado con una aplicación visor de PDF instalada
        val uri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider",
            pdfFile
        )
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivity(intent)
    }

    private fun splitProductName(name: String): List<String> {
        // Dividir el nombre del producto en líneas para ajustarse al ancho de la celda
        val words = name.split(" ").toMutableList()
        val lines = mutableListOf<String>()
        var currentLine = StringBuilder()

        while (words.isNotEmpty()) {
            if (currentLine.isEmpty()) {
                currentLine.append(words.removeAt(0))
            } else {
                val tempLine = StringBuilder(currentLine).append(" ").append(words[0])
                if (tempLine.length <= 30) {
                    currentLine = tempLine
                    words.removeAt(0)
                } else {
                    lines.add(currentLine.toString())
                    currentLine = StringBuilder()
                }
            }
        }

        if (currentLine.isNotEmpty()) {
            lines.add(currentLine.toString())
        }

        return lines
    }
}