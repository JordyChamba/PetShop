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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.preference.PreferenceManager;

import com.proyect.petshop.R;
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.adapters.ProductAdapter;
import com.proyect.petshop.models.Product;
import com.proyect.petshop.adapters.CartSingleton;

import java.util.ArrayList;
import java.util.List;

public class AlimentosActivity_birds extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

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

        // Inicializar lista de productos de alimentos para aves
        productList = new ArrayList<>();
        productList.add(new Product("SEMILLAS PARA AVES PEQUEÑAS", 8.0, R.drawable.semilla_aves_pequenas, 1));
        productList.add(new Product("SEMILLAS PARA AVES MEDIANAS", 10.0, R.drawable.semilla_aves_medianas, 1));
        productList.add(new Product("SEMILLAS PARA AVES GRANDES", 12.0, R.drawable.semilla_aves_grandes, 1));
        productList.add(new Product("MIJO ROJOS", 1.80, R.drawable.ca1, 1));
        productList.add(new Product("MIXTURA PARA PERICOS Y CANARIOS", 1.81, R.drawable.ca2, 1));
        productList.add(new Product("ALPISTE FUNDA LIBRA", 1.03, R.drawable.ca3, 1));
        productList.add(new Product("MIXTURA PARA CANARIOS", 6.34, R.drawable.ca4, 1));
        productList.add(new Product("MIXTURA PARA NINFA", 5.14, R.drawable.ca5, 1));
        productList.add(new Product("MIXTURA PARA LOROS", 6.34, R.drawable.ca6, 1));
        productList.add(new Product("PIENSO MANTENIMIENTO ALTA ENERGIA", 16.24, R.drawable.ca7, 1));
        productList.add(new Product("MENU PARA LOROS", 5.34, R.drawable.ca8, 1));
        productList.add(new Product("ALIMENTO COMPLETO PARA LOROS", 8.33, R.drawable.ca9, 1));
        productList.add(new Product("PIENSO MANTENIMIENTO MINI", 9.69, R.drawable.ca10, 1));
        productList.add(new Product("MENU PARA CANARIOS", 3.25, R.drawable.ca11, 1));
        productList.add(new Product("BRADIUM MIXTURA AGAPORNIS Y NINFAS", 11.79, R.drawable.ca12, 1));
        productList.add(new Product("CANTOS 250", 5.38, R.drawable.ca13, 1));
        productList.add(new Product("S. D. CANARIOS ", 4.33, R.drawable.ca14, 1));
        productList.add(new Product("BRADIUM MIXTURA PERICOS", 9.72, R.drawable.ca15, 1));
        productList.add(new Product("ALIMENTO COMPLETO PARA NINFAS", 7.78, R.drawable.ca16, 1));
        productList.add(new Product("MENU LIFE PARA PERICOS", 5.44, R.drawable.ca17, 1));
        productList.add(new Product("ALIMENTOS PREMIUM EXOTICOS", 3.26, R.drawable.ca18, 1));
        productList.add(new Product("BRADIUM PERICOS", 5.21, R.drawable.ca19, 1));
        productList.add(new Product("BRADIUM CANARIOS", 3.81, R.drawable.ca20, 1));
        productList.add(new Product("COMIDA PERICOS GRANDES", 5.62, R.drawable.ca21, 1));
        productList.add(new Product("PIENSO MINI", 134.04, R.drawable.ca23, 1));
        productList.add(new Product("MENU PARA COTORRAS", 4.89, R.drawable.ca22, 1));
        productList.add(new Product("BRADIUM AGAPORNIS Y NINFAS", 2.96, R.drawable.ca24, 1));
        productList.add(new Product("PIENSO PARA AGAPORNIS Y CAROLINAS", 3.36, R.drawable.ca25, 1));

        // Inicializar la lista filtrada
        filteredProductList = new ArrayList<>(productList);

        // Configurar RecyclerView y adaptador
        adapter = new ProductAdapter(this, filteredProductList, this, cartItemCountTextView);
        recyclerView.setAdapter(adapter);

        // Inicializar SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Restaurar el contador global al inicio
        updateCartItemCount();

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
            Intent intent = new Intent(AlimentosActivity_birds.this, CarritoActivity.class);
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
