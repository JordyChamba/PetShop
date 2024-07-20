package com.proyect.petshop.ActivityCats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
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
import com.proyect.petshop.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ZonaJuegosActivity_cats extends AppCompatActivity implements ProductAdapter.OnProductClickListener{

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
        cartItemCountTextView = findViewById(R.id.cartItemCount); // Ajusta el ID según tu layout

        // Inicializar la lista de productos
        productList = new ArrayList<>();
        productList.add(new Product("PELOTA DE JUGUETE", 8.00, R.drawable.pelota_juguete, 1));
        productList.add(new Product("RATON DE JUGUETE", 6.00, R.drawable.raton_juguete, 1));
        productList.add(new Product("TUNEL PARA GATOS", 15.00, R.drawable.tunel_gatos, 1));
        productList.add(new Product("CAÑA DE PLUMAS", 10.00, R.drawable.cana_plumas, 1));
        productList.add(new Product("LABERINTO PARA GATOS", 20.00, R.drawable.laberinto_gatos, 1));
        productList.add(new Product("RASCADOR DE ALFOMBRAS", 22.57, R.drawable.jc1, 1));
        productList.add(new Product("PLUMERO PARA GATO", 1.89, R.drawable.jc2, 1));
        productList.add(new Product("RASCADOR PARA GATOS", 15.10, R.drawable.jc3, 1));
        productList.add(new Product("CAÑA DE PESCAR", 1.89, R.drawable.jc4, 1));
        productList.add(new Product("PELOTA DE PLASTICO", 2.04, R.drawable.jc5, 1));
        productList.add(new Product("RATON CON MOVIMIENTO", 4.15, R.drawable.jc6, 1));
        productList.add(new Product("PELOTAS ARCOIRIS", 3.32, R.drawable.jc7, 1));
        productList.add(new Product("VARA PLUMERO", 2.39, R.drawable.jc8, 1));
        productList.add(new Product("PUNTERO LASER", 3.38, R.drawable.jc9, 1));
        productList.add(new Product("PELUCHE ERIZO", 5.37, R.drawable.jc10, 1));
        productList.add(new Product("PLUMA GIRATORIA", 20.95, R.drawable.jc11, 1));
        productList.add(new Product("TAMBOR RASCADOR", 17.25, R.drawable.jc12, 1));
        productList.add(new Product("PELOTA CASCABEL", 1.18, R.drawable.jc13, 1));
        productList.add(new Product("VARITA SPIDERMAN", 6.52, R.drawable.jc14, 1));
        productList.add(new Product("PELOTA ERIZO", 0.80, R.drawable.jc15, 1));
        productList.add(new Product("COJIN PARA GATOS", 2.12, R.drawable.jc16, 1));
        productList.add(new Product("JUGUETE INTERACTIVO", 19.22, R.drawable.jc17, 1));
        productList.add(new Product("TABLA RASCADOR", 11.48, R.drawable.jc18, 1));
        productList.add(new Product("TABLA RASCADOR GRIS", 12.52, R.drawable.jc19, 1));
        productList.add(new Product("RATON CON SONIDO", 20.00, R.drawable.jc20, 1));
        productList.add(new Product("TUNEL TRIPLE", 24.56, R.drawable.jc21, 1));
        productList.add(new Product("HAMACA RACADOR", 16.61, R.drawable.jc22, 1));
        productList.add(new Product("JUGUETE CARUSEL", 44.48, R.drawable.jc23, 1));


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
                Intent intent = new Intent(ZonaJuegosActivity_cats.this, CarritoActivity.class);
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
                Intent intent = new Intent(ZonaJuegosActivity_cats.this, MainActivity.class);
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
