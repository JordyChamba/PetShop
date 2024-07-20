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

public class AlimentosActivity_dogs extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

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

        // Actualizar el contador del carrito
        updateCartItemCount();

        // Inicializar la lista de productos
        productList = new ArrayList<>();
        productList.add(new Product("DOG CHOW ADULTO", 42.10, R.drawable.cp1, 1));
        productList.add(new Product("(Libra)-DOG CHOW ADULTO", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("DOG CHOW ADULTO/MEDIANA", 41.07, R.drawable.cp2, 1));
        productList.add(new Product("(Libra)-DOG CHOW ADULTO/MEDIANA", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("DOG CHOW ADULTO RAZA PEQUEÑA", 42.10, R.drawable.cr3, 1));
        productList.add(new Product("(Libra)-DOG CHOW ADULTO RAZA PEQUEÑA", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("DOG CHOW CACHORRO MEDIANA", 43.13, R.drawable.cp5, 1));
        productList.add(new Product("(Libra)-DOG CHOW CACHORRO MEDIANA", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("DOG CHOW CACHORROS RAZA PEQUEÑA", 35.94, R.drawable.cp6, 1));
        productList.add(new Product("(Libra)-DOG CHOW CACHORROS RAZA PEQUEÑA", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("DOG CHOW PICNIC CORDERO TROZOS", 1.15, R.drawable.cp7, 1));
        productList.add(new Product("(Libra)-DOG CHOW PICNIC CORDERO TROZOS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("PRO-CAN ADULTO RAZAS MEDIANAS/GRANDES", 4.72, R.drawable.cp8, 1));
        productList.add(new Product("(Libra)-PRO-CAN ADULTO RAZAS MEDIANAS/GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("PRO-CAN ADULTO RAZAS MEDIANAS/GRANDES", 55.45, R.drawable.cp9, 1));
        productList.add(new Product("(Libra)-PRO-CAN ADULTO RAZAS MEDIANAS/GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("PRO PLAN ACTIVE RAZAS MEDIANAS/GRANDES", 142.13, R.drawable.cp10, 1));
        productList.add(new Product("(Libra)-PRO PLAN ACTIVE RAZAS MEDIANAS/GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("PRO PLAN ADULT DOG COMPLETE", 110.50, R.drawable.cp13, 1));
        productList.add(new Product("(Libra)-PRO PLAN ADULT DOG COMPLETE", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("PRO PLAN ACTIVE RAZAS MEDIANAS/GRANDES", 142.13, R.drawable.cp12, 1));
        productList.add(new Product("(Libra)-PRO PLAN ACTIVE RAZAS MEDIANAS/GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("CANI ADULTOS RAZAS MEDIANAS/GRANDES", 35.25, R.drawable.cp14, 1));
        productList.add(new Product("(Libra)-CANI ADULTOS RAZAS MEDIANAS/GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("CANI ADULTOS RAZAS PEQUEÑAS", 38.50, R.drawable.cp15, 1));
        productList.add(new Product("(Libra)-CANI ADULTOS RAZAS PEQUEÑAS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("CANI CACHORROS RAZAS MEDIANAS Y GRANDES", 36.96, R.drawable.cp16, 1));
        productList.add(new Product("(Libra)-CANI CACHORROS RAZAS MEDIANAS Y GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("CANI PRIME CLA ADULTO", 5.13, R.drawable.cp17, 1));
        productList.add(new Product("(Libra)-CANI PRIME CLA ADULTO", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("CANI SENIOR", 61.50, R.drawable.cp18, 1));
        productList.add(new Product("(Libra)-CANI SENIOR", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("BUEN CAN ADULTOS RAZAS MEDIANAS/GRANDES", 56.17, R.drawable.cp19, 1));
        productList.add(new Product("(Libra)-BUEN CAN ADULTOS RAZAS MEDIANAS/GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("NUTRA PRO ADULTO LIGHT RAZAS MEDIANAS", 110.50, R.drawable.cp20, 1));
        productList.add(new Product("(Libra)-NUTRA PRO ADULTO LIGHT RAZAS MEDIANAS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("NUTRA PRO ADULTO RAZAS MEDIANAS/GRANDES", 110.50, R.drawable.cp21, 1));
        productList.add(new Product("(Libra)-NUTRA PRO ADULTO RAZAS MEDIANAS/GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("NUTRA PRO ADULTO RAZAS PEQUEÑAS/MINIATURAS", 110.50, R.drawable.cp22, 1));
        productList.add(new Product("(Libra)-NUTRA PRO ADULTO RAZAS PEQUEÑAS/MINIATURAS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("NUTRA PRO CACHORROS RAZAS MEDIANAS/GRANDES", 110.50, R.drawable.cp23, 1));
        productList.add(new Product("(Libra)-NUTRA PRO CACHORROS RAZAS MEDIANAS/GRANDES", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("NUTRA PRO CACHORROS RAZAS PEQUEÑAS", 110.50, R.drawable.cp24, 1));
        productList.add(new Product("(Libra)-NUTRA PRO CACHORROS RAZAS PEQUEÑAS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("PRO PAC ULTIMATES LAMB AND BROWN RICE", 83.17, R.drawable.cp25, 1));
        productList.add(new Product("(Libra)-PRO PAC ULTIMATES LAMB AND BROWN RICE", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("PRO PAC ULTIMATES LARGE BREED ADULT ", 83.17, R.drawable.cp26, 1));
        productList.add(new Product("(Libra)-PRO PAC ULTIMATES LARGE BREED ADULT", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("PRO PAC ULTIMATES LARGE BREED PUPPY ", 83.17, R.drawable.cp27, 1));
        productList.add(new Product("(Libra)-PRO PAC ULTIMATES LARGE BREED PUPPY", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("AVANT ADULTO RAZAS MINIATURAS PEQUEÑOS ", 21.05, R.drawable.cp28, 1));
        productList.add(new Product("(Libra)-AVANT ADULTO RAZAS MINIATURAS PEQUEÑOS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("GUERPO PREMIUM ADULTO RAZAS MEDIANAS ", 21.05, R.drawable.cp29, 1));
        productList.add(new Product("(Libra)-GUERPO PREMIUM ADULTO RAZAS MEDIANAS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("GUERPO PREMIUM ADULTO RAZAS PEQUEÑAS ", 21.05, R.drawable.cp30, 1));
        productList.add(new Product("(Libra)-GUERPO PREMIUM ADULTO RAZAS PEQUEÑAS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("GUERPO PREMIUM CACHORRO RAZAS MEDIANAS ", 21.05, R.drawable.cp31, 1));
        productList.add(new Product("(Libra)-GUERPO PREMIUM CACHORRO RAZAS MEDIANAS", 0.55, R.drawable.doglibra, 1));
        productList.add(new Product("GUERPO PREMIUM EXPERIENCE TODAS LAS RAZAS", 21.05, R.drawable.cp32, 1));
        productList.add(new Product("(Libra)-GUERPO PREMIUM EXPERIENCE TODAS LAS RAZAS", 0.55, R.drawable.doglibra, 1));

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
                Intent intent = new Intent(AlimentosActivity_dogs.this, CarritoActivity.class);
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
                Intent intent = new Intent(AlimentosActivity_dogs.this, MainActivity.class);
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

    private void updateCartItemCount() {
        int itemCount = CartSingleton.getInstance().getCartItemCount();
        cartItemCountTextView.setText(String.valueOf(itemCount));
    }

    // Método para manejar el clic en el botón de agregar al carrito
    @Override
    public void onAddToCartClick(Product product) {
        CartSingleton.getInstance().addToCart(product);
    }
}
