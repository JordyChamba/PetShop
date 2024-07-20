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

public class VestimentaActivity_cats extends AppCompatActivity implements ProductAdapter.OnProductClickListener{

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
                Intent intent = new Intent(VestimentaActivity_cats.this, CarritoActivity.class);
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
                Intent intent = new Intent(VestimentaActivity_cats.this, MainActivity.class);
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
