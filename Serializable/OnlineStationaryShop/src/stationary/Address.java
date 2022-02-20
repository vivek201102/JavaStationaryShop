package stationary;

import java.io.Serializable;

class Address implements Serializable{
    protected String houseNo;
    protected String address1;
    protected String address2;
    protected String city;
    protected long pincode;

    //Constructor

    public Address(String houseNo, String address1, String address2, String city, long pincode) {
        this.houseNo = houseNo;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.pincode = pincode;
    }

    @Override
    public String toString()
    {
        return "Address: " + houseNo + ", " + address1 + "\n" + address2 + "\n" + city + " - " + pincode + "\n";
    }
}
