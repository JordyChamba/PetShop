package com.proyect.petshop.ActivityCats;

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

import com.proyect.petshop.R;
import com.proyect.petshop.activityDogs.AlimentosActivity_dogs;
import com.proyect.petshop.adapters.CarritoActivity;
import com.proyect.petshop.adapters.ProductAdapter;
import com.proyect.petshop.models.Product;
import com.proyect.petshop.adapters.CartSingleton;

import java.util.ArrayList;
import java.util.List;

public class AlimentosActivity_cats extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

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
        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartItemCountTextView = findViewById(R.id.cartItemCount); // Ajusta el ID según tu layout

        // Inicializar la lista de productos
        productList = new ArrayList<>();
        productList.add(new Product("CAT CHOW ADULTO CARNE", 12.32, R.drawable.cg1,1));
        productList.add(new Product("(Libra)-CAT CHOW ADULTO CARNE", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("CAT CHOW ADULTO PESCADO", 12.32, R.drawable.cg2,1));
        productList.add(new Product("(Libra)-CAT CHOW ADULTO PESCADO", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("CAT CHOW GATITOS", 12.35, R.drawable.cg3,1));
        productList.add(new Product("(Libra)-CAT CHOW GATITOS", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("CAT CHOW GATITOS VIDA SANA", 12.32, R.drawable.cg4,1));
        productList.add(new Product("(Libra)-CAT CHOW GATITOS VIDA SANA", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN FELINE ADULT STERILISED", 4.56, R.drawable.cg5,1));
        productList.add(new Product("(Libra)-ROYAL CANIN FELINE ADULT STERILISED", 0.70, R.drawable.catlibra,1));;
        productList.add(new Product("ROYAL CANIN NUTRITION FIT", 35.50, R.drawable.cg6,1));
        productList.add(new Product("(Libra)-ROYAL CANIN NUTRITION FIT", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN NUTRITION KITTEN", 45.69, R.drawable.cg7,1));
        productList.add(new Product("(Libra)-ROYAL CANIN NUTRITION KITTEN", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN NUTRITION", 68.07, R.drawable.cg8,1));
        productList.add(new Product("(Libra)-ROYAL CANIN NUTRITION", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN NUTRITION MOTHER", 48.77, R.drawable.cg9,1));
        productList.add(new Product("(Libra)-ROYAL CANIN NUTRITION MOTHER", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN NUTRITION PERSIAN", 48.26, R.drawable.cg10,1));
        productList.add(new Product("(Libra)-ROYAL CANIN NUTRITION PERSIAN", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN NUTRITION SENSIBLE", 45.69, R.drawable.cg11,1));
        productList.add(new Product("(Libra)-ROYAL CANIN NUTRITION SENSIBLE", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN GASTROINTESTINAL FELINE", 60.07, R.drawable.cg12,1));
        productList.add(new Product("(Libra)-ROYAL CANIN GASTROINTESTINAL FELINE", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN LIGHT WEIGHT CARE FELINE", 35.42, R.drawable.cg13,1));
        productList.add(new Product("(Libra)-ROYAL CANIN LIGHT WEIGHT CARE FELINE", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("ROYAL CANIN RECOVERY CANINE FELINE", 7.71, R.drawable.cg14,1));
        productList.add(new Product("ROYAL CANIN SAVOUR EXIGENT", 46.10, R.drawable.cg15,1));
        productList.add(new Product("(Libra)-ROYAL CANIN SAVOUR EXIGENT", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("PRO PAC ULTIMATES SAVANNA PRIDE", 28.74, R.drawable.cg16,1));
        productList.add(new Product("(Libra)-PRO PAC ULTIMATES SAVANNA PRIDE", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("NUTRA PRO GATOS ADULTOS CONTROL PESO", 10.27, R.drawable.cg17,1));
        productList.add(new Product("(Libra)-NUTRA PRO GATOS ADULTOS CONTROL PESO", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("NUTRA PRO GATOS ADULTOS VITALITY", 10.27, R.drawable.cg18,1));
        productList.add(new Product("(Libra)-NUTRA PRO GATOS ADULTOS VITALITY", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("NUTRA PRO GATOS ADULTOS NUGGETS", 10.78, R.drawable.cg19,1));
        productList.add(new Product("(Libra)-NUTRA PRO GATOS ADULTOS NUGGETS", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("NUTRA PRO GATOS CACHORROS", 10.27, R.drawable.cg20,1));
        productList.add(new Product("(Libra)-NUTRA PRO GATOS CACHORROS", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("NUTRA PRO SNACKS NUGGETS CARNE-HIGADO", 2.57, R.drawable.cg21,1));
        productList.add(new Product("(Libra)-NUTRA PRO SNACKS NUGGETS CARNE-HIGADO", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("NUTRA PRO SNACKS NUGGETS SABORES MAR", 2.57, R.drawable.cg22,1));
        productList.add(new Product("(Libra)-NUTRA PRO SNACKS NUGGETS SABORES MAR", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("WELLNESS ADULT CAT GRAIN FREE", 27.01, R.drawable.cg23,1));
        productList.add(new Product("(Libra)-WELLNESS ADULT CAT GRAIN FREE", 0.70, R.drawable.catlibra,1));
        productList.add(new Product("WELLNESS KITTEN GRAIN FREE", 30.29, R.drawable.cg24,1));
        productList.add(new Product("(Libra)-WELLNESS KITTEN GRAIN FREE", 0.70, R.drawable.catlibra,1));

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
            Intent intent = new Intent(AlimentosActivity_cats.this, CarritoActivity.class);
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
