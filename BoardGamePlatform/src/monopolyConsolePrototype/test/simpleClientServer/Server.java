/*
 * 
 */
package monopolyConsolePrototype.test.simpleClientServer;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import boardGamePlatform.network.Rooms;
import boardGamePlatform.network.RoomsManager;

// TODO: Auto-generated Javadoc
/**
 * The Class Server.
 */
public class Server {
	
	/**
	 * Sets the up.
	 *
	 * @throws RemoteException the remote exception
	 */
	public void setUp() throws RemoteException {
		Rooms rooms = new Rooms(new MonopolyGameFactory1());
		
		RoomsManager roomsManager = (RoomsManager) UnicastRemoteObject.exportObject(rooms, 0);
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://127.0.0.1:1099/rooms", roomsManager);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	 
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		/*Server srv = new Server();
		try {
			srv.setUp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		boardGamePlatform.network.Server srv = new boardGamePlatform.network.Server("127.0.0.1", 1099, new MonopolyGameFactory1());
		
	}

}
