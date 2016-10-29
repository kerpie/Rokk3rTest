package co.herovitamin.rokk3rtest.viewholder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.herovitamin.rokk3rtest.MainActivity;
import co.herovitamin.rokk3rtest.R;
import co.herovitamin.rokk3rtest.model.Product;

/**
 * Created by kerry on 28/10/16.
 */
public class CartItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.product_name_in_cart)
    TextView mProductNameInCart;

    @BindView(R.id.product_delete_from_cart)
    Button mProductDelete;

    Activity mActivity;
    Product mProduct;

    public CartItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mProductDelete.setOnClickListener(this);
    }

    public void bind(Product product, Activity activity){
        mProduct = product;
        mActivity = activity;
        mProductNameInCart.setText(product.getName() + " (Total: " + product.getSelected() + ")");
    }

    @Override
    public void onClick(View view) {
        ((MainActivity)mActivity).removeFromCart(mProduct);
        ((MainActivity)mActivity).refreshDialog();
    }
}
