/**
 *  PROJECT : RPC and RMI Systems
 * 
 *  FILENAME : rsvclient.java
 * 
 *  DESCRIPTION :
 *      Client-server network application using the Remote Method Invocation
 *      (RMI) system.
 * 
 *  FUNCTIONS :
 *      rsvclient.rsvclient()
 *      rsvclient.main()
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

import java.rmi.*;      // Provides remote communication between Java programs

public class rsvclient  // Create rsvclient class
{  
    public static void main(String[] args) // main() function
    {
        try
        {   // Check if the client entered any arguments.
            if (!(args.length > 0))
            {   // The client program ends, informing the client on the syntax error.
                System.out.println("Invalid numbers of arguments.");
                return;
            }
            // Check if the client wants to list all available seats.
            if (args[0].equals("list"))
            {   // Verify that there is all 2 arguments.
                if (args.length != 2)
                {   // The client program ends, informing the client on the syntax error.
                    System.out.println("Invalid number of arguments for command 'list'.");
                    return;
                }
                // Lookup method to find the reference of the remote object.
                Rsv r_obj = (Rsv) Naming.lookup("rmi://" + args[1] + ":1900/reservation");
                System.out.println(r_obj.list());   // Print list of available seats
            }
            // If the client wants to reserve/book seat(s).
            else if (args[0].equals("reserve"))
            {   // Verify that there is all 5 arguments.
                if (args.length != 5)
                {   // The client program ends, informing the client on the syntax error.
                    System.out.println("Invalid number of arguments for command 'reserve'.");
                    return;
                }
                // Lookup method to find the reference of the remote object.
                Rsv r_obj = (Rsv) Naming.lookup("rmi://" + args[1] + ":1900/reservation");
                System.out.println(r_obj.reserve(args[2], args[3], Integer.valueOf(args[4])));
            }
            // If the clients wants to list the reserved/booked seat(s).
            else if (args[0].equals("passenger_list"))
            {   // Verify that there is all 2 arguments.
                if (args.length != 2)
                {   // The client program ends, informing the client on the syntax error.
                    System.out.println("Invalid number of arguments for command 'passenger_list'.");
                    return;
                }
                // Lookup method to find the reference of the remote object.
                Rsv r_obj = (Rsv) Naming.lookup("rmi://" + args[1] + ":1900/reservation");
                System.out.println(r_obj.passenger_list()); // Print list of reserved/booked seats
            }
            // If the client did not enter a valid command.
            else
            {
                System.out.println("The command '" + args[0] + "' is invalid.");
            }
        }
        catch (Exception e)
        {   // If an error has occured, print it to the screen.
            e.printStackTrace();
        }
    }
}