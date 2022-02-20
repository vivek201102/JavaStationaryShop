package stationary;

import java.io.Serializable;

class Pen extends Product implements Serializable {
    protected String color;
    protected String compName;

    //Constructor

    public Pen(String pId, String pName, String des, int price, int stock, String color, String compName) {
        super(pId, pName, des, price, stock);
        this.color = color;
        this.compName = compName;
    }

    //Getter and Setter

    public String getType() {
        return color;
    }

    public void setType(String color) {
        this.color = color;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    //toString method
    @Override
    public String toString()
    {
        return "Product ID: " + pId + "\tProduct name: " + pName + "\tProduct Description: " + des + "\tColor: " + color + "\tCompany Name: " + compName + "\tPrice: " + price + "/-\tStock: " + stock + "\n";
    }
}
