package com.proyect.petshop.ActivityBirds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.petshop.ActivityCats.VestimentaActivity_cats;
import com.proyect.petshop.R;
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.adapters.ProductAdapter;
import com.proyect.petshop.models.Product;
import com.proyect.petshop.adapters.CartSingleton;

import java.util.ArrayList;
import java.util.List;

public class ZonaJuegosActivity_birds extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    private List<Product> filteredProductList;
    private SearchView searchView;
    private TextView cartItemCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartItemCountTextView = findViewById(R.id.cartItemCount); // Ajusta el ID según tu layout

        // Inicializar lista de productos de zona de juegos para aves
        productList = new ArrayList<>();
        productList.add(new Product("COLUMPIO", 10.0, R.drawable.columpio_ave, 1));
        productList.add(new Product("ESCALERA", 15.0, R.drawable.escalera_ave, 1));
        productList.add(new Product("CASA PARA AVE", 20.0, R.drawable.casa_ave, 1));
        productList.add(new Product("JUGUETE ESPEJO REDONDO", 1.89, R.drawable.pj1, 1));
        productList.add(new Product("JUGUETE DE MADERA CON CARTON", 17.01, R.drawable.pj2, 1));
        productList.add(new Product("JUGUETE DE PERLAS", 5.74, R.drawable.pj3, 1));
        productList.add(new Product("JUGUETE DE CUERDAS", 6.44, R.drawable.pj4, 1));
        productList.add(new Product("MADERA PARA MORDISQUEAR", 4.89, R.drawable.pj5, 1));
        productList.add(new Product("JUGUETE COLGANTE", 8.34, R.drawable.pj6, 1));
        productList.add(new Product("COLUMPIO CON ARCO DE MADERA", 2.75, R.drawable.pj7, 1));
        productList.add(new Product("COLUMPIO ARCO DE TACOS", 17.08, R.drawable.pj8, 1));
        productList.add(new Product("ESCALERA PARA PAJAROS", 14.08, R.drawable.pj9, 1));
        productList.add(new Product("PERCHAS CON CUERDAS", 4.24, R.drawable.pj10, 1));
        productList.add(new Product("COLUMPIO ALGODON", 11.89, R.drawable.pj11, 1));
        productList.add(new Product("JUGUETE MOVIL PARA PAJAROS", 6.22, R.drawable.pj13, 1));
        productList.add(new Product("GIMNASIO INTERACTIVO", 51.50, R.drawable.pj14, 1));
        productList.add(new Product("ESCALERA NATURAL", 4.59, R.drawable.pj15, 1));
        productList.add(new Product("REA DE JUEGOS", 39.12, R.drawable.pj16, 1));
        productList.add(new Product("CUERDA DE JUEGOS", 16.65, R.drawable.pj17, 1));
        productList.add(new Product("JUGUETE DE MADERA", 21.89, R.drawable.pj18, 1));
        productList.add(new Product("PARQUE PARA AVES", 16.77, R.drawable.pj19, 1));
        productList.add(new Product("PERCHA COLUMPIO", 46.97, R.drawable.pj20, 1));
        productList.add(new Product("ARNES PARA TODO TIPO DE AVES", 16.65, R.drawable.pj21, 1));
        productList.add(new Product("COLUMPIO CON ESPEJO", 3.56, R.drawable.pj22, 1));
        productList.add(new Product("CUERDA DOS ANILLOS", 15.80, R.drawable.pj23, 1));
        productList.add(new Product("BOLAS DE COLORES", 8.35, R.drawable.pj24, 1));
        productList.add(new Product("MADERA DE COLORES", 12.07, R.drawable.pj25, 1));
        productList.add(new Product("RED DE ESCALADA", 7.18, R.drawable.pj26, 1));
        productList.add(new Product("STREET BALL", 13.01, R.drawable.pj27, 1));


        // Inicializar la lista filtrada
        filteredProductList = new ArrayList<>(productList);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar RecyclerView y adaptador
        adapter = new ProductAdapter(this, filteredProductList, this, cartItemCountTextView);
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
            Intent intent = new Intent(ZonaJuegosActivity_birds.this, CarritoActivity.class);
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
}
