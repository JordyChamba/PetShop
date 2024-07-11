package com.proyect.petshop.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.petshop.R;
import com.proyect.petshop.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private OnProductClickListener onProductClickListener;

    public interface OnProductClickListener {
        void onAddToCartClick(Product product);
    }

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener onProductClickListener) {
        this.context = context;
        this.productList = productList;
        this.onProductClickListener = onProductClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
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

        // Manejar clics en el botón
        holder.buttonAddToCart.setOnClickListener(v -> {
            // Incrementar el contador
            product.incrementCounter();

            // Actualizar el botón con el contador y mostrar mensaje
            holder.buttonAddToCart.setBackgroundColor(Color.GREEN);
            holder.buttonAddToCart.setText(String.format("Agregado %d veces", product.getCounter()));

            // Notificar al listener
            if (onProductClickListener != null) {
                onProductClickListener.onAddToCartClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

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
}
