package model;

public class FoodStuff extends Product {

    public FoodStuff(String name, double price, int stock){
        super(name, price, stock);
    }

    @Override
    public String getDescription() {
        return "This is a fruit";
    }
}
