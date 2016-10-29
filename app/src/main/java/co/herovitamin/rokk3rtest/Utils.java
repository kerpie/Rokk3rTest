package co.herovitamin.rokk3rtest;

import java.util.ArrayList;

import co.herovitamin.rokk3rtest.model.Product;

/**
 * Created by kerry on 28/10/16.
 */
public class Utils {

    public static ArrayList<Product> createDummyProducts(int quantity, String names[], int prices[], int stocks[]){
        ArrayList<Product> products = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Product product = new Product(
                    i,
                    names[i],
                    prices[i],
                    stocks[i],
                    0);
            products.add(product);
        }

        return products;
    }

}
