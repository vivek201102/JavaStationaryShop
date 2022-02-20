package stationary;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Customer implements Serializable{
    protected String cId;
    protected String cName;
    protected String email;
    protected String dob;
    protected long mobile;
    protected ArrayList<OrderHistory> history = new ArrayList<>();
    Address address;
    protected Date lastLogin = null;

    static int noOfCustomer;

    static {
        noOfCustomer=0;
    }
    {
        noOfCustomer++;
    }

    //Constructor


    Customer(){}
    public Customer(String cName, String email, long mobile, String dob, String houseNo, String address1, String address2, String city, long pincode) {
        this.cId = "CUS00" + (int)(Math.random()* (9001));
        this.cName = cName;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        address = new Address(houseNo, address1, address2, city, pincode);
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getLastLogin() {
        if(lastLogin != null)
            return lastLogin;
        else
            return null;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    //Past order details printing
    void display(ArrayList<Order> order, OrderHistory history)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        System.out.println("Name: "+ cName + "\nEmail: " + email + "\nOrdering Time: "+ dateFormat.format(history.getDateTime()) + "\nMobile No: " + mobile + "\n");

        System.out.println("----------------------------------Your items----------------------------------\n");

        for(Order r: order) System.out.println(r);

        System.out.println("------------------------------------------------------------------------------\n");

        System.out.println("\nTotal Amount To Be Paid: "+ history.getTotal()+ " Rs/- Only\n\n");

    }


    //Methods

    void addOrder(OrderHistory o)
    {
        history.add(o);
    }

    void displayAll()
    {
        for(OrderHistory h: history)
            display(h.orders, h);
    }

    //Billing
    void currentOrder(ArrayList<Order> order, OrderHistory history)
    {
        System.out.println("\n|Billing of your Order|\n");

        SimpleDateFormat dateFormat = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        System.out.println("Name: "+ cName + "\nEmail: " + email + "\nOrdering Time: "+ dateFormat.format(history.getDateTime()) + "\nMobile No: " + mobile + "\n");

        System.out.println("-----------------------------------Your items-----------------------------------\n");

        for(Order r: order) System.out.println(r);

        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("\nTotal Amount To Be Paid: "+ history.getTotal()+ " Rs/- Only\n\n");
    }

    //toString
    @Override
    public String toString()
    {
        return "Your ID: " + cId + "\nName: " + cName + "\nDOB: " + dob + "\nEmail: " + email + "\nMobile No:" + mobile + address.toString() + "\n";
    }
}
