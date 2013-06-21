/*
 * 
 */
package boardGamePlatform.gameInitializers;

// TODO: Auto-generated Javadoc
/**
 * Interfejs umozliwiajacy okreslenie szczegolow gry, implementowany przez tworce gry. 
 * Tworca gry, implementujacy intefejs jest zobowiazany do okreslenia nazwy gry oraz ilosci graczy 
 * dla ktorych mozliwe jest przeprowadzenie rozgrywki.
 */
public interface GameDetails {
	
	/**
	 * Zwraca true, jezeli mozliwe jest przeprowadzenie rozgrywki dla podanej liczby graczy.
	 *
	 * @param playersCount liczba graczy
	 * @return true, jezeli mozliwe jest przeprowadzenie rozgrywki dla liczby graczy podanej w parametrze
	 */
	public boolean ifPlayersCountPossible( int playersCount);	
	
	/**
	 * Zwraca nazwe gry.
	 *
	 * @return nazwa gry
	 */
	public String getGameName();

}
