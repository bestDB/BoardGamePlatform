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

import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.gameInitializers.GameFactory;
import boardGamePlatform.network.Accessible;
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
 * The Class ClientPlayer.
 */
public class ClientPlayer {
	
	/**
	 * Sets the up.
	 */
	public void setUp() {
		try {
			RoomsManager rooms = (RoomsManager) Naming.lookup("rmi://127.0.0.1:1099/rooms");
			GameFactory gameFactory = new MonopolyGameFactory1(); 
			
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
			
			 Accessible room = (Accessible) rooms.joinFirstAvailableRoom("Listkiewicz",remoteCurrentTurnHandlable,
									remoteOtherTurnHandlable, gameHasEndedCallback);
			 if(room == null) {
				 System.out.println("Room doesnt exist");
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
		/*ClientPlayer twojaStara = new ClientPlayer();
		twojaStara.setUp();
		*/
		
		Client clientPlayer = new Client("127.0.0.1", 1099, "Listkiewicz", new MonopolyGameFactory1());
		clientPlayer.joinFirstAvailableRoom();
		
	}
}
