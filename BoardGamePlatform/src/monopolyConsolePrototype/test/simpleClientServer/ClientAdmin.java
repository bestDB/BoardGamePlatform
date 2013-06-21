/*
 * 
 */
package monopolyConsolePrototype.test.simpleClientServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import boardGamePlatform.gameInitializers.GameFactory;
import boardGamePlatform.network.Client;
import boardGamePlatform.network.GameHasEndedCallback;
import boardGamePlatform.network.Managable;
import boardGamePlatform.network.RemoteTurnHandlable;
import boardGamePlatform.network.RemoteTurnHandler;
import boardGamePlatform.network.Room;
import boardGamePlatform.network.Rooms;
import boardGamePlatform.network.RoomsManager;
import boardGamePlatform.platformExceptions.RoomMakingException;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientAdmin.
 */
public class ClientAdmin { 
	
	/**
	 * Sets the up.
	 */
	public void setUp() { 
		try { 
			RoomsManager rooms = (RoomsManager) Naming.lookup("rmi://127.0.0.1:1099/rooms");
			GameFactory gameFactory = new MonopolyGameFactory1();
			try {
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
				
				Managable room = (Managable) rooms.makeRoom("PokojCiochonia", 2, remoteCurrentTurnHandlable , remoteOtherTurnHandlable, gameHasEndedCallbackImpl, "Ciochon"); 
				Scanner scanner = new Scanner(System.in); 
				scanner.next();
				System.out.println("Lets start the game...");
				if ( room.start() ){
					System.out.println("Game starts...");
				}else
					System.out.println("Game ends...");
			 
			} catch (RoomMakingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
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
		/*ClientAdmin ciochon = new ClientAdmin();
		ciochon.setUp();
		*/
		
		Client clientAdmin = new Client("127.0.0.1", 1099, "Ciochon", new MonopolyGameFactory1());
		clientAdmin.makeRoom("PokojCiochonia");
	}
}
