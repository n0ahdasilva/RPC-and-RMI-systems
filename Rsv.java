/**
 *  PROJECT : RPC and RMI Systems
 * 
 *  FILENAME : Reservation.java
 * 
 *  DESCRIPTION :
 *      Compute engine's interface that will provide the description of the methods
 *      that can be invoked by remote clients. Enables tasks from remote clients to be
 *      submitted to the engine, which are executed and returned to submitter(s).
 * 
 *  FUNCTIONS :
 *      ...
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
import java.util.List;


public interface Rsv extends Remote
{
    // Declaring the method prototypes.
    public String list() throws RemoteException;
    public String reserve() throws RemoteException;
    public String passenger_list() throws RemoteException;

    Seat find_seat(Seat n) throws RemoteException;
    List<Seat> all_seats() throws RemoteException;
}
