package co.herovitamin.rokk3rtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.herovitamin.rokk3rtest.MainActivity;
import co.herovitamin.rokk3rtest.R;
import co.herovitamin.rokk3rtest.model.Product;
import co.herovitamin.rokk3rtest.viewholder.CartItemViewHolder;

/**
 * Created by kerry on 28/10/16.
 */
public class CartAdapter extends RecyclerView.Adapter<CartItemViewHolder> {

    ArrayList<Product> items, removedItems;
    MainActivity mActivity;

    public CartAdapter(ArrayList<Product> items, MainActivity activity) {
        this.items = items;
        mActivity = activity;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CartItemViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(
                                R.layout.item_cart_item,
                                parent,
                                false)
        );
    }

    @Override
    public void onBindViewHolder(CartItemViewHolder holder, int position) {
        holder.bind(items.get(position), mActivity);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
