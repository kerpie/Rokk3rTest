package co.herovitamin.rokk3rtest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.herovitamin.rokk3rtest.adapter.ProductAdapter;
import co.herovitamin.rokk3rtest.model.Product;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.products_list)
    RecyclerView mProductsList;

    CartFragment mCartFragment;

    String[] mNames;
    int[] mPrices, mStocks;
    int mTotal;

    ArrayList<Product> mProducts, mRemovedProducts;


    ProductAdapter mProductAdapter;

    int mTotalProductsInCart;
    long mTotalPrice;
    CartFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initiateValues();
    }

    private void initiateValues() {
        mTotalProductsInCart = 0;
        mTotalPrice = 0;

        mTotal = getResources().getInteger(R.integer.total_of_products);
        mNames = getResources().getStringArray(R.array.names);
        mPrices = getResources().getIntArray(R.array.prices);
        mStocks = getResources().getIntArray(R.array.stock);

        mRemovedProducts = new ArrayList<>();

        mTotal = Math.min(mNames.length, Math.min(mPrices.length, mStocks.length));
        mProducts = Utils.createDummyProducts(mTotal, mNames, mPrices, mStocks);

        mProductsList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        mProductAdapter = new ProductAdapter(mProducts, this);
        mProductsList.setAdapter(mProductAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        updateMenuValue(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        updateMenuValue(menu);
        return true;
    }

    ArrayList<Product> inCart = new ArrayList<>();
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart:
            default:
                inCart.clear();
                inCart.addAll(mRemovedProducts);
                inCart.addAll(chooseSelectedProducts());
                mFragment = CartFragment.newInstance(inCart);
                FragmentManager fm = getSupportFragmentManager();
                mFragment.show(fm, "");
                break;
        }
        return true;
    }

    private ArrayList<Product> chooseSelectedProducts() {
        ArrayList<Product> tmp = new ArrayList<>();
        for (int i = 0; i < mProducts.size(); i++) {
            if(mProducts.get(i).getSelected() > 0){
                tmp.add(mProducts.get(i));
            }
        }
        return tmp;
    }

    private void updateMenuValue(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.menu_cart);
        menuItem.setTitle(getString(R.string.total_products_in_cart) + " " + mTotalProductsInCart + "($" + mTotalPrice + ")");
    }

    public void addItemToCart(Product product){
        for (int i = 0; i < mProducts.size(); i++) {
            if(product.getId() == mProducts.get(i).getId()){
                mProducts.set(i, product);
                break;
            }
        }
        removeProductsOutOfStock();
        updateValuesInCart();
        invalidateOptionsMenu();
    }

    private void removeProductsOutOfStock() {
        for (int i = 0; i < mProducts.size(); i++) {
            if(mProducts.get(i).getStock() == 0){
                mRemovedProducts.add(mProducts.get(i));
                mProducts.remove(i);
                mProductAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateValuesInCart() {
        mTotalProductsInCart = 0;
        mTotalPrice = 0;
        for (int i = 0; i < mProducts.size(); i++) {
            mTotalProductsInCart += mProducts.get(i).getSelected();
            mTotalPrice += mProducts.get(i).getPrice() * mProducts.get(i).getSelected();
        }

        for (int i = 0; i < mRemovedProducts.size(); i++) {
            mTotalProductsInCart += mRemovedProducts.get(i).getSelected();
            mTotalPrice += mRemovedProducts.get(i).getPrice() * mRemovedProducts.get(i).getSelected();
        }

        mProductAdapter.notifyDataSetChanged();
    }

    public void removeFromCart(Product product){
        for (int i = 0; i < mProducts.size(); i++) {
            if(mProducts.get(i).getId() == product.getId()){
                mProducts.get(i).removeFromCart();
            }
        }
        for (int i = 0; i < mRemovedProducts.size(); i++) {
            if(mRemovedProducts.get(i).getId() == product.getId()){
                mRemovedProducts.get(i).removeFromCart();
                mProducts.add(mRemovedProducts.get(i));
                mRemovedProducts.remove(i);
            }
        }

        Collections.sort(mProducts);
        mProductAdapter.notifyDataSetChanged();
        updateValuesInCart();
        invalidateOptionsMenu();
    }

    public void refreshDialog() {
        mFragment.dismiss();
        inCart.clear();
        inCart.addAll(mRemovedProducts);
        inCart.addAll(chooseSelectedProducts());
        if(inCart.size() != 0){
            mFragment = CartFragment.newInstance(inCart);
            FragmentManager fm = getSupportFragmentManager();
            mFragment.show(fm, "");
        }
    }
}
