package co.herovitamin.rokk3rtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.herovitamin.rokk3rtest.adapter.CartAdapter;
import co.herovitamin.rokk3rtest.model.Product;

/**
 * Created by kerry on 28/10/16.
 */
public class CartFragment extends DialogFragment {

    private static final String KEY = "PRODUCTS";
    private static final String KEY_REMOVED = "REMOVED_PRODUCTS";

    @BindView(R.id.cart)
    RecyclerView mCart;

    ArrayList<Product> items, removedItems;

    public static CartFragment newInstance(ArrayList<Product> items) {
        Bundle args = new Bundle();

        args.putParcelableArrayList(KEY, items);

        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            items = getArguments().getParcelableArrayList(KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_cart, container, false);
        ButterKnife.bind(this, view);

        getDialog().setTitle(R.string.dialog_title);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        CartAdapter adapter = new CartAdapter(items, (MainActivity) getActivity());

        mCart.setLayoutManager(new LinearLayoutManager(getContext()));
        mCart.setAdapter(adapter);
    }
}
