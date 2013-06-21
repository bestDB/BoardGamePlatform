/*
 * 
 */
package boardGamePlatform.game;

import java.util.List;
import java.util.Map;

import boardGamePlatform.gameResources.Board;


// TODO: Auto-generated Javadoc
/**
 * Klasa abstrakcyjna, ktora jest implementowana przez tworce gry. 
 * Zawiera ona informacje o akcjach ktore musza zostac wykonane w momencie gdy gra znajdzie sie w danym stanie, 
 * oraz o tym w jakim stanie znajdzie sie po wykonaniu zdefiniowanych akcji. 
 * 
 * Na potrzeby dokonania koniecznych zmian obiekty klasy przechowuja informacje o obecnym stanie planszy, 
 * graczu ktorego obecnie jest tura, oraz wszystkich graczach bioracych udzial w rozgrywce. 
 * Istnieje takze mozliwosc przekazania dodatkowych informacji koniecznych do dokonania zmian 
 * w dodatkowym parametrze attributes. 
 * 
 * Klasa ta jest wykorzystywana przez obiekt StateChangable do zarzadzania przebiegiem gry. 
 * 
 * Kazda prawidlowa implementacja gry, z wykorzystaniem platformy musi zawierac co najmniej
 * jedna implementacje klasy State - stan poczatkowy, ktory okresla co ma sie stac w momencie rozpoczecia rozgrywki.
 */
public abstract class State {
	
	/** Plansza na ktorej operuje stan. */
	protected Board board;
	
	/** Gracz ktorego obecnie jest tura. */
	protected Player currentPlayer;
	
	/** Lista wszystkich graczy bioracych udzial w rozgrywce. */
	protected List<Player> allPlayers;
	
	/** Obiekt StateChangable, na ktorym operuje stan. */
	protected StateChangable stateChangable;
	
	/** Dodatkowe parametry stanu. */
	protected Map<String, String> attributes;
	
	/**
	 * Instancjonuje nowy stan.
	 *
	 * @param board plansza na ktorej operuje stan
	 * @param currentPlayer gracz ktorego obecnie jest tura
	 * @param allPlayers lista wszystkich graczy bioracych udzial w rozgrywce
	 * @param stateChangable obiekt StateChangable na ktorym operuje stan
	 */
	public State(Board board,Player currentPlayer,List<Player> allPlayers,StateChangable stateChangable) {
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.allPlayers = allPlayers;
		this.stateChangable = stateChangable;
	}
	
	/**
	 * Instancjonuje nowy stan.
	 *
	 * @param board plansza na ktorej operuje stan
	 * @param currentPlayer gracz ktorego obecnie jest tura
	 * @param allPlayers lista wszystkich graczy bioracych udzial w rozgrywce
	 * @param stateChangable obiekt StateChangable na ktorym operuje stan
	 * @param attributes dodatkowe atrybuty stanu
	 */
	public State(Board board,Player currentPlayer,List<Player> allPlayers,StateChangable stateChangable, Map<String, String> attributes) {
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.allPlayers = allPlayers;
		this.stateChangable = stateChangable;
		this.attributes = attributes;
	}
	
	/**
	 * Zwraca gracza ktorego obecnie jest tura.
	 *
	 * @return gracz ktorego obecnie jest tura
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Zwraca liste wszystkich graczy bioracych udzial w rozgrywce.
	 *
	 * @return lista graczy bioracych udzial w rozgrywce
	 */
	public List<Player> getAllPlayers() {
		return allPlayers;
	}
	
	/**
	 * Zwraca obecny kontekst.
	 *
	 * @return obecny kontekst
	 */
	public abstract Context getContext();
	
	/**
	 * Dokonuje zmian w modelu gry (tj. w modelu planszy, graczy bioracych udzial w grze, dodatkowych przedmiotow)
	 * w zaleznosci od rezultatow wykonania tury gracza ktorego obecnie jest tura, jak i pozostalych graczy,
	 * przekazanych w parametrze.
	 *
	 * @param currTurnResult rezultat tury gracza ktorego obecnie jest tura
	 * @param otherTurnResults lista rezultatow tury pozostalych graczy
	 */
	public abstract void makeTurn(TurnResult currTurnResult,Map<Player,TurnResult> otherTurnResults);
	
	/**
	 * Zwraca true jezeli rozgrywka dobiegla konca.
	 *
	 * @return true, jezeli rozgrywka dobiegla konca
	 */
	public abstract boolean gameEnded();
}
