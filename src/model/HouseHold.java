package model;

public class HouseHold extends Product{

    public HouseHold (String name, double price, int stock){
        super(name, price, stock);
    }

    @Override
    public String getDescription() {
        return "This is a houseHold";
    }
}
