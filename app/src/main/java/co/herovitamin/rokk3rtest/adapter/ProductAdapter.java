package co.herovitamin.rokk3rtest.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.herovitamin.rokk3rtest.MainActivity;
import co.herovitamin.rokk3rtest.R;
import co.herovitamin.rokk3rtest.model.Product;
import co.herovitamin.rokk3rtest.viewholder.ProductViewHolder;

/**
 * Created by kerry on 28/10/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    ArrayList<Product> mProducts;
    Activity mActivity;

    public ProductAdapter(ArrayList<Product> products, MainActivity mainActivity) {
        mProducts = products;
        mActivity = mainActivity;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(
                        parent.getContext())
                        .inflate(
                                R.layout.item_product_in_list,
                                parent,
                                false)
                );
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(mProducts.get(position), mActivity);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
