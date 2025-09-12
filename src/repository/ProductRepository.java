package repository;


import java.util.ArrayList;
import java.util.HashMap;

/*
*   repository simulation with fake database ↓
*   the database are three collection.
* */
public class ProductRepository {
    private FakeDataBase db;

    public ProductRepository(){
        db = new FakeDataBase();
    }

    public void save(String name, double price, int stock){
            db.setPrices(price);
            db.setProductsName(name);
            db.setProducts(name,stock);
    }

    public HashMap<String,Integer> getProducts(){
        return db.getProducts();
    }

    public ArrayList<String> getProductNames(){
        return db.getProductsName();
    }
    public double[] getPrices(){
        return db.getPrices();
    }
}
