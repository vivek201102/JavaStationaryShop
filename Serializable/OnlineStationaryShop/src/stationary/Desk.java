package stationary;

import java.io.Serializable;

class Desk extends Product implements Serializable {
    protected String material;
    protected String compName;

    //Constructor


    public Desk(String pId, String pName, String des, int price, int stock, String material, String compName) {
        super(pId, pName, des, price, stock);
        this.material = material;
        this.compName = compName;
    }

    //Getter and Setter

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    //toString
    public String toString()
    {
        return "Product ID: " + pId + "\tProduct name: " + pName + "\tProduct Description: " + des + "\tMaterial: " + material + "\tCompany Name: " + compName + "\tPrice: " + price + "/-\tStock: " + stock + "\n";
    }
}
