/*
 * 
 */
package boardGamePlatform.gameInitializers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import monopolyConsolePrototype.states.StartGame;

import boardGamePlatform.game.Game;
import boardGamePlatform.game.Player;
import boardGamePlatform.game.State;
import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.game.TurnLogic;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Item;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.network.RemoteTurnHandlable;
import boardGamePlatform.network.RemoteTurnHandler;
import boardGamePlatform.platformExceptions.PlayerWithGivenNickAlreadyExistsException;

// TODO: Auto-generated Javadoc
/**
 * Klasa zajmujaca sie inicjalizacja gry, na podstawie zaimplementowanej przez tworce gry fabryki 
 * {@link boardGamePlatform.gameInitializers.GameFactory GameFactory}.
 * Dzieki informacjom dostarczonym przez tworce w implementacji fabryki zostaja tu przeprowadzone odpowiednie
 * kroki, w wyniku ktorych uzyskany zostaje gotowy obiekt {@link boardGamePlatform.game.Game Game}.
 * 
 * Pelny przebieg inicjalizacji wyglada nastepujaco:
 * 
 * 1) pierwszym krokiem inicjalizacji (metoda {@link boardGamePlatform.gameInitializers.Initializer#initialize(int) initialize}) 
 * jest stworzenie planszy, listy graczy, oraz ustawienie stanu poczatkowego. Wykorzystywana jest do tego,
 * podana w konstruktorze klasy, implementacja fabryki GameFactory, dostarczona przez tworce gry, 
 * 
 * 2) po pierwszym kroku zainicjalizowana lista graczy nie zawiera jeszcze konkretnego odwzorowania na faktycznych
 * uczestnikow rozgrywki, poniewaz obiekty {@link boardGamePlatform.game.Player Player} wykorzystywane do
 * reprezentowania graczy nie maja ustawionego nicku, oraz {@link boardGamePlatform.game.TurnHandler TurnHandlerow},
 * ktore sa niezbedne do poprawnego przeprowadzenia rozgrywki,
 * 
 * 3) w zwiazku z powyzszym kolejnym niezbednym krokiem jest dodanie graczy faktycznie bioracych udzial w rozgrywce,
 * poprzez przekazanie ich {@link boardGamePlatform.game.TurnHandler TurnHandlerow} oraz nickow.
 * 
 * @see boardGamePlatform.gameInitializers.GameFactory 
 * @see boardGamePlatform.game.Game
 */
public class Initializer implements Serializable{
	
	/** Szczegoly gry. */
	private GameDetails gameDetails;
	
	/** Inicjalizator przedmiotow dodatkowych. */
	private ItemsInitializer itemsInitializer;
	
	/** Inicjalizator pionkow. */
	private PawnsInitializer pawnsInitializer;
	
	/** Inicjalizator planszy. */
	private BoardInitializer boardInitializer;
	
	/** Inicjalizator graczy. */
	private PlayersInitializer playersInitializer;
	
	/** Lista graczy bioracych udzial w grze. */
	private List<Player> players;
	
	/** Plansza. */
	private Board board;
	
	/** Obiekt gry. */
	private Game game;
	
	/** Obiekt implementujacy StateChangable. */
	private TurnLogic turnLogic;
	
	/** Fabryka wykorzystywana do inicjalizacji. */
	private GameFactory gameFactory;
	
	/**
	 * Instancjonuje nowy inicjalizator.
	 *
	 * @param gameFactory obiekt klasy implementujacej GameFactory zaimplementowanej przez tworce gry
	 * 
	 * @see boardGamePlatform.gameInitializers.GameFactory
	 */
	public Initializer(GameFactory gameFactory) {
		this.gameFactory = gameFactory;
		
		this.gameDetails = gameFactory.createGameDetails() ;
		this.itemsInitializer = gameFactory.createItemsInitializer() ;
		this.pawnsInitializer = gameFactory.createPawnsInitializer() ;
		this.boardInitializer = gameFactory.createBoardInitializer() ;
		this.playersInitializer = gameFactory.createPlayersInitializer() ;
	}
	
	/**
	 * Metoda inicjalizujaca gre dla podanej w parametrze liczby graczy.
	 *
	 * @param playersCount liczba graczy dla ktorej ma zostac zainicjalizowana gra
	 * @return true, jezeli inicjalizacja zakonoczona powodzeniem
	 */ 
	public final boolean initialize(int playersCount) {
		if(gameDetails.ifPlayersCountPossible(playersCount)) {
			Map<String, Item> items = itemsInitializer.initializeItems(playersCount);
			Map<String, Pawn> pawns = pawnsInitializer.initializePawns(playersCount, items);
			board = boardInitializer.initializeBoard(playersCount, items, pawns);
			players = playersInitializer.initializePlayers(playersCount, items, pawns);
			
			turnLogic = new TurnLogic();
			turnLogic.setStartState(getInitialState(board, players, turnLogic));
			
			game = new Game(turnLogic);
			
			return true;
		} else 
			return false;
	} 
	
	/**
	 * Dodaje nowego gracza do listy graczy bioracych udzial w rozgrywce.
	 *
	 * @param nick nick dodawanego gracza
	 * @param currentTurnHandler obiekt RemoteTurnHandler do obslugi gracza w przypadku gdy jest jego tura
	 * @param otherTurnHandler obiekt RemoteTurnHandler do obslugi gracza w przypadku gdy jest tura przeciwnika
	 * @throws PlayerWithGivenNickAlreadyExistsException jezeli gracz o podanym nicku juz istnieje w pokoju
	 * 
	 * @see boardGamePlatform.network.RemoteTurnHandler
	 */
	public final void addNewActivePlayer(String nick, RemoteTurnHandlable currentTurnHandler, RemoteTurnHandlable otherTurnHandler) 
							throws PlayerWithGivenNickAlreadyExistsException{
		for(Player p : players) {
			if(p.getNick() == null) {
				p.setCurrentTurnHandler(currentTurnHandler);
				p.setOtherTurnHandler(otherTurnHandler);
				p.setNick(nick);
				return;
			} else if(p.getNick().equals(nick))
				throw new PlayerWithGivenNickAlreadyExistsException();
		}
	}
	
	/**
	 * Usuwa gracza z listy aktywnych graczy.
	 *
	 * @param nick nick gracza ktory ma zostac usuniety
	 */
	public final void removeActivePlayer(String nick) {
		for(Player p : players) {
			if(p.getNick() == null) {
				p.setCurrentTurnHandler(null);
				p.setOtherTurnHandler(null);
				p.setNick(null);
				return;
			}
		}
	}
	
	/**
	 * Zwraca true, jezeli liczba aktywnych graczy odpowiada liczbie dla jakiej zainicjalizowana zostala gra.
	 *
	 * @return true, jezeli liczba aktywnych graczy odpowiada liczbie dla jakiej zainicjalizowana zostala gra
	 */
	public final boolean ifAllPlayersReady() {
		for(Player p : players) {
			if(p.getNick() == null) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Zwraca inicjalizowana gre.
	 *
	 * @return inicjalizowana gra
	 */
	public final Game getGame() {
		return game;
	}
	
	/**
	 * Zwraca stan poczatkowy okreslony przez tworce gry w implementacji GameFactory podanej w konstruktorze.
	 *
	 * @param board plansza
	 * @param allPlayers lista wszystkich graczy bioracych udzial w rozgrywce
	 * @param turnLogic obiekt implementujacy StateChangable
	 * @return stan poczatkowy
	 */
	private State getInitialState(Board board, List<Player> allPlayers, TurnLogic turnLogic) {
		return gameFactory.createInitialState(board, allPlayers, turnLogic);
	}

	/**
	 * Zwraca inicjalizowana plansze.
	 *
	 * @return inicjalizowana plansza
	 * @deprecated wykorzystywane wylacznie do testow
	 */
	@Deprecated
	public Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}

	/**
	 * Zwraca inicjalizowana liste graczy.
	 *
	 * @return inicjalizowana lista graczy
	 * @deprecated wykorzystywane wylacznie do testow
	 */
	@Deprecated
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return players;
	}

	/**
	 * Zwraca inicjalizowany obiekt implementujacy StateChangable
	 *
	 * @return inicjalizowany obiekt implementujacy StateChangable
	 * @deprecated wykorzystywane wylacznie do testow
	 */
	@Deprecated
	public TurnLogic getTurnLogic() {
		// TODO Auto-generated method stub
		return turnLogic;
	}
	
}
