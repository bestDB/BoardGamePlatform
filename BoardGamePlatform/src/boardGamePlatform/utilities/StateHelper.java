/*
 * 
 */
package boardGamePlatform.utilities;

import java.util.List;

import boardGamePlatform.game.Player;
import boardGamePlatform.platformExceptions.NotPlayerExistsException;


// TODO: Auto-generated Javadoc
/**
 * Klasa ulatwiajaca operacje na stanach.
 */
public class StateHelper {
	
	/**
	 * Zwraca nastepnego gracza z listy graczy.
	 *
	 * @param currentPlayer obecny gracz
	 * @param allPlayers lita wszystkich graczy
	 * @return nastepnik obecnego gracza na liscie graczy
	 */
	public static Player getNextPlayer(Player currentPlayer, List<Player> allPlayers) {
		return allPlayers.get( (allPlayers.indexOf(currentPlayer) + 1) % allPlayers.size() );
	}
	
	/**
	 * Zwraca gracza o podanym identyfikatorze.
	 *
	 * @param id ID gracza
	 * @param players lista wszystkich graczy
	 * @return gracz o identyfikatorze == id
	 * @throws NotPlayerExistsException jezeli gracz o podanym ID nie istnieje
	 */
	public static Player getPlayerByID(int id, List<Player> players) throws NotPlayerExistsException{
		for(Player p : players) {
			if(p.getPlayerID() == id)
				return p;
		}
		throw new NotPlayerExistsException();
	}
}
