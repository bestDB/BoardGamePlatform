/*
 * 
 */
package boardGamePlatform.gameInitializers;

import java.util.List;

import boardGamePlatform.game.Player;
import boardGamePlatform.game.State;
import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.game.TurnLogic;
import boardGamePlatform.gameResources.Board;

// TODO: Auto-generated Javadoc
/**
 * Fabryka gry implementowana przez tworce gry. 
 * Tworca gry, implementujacy interfejs zobowiazany jest do okreslenia w jaki sposob zainicjalizowane zostana
 * niezbedne elementy modelu rozgrywki, takie jak: przedmioty dodatkowe, pionki, plansza, 
 * gracze oraz obiekty TurnHandler zawierajace informacje o sposobach obslugi tur po stronie graczy.
 * Poza tym tworca gry okresla tutaj nazwe gry, oraz ilosci graczy dla ktorych mozliwe jest przeprowadzenie
 * rozgrywki.
 * 
 */
public interface GameFactory {
	
	/**
	 * Zwraca zainicjalizowany obiekt ItemsInitializer.
	 *
	 * @return zainicjalizowany obiekt ItemsInitializer
	 * @see boardGamePlatform.gameInitializers.ItemsInitializer
	 */
	public ItemsInitializer createItemsInitializer();
	
	/**
	 * Zwraca zainicjalizowany obieky PawnsInitializer.
	 *
	 * @return zainicjalizowany obiekt PawnsInitializer
	 * @see boardGamePlatform.gameInitializers.PawnsInitializer
	 */
	public PawnsInitializer createPawnsInitializer();
	
	/**
	 * Zwraca zainicjalizowany obiekt BoardInitializer.
	 *
	 * @return zainicjalizowany obiekt BoardInitializer
	 * @see boardGamePlatform.gameInitializers.BoardInitializer
	 */
	public BoardInitializer createBoardInitializer();
	
	/**
	 * Zwraca zainicjalizowany obiekt PlayersInitializer.
	 *
	 * @return zainicjalizowany obiekt PlayersInitializer
	 * @see boardGamePlatform.gameInitializers.PlayersInitializer
	 */
	public PlayersInitializer createPlayersInitializer();
	
	/**
	 * Zwraca zainicjalizowany obiekt TurnHandler odpowiedzialny za wykonywanie tury
	 * w przypadku gdy jest to tura gracza.
	 *
	 * @return obiekt TurnHandler odpowiedzialny za wykonywanie tury w przypadku gdy jest to tura gracza
	 * @see boardGamePlatform.game.TurnHandler
	 */
	public TurnHandler createCurrentTurnHandler();
	
	/**
	 * Zwraca zainicjalizowany obiekt TurnHandler odpowiedzialny za wykonywanie tury
	 * w przypadku gdy jest to tura innego gracza.
	 *
	 * @return TurnHandler odpowiedzialny za wykonywanie tury w przypadku gdy jest to tura innego gracza
	 * @see boardGamePlatform.game.TurnHandler
	 */
	public TurnHandler createOtherTurnHandler();
	
	/**
	 * Zwraca zainicjalizowany obiekt GameDetails.
	 *
	 * @return zainicjalizowany obiekt GameDetails
	 * @see boardGamePlatform.gameInitializers.GameDetails
	 */
	public GameDetails createGameDetails();
	
	/**
	 * Zwraca obiekt implementujacy State reprezentujacy stan poczatkowy gry.
	 *
	 * @param board model planszy na ktorej odbywa sie rozgrywka
	 * @param allPlayers lista wszystkich graczy bioracych udzial w grze
	 * @param turnLogic obiekt implementujacy StateChangable
	 * @return obiekt implementujacy State reprezentujacy stan poczatkowy gry
	 * @see boardGamePlatform.game.TurnHandler, boardGamePlatform.game.StateChangable, boardGamePlatform.game.State
	 */
	public State createInitialState(Board board, List<Player> allPlayers, TurnLogic turnLogic);
}
