package com.proyect.petshop.adapters;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.petshop.R;
import com.proyect.petshop.models.Product;
import com.proyect.petshop.models.PDFActivity;

import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> cartItems = CartSingleton.getInstance().getCartItems();
        adapter = new CartAdapter(this, cartItems);
        recyclerView.setAdapter(adapter);

        textViewTotal = findViewById(R.id.textViewTotal);
        updateTotal(cartItems);

        Button buttonCart = findViewById(R.id.buttonCart);
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEfectivoDialog();
            }
        });

        Button buttonEmergente = findViewById(R.id.buttonEmergente);
        buttonEmergente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTarjetaDialog();
            }
        });
    }

    private void updateTotal(List<Product> cartItems) {
        double total = 0.0;
        for (Product product : cartItems) {
            total += product.getPrecio() * product.getCantidad();
        }
        if (textViewTotal != null) {
            textViewTotal.setText(String.format("Total: $%.2f", total));
        }
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    private boolean isValidCedula(String cedula) {
        return cedula.matches("\\d{1,10}");
    }

    private boolean isValidBankName(String bankName) {
        return bankName.matches("[a-zA-Z ]+");
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{10}");
    }

    private void showEfectivoDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_efectivo, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        EditText editTextNombre = dialogView.findViewById(R.id.editTextNombre);
        EditText editTextCedula = dialogView.findViewById(R.id.editTextCedula);

        // Set input filters
        editTextNombre.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        editTextCedula.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});

        // Add Text Watchers
        addTextWatchers(editTextNombre, editTextCedula, null, null);

        dialogBuilder.setTitle("Pago en Efectivo")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    String nombre = editTextNombre.getText().toString();
                    String cedula = editTextCedula.getText().toString();

                    if (!isValidName(nombre)) {
                        Toast.makeText(CarritoActivity.this, "Nombre inválido. Solo letras permitidas.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!isValidCedula(cedula)) {
                        Toast.makeText(CarritoActivity.this, "Cédula inválida. Solo números permitidos, máximo 10 dígitos.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Aquí puedes agregar la lógica para procesar el pago en efectivo
                    openPDFActivity(nombre, cedula, "Efectivo");
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void showTarjetaDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_tarjeta, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        EditText editTextNombre = dialogView.findViewById(R.id.editTextNombre);
        EditText editTextCedula = dialogView.findViewById(R.id.editTextCedula);
        EditText editTextBanco = dialogView.findViewById(R.id.editTextBanco);
        EditText editTextNumeroTarjeta = dialogView.findViewById(R.id.editTextNumeroTarjeta);

        // Set input filters
        editTextNombre.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        editTextCedula.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        editTextBanco.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        editTextNumeroTarjeta.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});

        // Add Text Watchers
        addTextWatchers(editTextNombre, editTextCedula, editTextBanco, editTextNumeroTarjeta);

        dialogBuilder.setTitle("Pago con Tarjeta")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    String nombre = editTextNombre.getText().toString();
                    String cedula = editTextCedula.getText().toString();
                    String banco = editTextBanco.getText().toString();
                    String numeroTarjeta = editTextNumeroTarjeta.getText().toString();

                    if (!isValidName(nombre)) {
                        Toast.makeText(CarritoActivity.this, "Nombre inválido. Solo letras permitidas.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!isValidCedula(cedula)) {
                        Toast.makeText(CarritoActivity.this, "Cédula inválida. Solo números permitidos, máximo 10 dígitos.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!isValidBankName(banco)) {
                        Toast.makeText(CarritoActivity.this, "Nombre del banco inválido. Solo letras permitidas.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!isValidCardNumber(numeroTarjeta)) {
                        Toast.makeText(CarritoActivity.this, "Número de tarjeta inválido. Solo números permitidos, 10 dígitos.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Aquí puedes agregar la lógica para procesar el pago con tarjeta
                    openPDFActivity(nombre, cedula, "Tarjeta");
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    private void addTextWatchers(EditText editTextNombre, EditText editTextCedula, EditText editTextBanco, EditText editTextNumeroTarjeta) {
        if (editTextNombre != null) {
            editTextNombre.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!isValidName(s.toString())) {
                        editTextNombre.setError("Nombre inválido. Solo letras permitidas.");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        if (editTextCedula != null) {
            editTextCedula.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!isValidCedula(s.toString())) {
                        editTextCedula.setError("Cédula inválida. Solo números permitidos, máximo 10 dígitos.");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        if (editTextBanco != null) {
            editTextBanco.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!isValidBankName(s.toString())) {
                        editTextBanco.setError("Nombre del banco inválido. Solo letras permitidas.");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        if (editTextNumeroTarjeta != null) {
            editTextNumeroTarjeta.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!isValidCardNumber(s.toString())) {
                        editTextNumeroTarjeta.setError("Número de tarjeta inválido. Solo números permitidos, 10 dígitos.");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

// Método para abrir la actividad de PDF
public void openPDFActivity(String clienteNombre, String clienteCedula, String pagoTipoSeleccionado) {
    Intent intent = new Intent(this, PDFActivity.class);
    intent.putExtra("CLIENTE_NOMBRE", clienteNombre); // Quita el ":" después de CLIENTE_NOMBRE
    intent.putExtra("CLIENTE_CEDULA", clienteCedula); // Quita el ":" después de CLIENTE_CEDULA
    intent.putExtra("PAGO_TIPO", pagoTipoSeleccionado);

    startActivity(intent);
}


    // Método para manejar la eliminación del producto
    public void onDeleteButtonClick(Product product) {
        CartSingleton.getInstance().removeFromCart(product);
        updateTotal(CartSingleton.getInstance().getCartItems());
    }
}
