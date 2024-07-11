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

public class DeletedCartAdapter extends RecyclerView.Adapter<DeletedCartAdapter.DeletedViewHolder> {

    private Context context;
    private List<Product> deletedItems;

    public DeletedCartAdapter(Context context, List<Product> deletedItems) {
        this.context = context;
        this.deletedItems = deletedItems;
    }

    @NonNull
    @Override
    public DeletedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_product_deleted, parent, false);
        return new DeletedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeletedViewHolder holder, int position) {
        Product product = deletedItems.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return deletedItems.size();
    }

    public class DeletedViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        Button buttonDelete;

        public DeletedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductNameDeleted);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        public void bind(final Product product) {
            textViewProductName.setText(product.getNombre());
            imageViewProduct.setImageResource(product.getImageResourceId());

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteButtonClick(product);
                }
            });
        }

        private void onDeleteButtonClick(Product product) {
            ((CarritoActivity) context).onDeleteButtonClick(product);
        }
    }
}
