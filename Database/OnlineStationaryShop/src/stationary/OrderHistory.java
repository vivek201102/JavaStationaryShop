package stationary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

class OrderHistory implements Serializable {
    protected String dateTime;
    protected int total;
    protected ArrayList<Order> orders = new ArrayList<>();

    //Constructor
    OrderHistory(){}

    //Methods
    void addDateTime(String d){ dateTime = d; }

    void addOrder(Order o){ orders.add(o); }

    void addTotal(int t){ total = t; }

    String getDateTime(){ return dateTime; }

    int getTotal(){ return total; }
}
