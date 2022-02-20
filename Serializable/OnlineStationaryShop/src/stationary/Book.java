package stationary;

import java.io.Serializable;

class Book extends Product implements Serializable{
    protected String author;
    protected int noOfpages;

    //constructor

    public Book(String pId, String pName, String des, int price, int stock, String author, int noOfpages) {
        super(pId, pName, des, price, stock);
        this.author = author;
        this.noOfpages = noOfpages;
    }

    //getter and setter


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNoOfpages() {
        return noOfpages;
    }

    public void setNoOfpages(int noOfpages) {
        this.noOfpages = noOfpages;
    }



    //toString method
    @Override
    public String toString()
    {
        return "Product ID: " + pId + "\tProduct name: " + pName + "\tProduct Description: " + des + "\tAuthor Name: " + author + "\tNo of pages: " + noOfpages + "\tPrice: " + price + "/-\tStock: " + stock + "\n";
    }

}
