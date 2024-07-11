package com.proyect.petshop.adapters;

import android.content.Context;
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

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Product> cartItems;

    private static final int TYPE_ACTIVE = 1;
    private static final int TYPE_DELETED = 2;

    public CartAdapter(Context context, List<Product> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_ACTIVE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_cart_product_active, parent, false);
            return new ActiveViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_cart_product_deleted, parent, false);
            return new DeletedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = cartItems.get(position);

        if (holder.getItemViewType() == TYPE_ACTIVE) {
            ActiveViewHolder activeViewHolder = (ActiveViewHolder) holder;
            activeViewHolder.bind(product);
        } else {
            DeletedViewHolder deletedViewHolder = (DeletedViewHolder) holder;
            deletedViewHolder.bind(product);
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Determinar el tipo de vista según si el producto está activo o eliminado
        Product product = cartItems.get(position);
        if (product.isActive()) {
            return TYPE_ACTIVE;
        } else {
            return TYPE_DELETED;
        }
    }

    // ViewHolder para productos activos
    private class ActiveViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;
        Button buttonDelete;

        ActiveViewHolder(View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        void bind(final Product product) {
            textViewProductName.setText(product.getNombre());
            textViewProductPrice.setText(String.valueOf(product.getPrecio()));
            imageViewProduct.setImageResource(product.getImageResourceId());

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteButtonClick(product);
                }
            });
        }
    }
    // ViewHolder para productos eliminados
    private class DeletedViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        Button buttonDelete; // Cambiado de buttonRestore a buttonDelete

        DeletedViewHolder(View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.textViewProductNameDeleted);
            buttonDelete = itemView.findViewById(R.id.buttonDelete); // Cambiado de buttonRestore a buttonDelete
        }

        void bind(final Product product) {
            textViewProductName.setText(product.getNombre());

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteButtonClick(product); // Llamada al método onDeleteButtonClick para eliminar completamente el producto
                }
            });
        }
    }

    private void onDeleteButtonClick(Product product) {
        // Eliminar el producto del carrito
        cartItems.remove(product);
        notifyDataSetChanged();
        ((CarritoActivity) context).onDeleteButtonClick(product);
    }
}
