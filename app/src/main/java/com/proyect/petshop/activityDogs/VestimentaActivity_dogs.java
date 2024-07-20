package com.proyect.petshop.activityDogs;

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
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.adapters.ProductAdapter;
import com.proyect.petshop.models.Product;
import com.proyect.petshop.adapters.CartSingleton;
import com.proyect.petshop.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class VestimentaActivity_dogs extends AppCompatActivity implements ProductAdapter.OnProductClickListener{

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> clothingList;
    private List<Product> filteredProductList;
    private SearchView searchView;
    private TextView cartItemCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        cartItemCountTextView = findViewById(R.id.cartItemCount); // Ajusta el ID según tu layout

        clothingList = new ArrayList<>();
        // Camiseta para perro en diferentes tamaños
        clothingList.add(new Product("CAMISETA PERRO GRANDE", 12.00, R.drawable.camiseta_perro_grande, 1));
        clothingList.add(new Product("CAMISETA PERRO MEDIANO", 10.00, R.drawable.camiseta_perro_mediano, 1));
        clothingList.add(new Product("CAMISETA PERRO PEQUEÑO", 8.00, R.drawable.camiseta_perro_pequeno, 1));

        // Chaqueta impermeable en diferentes tamaños
        clothingList.add(new Product("CHAQUETA IMPERMEABLE GRANDE", 28.00, R.drawable.chaqueta_impermeable_grande, 1));
        clothingList.add(new Product("CHAQUETA IMPERMEABLE MEDIANO", 25.00, R.drawable.chaqueta_impermeable_mediana, 1));
        clothingList.add(new Product("CHAQUETA IMPERMEABLE PEQUEÑO", 22.00, R.drawable.chaqueta_impermeable_pequena, 1));

        // Jersey de lana en diferentes tamaños
        clothingList.add(new Product("JERSEY DE LANA GRANDE", 22.00, R.drawable.jersey_lana_grande, 1));
        clothingList.add(new Product("JERSEY DE LANA MEDIANO", 20.00, R.drawable.jersey_lana_mediano, 1));
        clothingList.add(new Product("JERSEY DE LANA PEQUEÑO", 18.00, R.drawable.jersey_lana_pequeno, 1));

        // Sombrero divertido en diferentes tamaños
        clothingList.add(new Product("SOMBRERO DIVERTIDO GRANDE", 17.00, R.drawable.sombrero_divertido_grande, 1));
        clothingList.add(new Product("SOMBRERO DIVERTIDO MEDIANO", 15.00, R.drawable.sombrero_divertido_mediano, 1));
        clothingList.add(new Product("SOMBRERO DIVERTIDO PEQUEÑO", 13.00, R.drawable.sombrero_divertido_pequeno, 1));

        clothingList.add(new Product("IMPERMEABLE NARANJA", 26.43, R.drawable.rp1, 1));
        clothingList.add(new Product("CHUBASCA CON CAPUCHA ROJA", 19.78, R.drawable.rp2, 1));
        clothingList.add(new Product("JERSEY FRAPP ROJO", 8.91, R.drawable.rp3, 1));
        clothingList.add(new Product("CHAQUETA NARANJA", 19.61, R.drawable.rp4, 1));
        clothingList.add(new Product("JERSEY AZUL", 9.91, R.drawable.rp5, 1));
        clothingList.add(new Product("JERSEY WINTER", 12.94, R.drawable.rp6, 1));
        clothingList.add(new Product("SUDADERA CON CAPUCHA", 10.79, R.drawable.rp7, 1));
        clothingList.add(new Product("SUDADERA ROSADA", 15.95, R.drawable.rp8, 1));
        clothingList.add(new Product("ABRIGO STAR WAR", 39.12, R.drawable.rp9, 1));
        clothingList.add(new Product("SUDADERA SUPER GIRL", 23.92, R.drawable.rp10, 1));
        clothingList.add(new Product("SUDADERA MARVEL", 25.41, R.drawable.rp11, 1));
        clothingList.add(new Product("SUDADERA BATMAN", 27.52, R.drawable.rp12, 1));
        clothingList.add(new Product("CHALECO REFRESCANTE", 16.85, R.drawable.rp13, 1));
        clothingList.add(new Product("CALCETINES DESLISZANTES", 9.78, R.drawable.rp14, 1));
        clothingList.add(new Product("CHAQUETA AZUL", 16.61, R.drawable.rp15, 1));
        clothingList.add(new Product("CHAQEUTA ALASKA NARANJA", 19.63, R.drawable.rp16, 1));
        clothingList.add(new Product("SUDADERA MOSTAZA", 36.92, R.drawable.rp17, 1));
        clothingList.add(new Product("CAMISETA BASKET", 16.61, R.drawable.rp18, 1));
        clothingList.add(new Product("BOTAS PROTECTORAS", 19.39, R.drawable.rp19, 1));
        clothingList.add(new Product("CALCETINES DESLIZANTES", 2.96, R.drawable.rp20, 1));
        clothingList.add(new Product("BOTAS ", 13.35, R.drawable.rp21, 1));
        clothingList.add(new Product("BUFANDA", 5.35, R.drawable.rp22, 1));
        clothingList.add(new Product("GORRO MOSTERS", 19.98, R.drawable.rp23, 1));
        clothingList.add(new Product("CAMISETA BATMAN", 16.05, R.drawable.rp24, 1));
        clothingList.add(new Product("CAMISETA SPIDERMAN", 11.74, R.drawable.rp25, 1));
        clothingList.add(new Product("CAMISETA HULK", 16.68, R.drawable.rp26, 1));
        clothingList.add(new Product("PAÑUELO OSCURO", 7.12, R.drawable.rp27, 1));




        // Inicializar la lista filtrada
        filteredProductList = new ArrayList<>(clothingList);

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
                Intent intent = new Intent(VestimentaActivity_dogs.this, CarritoActivity.class);
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
                Intent intent = new Intent(VestimentaActivity_dogs.this, MainActivity.class);
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
            filteredProductList.addAll(clothingList);
        } else {
            for (Product product : clothingList) {
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
