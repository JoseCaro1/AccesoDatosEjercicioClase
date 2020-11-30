package ejercicio1;

public class Articulo {

    private String name;
    private double price;

    Articulo(String name,int price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
