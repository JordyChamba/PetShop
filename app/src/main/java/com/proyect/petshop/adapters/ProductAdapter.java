package com.proyect.petshop.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.petshop.R;
import com.proyect.petshop.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private OnProductClickListener onProductClickListener;
    private TextView cartItemCount;

    // Interfaz para manejar clics en el botón "Agregar al carrito"
    public interface OnProductClickListener {
        void onAddToCartClick(Product product);
    }

    // Constructor del adaptador
    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener onProductClickListener, TextView cartItemCount) {
        this.context = context;
        this.productList = productList;
        this.onProductClickListener = onProductClickListener;
        this.cartItemCount = cartItemCount;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout de cada item de producto
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Obtener el producto en la posición actual
        Product product = productList.get(position);

        // Configurar la vista con los datos del producto
        holder.imageViewProduct.setImageResource(product.getImageResourceId());
        holder.textViewProductName.setText(product.getNombre());
        holder.textViewProductPrice.setText(String.format("$%.2f", product.getPrecio()));

        // Mostrar el contador en el botón
        if (product.getCounter() > 0) {
            holder.buttonAddToCart.setBackgroundColor(Color.GREEN);
            holder.buttonAddToCart.setText(String.format("Agregado %d veces", product.getCounter()));
        } else {
            holder.buttonAddToCart.setBackgroundColor(Color.GRAY);
            holder.buttonAddToCart.setText("Pulsa para agregar");
        }

        // Manejar clics en el botón "Agregar al carrito"
        holder.buttonAddToCart.setOnClickListener(v -> {
            // Incrementar el contador del producto
            product.incrementCounter();

            // Actualizar el botón con el contador actualizado y mostrar mensaje
            holder.buttonAddToCart.setBackgroundColor(Color.GREEN);
            holder.buttonAddToCart.setText(String.format("Agregado %d veces", product.getCounter()));

            // Notificar al listener sobre el clic en "Agregar al carrito"
            if (onProductClickListener != null) {
                onProductClickListener.onAddToCartClick(product);
            }

            // Guardar el contador actualizado en SharedPreferences
            saveCounterToSharedPreferences(product.getId(), product.getCounter());

            // Actualizar el TextView del contador global del carrito
            updateCartItemCount();
        });
    }

    // Método para guardar el contador del producto en SharedPreferences
    private void saveCounterToSharedPreferences(long productId, int counter) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter_" + productId, counter);
        editor.apply();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Clase ViewHolder para mantener las vistas de cada item de producto
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;
        Button buttonAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
        }
    }

    // Método para actualizar el TextView del contador global del carrito
    private void updateCartItemCount() {
        int total = 0;
        for (Product product : productList) {
            total += product.getCounter();
        }
        // Actualizar el TextView
        cartItemCount.setText(String.valueOf(total));

        // Guardar el total en SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("cart_count", total);
        editor.apply();
    }
}
