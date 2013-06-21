/*
 * 
 */
package boardGamePlatform.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.gameInitializers.Initializer;
import boardGamePlatform.platformExceptions.PlayerNotRespondException;
import boardGamePlatform.platformExceptions.PlayerWithGivenNickAlreadyExistsException;

// TODO: Auto-generated Javadoc
/**
 * Klasa reprezentujaca pojedynczy pokoj na serwerze, w ktorym odbywa sie gra.
 */
public class Room implements Accessible, Managable{
	
	/** Obiekt Initializer zawierajacy informacje o grze ktora ma byc obslugiwana w pokoju. */ 
	private Initializer initializer; 
	

	/**
	 * Instancjonuje nowy pokoj.
	 *
	 * @param initializer obiekt Initializer zawierajacy informacje o grze ktora ma byc obslugiwana w pokoju
	 * @param playersCount liczba graczy dla ktorych inicjowana jest gra
	 * @param currentTurnHandler obiekt RemoteTurnHandler zalozyciela pokoju do obslugi tury gracza
	 * @param otherTurnHandler obiekt RemoteTurnHandler zalozyciela pokoju do obslugi tury innego gracza
	 * @param gameHasEndedCallback callback do sygnalizowania zalozyciela pokoju o zakonczeniu gry
	 * @param nick nick wlasciciela pokoju
	 * @throws RemoteException the remote exception
	 */
	public Room(Initializer initializer, int playersCount, RemoteTurnHandlable currentTurnHandler,
			RemoteTurnHandlable otherTurnHandler, GameHasEndedCallback gameHasEndedCallback, String nick) throws RemoteException {
		this.initializer = initializer;
		initializer.initialize(playersCount) ;
		join(nick, currentTurnHandler, otherTurnHandler, gameHasEndedCallback);
	}

	/* (non-Javadoc) 
	 * @see boardGamePlatform.network.Accessible#join(java.lang.String, boardGamePlatform.game.TurnHandler, boardGamePlatform.game.TurnHandler, boardGamePlatform.network.GameHasEndedCallback)
	 */
	@Override
	public boolean join(String nick, RemoteTurnHandlable currTurnHandler,
			RemoteTurnHandlable otherTurnHandler, GameHasEndedCallback callback)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			if ( isJoinable() ) 
				initializer.addNewActivePlayer(nick, currTurnHandler, otherTurnHandler);
			else return false;
		} catch (PlayerWithGivenNickAlreadyExistsException e) {
			return false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.network.Accessible#leave(java.lang.String)
	 */
	@Override
	public void leave(String nick) throws RemoteException {
		// TODO Auto-generated method stub
		initializer.removeActivePlayer(nick);
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.network.Accessible#isJoinable()
	 */
	@Override
	public boolean isJoinable() throws RemoteException{
		// TODO Auto-generated method stub
		return !initializer.ifAllPlayersReady() ;
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.network.Managable#start()
	 */
	@Override
	public boolean start() throws RemoteException {
		if ( initializer.ifAllPlayersReady() ) {
			/*
			 * @TODO Przemysl sposob reakcji na bledy ze strony gracza
			 */
			try {
				initializer.getGame().start();
			} catch (PlayerNotRespondException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else return false;
	}

}
