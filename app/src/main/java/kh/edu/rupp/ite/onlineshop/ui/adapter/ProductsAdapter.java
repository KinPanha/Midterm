package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import kh.edu.rupp.ite.onlineshop.api.model.Products;
import kh.edu.rupp.ite.onlineshop.databinding.ViewHolderHomeBinding;
import kh.edu.rupp.ite.onlineshop.databinding.ViewHolderProductBinding;

public class ProductsAdapter extends ListAdapter<Products, ProductsAdapter.ProductViewHolder> {
    public ProductsAdapter() {
        super(new DiffUtil.ItemCallback<Products>() {
            @Override
            public boolean areItemsTheSame(@NonNull Products oldItem, @NonNull Products newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Products oldItem, @NonNull Products newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderProductBinding binding = ViewHolderProductBinding.inflate(layoutInflater, parent,false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
      Products item = getItem(position);
        try {
            holder.bind(item);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ViewHolderProductBinding itembinding;
        public ProductViewHolder(ViewHolderProductBinding itembinding) {
            super(itembinding.getRoot());
            this.itembinding = itembinding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Products products) throws IOException {
              Picasso.get().load(products.getImage_url()).into(itembinding.image);
              itembinding.nameproduct.setText(products.getName());
              itembinding.price.setText("$" + products.getPrice());
        }
    }
}
