package co.herovitamin.rokk3rtest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kerry on 28/10/16.
 */
public class Product implements Comparable<Product>, Parcelable{

    int id;
    private String name;
    private int price;
    private int stock;
    private int selected;

    public Product(int id, String name, int price, int stock, int selected) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void addToCart(){
        if(getStock() > 0){
            stock--;
            selected++;
        }
    }

    public void removeFromCart(){
        stock += stock + selected;
        selected = 0;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Override
    public int compareTo(Product product) {
        return Integer.compare(this.id, product.getId());
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        stock = in.readInt();
        selected = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(stock);
        dest.writeInt(selected);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
