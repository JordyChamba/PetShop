package com.proyect.petshop.ActivityCats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.petshop.R;
import com.proyect.petshop.activityDogs.AlimentosActivity_dogs;
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.adapters.ProductAdapter;
import com.proyect.petshop.models.Product;
import com.proyect.petshop.adapters.CartSingleton;

import java.util.ArrayList;
import java.util.List;

public class VestimentaActivity_cats extends AppCompatActivity implements ProductAdapter.OnProductClickListener{

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    private List<Product> productList;

    private List<Product> filteredProductList;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar lista de productos de vestimenta para gatos
        productList = new ArrayList<>();
        productList.add(new Product("CHAQUETA PARA GATO", 15.0, R.drawable.chaqueta_gato, 1));
        productList.add(new Product("SUETER PARA GATO", 20.0, R.drawable.sueter_gato, 1));
        productList.add(new Product("VESTIDO PARA GATO", 25.0, R.drawable.vestido_gato, 1));
        productList.add(new Product("ARMES CHALECO", 21.00, R.drawable.rc1, 1));
        productList.add(new Product("CHALECO REFLECTIVO", 21.00, R.drawable.rc2, 1));
        productList.add(new Product("DISFRAZ DE ABEJA", 15.00, R.drawable.rc3, 1));
        productList.add(new Product("LAZOS GATUNOS", 6.00, R.drawable.rc4, 1));
        productList.add(new Product("MELENA PARA GATOS", 10.00, R.drawable.rc5, 1));
        productList.add(new Product("TRAJE DE ENFERMERA", 20.00, R.drawable.rc6, 1));
        productList.add(new Product("TRAJE DOCTOR", 17.00, R.drawable.rc7, 1));
        productList.add(new Product("TRAJE POLICIA", 15.00, R.drawable.rc8, 1));
        productList.add(new Product("TRAJE VAQUERO", 19.00, R.drawable.rc9, 1));

        // Inicializar la lista filtrada
        filteredProductList = new ArrayList<>(productList);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar RecyclerView y adaptador
        adapter = new ProductAdapter(this, filteredProductList, this);
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
            Intent intent = new Intent(VestimentaActivity_cats.this, CarritoActivity.class);
            startActivity(intent);
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
}
