package stationary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


@SuppressWarnings("Unchecked")
public class Main {

    //Find Customer
    static int findCustomer(long mobile, ArrayList<Customer>customer)
    {
        int n = -1, t=0;
        long cId = 0;
        for(Customer c: customer)
        {
            n++;
            cId = c.getMobile();
            if(mobile == cId)
            {
                t++;
                break;
            }
        }

        if(t==0) n = -1;
        return n;
    }

    //Find Book
    static int findBook(String id, ArrayList<Book> book)
    {
        int n = -1;
        int t=0;
        String bookId = "null";
        for(Book b:book)
        {
            n++;
            bookId = b.getpId();
            if(id.equals(bookId))
            {
                t++;
                break;
            }
        }

        if(t==0) n = -1;
        return n;
    }

    //Find Pen
    static int findPen(String id, ArrayList<Pen> pen)
    {
        int n = -1;
        int t = 0;
        String penId = "null";
        for(Pen p:pen)
        {
            n++;
            penId = p.getpId();
            if(id.equals(penId))
            {
                t++;
                break;
            }
        }

        if(t==0) n = -1;
        return n;
    }

    static int findOrder(String id, ArrayList<Order> order)
    {
        int n = -1;
        int t = 0;
        String orderId = "null";
        for(Order o: order)
        {
            n++;
            orderId = o.getpId();
            if(id.equals(orderId))
            {
                t++;
                break;
            }
        }
        if(t==0) n=-1;
        return n;
    }

    //Find Desk
    static int findDesk(String id, ArrayList<Desk> desk)
    {
        int n=-1, t=0;
        String deskId = "null";

        for(Desk d:desk)
        {
            n++;
            deskId = d.getpId();

            if(id.equals(deskId))
            {
                t++;
                break;
            }

        }
        if(t==0) n = -1;
        return n;
    }

    //Find Calculator
    static int findCalc(String id, ArrayList<Calc> calc)
    {
        int n=-1;
        int t=0;
        String calcId = "null";

        for(Calc c:calc)
        {
            n++;
            calcId = c.getpId();
            if(id.equals(calcId))
            {
                t++;
                break;
            }
        }

        if(t==0) n = -1;
        return n;
    }

    //View Cart
    static void displayCart(ArrayList<Order> order)
    {
        System.out.println("\n**********************************  Your Cart  **********************************\n");
        for(Order o: order)
            System.out.println(o);
        System.out.println("*********************************************************************************");
    }

    //Remove Item From Cart
    static int RemoveItem(String id, ArrayList<Order> order)
    {
        int n = -1;
        String idItem = "";
        for(Order o:order)
        {
            idItem = o.getpId();
            n++;
            if(id.equals(idItem))
                break;
        }

        return n;
    }

    //Main method

    public static void main(String[] args) throws Exception {
        // write your code here
        //Scanner object
        Scanner sc = new Scanner(System.in);


        //Customer id
        int cusId = -1;
        //Database Object
        Database database = new Database();
        int total = 0;
        int bookItem = 0, penItem = 0, deskItem = 0, calcItem = 0;

        String url = "jdbc:mysql://localhost:3306/stationary";
        Connection conn = DriverManager.getConnection(url, "root", "201102");
        String query = "select * from book;";
        Statement stm = conn.createStatement();
        Statement stm1 = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        //Product List
        ArrayList<Book> book = new ArrayList<>();

        while(rs.next())
        {
            Book newb = new Book(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getInt("price"), rs.getInt("stock"), rs.getString("author"), rs.getInt("noOfpages") );
            book.add(newb);
        }


        ArrayList<Pen> pen = new ArrayList<>();
        query = "select * from pen;";
        rs = stm.executeQuery(query);

        while(rs.next())
        {
            Pen newp = new Pen(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getInt("price"), rs.getInt("stock"), rs.getString("color"), rs.getString("compName"));
            pen.add(newp);
        }

        ArrayList<Desk> desk = new ArrayList<>();
        query = "select * from desk;";
        rs = stm.executeQuery(query);

        while(rs.next())
        {
            Desk newd = new Desk(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getInt("price"), rs.getInt("stock"), rs.getString("material"), rs.getString("compName"));
            desk.add(newd);
        }

        ArrayList<Calc> calc = new ArrayList<>();
        query = "select * from calc;";
        rs = stm.executeQuery(query);

        while (rs.next())
        {
            Calc newc = new Calc(rs.getString("pId"), rs.getString("pName"), rs.getString("description"), rs.getInt("price"), rs.getInt("stock"), rs.getString("compName"), rs.getString("type"));
            calc.add(newc);
        }
        ArrayList<Order> order = new ArrayList<>();
        Customer customer = new Customer();
        ArrayList<Customer> customerArray = new ArrayList<>();

//        String sId = null;

        //Deserialize File

        FileInputStream file = new FileInputStream("sessionid.txt");
        ObjectInputStream input = new ObjectInputStream(file);
        RandomString ran = (RandomString)input.readObject();
        input.close();
        file.close();

        String sId = ran.getSessionId(ran.change);

//        FileInputStream file = new FileInputStream("customerData.txt");
//        ObjectInputStream input = new ObjectInputStream(file);
//        customerArray = (ArrayList<Customer>) input.readObject();
//        input.close();
//        file.close();
//
//        FileInputStream bookFile1 = new FileInputStream("bookData.txt");
//        ObjectInputStream bookInput = new ObjectInputStream(bookFile1);
//        book = (ArrayList<Book>) bookInput.readObject();
//        bookInput.close();
//        bookFile1.close();
//
//        FileInputStream penFile1 = new FileInputStream("penData.txt");
//        ObjectInputStream penInput = new ObjectInputStream(penFile1);
//        pen = (ArrayList<Pen>) penInput.readObject();
//        penInput.close();
//        penFile1.close();
//
//        FileInputStream deskFile1 = new FileInputStream("deskData.txt");
//        ObjectInputStream deskInput = new ObjectInputStream(deskFile1);
//        desk = (ArrayList<Desk>) deskInput.readObject();
//        deskInput.close();
//        deskFile1.close();
//
//        FileInputStream calcFile1 = new FileInputStream("calcData.txt");
//        ObjectInputStream calcInput = new ObjectInputStream(calcFile1);
//        calc = (ArrayList<Calc>) calcInput.readObject();
//        calcInput.close();
//        calcFile1.close();

        //Order History
        OrderHistory myOrder = new OrderHistory();

        int newCustomer = 0, customerIndex = -1;
        long findCustomer = 0;

//        RandomString ran = new RandomString();

        //Start
        System.out.println("Choose your option");
        System.out.println("1 For Register Product");
        System.out.println("2 For Buy Product");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {
            System.out.println("Choose 1 For new product:");
            System.out.println("Choose 2 For update stock of existing Product");
            int choose = sc.nextInt();
            sc.nextLine();

            if(choose == 1)
            {
                System.out.println("Choose category for register product:");
                System.out.println("1 for Book");
                System.out.println("2 for Pen");
                System.out.println("3 for Desk");
                System.out.println("4 for Calculator");
                System.out.println("-1 for exit");
                int category = sc.nextInt();
                sc.nextLine();

                while(category != -1) {
                    //Book
                    if(category == 1) {
                        String pId, pName, des, author;
                        int price, stock, noOfPages;
                        System.out.println("Enter product ID:");
                        pId = sc.nextLine();

                        //Try
                        int duplicateProduct = 0;
                        for(Book b: book)
                        {
                            if(b.getpId().equals(pId))
                            {
                                duplicateProduct++;
                                break;
                            }
                        }
                        if(duplicateProduct == 0) {
                            System.out.println("Enter product Name:");
                            pName = sc.nextLine();
                            System.out.println("Enter product description:");
                            des = sc.nextLine();
                            System.out.println("Enter the name of author:");
                            author = sc.nextLine();
                            System.out.println("Enter number of pages:");
                            noOfPages = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter price of product:");
                            price = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter the no of stock available:");
                            stock = sc.nextInt();
                            sc.nextLine();

                            Book addBook = new Book(pId, pName, des, price, stock, author, noOfPages);
                            book.add(addBook);
                        }
                        else{
                            System.out.println("Product with this product ID is already registered!!!");
                        }
                    }

                    //Pen
                    else if(category == 2) {
                        String pId, pName, des, color, compName;
                        int price, stock;
                        System.out.println("Enter product ID:");
                        pId = sc.nextLine();

                        int duplicateProduct = 0;

                        for(Pen p: pen)
                        {
                            if(p.getpId().equals(pId))
                            {
                                duplicateProduct++;
                                break;
                            }
                        }

                        if(duplicateProduct == 0) {
                            System.out.println("Enter product Name:");
                            pName = sc.nextLine();
                            System.out.println("Enter product description:");
                            des = sc.nextLine();
                            System.out.println("Enter the name of company:");
                            compName = sc.nextLine();
                            System.out.println("Enter the color of pen:");
                            color = sc.nextLine();
                            System.out.println("Enter price of product:");
                            price = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter the no of stock available:");
                            stock = sc.nextInt();
                            sc.nextLine();
                            Pen addPen = new Pen(pId, pName, des, price, stock, color, compName);
                            pen.add(addPen);
                        }
                        else
                        {
                            System.out.println("Product with this product Id is already registered!!!");
                        }
                    }

                    else if(category == 3) {
                        String pId, pName, des, compName, material;
                        int price, stock;
                        System.out.println("Enter product ID:");
                        pId = sc.nextLine();

                        int duplicateProduct = 0;

                        for(Desk d: desk)
                        {
                            if(d.getpId().equals(pId))
                            {
                                duplicateProduct++;
                                break;
                            }
                        }

                        if(duplicateProduct == 0) {
                            System.out.println("Enter product Name:");
                            pName = sc.nextLine();
                            System.out.println("Enter product description:");
                            des = sc.nextLine();
                            System.out.println("Enter the name of company:");
                            compName = sc.nextLine();
                            System.out.println("Enter the type of material:");
                            material = sc.nextLine();
                            System.out.println("Enter price of product:");
                            price = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter the no of stock available:");
                            stock = sc.nextInt();
                            sc.nextLine();
                            Desk addDesk = new Desk(pId, pName, des, price, stock, material, compName);
                            desk.add(addDesk);
                        }

                        else
                        {
                            System.out.println("Product with this product Id is already registered!!!");
                        }
                    }

                    else if(category == 4) {
                        String pId, pName, des, compName, type;
                        int price, stock;
                        System.out.println("Enter product ID:");
                        pId = sc.nextLine();

                        int duplicateProduct = 0;

                        for(Calc c: calc)
                        {
                            if(c.getpId().equals(pId))
                            {
                                duplicateProduct++;
                                break;
                            }
                        }

                        if(duplicateProduct == 0) {
                            System.out.println("Enter product Name:");
                            pName = sc.nextLine();
                            System.out.println("Enter product description:");
                            des = sc.nextLine();
                            System.out.println("Enter the name of company: ");
                            compName = sc.nextLine();
                            System.out.println("Enter the type of calculator:");
                            type = sc.nextLine();
                            System.out.println("Enter price of product:");
                            price = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter the no of stock available:");
                            stock = sc.nextInt();
                            sc.nextLine();
                            Calc addCalc = new Calc(pId, pName, des, price, stock, compName, type);
                            calc.add(addCalc);
                        }
                        else
                        {
                            System.out.println("Product with this product Id is already registered!!!");
                        }
                    }

                    else{
                        System.out.println("Invalid Input!!!");
                        category = -1;
                    }

                    System.out.println("Choose category for register product:");
                    System.out.println("1 for Book");
                    System.out.println("2 for Pen");
                    System.out.println("3 for Desk");
                    System.out.println("4 for Calculator");
                    System.out.println("-1 for exit");
                    category = sc.nextInt();
                    sc.nextLine();

                }
            }

            else if(choose == 2)
            {
                System.out.println("Choose product for add stock");
                System.out.println("1 for Book");
                System.out.println("2 for Pen");
                System.out.println("3 for Desk");
                System.out.println("4 for Calculator");
                System.out.println("-1 for exit");
                int changeStock = sc.nextInt();
                sc.nextLine();

                while (changeStock != -1)
                {
                    if(changeStock == 1)
                    {
                        System.out.println("Registered books are shown below:");
                        for(Book b: book)
                        {
                            System.out.println(b);
                        }

                        System.out.println("Enter the ID of book for you want to update stock:");
                        String checkId = sc.nextLine();
                        int ind = findBook(checkId, book);

                        if(ind == -1)
                            System.out.println("No Book found!!!");
                        else{
                            Book temp = book.get(ind);
                            System.out.println("Enter no of quantity you want to add:");
                            int updateStock = sc.nextInt();
                            sc.nextLine();
                            temp.maipulateStock(-1 * updateStock);
                            book.set(ind, temp);
                        }
                    }

                    else if(changeStock == 2)
                    {
                        System.out.println("Registered Pen are shown below:");
                        for(Pen p: pen)
                        {
                            System.out.println(p);
                        }

                        System.out.println("Enter the ID of Pen for you want to update stock:");
                        String checkId = sc.nextLine();
                        int ind = findPen(checkId, pen);

                        if(ind == -1)
                            System.out.println("No pen found!!!");
                        else{
                            Pen temp = pen.get(ind);
                            System.out.println("Enter no of quantity you want to add:");
                            int updateStock = sc.nextInt();
                            sc.nextLine();
                            temp.maipulateStock(-1 * updateStock);
                            pen.set(ind, temp);
                        }
                    }

                    else if(changeStock == 3)
                    {
                        System.out.println("Registered Desk are shown below:");
                        for(Desk d: desk)
                        {
                            System.out.println(d);
                        }

                        System.out.println("Enter the ID of Desk for you want to update stock:");
                        String checkId = sc.nextLine();
                        int ind = findDesk(checkId, desk);

                        if(ind == -1)
                            System.out.println("No Desk found!!!");
                        else{
                            Desk temp = desk.get(ind);
                            System.out.println("Enter no of quantity you want to add:");
                            int updateStock = sc.nextInt();
                            sc.nextLine();
                            temp.maipulateStock(-1 * updateStock);
                            desk.set(ind, temp);
                        }
                    }

                    else if(changeStock == 4)
                    {
                        System.out.println("Registered Calculator are shown below:");
                        for(Calc c: calc)
                        {
                            System.out.println(c);
                        }

                        System.out.println("Enter the ID of Calculator for you want to update stock:");
                        String checkId = sc.nextLine();
                        int ind = findCalc(checkId, calc);

                        if(ind == -1)
                            System.out.println("No pen found!!!");
                        else{
                            Calc temp = calc.get(ind);
                            System.out.println("Enter no of quantity you want to add:");
                            int updateStock = sc.nextInt();
                            sc.nextLine();
                            temp.maipulateStock(-1 * updateStock);
                            calc.set(ind, temp);
                        }
                    }
                    else
                    {
                        System.out.println("Invalid Input!!!");
                    }

                    System.out.println("Choose product for add stock");
                    System.out.println("1 for Book");
                    System.out.println("2 for Pen");
                    System.out.println("3 for Desk");
                    System.out.println("4 for Calculator");
                    System.out.println("-1 for exit");
                    changeStock = sc.nextInt();
                    sc.nextLine();

                }
            }
            else
                System.out.println("Your entered invalid option!!! Try again!!!");
        }
        else if (option == 2) {

            while (true) {
                System.out.println("Are you new customer:");
                System.out.println("1 --- yes");
                System.out.println("2 --- no");
                newCustomer = sc.nextInt();
                sc.nextLine();

                //Existing customer
                if (newCustomer == 2) {
                    System.out.println("Enter your mobile no:");
                    findCustomer = sc.nextLong();
                    sc.nextLine();
                    query = "select * from user where `mobile` = '"+findCustomer+"'";
                    rs = stm.executeQuery(query);

                    if(rs.next())
                    {
                        cusId = rs.getInt("id");
                        customer = new Customer(rs.getString("name"), rs.getString("email"), rs.getLong("mobile"), rs.getString("dob"), rs.getString("hno"), rs.getString("add1"), rs.getString("add2"), rs.getString("city"), rs.getLong("pincode"), rs.getString("last_login"));
                        break;
                    }

                    else{
                        System.out.println("Customer with this mobile no not found!!!\nFirst register yourself");
                        newCustomer = 1;
                    }

                }

                //New Customer
                if (newCustomer == 1) {
                    String name, email, dob, hno, add1, add2, city;
                    long mobile, pincode;
                    System.out.println("Register your self:\n");
                    System.out.println("Enter your Name: ");
                    name = sc.nextLine();
                    System.out.println("Enter your Email: ");
                    email = sc.nextLine();
                    System.out.println("Enter your Date of Birth: ");
                    dob = sc.nextLine();
                    System.out.println("Enter your mobile no: ");
                    mobile = sc.nextLong();
                    sc.nextLine();


                    int cusDuplicate = 0;
//                    for (Customer c : customerArray) {
//                        if (c.getMobile() == mobile) {
//                            cusDuplicate++;
//                            break;
//                        }
//                    }
                    query = "select * from user where `mobile` = '"+mobile+"'";
                    if(rs.next())
                    {
                        cusDuplicate++;
                    }

                    if (cusDuplicate == 0) {
                        System.out.println("\nEnter your address:");
                        System.out.println("Enter your house no: ");
                        hno = sc.nextLine();
                        System.out.println("Enter address line 1: ");
                        add1 = sc.nextLine();
                        System.out.println("Enter address line 2: ");
                        add2 = sc.nextLine();
                        System.out.println("Enter the city: ");
                        city = sc.nextLine();
                        System.out.println("Enter the Pincode: ");
                        pincode = sc.nextLong();
                        sc.nextLine();
                        Date dt = new Date();

                        customer = new Customer(name, email, mobile, dob, hno, add1, add2, city, pincode, null);
                        //Adding customer to database
                        try
                        {
                            query = "insert into user(name, email, mobile, dob, hNo, add1, add2, city, pincode, last_login) values('"+name+"', '"+email+"', '"+mobile+"', '"+dob+"', '"+hno+"', '"+add1+"', '"+add2+"', '"+city+"', '"+pincode+"', '"+dt+"')";
                            stm.executeUpdate(query);
                            query = "select * from user where mobile =  '"+mobile+"';";
                            rs = stm.executeQuery(query);
                            rs.next();
                            cusId = rs.getInt("id");

                        }

                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;
                    } else {
                        System.out.println("Customer with this mobile no already exist!!!");
                        System.out.println("Try to use existing customer!!!");
                    }

                }
            }

            String login = customer.getLastLogin();
            if(login != null)
            {
                System.out.println("Last login: "+ login);
            }
            //Updating lastLogin
            Date lastLogin = new Date();
            query = "update user set `last_login` = '"+lastLogin+"' where `id` = '"+cusId+"';";
            stm.executeUpdate(query);
            //Choosing the product

            System.out.println("Select the Product Which you want to buy\n");
            System.out.println("Choose 1 For Books");
            System.out.println("Choose 2 For Pen");
            System.out.println("Choose 3 For Desk");
            System.out.println("Choose 4 For Calculator");
            System.out.println("Choose -1 for Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            while (choice != -1) {
                if (choice == 1) {
                    System.out.println("Available books are  shown below: ");
                    for (Book b : book) {
                        if (b.stock != 0) {
                            System.out.println(b);
                        }
                    }

                    System.out.println("Enter the ID of Book which you want to purchase: ");
                    String id = sc.nextLine();
                    int add = findBook(id, book);

                    if (add == -1) System.out.println("No book Found!!!");
                    else {
                        Book temp = book.get(add);
                        System.out.println("How many quantity you want to purchase:");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        if (quantity <= temp.getStock()) {
                            total += (temp.getPrice() * quantity);
                            temp.maipulateStock(quantity);
                            book.set(add, temp);
                            int duplicate = 0;
                            duplicate = findOrder(id, order);
                            if (duplicate == -1) {
                                order.add(new Order(temp.getpId(), temp.getpName(), quantity, temp.getPrice()));
                            } else {
                                Order tempOrder = order.get(duplicate);
                                tempOrder.setTotalItem(tempOrder.getTotalItem() + quantity);
                                order.set(duplicate, tempOrder);
                            }
                        } else {
                            System.out.println("No Quantity you entered are not in stock!!!");
                        }

                    }

                } else if (choice == 2) {
                    System.out.println("Available Pen's are shown below:");

                    for (Pen p : pen) {
                        if (p.stock != 0)
                            System.out.println(p);
                    }

                    System.out.println("Enter the ID of Product which you wanted to purchase");
                    String id = sc.nextLine();
                    int add = findPen(id, pen);

                    if (add == -1)
                        System.out.println("No pen found!!!");

                    else {
                        Pen temp = pen.get(add);
                        System.out.println("How many quantity you want to purchase:");
                        int quantity = sc.nextInt();
                        sc.nextLine();

                        if (quantity <= temp.getStock()) {
                            total += (temp.getPrice() * quantity);
                            temp.maipulateStock(quantity);
                            pen.set(add, temp);
                            int duplicate = 0;
                            duplicate = findOrder(id, order);
                            if (duplicate == -1) {
                                order.add(new Order(temp.getpId(), temp.getpName(), quantity, temp.getPrice()));
                            } else {
                                Order tempOrder = order.get(duplicate);
                                tempOrder.setTotalItem(tempOrder.getTotalItem() + quantity);
                                order.set(duplicate, tempOrder);
                            }
                        } else {
                            System.out.println("No Quantity you entered are not in stock!!!");
                        }
                    }
                } else if (choice == 3) {
                    System.out.println("Available Desk's are shown below:");
                    for (Desk d : desk) {
                        if (d.getStock() != 0)
                            System.out.println(d);
                    }
                    System.out.println("Enter the ID of Product which you want to purchase:");
                    String id = sc.nextLine();
                    int add = findDesk(id, desk);
                    if (add == -1)
                        System.out.println("No Desk found!!!");
                    else {
                        Desk temp = desk.get(add);
                        System.out.println("How many quantity you want to purchase:");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        if (quantity <= temp.getStock()) {
                            total += (temp.getPrice() * quantity);
                            temp.maipulateStock(quantity);
                            desk.set(add, temp);
                            int duplicate = 0;
                            duplicate = findOrder(id, order);
                            if (duplicate == -1) {
                                order.add(new Order(temp.getpId(), temp.getpName(), quantity, temp.getPrice()));
                            } else {
                                Order tempOrder = order.get(duplicate);
                                tempOrder.setTotalItem(tempOrder.getTotalItem() + quantity);
                                order.set(duplicate, tempOrder);
                            }

                        } else {
                            System.out.println("No of quantity you entered are not in the stock!!!");
                        }
                    }
                } else if (choice == 4) {
                    System.out.println("Available Calculators are shown below:");
                    for (Calc c : calc) {
                        if (c.getStock() != 0)
                            System.out.println(c);
                    }

                    System.out.println("Enter the ID of Calculator which you want to purchase:");
                    String id = sc.nextLine();
                    int add = findCalc(id, calc);

                    if (add == -1)
                        System.out.println("No calculator Found!!!");
                    else {
                        Calc temp = calc.get(add);
                        System.out.println("How many quantity you want to purchase:");
                        int quantity = sc.nextInt();

                        if (quantity <= temp.getStock()) {
                            total += (temp.getPrice() * quantity);
                            temp.maipulateStock(quantity);
                            calc.set(add, temp);

                            //Try
                            int duplicate = 0;
                            duplicate = findOrder(id, order);
                            if (duplicate == -1) {
                                order.add(new Order(temp.getpId(), temp.getpName(), quantity, temp.getPrice()));
                            } else {
                                Order tempOrder = order.get(duplicate);
                                tempOrder.setTotalItem(tempOrder.getTotalItem() + quantity);
                                order.set(duplicate, tempOrder);
                            }
                        } else
                            System.out.println("No of quantity you entered are not in the stock!!!");
                    }
                } else {
                    System.out.println("Please enter valid input");
                    System.out.println("Choose 1 For Books");
                    System.out.println("Choose 2 For Pen");
                    System.out.println("Choose 3 For Desk");
                    System.out.println("Choose 4 For Calculator");
                    System.out.println("Choose -1 for Exit");

                    choice = sc.nextInt();
                    sc.nextLine();

                }

                System.out.println("Select the Product Which you want to buy\n");
                System.out.println("Choose 1 For Books");
                System.out.println("Choose 2 For Pen");
                System.out.println("Choose 3 For Desk");
                System.out.println("Choose 4 For Calculator");
                System.out.println("Choose -1 for Exit");

                choice = sc.nextInt();
                sc.nextLine();
            }
            int error = 0;
            if(order.isEmpty())
                error++;

            if(error == 0)
            {

                // Displaying Cart
                int cartChoice = -1;
                System.out.println("Do you want to see cart:");
                System.out.println("1 For Yes");
                System.out.println("2 For No");
                cartChoice = sc.nextInt();
                sc.nextLine();

                //Remove from cart
                int updateError = 0;
                if (cartChoice == 1)
                    displayCart(order);

                System.out.println("Do you want to remove any things:");
                System.out.println("1 For yes");
                System.out.println("2 for no");
                cartChoice = sc.nextInt();
                sc.nextLine();

                if (cartChoice == 1) {
                    System.out.println("How many items you want to remove?");
                    int noRemoveItem = sc.nextInt();
                    sc.nextLine();
                    if (noRemoveItem > order.size())
                        System.out.println("You don't have enough item to remove!!!");
                    else {
                        for (int i = 0; i < noRemoveItem; i++) {
                            System.out.println("Enter the id of product which you want to remove:");
                            String idRemoveItem = sc.nextLine();
                            int index = RemoveItem(idRemoveItem, order);
                            Order temp = order.get(index);
                            if (temp.getTotalItem() > 1) {
                                System.out.println("How many quantity you want to remove from cart:");
                                int noItem = sc.nextInt();
                                sc.nextLine();
                                if (temp.getTotalItem() < noItem)
                                    System.out.println("No enough quantity added in cart for remove!!!");
                                else{

                                    //Try
                                    for(Book b: book)
                                    {
                                        if(b.getpId().equals(idRemoveItem))
                                            b.maipulateStock(-1 * noItem);
                                    }

                                    for(Pen p: pen)
                                    {
                                        if(p.getpId().equals(idRemoveItem))
                                            p.maipulateStock(-1 * noItem);
                                    }

                                    for(Desk d: desk)
                                    {
                                        if(d.getpId().equals(idRemoveItem))
                                            d.maipulateStock(-1 * noItem);
                                    }

                                    for(Calc c: calc)
                                    {
                                        if(c.getpId().equals(idRemoveItem))
                                            c.maipulateStock(-1 * noItem);
                                    }

                                    total -= (noItem * temp.getItemPrice());
                                    if (temp.getTotalItem() == noItem) {
                                        order.remove(index);
                                    }
                                    else
                                        temp.changeTotalItem(noItem);
                                }

                            }
                            else
                            {
                                order.remove(index);
                            }
                        }


                        if(order.isEmpty())
                        {
                            updateError++;
                        }

                        if(updateError == 0) {
                            System.out.println("Updated Cart");
                            displayCart(order);
                        }
                    }
                }

                //Asking For Changing Personal Details
                if(updateError == 0) {

                    try{
                        query = "select * from orderhistory where id = '"+cusId+"';";
                        rs = stm.executeQuery(query);

                        while(rs.next())
                        {
                            String sesId = rs.getString("sId");
                            OrderHistory newHistory = new OrderHistory();
                            query = "select * from orderdetails where sId = '"+sesId+"';";
                            ResultSet rs2 = stm1.executeQuery(query);
                            while (rs2.next())
                            {
                                int qty = rs2.getInt("qty");
                                Order or = new Order(rs2.getString("oid"), rs2.getString("oname"), qty, rs2.getInt("totalprice")/qty);
                                newHistory.addOrder(or);
                            }
                            newHistory.addDateTime(rs.getString("odate"));
                            newHistory.addTotal(rs.getInt("pay"));
                            customer.addOrder(newHistory);


                        }

                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Do you want to see your personal detail:");
                    System.out.println("1 for Yes:");
                    System.out.println("2 for No:");
                    int personal = sc.nextInt();
                    sc.nextLine();
                    if (personal == 1) {
                        System.out.println(customer);
                    }


                    //Change customer details
                    System.out.println("Want to change your personal details?");
                    System.out.println("1 For yes");
                    System.out.println("2 For no");
                    int changePersonalDetail = sc.nextInt();
                    sc.nextLine();

                    if (changePersonalDetail == 1) {
                        String cName, email, dob, houseNo, address1, address2, city;
                        long pincode, mobile;
                        System.out.println("Enter your Name:");
                        cName = sc.nextLine();

                        System.out.println("Enter your Email:");
                        email = sc.nextLine();

                        sc.nextLine();

                        System.out.println("Enter your Date of Birth:");
                        dob = sc.nextLine();

                        System.out.println("Enter your address:");
                        System.out.println("Enter the house No:");
                        houseNo = sc.nextLine();
                        System.out.println("Enter your address line1:");
                        address1 = sc.nextLine();
                        System.out.println("Enter your address line2:");
                        address2 = sc.nextLine();
                        System.out.println("Enter your city:");
                        city = sc.nextLine();
                        System.out.println("Enter your pincode:");
                        pincode = sc.nextLong();
                        sc.nextLine();

                        query = "update user set name ='"+cName+"' , email ='"+email+"' , dob ='"+dob+"' , hno ='"+houseNo+"' , add1 ='"+address1+"' , add2 ='"+address2+"' , city ='"+city+"' , pincode = '"+pincode+"';";
                        stm.executeUpdate(query);

                    }


                    Date currentDate = new Date();
                    String text = currentDate.toString();

                    if(order.size() != 0)
                    {
                        query = "insert into orderhistory values('"+cusId+"', '"+sId+"', '"+total+"', '"+currentDate+"');";
                        stm.executeUpdate(query);

                        for(Order o:order)
                        {
                            query = "insert into orderdetails(oid, oname, qty, id, sid, totalprice) values ('"+o.getpId()+"', '"+o.getItemName()+"', '"+o.getTotalItem()+"', '"+cusId+"', '"+sId+"', '"+o.countTotal()+"')";
                            stm.executeUpdate(query);
                            myOrder.addOrder(o);
                        }
                        myOrder.addDateTime(text);
                        myOrder.addTotal(total);
                        customer.addOrder(myOrder);
                        customer.currentOrder(order, myOrder);

                    }

                    //Modify customer array
                    if (customerIndex != -1)
                        customerArray.set(customerIndex, customer);
                    else if (customerIndex == -1)
                        customerArray.add(customer);
                    System.out.println("Do you want to see your past orders:");
                    System.out.println("1 For yes");
                    System.out.println("2 For No");
                    int pastOrder = sc.nextInt();
                    sc.nextLine();
                    if (pastOrder == 1) {
                        System.out.println("\nYour All Orders\n");
                        customer.displayAll();
                    }
                }

                else{
                    System.out.println("You have not buy anything!!!\n");
                    System.out.println("Do you want to see your past orders:");
                    System.out.println("1 For yes");
                    System.out.println("2 For No");
                    int pastOrder = sc.nextInt();
                    sc.nextLine();
                    if (pastOrder == 1) {
                        System.out.println("\nYour All Orders\n");
                        customer.displayAll();
                    }
                    System.out.println("Thank you for coming!!\nHave a nice day\n");

                }
            }

            else{
                System.out.println("You have not buy anything!!!\n");
                System.out.println("Do you want to see your past orders:");
                System.out.println("1 For yes");
                System.out.println("2 For No");
                int pastOrder = sc.nextInt();
                sc.nextLine();
                try{
                    query = "select * from orderhistory where id = '"+cusId+"';";
                    rs = stm.executeQuery(query);

                    while(rs.next())
                    {
                        String sesId = rs.getString("sId");
                        OrderHistory newHistory = new OrderHistory();
                        query = "select * from orderdetails where sId = '"+sesId+"';";
                        ResultSet rs2 = stm1.executeQuery(query);
                        while (rs2.next())
                        {
                            int qty = rs2.getInt("qty");
                            Order or = new Order(rs2.getString("oid"), rs2.getString("oname"), qty, rs2.getInt("totalprice")/qty);
                            newHistory.addOrder(or);
                        }
                        newHistory.addDateTime(rs.getString("odate"));
                        newHistory.addTotal(rs.getInt("pay"));
                        customer.addOrder(newHistory);


                    }

                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
                if (pastOrder == 1) {
                    System.out.println("\nYour All Orders\n");
                    customer.displayAll();
                }
                System.out.println("Thank you for coming!!\nHave a nice day\n");
            }
        }

        for(Book b: book)
        {
            try{
                stm.executeUpdate("update book set pId ='"+b.pId+"' , pName ='"+b.pName+"' , description ='"+b.des+"' , author ='"+b.author+"' , price = '"+b.price+"', noOfpages = '"+b.noOfpages+"' , stock = '"+b.stock+"' where pId = '"+b.pId+"'");
                int countProduct = 0;

                rs = stm.executeQuery("select * from book where pId = '"+b.pId+"'");
                if(rs.next())
                {
                    countProduct++;
                }
                if(countProduct == 0)
                {
                    stm.executeUpdate("insert into book values ('"+b.pId+"', '"+b.pName+"', '"+b.des+"', '"+b.price+"', '"+b.stock+"', '"+b.author+"', '"+b.noOfpages+"');");
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        for(Pen b: pen)
        {
            try{
                stm.executeUpdate("update pen set pId ='"+b.pId+"' , pName ='"+b.pName+"' , description ='"+b.des+"' , color ='"+b.color+"' , price = '"+b.price+"', compName = '"+b.compName+"' , stock = '"+b.stock+"' where pId = '"+b.pId+"'");

                int countProduct = 0;

                rs = stm.executeQuery("select * from pen where pId = '"+b.pId+"'");
                if(rs.next())
                {
                    countProduct++;
                }
                if(countProduct == 0)
                {
                    stm.executeUpdate("insert into pen values ('"+b.pId+"', '"+b.pName+"', '"+b.des+"', '"+b.price+"', '"+b.stock+"', '"+b.color+"', '"+b.compName+"');");
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        for(Desk b: desk)
        {
            try{
                stm.executeUpdate("update desk set pId ='"+b.pId+"' , pName ='"+b.pName+"' , description ='"+b.des+"' , material ='"+b.material+"' , price = '"+b.price+"', compName = '"+b.compName+"' , stock = '"+b.stock+"' where pId = '"+b.pId+"'");

                int countProduct = 0;

                rs = stm.executeQuery("select * from desk where pId = '"+b.pId+"'");
                if(rs.next())
                {
                    countProduct++;
                }
                if(countProduct == 0)
                {
                    stm.executeUpdate("insert into desk values ('"+b.pId+"', '"+b.pName+"', '"+b.des+"', '"+b.price+"', '"+b.stock+"', '"+b.material+"', '"+b.compName+"');");
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        for(Calc b: calc)
        {
            try{
                stm.executeUpdate("update calc set pId ='"+b.pId+"' , pName ='"+b.pName+"' , description ='"+b.des+"' , type ='"+b.type+"' , price = '"+b.price+"', compName = '"+b.compName+"' , stock = '"+b.stock+"' where pId = '"+b.pId+"'");

                int countProduct = 0;

                rs = stm.executeQuery("select * from calc where pId = '"+b.pId+"'");
                if(rs.next())
                {
                    countProduct++;
                }
                if(countProduct == 0)
                {
                    stm.executeUpdate("insert into calc values ('"+b.pId+"', '"+b.pName+"', '"+b.des+"', '"+b.price+"', '"+b.stock+"', '"+b.compName+"', '"+b.type+"');");
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }


        FileOutputStream file2 = new FileOutputStream("sessionid.txt");
        ObjectOutputStream out = new ObjectOutputStream(file2);
        out.writeObject(ran);
        out.close();
        file2.close();
        //Serialise Files
        //Customer
//        FileOutputStream myFile = new FileOutputStream("customerData.txt");
//        ObjectOutputStream output = new ObjectOutputStream(myFile);
//        output.writeObject(customerArray);
//        output.close();
//        myFile.close();
//
//        //Book
//        FileOutputStream bookFile = new FileOutputStream("bookData.txt");
//        ObjectOutputStream bookOutput = new ObjectOutputStream(bookFile);
//        bookOutput.writeObject(book);
//        bookOutput.close();
//        bookFile.close();
//
//        //Pen
//        FileOutputStream penFile = new FileOutputStream("penData.txt");
//        ObjectOutputStream penOutput = new ObjectOutputStream(penFile);
//        penOutput.writeObject(pen);
//        penOutput.close();
//        penFile.close();
//
//        //Desk
//        FileOutputStream deskFile = new FileOutputStream("deskData.txt");
//        ObjectOutputStream deskOutput = new ObjectOutputStream(deskFile);
//        deskOutput.writeObject(desk);
//        deskOutput.close();
//        deskFile.close();
//
//        //Calculator
//        FileOutputStream calcFile = new FileOutputStream("calcData.txt");
//        ObjectOutputStream calcOutput = new ObjectOutputStream(calcFile);
//        calcOutput.writeObject(calc);
//        calcOutput.close();
//        calcFile.close();
    }
}
