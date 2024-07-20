package com.proyect.petshop.activityDogs;

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
import com.proyect.petshop.R;
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.adapters.ProductAdapter;
import com.proyect.petshop.models.Product;
import com.proyect.petshop.adapters.CartSingleton;
import com.proyect.petshop.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ZonaJuegosActivity_dogs extends AppCompatActivity implements ProductAdapter.OnProductClickListener{

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> gameList;

    private List<Product> filteredProductList;
    private SearchView searchView;
    private TextView cartItemCountTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        cartItemCountTextView = findViewById(R.id.cartItemCount); // Ajusta el ID según tu layout

        // Inicializar la lista de juegos
        gameList = new ArrayList<>();
        gameList.add(new Product("PELOTA DE GOMA", 5.55, R.drawable.pelota_goma, 1));
        gameList.add(new Product("CUERDA PARA MORDER", 15.00, R.drawable.cuerda_morder, 1));
        gameList.add(new Product("FRISBEE", 15.50, R.drawable.frisbee, 1));
        gameList.add(new Product("JUEGUTE DE PELUCHE", 10.27, R.drawable.juguete_peluche, 1));
        gameList.add(new Product("JUGUETE INTERACTIVO", 15.24, R.drawable.juguete_interactivo, 1));
        gameList.add(new Product("PELUCHE CASTOR DE FELPA", 10.54, R.drawable.jp1, 1));
        gameList.add(new Product("PELUCHE ASNO DE FELPA", 10.35, R.drawable.jp2, 1));
        gameList.add(new Product("PELUCHE AGUCATE CON SONIDO", 8.38, R.drawable.jp3, 1));
        gameList.add(new Product("PELUCHE ELEFANTE", 10.18, R.drawable.jp4, 1));
        gameList.add(new Product("PELUCGE CONEJO DE FELPA", 7.18, R.drawable.jp5, 1));
        gameList.add(new Product("CONFORT PARA PERRO", 14.14, R.drawable.jp6, 1));
        gameList.add(new Product("PELUCHE PATO DE FELPA", 13.06, R.drawable.jp7, 1));
        gameList.add(new Product("PELUCHE NAVIDAD PARA PERROS", 8.30, R.drawable.jp8, 1));
        gameList.add(new Product("PELUCHE LEON", 8.49, R.drawable.jp9, 1));
        gameList.add(new Product("PELUCHE PERRO", 9.57, R.drawable.jp10, 1));
        gameList.add(new Product("PELUCHE MORDEDOR", 7.71, R.drawable.jp11, 1));
        gameList.add(new Product("PELUCHE PAJARO DE FELPA", 13.11, R.drawable.jp12, 1));
        gameList.add(new Product("PELUCHE DE NAVIDAD GRANDE", 9.92, R.drawable.jp13, 1));
        gameList.add(new Product("PERRO STUPS CON SONIDO", 10.27, R.drawable.jp14, 1));
        gameList.add(new Product("MINI IWA DE PELUCHE", 13.57, R.drawable.jp15, 1));
        gameList.add(new Product("PELUCHE FLAMENCO GRANDE", 12.46, R.drawable.jp16, 1));
        gameList.add(new Product("COMFORT BEAR", 12.85, R.drawable.jp17, 1));
        gameList.add(new Product("ARDILLA", 9.20, R.drawable.jp18, 1));
        gameList.add(new Product("OVEJA PELUCHE", 10.62, R.drawable.jp19, 1));
        gameList.add(new Product("PELUCHE HUESO ACOLCHADO", 3.94, R.drawable.jp20, 1));
        gameList.add(new Product("OLEG DE PELUCHE", 4.41, R.drawable.jp21, 1));
        gameList.add(new Product("JUGUETE ANIMALES DE CUERDA", 9.71, R.drawable.jp22, 1));
        gameList.add(new Product("PELUCHE ERIZO", 9.58, R.drawable.jp23, 1));
        gameList.add(new Product("JUEGUETE PERRO LONA", 15.67, R.drawable.jp24, 1));
        gameList.add(new Product("MORDEDOR ZORRO", 9.58, R.drawable.jp25, 1));
        gameList.add(new Product("PELUCHE ZEBRA CON SONIDO", 5.86, R.drawable.jp26, 1));
        gameList.add(new Product("JUEGUTE JIRAFA PARA CACHORRO", 10.90, R.drawable.jp27, 1));



        // Inicializar la lista filtrada
        filteredProductList = new ArrayList<>(gameList);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar RecyclerView y adaptador
        adapter = new ProductAdapter(this, filteredProductList, this, cartItemCountTextView);

        recyclerView.setAdapter(adapter);

        // Inicializar SearchView
        searchView = findViewById(R.id.searchView);
        //notificador contador
        SharedPreferences sharedPreferences = getSharedPreferences("cart_prefs", MODE_PRIVATE);
        int cartItemCount = sharedPreferences.getInt("cart_item_count", 0);
        cartItemCountTextView.setText(String.valueOf(cartItemCount));

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
        ImageView imageViewPrincipal = findViewById(R.id.imageViewPrincipal);

        View.OnClickListener quickBuyClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar hacia CarritoActivity
                Intent intent = new Intent(ZonaJuegosActivity_dogs.this, CarritoActivity.class);
                startActivity(intent);
            }
        };

        buttonQuickBuy.setOnClickListener(quickBuyClickListener);
        imageViewPrincipal.setOnClickListener(quickBuyClickListener);

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

        ImageView imageViewHome = findViewById(R.id.imageViewHome);
        Button buttonHome = findViewById(R.id.buttonHome);

        View.OnClickListener homeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZonaJuegosActivity_dogs.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        };

        imageViewHome.setOnClickListener(homeClickListener);
        buttonHome.setOnClickListener(homeClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualizar el contador del carrito cuando la actividad vuelva a estar en primer plano
        updateCartItemCount();
    }

    private void updateCartItemCount() {
        int itemCount = CartSingleton.getInstance().getCartItemCount();
        cartItemCountTextView.setText(String.valueOf(itemCount));
    }

    private void filterProducts(String query) {
        filteredProductList.clear();
        if (query.isEmpty()) {
            filteredProductList.addAll(gameList);
        } else {
            for (Product product : gameList) {
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
