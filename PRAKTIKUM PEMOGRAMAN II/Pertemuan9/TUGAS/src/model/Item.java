package model;

public class Item {
    private int id;
    private String name;
    private int weight;
    private int qty;
    private int price;

    // GETTER AND SETTER
    public int getId(){ return id; };
    public void setId(int id){ this.id = id; };
    
    public String getName(){ return name; };
    public void setName(String name){ this.name = name; };  

    public int getWeight(){ return weight; };
    public void setWeight(int weight){ this.weight = weight; };
    
    public int getQty(){ return qty; };
    public void setQty(int qty){ this.qty = qty; };
    
    public int getPrice(){ return price; };
    public void setPrice(int price){ this.price = price; };

    

}
