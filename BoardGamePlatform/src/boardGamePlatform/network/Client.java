/*
 * 
 */
package boardGamePlatform.network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import boardGamePlatform.gameInitializers.GameFactory;
import boardGamePlatform.platformExceptions.RoomMakingException;


// TODO: Auto-generated Javadoc
/**
 * Klasa reprezentujaca klienta, laczacego sie z serwerem gry, w celu stworzenia nowego pokoju do gry, lub dolaczenia do istniejacego pokoju.
 */
public class Client implements GameHasEndedCallback{
	
	/** IP serwera. */
	private String ip;
	
	/** Port serwera. */
	private int port;
	
	/** Lista pokoi zwrocona przez serwer. */
	private RoomsManager rooms;
	
	/** Nick klienta. */
	private String nick;
	
	/** Obiekt implementujacy GameFactory dostarczony przez tworce gry. */
	private GameFactory gameFactory;
	
	/**
	 * Instancjonuje nowy obiekt klienta.
	 *
	 * @param ip IP serwera
	 * @param port port serwera
	 * @param nick nick klienta
	 * @param gameFactory obiekt implementujacy GameFactory dostarczony przez tworce gry
	 */
	public Client(String ip,int port, String nick, GameFactory gameFactory) {
		this.ip = ip;
		this.port = port;
		this.nick = nick;
		this.gameFactory = gameFactory;
	}
	
	/**
	 * Tworzy nowy pokoj na serwerze.
	 *
	 * @param roomName nazwa pokoju ktora zostanie nadana tworzonemu pokojowi
	 */
	public void makeRoom(String roomName) {
		try { 
			RoomsManager rooms = (RoomsManager) Naming.lookup("rmi://"+ip+":"+String.valueOf(port)+"/rooms");

			RemoteTurnHandler remoteCurrentTurnHandler = new RemoteTurnHandler(gameFactory.createCurrentTurnHandler());
			RemoteTurnHandlable remoteCurrentTurnHandlable = (RemoteTurnHandlable) UnicastRemoteObject.exportObject( remoteCurrentTurnHandler,0);
			
			RemoteTurnHandler remoteOtherTurnHandler = new RemoteTurnHandler(gameFactory.createOtherTurnHandler());
			RemoteTurnHandlable remoteOtherTurnHandlable = (RemoteTurnHandlable) UnicastRemoteObject.exportObject( remoteOtherTurnHandler,0);
			
			GameHasEndedCallback gameHasEndedCallbackImpl = new GameHasEndedCallback() {

				@Override
				public void signalGameHasEnded() throws RemoteException {
					// TODO Auto-generated method stub
					
			}};
				
			GameHasEndedCallback gameHasEndedCallback = (GameHasEndedCallback) UnicastRemoteObject.exportObject(gameHasEndedCallbackImpl,0);
			
			Managable room = (Managable) rooms.makeRoom(roomName, 2, remoteCurrentTurnHandlable , remoteOtherTurnHandlable, gameHasEndedCallbackImpl, nick); 
			Scanner scanner = new Scanner(System.in); 
			scanner.next();
			System.out.println("Lets start the game...");
			if ( room.start() ){
				System.out.println("Game starts...");
			}else
				System.out.println("Game ends...");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (RoomMakingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Dolacza do pierwszego dostepnego na serwerze pokoju.
	 */
	public void joinFirstAvailableRoom() {
		try {
			RoomsManager rooms = (RoomsManager) Naming.lookup("rmi://"+ip+":"+String.valueOf(port)+"/rooms");
			
			RemoteTurnHandler remoteCurrentTurnHandler = new RemoteTurnHandler(gameFactory.createCurrentTurnHandler());
			RemoteTurnHandlable remoteCurrentTurnHandlable = (RemoteTurnHandlable) UnicastRemoteObject.exportObject( remoteCurrentTurnHandler,0);
			
			RemoteTurnHandler remoteOtherTurnHandler = new RemoteTurnHandler(gameFactory.createOtherTurnHandler());
			RemoteTurnHandlable remoteOtherTurnHandlable = (RemoteTurnHandlable) UnicastRemoteObject.exportObject( remoteOtherTurnHandler,0);
			
			GameHasEndedCallback gameHasEndedCallbackImpl = new GameHasEndedCallback() {
				@Override
				public void signalGameHasEnded() throws RemoteException {
					
			}};
			
			GameHasEndedCallback gameHasEndedCallback = (GameHasEndedCallback) UnicastRemoteObject.exportObject(gameHasEndedCallbackImpl,0);
			
			 Accessible room = (Accessible) rooms.joinFirstAvailableRoom(nick,remoteCurrentTurnHandlable,
									remoteOtherTurnHandlable, gameHasEndedCallback);
			 if(room == null) {
				 System.out.println("Room doesnt exist");
			 }
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.network.GameHasEndedCallback#signalGameHasEnded()
	 */
	@Override
	public void signalGameHasEnded() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}









