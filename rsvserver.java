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

import java.rmi.*;                      // Provides remote communication between Java programs
import java.rmi.server.*;               // Provides classes and interfaces for supporting the server side of RMI.
import java.rmi.registry.*;             // Provides a class and two interfaces for the RMI registry.
import java.util.*;                     // Contains the collections framework, legacy collection classes, event model, date and time 
                                        // facilities, internationalization, and miscellaneous utility classes
import java.util.function.Predicate;    // Functional interface that accepts an argument and returns a boolean

public class rsvserver extends UnicastRemoteObject implements Rsv   // Class which extends the class UnicastRemoteObject and implements the interface Rsv
{
    private List<Seat> seat_list;

    // Default constructor for throwing the RemoteException from its parent constructor (Rsv).
    protected rsvserver(List<Seat> list) throws RemoteException
    {
        super();    // Call the superclass default constructor, the interface in this case, to initialize its functions and variables.
        this.seat_list = list;
    }

    @Override
    public Seat find_seat(Seat seat) throws RemoteException
    {
        // Look through the seats to see if there is any matching the requested seat number.
        Predicate<Seat> predicate = x -> (x.get_seat_number() == seat.get_seat_number());
        // Returns the first result of the requested seat.
        return seat_list.stream().filter(predicate).findFirst().get();
    }

    @Override
    public List<Seat> all_seats() throws RemoteException
    {
        return seat_list;   // Print the seat list
    }

    private static List<Seat> initialize_list() // Initializing the list of all the available seats
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

    // Implementations of the remote interface.

    public String list()
    {
        String available_seat_list = "";            // List output.
        String b_seat_numbers = "seat numbers: ";   // Available business seat numbers.
        String e_seat_numbers = "seat numbers: ";   // Available economy seat numbers.
        
        int business_t1 = 0;    // Initialize all the business seats in tier 1 ($500 each)
        int business_t2 = 0;    // Initialize all the business seats in tier 2 ($800 each)

        int economy_t1 = 0;     // Initialize all the economy seats in tier 1 ($200 each)
        int economy_t2 = 0;     // Initialize all the economy seats in tier 2 ($300 each)
        int economy_t3 = 0;     // Initialize all the economy seats in tier 3 ($450 each)

        try
        {
            List<Seat> list = all_seats();

            for (int i = 0; i < list.size(); i++)   // Cycle through the list of all seats
            {
                Seat seat = list.get(i);            // Check seat at instance (i)

                if (seat.get_seat_number() <= 5)    // Check if the seat number is less than 5 (business class)
                {
                    if ((business_t2 < 2) && seat.get_available())          // Check if the seat number is less than 2 and is available
                    {   
                        business_t2++;                                      // Increase until seat number is greater than 2
                        b_seat_numbers += seat.get_seat_number() + ", ";    // Print available business seat numbers at $800 each
                    }
                    else if (seat.get_available())                          // Check availability for remaining 3 seats                 
                    {
                        business_t1++;                                      // Increase until seat number is greater than 5
                        b_seat_numbers += seat.get_seat_number() + ", ";    // Print available business seat numbers at $500 each
                    }
                }
                else
                {
                    if ((economy_t3 < 5) && seat.get_available())           // Check if the seat number is less than 5 and is available
                    {
                        economy_t3++;                                       // Increase until seat number is greater than 5
                        e_seat_numbers += seat.get_seat_number() + ", ";    // Print available economy seat numbers at $450 each
                    }
                    else if ((economy_t2 < 10) && seat.get_available())     // Check if the seat number is less than 10 and is available
                    {
                        economy_t2++;                                       // Increase until seat number is greater than 10
                        e_seat_numbers += seat.get_seat_number() + ", ";    // Print available economy seat numbers at $300 each
                    }
                    else if (seat.get_available())                          // Check availability for remaining 10 seats
                    {
                        economy_t1++;                                       // Check availability for remaining 10 seats
                        e_seat_numbers += seat.get_seat_number() + ", ";    // Print available economy seat numbers at $200 each
                    }
                }
            }

            if (business_t2 > 0)
                available_seat_list += "business class:\n";
            if (business_t1 > 0)
                available_seat_list += business_t1 + " seat(s) at $500 each\n"; // Number of available seats in business class that cost $500
            if (business_t2 > 0)
            {
                available_seat_list += business_t2 + " seat(s) at $800 each\n"; // Number of available seats in business class that cost $800
                available_seat_list += b_seat_numbers + "\n";                   // Specific seat numbers of available seats in business class
            }

            if (economy_t3 > 0)
                available_seat_list += "economy class:\n";
            if (economy_t1 > 0)
                available_seat_list += economy_t1 + " seat(s) at $200 each\n";  // Number of available seats in economy class that cost $200
            if (economy_t2 > 0)
                available_seat_list += economy_t2 + " seat(s) at $300 each\n";  // Number of available seats in economy class that cost $300
            if (economy_t3 > 0)
            {
                available_seat_list += economy_t3 + " seat(s) at $450 each\n";  // Number of available seats in economy class that cost $450
                available_seat_list += e_seat_numbers + "\n";                   // Specific seat numbers of available seats in economy class
            }
        }
        catch (Exception e)
        {
            // If an error has occured, print it to the screen.
            e.printStackTrace();
        }
        return available_seat_list; // Print the list of all available seats in each class and price range
    }
    
    public String reserve(String ticket_class, String passenger_name, int seat_number)
    {
        String message = "";    // List output.

        try
        {
            List<Seat> list = all_seats();
  
            if ((seat_number < 1) || (seat_number > 30))            // Check if seat number is valid (from 1-30)
                return "Failed to reserve: invalid seat number";    // If seat number is invalid, print fail msg

            Seat seat = list.get(seat_number - 1);                  // Subtract 1 from seat number to account for seat number not starting at 0

            if (!seat.get_ticket_class().equals(ticket_class))      // Check if seat class is valid (economy or business)
                return "Failed to reserve: invalid seat number";    // If seat number is invalid, print fail msg

            if (!seat.get_available())                              // Check if seat number is available
                return "Failed to reserve: seat not available";     // If seat number is unavailable, print fail msg
            else
            {
                seat.set_passenger_name(passenger_name);            // Set passenger_name to the name of the passenger making the reservation 
                seat.set_available(false);               // Set the seat as unavailable for future bookings 
                message = "Successfully reserved seat #" + seat.get_seat_number() + " for passenger " + passenger_name + "\n";  // Successful reservation msg
            }
        }
        catch (Exception e)
        {
            // If an error has occured, print it to the screen.
            e.printStackTrace();
        }
        return message; // Print successful reservation msg
    }

    public String passenger_list()
    {
        String taken_seat_list = "";    // List output.

        try
        {
            List<Seat> list = all_seats();

            for (int i = 0; i < list.size(); i++)   // Cycle through the list of all seats
            {
                Seat seat = list.get(i);            // Check seat at instance (i)

                if (!seat.get_available())          // Check if seat is unavailable
                {   // Set taken_seat_list to the passenger's name who reserved it, along with the ticket class and seat number
                    taken_seat_list += seat.get_passenger_name() + " " + seat.get_ticket_class() + " " + seat.get_seat_number() + "\n";
                }
            }
        }
        catch (Exception e)
        {
            // If an error has occured, print it to the screen.
            e.printStackTrace();
        }
        return taken_seat_list; // Print the list of all taken seats along with the passenger's name, ticket class and seat number
    }

    public static void main(String[] args)  // Main function.
    {
        try
        {   // Creating an object of the interface implementation class 'rsvQuery'.
            Rsv obj = new rsvserver(initialize_list());
            // Set the port to 1900 for the server JVM (Had connection refused exception without it).
            LocateRegistry.createRegistry(1900);
            // Bind the remote object by the name 'reservation'.
            Naming.rebind("rmi://localhost:1900/reservation", obj);
        }
        catch (Exception e)
        {
            // If an error has occured, print it to the screen.
            e.printStackTrace();
        }
    }
}

class Seat 
{
    private int seat_number;        // Initializing the variable to store passenger's seat number (1-30)
    private String ticket_class;    // Initializing the variable to store passenger's ticket class (economy or business)
    private Boolean available;      // Initializing the variable to store the number of seats available
    private int ticket_price;       // Initializing the variable to store the price of the ticket
    private String passenger_name;  // Initializing the variable to store passenger's name 

    // Constructors
    public Seat(int seat_number)
    {
        this.seat_number = seat_number;
    }

    public Seat(int seat_number, String ticket_class, Boolean available, int ticket_price, String passenger_name)
    {
        this.seat_number = seat_number;
        this.ticket_class = ticket_class;
        this.available = available;
        this.ticket_price = ticket_price;
        this.passenger_name = passenger_name;
    }

    // Getter and Setter functions.

    // Seat number.
    public int get_seat_number()
    {
        return seat_number;                                 // Returns the value of seat_number
    }
    public void set_seat_number(int seat_number)
    {
        this.seat_number = seat_number;                     // Setting the value of seat_number
    }

    // Ticket class.
    public String get_ticket_class()
    {
        return ticket_class;                                // Returns the value of ticket_class
    }
    public void set_ticket_class(String ticket_class)
    {
        this.ticket_class = ticket_class;                   // Setting the value of ticket_class
    }
    
    // Availability.
    public Boolean get_available()
    {
        return available;                                   // Returns the value of available
    }
    public void set_available(Boolean available)
    {
        this.available = available;                         // Setting the value of available
    }

    // Ticket price.
    public int get_ticket_price()
    {
        return ticket_price;                                // Returns the value of ticket_price
    }
    public void set_ticket_price(int ticket_price)
    {
        this.ticket_price = ticket_price;                   // Setting the value of ticket_price
    }

    // Passenger name.
    public String get_passenger_name()
    {
        return passenger_name;                              // Returns the value of passenger_name
    }
    public void set_passenger_name(String passenger_name)
    {
        this.passenger_name = passenger_name;               // Setting the value of passenger_name
    }
}