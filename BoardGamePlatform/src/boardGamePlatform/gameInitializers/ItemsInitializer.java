/*
 * 
 */
package boardGamePlatform.gameInitializers;

import java.util.Map;

import boardGamePlatform.gameResources.Item;

// TODO: Auto-generated Javadoc
/**
 * Interfejs umozliwiajacy inicjalizacje przedmiotow dodatkowych wykorzystywanych w rozgrywce - implementowany 
 * przez tworce gry. 
 * Tworca gry, implementujacy interfejs jest zobowiazany do okreslenia przedmiotow dodatkowych ktore sa niezbedne
 * do prawidlowego przebiegu gry.
 */
public interface ItemsInitializer {
	
	/**
	 * Zwraca mape przedmiotow dodatkowych na podstawie podanej w parametrze ilosci graczy.
	 *
	 * @param playersCount liczba graczy 
	 * @return mapa przedmiotow dodatkowych
	 */
	public Map<String, Item> initializeItems(int playersCount);
}
