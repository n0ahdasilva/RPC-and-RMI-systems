/**
 *  PROJECT : RPC and RMI Systems
 * 
 *  FILENAME : rsvserver.java
 * 
 *  DESCRIPTION :
 *      Client-server network application using the Remote Method Invocation 
 *      (RMI) system.
 * 
 *  FUNCTIONS :
 *      rsvserver.main()
 * 
 *  NOTES :
 *      - ...
 * 
 *  AUTHOR(S) : Noah Arcand Da Silva    START DATE : 2022.10.03 (YYYY.MM.DD)
 *
 *  CHANGES :
 *      - ...
 * 
 *  VERSION     DATE        WHO             DETAILS
 *  0.0.1a      2022.10.03  Noah            Creation of project.
 */


import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.*;
import java.util.function.Predicate;

import javax.crypto.SealedObject;


public class rsvserver  extends UnicastRemoteObject
                        implements Rsv
{
    private List<Seat> seat_list;
    /*
     *  Default constructor for throwing the RemoteException from its parent
     *  constructor (Rsv).
     */
    protected rsvserver(List<Seat> list) throws RemoteException
    {
        super();    // Call the superclass default constructo, the interface in
                    // this case, to initialize its functions and variables.
        this.seat_list = list;
    }

    @Override
    public Seat find_seat(Seat seat) throws RemoteException
    {
        Predicate<Seat> predicate = x -> (x.get_seat_number() == seat.get_seat_number());
        return seat_list.stream().filter(predicate).findFirst().get();
    }

    @Override
    public List<Seat> all_seats() throws RemoteException
    {
        return seat_list;
    }

    private static List<Seat> initialize_list()
    {
        List<Seat> list = new ArrayList<>();
        list.add(new Seat(1, "business", true, 500, 500, null));
        list.add(new Seat(2, "business", true, 500, 500, null));
        list.add(new Seat(3, "business", true, 500, 500, null));
        list.add(new Seat(4, "business", true, 500, 500, null));
        list.add(new Seat(5, "business", true, 500, 500, null));
        list.add(new Seat(6, "economy", true, 200, 200, null));
        list.add(new Seat(7, "economy", true, 200, 200, null));
        list.add(new Seat(7, "economy", true, 200, 200, null));
        list.add(new Seat(8, "economy", true, 200, 200, null));
        list.add(new Seat(9, "economy", true, 200, 200, null));
        list.add(new Seat(10, "economy", true, 200, 200, null));
        list.add(new Seat(11, "economy", true, 200, 200, null));
        list.add(new Seat(12, "economy", true, 200, 200, null));
        list.add(new Seat(13, "economy", true, 200, 200, null));
        list.add(new Seat(14, "economy", true, 200, 200, null));
        list.add(new Seat(15, "economy", true, 200, 200, null));
        list.add(new Seat(16, "economy", true, 200, 200, null));
        list.add(new Seat(17, "economy", true, 200, 200, null));
        list.add(new Seat(18, "economy", true, 200, 200, null));
        list.add(new Seat(19, "economy", true, 200, 200, null));
        list.add(new Seat(20, "economy", true, 200, 200, null));
        list.add(new Seat(21, "economy", true, 200, 200, null));
        list.add(new Seat(22, "economy", true, 200, 200, null));
        list.add(new Seat(23, "economy", true, 200, 200, null));
        list.add(new Seat(24, "economy", true, 200, 200, null));
        list.add(new Seat(25, "economy", true, 200, 200, null));
        list.add(new Seat(26, "economy", true, 200, 200, null));
        list.add(new Seat(27, "economy", true, 200, 200, null));
        list.add(new Seat(28, "economy", true, 200, 200, null));
        list.add(new Seat(29, "economy", true, 200, 200, null));
        list.add(new Seat(30, "economy", true, 200, 200, null));
        return list;
    }

    /*
     *  Implementations of the remote interface.
     */
    public String list()
    {
        return "TEST";
    }
    
    public String reserve()
    {
        return "TEST";
    }

    public String passenger_list()
    {
        return "TEST";
    }

    /*
     *  Main function.
     */
    public static void main(String[] args)
    {
        try
        {   // Creating an object of the interface implementation class 'rsvQuery'.
            Rsv obj = new rsvserver(initialize_list());
            // Set the port to 1900 for the server JVM (Had connection refused exception
            // without it).
            LocateRegistry.createRegistry(1900);
            // Bind the remote object by the name 'reservation'.
            Naming.rebind("rmi://localhost:1900/reservation", obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


class Seat 
{
    private int seat_number;
    private String ticket_class;
    private Boolean available;
    private int ticket_price;
    private int availability_price;
    private String passenger_name;

    /*
        *  Constructors
        */
    public Seat(int seat_number)
    {
        this.seat_number = seat_number;
    }

    public Seat(int seat_number, 
                String ticket_class,
                Boolean available,
                int ticket_price,
                int availability_price,
                String passenger_name)
    {
        this.seat_number = seat_number;
        this.ticket_class = ticket_class;
        this.available = available;
        this.ticket_price = ticket_price;
        this.availability_price = availability_price;
        this.passenger_name = passenger_name;
    }

    /*
        *  Setter and Getter functions.
        */

    // Seat number.
    public int get_seat_number()
    {
        return seat_number;
    }
    public void set_seat_number(int seat_number)
    {
        this.seat_number = seat_number;
    }

    // Ticket class.
    public String get_ticket_class()
    {
        return ticket_class;
    }
    public void set_ticket_class(String ticket_class)
    {
        this.ticket_class = ticket_class;
    }
    
    // Availability.
    public Boolean get_available()
    {
        return available;
    }
    public void set_available(Boolean available)
    {
        this.available = available;
    }

    // Ticket price.
    public int get_ticket_price()
    {
        return ticket_price;
    }
    public void set_ticket_price(int ticket_price)
    {
        this.ticket_price = ticket_price;
    }

    // Availability price.
    public int get_availability_price()
    {
        return availability_price;
    }
    public void set_availability_price(int availability_price)
    {
        this.availability_price = availability_price;
    }

    // Passenger name.
    public String get_passenger_name()
    {
        return passenger_name;
    }
    public void set_passenger_name(String passenger_name)
    {
        this.passenger_name = passenger_name;
    }
}

