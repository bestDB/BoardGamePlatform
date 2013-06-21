/*
 * 
 */
package boardGamePlatform.gameInitializers;

import java.util.List;
import java.util.Map;

import boardGamePlatform.game.Player;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;

// TODO: Auto-generated Javadoc
/**
 * Interfejs umozliwiajacy inicjalizacje listy graczy bioracych udzial w rozgrywce - implementowany 
 * przez tworce gry. 
 * Tworca gry, implementujacy interfejs jest zobowiazany do okreslenia szczegolowych parametrow graczy,
 * ktorzy beda brali udzial w grze.
 */
public interface PlayersInitializer {
	
	/**
	 * Zwraca liste graczy, na podstawie podanych w parametrze liczby graczy, dodatkowych przedmiotow
	 * oraz pionkow.
	 *
	 * @param playersCount the players count
	 * @param items the items
	 * @param pawns the pawns
	 * @return the list
	 */
	public List<Player> initializePlayers(int playersCount, Map<String, Item> items, Map<String, Pawn> pawns); 
}
