/*
 * 
 */
package boardGamePlatform.gameInitializers;

import java.util.Map;

import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;

/**
 * Interfejs umozliwiajacy inicjalizacje planszy - implementowany przez tworce gry. 
 * Tworca gry, implementujacy interfejs jest zobowiazany do okreslenia poczatkowego modelu planszy, 
 * ktora bedzie wykorzystywana w tworzonej przez niego grze.
 */
public interface BoardInitializer {
	
	/**
	 * Zwraca plansze, na podstawie podanych w parametrze liczby graczy, dodatkowych przedmiotow
	 * oraz pionkow.
	 *
	 * @param playersCount liczba graczy dla ktorej tworzona jest plansza
	 * @param items dodatkowe przedmioty konieczne do stworzenia planszy
	 * @param pawns pionki
	 * @return zainicjalizowana plansza
	 */
	public Board initializeBoard(int playersCount, Map<String, Item> items, Map<String, Pawn> pawns);
}
