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
        list.add(new Seat(1, "business", true, 500, null));
        list.add(new Seat(2, "business", true, 500, null));
        list.add(new Seat(3, "business", true, 500, null));
        list.add(new Seat(4, "business", true, 500, null));
        list.add(new Seat(5, "business", true, 500, null));
        list.add(new Seat(6, "economy", true, 200, null));
        list.add(new Seat(7, "economy", true, 200, null));
        list.add(new Seat(8, "economy", true, 200, null));
        list.add(new Seat(9, "economy", true, 200, null));
        list.add(new Seat(10, "economy", true, 200, null));
        list.add(new Seat(11, "economy", true, 200, null));
        list.add(new Seat(12, "economy", true, 200, null));
        list.add(new Seat(13, "economy", true, 200, null));
        list.add(new Seat(14, "economy", true, 200, null));
        list.add(new Seat(15, "economy", true, 200, null));
        list.add(new Seat(16, "economy", true, 200, null));
        list.add(new Seat(17, "economy", true, 200, null));
        list.add(new Seat(18, "economy", true, 200, null));
        list.add(new Seat(19, "economy", true, 200, null));
        list.add(new Seat(20, "economy", true, 200, null));
        list.add(new Seat(21, "economy", true, 200, null));
        list.add(new Seat(22, "economy", true, 200, null));
        list.add(new Seat(23, "economy", true, 200, null));
        list.add(new Seat(24, "economy", true, 200, null));
        list.add(new Seat(25, "economy", true, 200, null));
        list.add(new Seat(26, "economy", true, 200, null));
        list.add(new Seat(27, "economy", true, 200, null));
        list.add(new Seat(28, "economy", true, 200, null));
        list.add(new Seat(29, "economy", true, 200, null));
        list.add(new Seat(30, "economy", true, 200, null));
        return list;
    }

    /*
     *  Implementations of the remote interface.
     */
    public String list()
    {
        String available_seat_list = "";            // List output.
        String b_seat_numbers = "seat numbers: ";   // Available business seat numbers.
        String e_seat_numbers = "seat numbers: ";   // Available business seat numbers.
        
        int business_t1 = 0;
        int business_t2 = 0;

        int economy_t1 = 0;
        int economy_t2 = 0;
        int economy_t3 = 0;

        try
        {
            List<Seat> list = all_seats();

            for (int i = 0; i < list.size(); i++)
            {
                Seat seat = list.get(i);

                if (seat.get_seat_number() <= 5)
                {
                    if ((business_t2 < 2) && seat.get_available())
                    {
                        business_t2++;
                        b_seat_numbers += seat.get_seat_number() + ", ";
                    }
                    else if (seat.get_available())
                    {
                        business_t1++;
                        b_seat_numbers += seat.get_seat_number() + ", ";
                    }
                }
                else
                {
                    if ((economy_t3 < 5) && seat.get_available())
                    {
                        economy_t3++;
                        e_seat_numbers += seat.get_seat_number() + ", ";
                    }
                    else if ((economy_t2 < 10) && seat.get_available())
                    {
                        economy_t2++;
                        e_seat_numbers += seat.get_seat_number() + ", ";
                    }
                    else if (seat.get_available())
                    {
                        economy_t1++;
                        e_seat_numbers += seat.get_seat_number() + ", ";
                    }
                }
            }

            if (business_t2 > 0)
                available_seat_list += "business class:\n";
            if (business_t1 > 0)
                available_seat_list += business_t1 + " seat(s) at $500 each\n";
            if (business_t2 > 0)
            {
                available_seat_list += business_t2 + " seat(s) at $800 each\n";
                available_seat_list += b_seat_numbers + "\n";
            }

            if (economy_t3 > 0)
                available_seat_list += "economy class:\n";
            if (economy_t1 > 0)
                available_seat_list += economy_t1 + " seat(s) at $200 each\n";
            if (economy_t2 > 0)
                available_seat_list += economy_t2 + " seat(s) at $300 each\n";
            if (economy_t3 > 0)
            {
                available_seat_list += economy_t3 + " seat(s) at $450 each\n";
                available_seat_list += e_seat_numbers + "\n";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return available_seat_list;
    }
    
    public String reserve(String ticket_class, String passenger_name, int seat_number)
    {
        String message = "";

        try
        {
            List<Seat> list = all_seats();

            if ((seat_number < 1) || (seat_number > 30))
                return "Failed to reserve: invalid seat number";

            Seat seat = list.get(seat_number - 1);

            if (!seat.get_ticket_class().equals(ticket_class))
                return "Failed to reserve: invalid seat number";
            if (!seat.get_available())
                return "Failed to reserve: seat not available";
            else
            {
                seat.set_passenger_name(passenger_name);
                seat.set_available(false);
                message = "Successfully reserved seat #" + seat.get_seat_number() + " for passenger " + passenger_name + "\n";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return message;
    }

    public String passenger_list()
    {
        String taken_seat_list = "";            // List output.

        try
        {
            List<Seat> list = all_seats();

            for (int i = 0; i < list.size(); i++)
            {
                Seat seat = list.get(i);

                if (!seat.get_available())
                {
                    taken_seat_list += seat.get_passenger_name() + " "
                        + seat.get_ticket_class() + " " + seat.get_seat_number() + "\n";
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return taken_seat_list;
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
                String passenger_name)
    {
        this.seat_number = seat_number;
        this.ticket_class = ticket_class;
        this.available = available;
        this.ticket_price = ticket_price;
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

