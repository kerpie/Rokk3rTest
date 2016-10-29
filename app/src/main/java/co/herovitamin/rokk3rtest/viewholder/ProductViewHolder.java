package co.herovitamin.rokk3rtest.viewholder;

import android.app.Activity;
import android.content.ContextWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.herovitamin.rokk3rtest.MainActivity;
import co.herovitamin.rokk3rtest.R;
import co.herovitamin.rokk3rtest.model.Product;

/**
 * Created by kerry on 28/10/16.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.product_name)
    TextView mProductName;

    @BindView(R.id.product_price)
    TextView mProductPrice;

    @BindView(R.id.product_stock)
    TextView mProductStock;

    @BindView(R.id.product_buy)
    Button mProductBuy;

    @BindView(R.id.item_product_container)
    RelativeLayout container;

    Product mProduct;

    Activity mActivity;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mProductBuy.setOnClickListener(this);
    }

    public void bind(Product product, Activity activity){
        mProduct = product;
        mActivity = activity;
        mProductName.setText(product.getName());
        mProductPrice.setText("Price: $" + product.getPrice());
        mProductStock.setText("Stock: " + product.getStock());
    }


    @Override
    public void onClick(View v) {
        mProduct.addToCart();
        mProductStock.setText("Stock: " + mProduct.getStock());
        ((MainActivity)mActivity).addItemToCart(mProduct);
    }
}
