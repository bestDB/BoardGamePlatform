package boardGamePlatform.network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import boardGamePlatform.gameInitializers.GameFactory;


// TODO: Auto-generated Javadoc
/**
 * Klasa reprezentujaca glowny serwer gry. Przechowuje on liste pokoi, w ktorych odbywa sie gra,
 * lub ktore czekaja na dolaczenia odpowiedniej liczby graczy. Odpowiada takze za udostepnianie
 * listy klientom ktorzy tego zazadaja.
 */
public class Server {
	
	/** IP serwera */
	String ip;
	
	/** Port na ktorym nasluchuje serwer. */
	int port;
	
	/** Obiekt implementujacy GameFactory*/
	GameFactory gameFactory;
	
	/**
	 * Instancjonuje nowy serwer.
	 *
	 * @param ip IP serwera
	 * @param port port na ktorym nasluchuje serwer
	 * @param gameFactory obiekt implementujacy GameFactory
	 */
	public Server(String ip, int port, GameFactory gameFactory) {
		this.ip = ip;
		this.port = port;
		this.gameFactory = gameFactory;
		
		Rooms rooms;
		try {
			
			rooms = new Rooms(gameFactory);
			RoomsManager roomsManager = (RoomsManager) UnicastRemoteObject.exportObject(rooms, 0); 
			
			Registry registry = LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://"+":"+String.valueOf(port)+"/rooms", roomsManager);
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
