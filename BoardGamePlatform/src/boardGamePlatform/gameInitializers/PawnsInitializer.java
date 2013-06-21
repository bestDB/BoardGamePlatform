/*
 * 
 */
package boardGamePlatform.gameInitializers;

import java.util.Map;

import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;

// TODO: Auto-generated Javadoc
/**
 * Interfejs umozliwiajacy inicjalizacje pionkow wykorzystywanych w rozgrywce - implementowany 
 * przez tworce gry. 
 * Tworca gry, implementujacy interfejs jest zobowiazany do okreslenia pionkow, ktore beda braly udzial w grze.
 */
public interface PawnsInitializer {
	
	/**
	 * Zwraca mape pionkow, na podstawie podanych w parametrze liczby graczy, oraz dodatkowych przedmiotow.
	 *
	 * @param playerCount the player count
	 * @param items the items
	 * @return the map
	 */
	public Map<String, Pawn> initializePawns(int playerCount, Map<String, Item> items);
}
