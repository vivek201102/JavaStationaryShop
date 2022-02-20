package stationary;

import java.io.Serializable;

class Order implements Serializable {
    protected String itemName;
    protected String pId;
    protected int totalItem;
    protected int itemPrice;

    //Constructor

    public Order(String pId, String itemName, int totalItem, int itemPrice) {
        this.itemName = itemName;
        this.pId = pId;
        this.totalItem = totalItem;
        this.itemPrice = itemPrice;
    }

    //Getter and Setter

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    void changeTotalItem(int n)
    {
        totalItem-=n;
    }

    public  int countTotal() {
        return itemPrice * totalItem; }

    @Override
    public String toString() {
                return "Product Id: " + pId +
                 "\t\tName: " + itemName +
                "\t\tQuantity: " + totalItem +
                "\t\tPrice: " + itemPrice +
                "\n";
    }
}
