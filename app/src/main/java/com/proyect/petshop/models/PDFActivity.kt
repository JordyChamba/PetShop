package com.proyect.petshop.models

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size: 595x842 points
        var page: PdfDocument.Page? = null
        var canvas: Canvas? = null
        val paint = Paint()

        paint.color = Color.BLACK
        paint.textSize = 10f

        // Coordenadas iniciales para el contenido
        var yPosition = 100f
        val columnWidths = floatArrayOf(200f, 20f, 20f, 20f) // Ancho de las columnas

        // Título de la factura en rojo
        paint.color = Color.RED
        paint.textSize = 20f
        canvas?.drawText("Factura de su compra", 50f, yPosition, paint)

        // Subtítulo "Datos del cliente"
        yPosition += 40
        paint.color = Color.BLACK
        paint.textSize = 10f
        canvas?.drawText("Datos del cliente", 50f, yPosition, paint)

        // Separador
        yPosition += 20
        paint.color = Color.GRAY
        canvas?.drawLine(50f, yPosition, 550f, yPosition, paint)

        // Texto "Nombre del Cliente"
        yPosition += 30
        paint.color = Color.BLACK
        paint.textSize = 10f
        canvas?.drawText("Nombre del Cliente: $clienteNombre", 50f, yPosition, paint)

        // Texto "Cédula"
        yPosition += 20
        canvas?.drawText("Cédula: $clienteCedula", 50f, yPosition, paint)

        // Tipo de pago
        yPosition += 20
        canvas?.drawText("Tipo de Pago: $clienteDireccion", 50f, yPosition, paint)

        // Separador
        yPosition += 20
        canvas?.drawLine(50f, yPosition, 550f, yPosition, paint)

        // Dibujar cabeceras de tabla
        yPosition += 30
        paint.color = Color.BLACK
        paint.textSize = 10f

        // Dibujar bordes de la tabla y cabeceras
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1f

        // Dibujar las cabeceras de la tabla
        canvas?.drawRect(50f, yPosition, 550f, yPosition + 40, paint) // Encabezado de la tabla
        canvas?.drawText("Nombre", 70f, yPosition + 30, paint)
        canvas?.drawText("Precio", 300f, yPosition + 30, paint)
        canvas?.drawText("Cantidad", 400f, yPosition + 30, paint)
        canvas?.drawText("Subtotal", 500f, yPosition + 30, paint)

        yPosition += 40

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
                canvas?.drawText("Factura de su compra", 50f, yPosition, paint)
                yPosition += 40
                canvas?.drawText("Datos del cliente", 50f, yPosition, paint)
                yPosition += 20
                canvas?.drawLine(50f, yPosition, 550f, yPosition, paint)
                yPosition += 30
                canvas?.drawText("Nombre del Cliente: $clienteNombre", 50f, yPosition, paint)
                yPosition += 20
                canvas?.drawText("Cédula: $clienteCedula", 50f, yPosition, paint)
                yPosition += 20
                canvas?.drawText("Tipo de Pago: $clienteDireccion", 50f, yPosition, paint)
                yPosition += 20
                canvas?.drawLine(50f, yPosition, 550f, yPosition, paint)
                yPosition += 30

                // Dibujar las cabeceras de la tabla en cada nueva página
                canvas?.drawRect(50f, yPosition, 550f, yPosition + 40, paint) // Encabezado de la tabla
                canvas?.drawText("Nombre", 70f, yPosition + 30, paint)
                canvas?.drawText("Precio", 300f, yPosition + 30, paint)
                canvas?.drawText("Cantidad", 400f, yPosition + 30, paint)
                canvas?.drawText("Subtotal", 500f, yPosition + 30, paint)

                yPosition += 40
            }

            // Dibujar los datos del producto en la tabla
            canvas?.drawText(product.nombre, 70f, yPosition, paint)
            canvas?.drawText("$${product.precio}", 300f, yPosition, paint)
            canvas?.drawText("${product.cantidad}", 400f, yPosition, paint)
            canvas?.drawText("$${product.precio * product.cantidad}", 500f, yPosition, paint)

            yPosition += rowHeight // Avanzar la posición vertical
        }

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
