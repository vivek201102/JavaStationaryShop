package stationary;

import java.io.Serializable;

public class Product implements Serializable {
    protected String pId;
    protected String pName;
    protected String des;
    protected int price;
    protected int stock;

    //Static member

    static int noOfProduct;

    static{
        noOfProduct=0;
    }

    {
        noOfProduct++;
    }

    //Constructor

    public Product(String pId, String pName, String des, int price, int stock) {
        this.pId = pId;
        this.pName = pName;
        this.des = des;
        this.price = price;
        this.stock = stock;
    }

    //Getter and Setter method


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    void maipulateStock(int n)
    {
        stock -= n;
    }

    //toString method
    @Override
    public String toString()
    {
        return "none";
    }
}
