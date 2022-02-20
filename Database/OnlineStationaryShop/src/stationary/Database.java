package stationary;

import java.util.ArrayList;

class Database {
    protected static ArrayList<Book> book = new ArrayList<>();
    protected static ArrayList<Pen> pen = new ArrayList<>();
    protected static ArrayList<Desk> desk = new ArrayList<>();
    protected static ArrayList<Calc> calc = new ArrayList<>();
    protected static ArrayList<Customer> customer = new ArrayList<>();

    //Constructor
    Database() {
        //Books
        book.add(new Book("NB001", "Game of Thrones", "Politics", 3549, 2, "Gorge R. R. Martin", 1245));

        //Pen
        pen.add(new Pen("PN001", "Sanio-Softek", "Ball point pen", 3, 100, "Blue","Sanio"));

        //Desk
        desk.add(new Desk("DK001", "Writing Desk", "Side Stand Desk", 80, 10 ,"Wooden", "Tata & Co."));

        //Calculator
        calc.add(new Calc("FX-991MS","Calculator","Solve with casio", 899, 5, "Casio", "Scientific"));
    }


    static ArrayList<Book> giveBook()
    {
        return book;
    }

    static ArrayList<Pen> givePen()
    {
        return pen;
    }

    static ArrayList<Desk> giveDesk()
    {
        return desk;
    }

    static ArrayList<Calc> giveCalc()
    {
        return calc;
    }
}
