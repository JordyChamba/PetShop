package com.proyect.petshop.ActivityBirds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.petshop.R;
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.adapters.ProductAdapter;
import com.proyect.petshop.models.Product;
import com.proyect.petshop.adapters.CartSingleton;

import java.util.ArrayList;
import java.util.List;

public class VestimentaActivity_birds extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    private List<Product> filteredProductList;
    private SearchView searchView;
    private TextView cartItemCountTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartItemCountTextView = findViewById(R.id.cartItemCount); // Ajusta el ID según tu layout

        // Inicializar lista de productos de vestimenta para aves
        productList = new ArrayList<>();
        productList.add(new Product("CJAQUETA PARA AVE", 10.0, R.drawable.chaqueta_ave, 1));
        productList.add(new Product("SUETER PARA AVE", 15.0, R.drawable.sueter_ave, 1));
        productList.add(new Product("VESTIDO PARA AVE", 18.0, R.drawable.vestido_ave, 1));
        productList.add(new Product("JAULA ECONOMICA", 48.00, R.drawable.ap1, 1));
        productList.add(new Product("JAULA DOBLE TECHO AVES PEQUEÑAS", 26.00, R.drawable.ap2, 1));
        productList.add(new Product("JAULA GRANDE AVES CANARIAS", 53.50, R.drawable.ap3, 1));
        productList.add(new Product("JAULA GRANDE LOROS", 223.20, R.drawable.ap4, 1));
        productList.add(new Product("JAULA EXTRA GRANDE PARA LOROS", 269.20, R.drawable.ap5, 1));
        productList.add(new Product("FIBRA DE COCO EN HILO", 2.50, R.drawable.ap6, 1));
        productList.add(new Product("MATERIAL PARA NIDOS DE AVES", 0.50, R.drawable.ap7, 1));
        productList.add(new Product("NIDO DE FIBRA NATURAL PARA PERICOS", 4.50, R.drawable.ap8, 1));
        productList.add(new Product("NIDOTERMICO PARA PERICOS", 4.00, R.drawable.ap9, 1));
        productList.add(new Product("BAÑERA EXTERNA PARA PERICOS", 5.00, R.drawable.ap10, 1));
        productList.add(new Product("PLATO PLASTICO PARA CANARIOS", 1.50, R.drawable.ap11, 1));
        productList.add(new Product("PLATO PLASCTICO TRIPLE", 1.50, R.drawable.ap12, 1));
        productList.add(new Product("BEBEDERO PARA JAULAS", 2.00, R.drawable.ap13, 1));
        productList.add(new Product("BALERA EN FORMA DE HOJA", 3.50, R.drawable.ap14, 1));
        productList.add(new Product("BEBERO", 22.00, R.drawable.ap15, 1));
        productList.add(new Product("COMEDERO/BEBEDERO", 2.64, R.drawable.ap16, 1));
        productList.add(new Product("COMEDOR PARA PAJARON", 17.79, R.drawable.ap17, 1));
        productList.add(new Product("COMEDERO CERAMICO", 2.39, R.drawable.ap18, 1));
        productList.add(new Product("ENGANCHE COMEDERO", 1.43, R.drawable.ap19, 1));
        productList.add(new Product("TAZA PARA SEMILLAS Y AGUA", 5.84, R.drawable.ap20, 1));
        productList.add(new Product("BEBEDERO 940 GR", 29.75, R.drawable.ap21, 1));
        productList.add(new Product("COMEDERO DE FRUTAS", 7.72, R.drawable.ap22, 1));
        productList.add(new Product("COMEDERO HOTEL", 42.40, R.drawable.ap23, 1));
        productList.add(new Product("CAMPANA DE CALCIO", 1.50, R.drawable.ap24, 1));
        productList.add(new Product("COMEDOR PARA AVES EN LIBERTAD", 16.00, R.drawable.ap25, 1));

        // Inicializar la lista filtrada
        filteredProductList = new ArrayList<>(productList);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar RecyclerView y adaptador
        adapter = new ProductAdapter(this, filteredProductList, this, cartItemCountTextView);

        // Inicializar SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Restaurar el contador global al inicio
        updateCartItemCount();

        recyclerView.setAdapter(adapter);

        // Inicializar SearchView
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return true;
            }
        });

        // Botón "Compra rápida"
        Button buttonQuickBuy = findViewById(R.id.buttonQuickBuy);
        buttonQuickBuy.setOnClickListener(v -> {
            // Navegar hacia CarritoActivity
            Intent intent = new Intent(VestimentaActivity_birds.this, CarritoActivity.class);
            startActivity(intent);
        });
        // Configurar el botón de regreso (ImageView)
        ImageView imageViewRegresar = findViewById(R.id.imageViewRegresar);
        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finaliza la actividad actual
            }
        });

        // Configurar el botón de regreso (Button)
        Button buttonRegresar = findViewById(R.id.buttonRegresar);
        buttonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finaliza la actividad actual
            }
        });
    }

    private void filterProducts(String query) {
        filteredProductList.clear();
        if (query.isEmpty()) {
            filteredProductList.addAll(productList);
        } else {
            for (Product product : productList) {
                if (product.getNombre().toLowerCase().contains(query.toLowerCase())) {
                    filteredProductList.add(product);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    // Método para manejar el clic en el botón de agregar al carrito
    @Override
    public void onAddToCartClick(Product product) {
        CartSingleton.getInstance().addToCart(product);
    }
    // Método para actualizar el TextView del contador global
    private void updateCartItemCount() {
        int total = 0;
        for (Product product : productList) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            int counter = sharedPreferences.getInt("counter_" + product.getId(), 0);
            total += counter;
        }
        // Actualizar el TextView
        cartItemCountTextView.setText(String.valueOf(total));
    }
}
