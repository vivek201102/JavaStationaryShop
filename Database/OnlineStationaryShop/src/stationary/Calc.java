package stationary;

import java.io.Serializable;

class Calc extends Product implements Serializable {
    protected String compName;
    protected String type;

    //Constructor

    public Calc(String pId, String pName, String des, int price, int stock, String compName, String type) {
        super(pId, pName, des, price, stock);
        this.compName = compName;
        this.type = type;
    }

    //Getter and Setter

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //toString
    @Override
    public String toString()
    {
        return "Product ID: " + pId + "\tProduct name: " + pName + "\tProduct Description: " + des + "\tType: " + type + "\tCompany Name: " + compName + "\tPrice: " + price + "/-\tStock: " + stock + "\n";
    }
}
