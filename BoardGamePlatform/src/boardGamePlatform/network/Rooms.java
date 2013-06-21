/*
 * 
 */
package boardGamePlatform.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.gameInitializers.GameFactory;
import boardGamePlatform.gameInitializers.Initializer;
import boardGamePlatform.platformExceptions.RoomMakingException;

// TODO: Auto-generated Javadoc
/**
 * Klasa implementujaca RoomsManager. Przechowuje informacje o wszystkich pokojach zalozonych na serwerze. 
 * Umozliwia klientom odpytywanie o nie, oraz dolaczanie do wybranego. W momencie tworzenia nowego obiektu
 * okreslana jest logika gry ktora bedzie obslugiwana przez wszystkie pokoje tworzone na serwerze poprzez, przekazanie
 * w konstruktorze obiektu implementujacego GameFactory.
 */
public class Rooms implements RoomsManager {
	
	/** Pokoje ktore sa na serwerze. */
	private Map<String, Room > rooms;
	
	/** Obiekt implementujacy GameFactory */ 
	private GameFactory gameFactory ;
	
	
	/**
	 * Instancjonuje nowy obiekt Rooms.
	 *
	 * @param gameFactory obiekt implementujacy GameFactory
	 * @throws RemoteException the remote exception
	 */
	public Rooms(GameFactory gameFactory) throws RemoteException {
		rooms = new HashMap<String, Room>(); 
		this.gameFactory = gameFactory;
	}
	
	/* (non-Javadoc)
	 * @see boardGamePlatform.network.RoomsManager#makeRoom()
	 */
	@Override
	public Managable makeRoom(String roomName, int playersCount, RemoteTurnHandlable currentTurnHandler,
			RemoteTurnHandlable otherTurnHandler, GameHasEndedCallback gameHasEndedCallback, String nick) throws RemoteException,RoomMakingException {
		Room room = new Room(new Initializer(gameFactory) , playersCount, currentTurnHandler, otherTurnHandler,
				gameHasEndedCallback, nick);
		try {
			Managable roomManagable = (Managable) UnicastRemoteObject.exportObject( room , 0);
			rooms.put(roomName, room);
			return roomManagable;
		} catch(RemoteException remoteException) { 
			throw new RoomMakingException();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see boardGamePlatform.network.RoomsManager#joinFirstAvailableRoom(java.lang.String, boardGamePlatform.game.TurnHandler, boardGamePlatform.game.TurnHandler, boardGamePlatform.network.Client)
	 */
	@Override
	public Accessible joinFirstAvailableRoom(String nick, RemoteTurnHandlable currTurnHandler, RemoteTurnHandlable otherTurnHandler, GameHasEndedCallback gameHasEndedCallback) throws RemoteException {
		for (String room : rooms.keySet() ) {
			if ( rooms.get( room ).isJoinable() )
				rooms.get(room).join(nick, currTurnHandler, otherTurnHandler, gameHasEndedCallback);
				return rooms.get( room );
		}
		return null;
	}

}
