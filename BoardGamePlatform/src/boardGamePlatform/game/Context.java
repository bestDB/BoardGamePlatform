/*
 * 
 */
package boardGamePlatform.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boardGamePlatform.gameResources.Board;


// TODO: Auto-generated Javadoc
/**
 * Klasa reprezentujaca obecny stanu gry przekazywany do wszystkich graczy. Obiekty sa 
 * rozroznialne na podstawie nazwy. Zawiera obecna plansze, informacje o graczu aktualnie 
 * wykonujacym ruch oraz o wszystkich graczach bioracych udzial w rozgrywce. Pozwala takze na przechowywanie
 * dodatkowych atrybutow reprezentowanych za pomoca lancucha znakow oraz rozroznialnych 
 * przez nazwe. Zawiera odpowiednie mechanizmy dostepu do wszystkich informacji.
 */
public class Context implements Serializable{
	
	/** Nazwa kontekstu. */
	private String name;
	
	/** Plansza. */
	private Board board;
	
	/** Gracz aktualnie wykonujacy ruch. */
	private Player currPlayer;
	
	/** Lista wszystkich graczy bioracych udzial w rozgrywce. */
	private List<Player> allPlayers;
	
	/** Dodatkowe atrybuty. */
	private Map<String,String> attributes;
	
	/**
	 * Instancjonuje nowy kontekst.
	 *
	 * @param name nazwa kontekstu
	 * @param board plansza
	 * @param currPlayer gracz aktualnie wykonujacy ruch
	 * @param allPlayers lista wszystkich graczy
	 */
	public Context(String name,Board board,Player currPlayer , List<Player> allPlayers) {
		this.name = name;
		this.board = board;
		this.currPlayer = currPlayer;
		this.allPlayers = allPlayers;
		attributes = new HashMap<String,String>();
	}
	
	/**
	 * Instancjonuje nowy kontekst.
	 *
	 * @param name nazwa kontekstu
	 * @param board plansza
	 * @param currPlayer gracz aktualnie wykonujacy ruch
	 * @param allPlayers lista wszystkich graczy
	 * @param attributes dodatkowe atrybuty charakterystyczne dla kontekstu
	 */
	public Context(String name,Board board,Player currPlayer , List<Player> allPlayers, Map<String, String> attributes) {
		this.name = name;
		this.board = board;
		this.currPlayer = currPlayer;
		this.allPlayers = allPlayers;
		this.attributes = attributes;
	}

	/**
	 * Zwraca nazwe kontekstu.
	 *
	 * @return nazwa kontekstu
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Dodaje nowy atrybut kontekstu.
	 *
	 * @param name nazwa atrybutu
	 * @param value wartosc atrybutu
	 */
	public void addAttribute(String name,String value) {
		attributes.put(name, value);
	}
	
	/**
	 * Zwraca wartosc atrybutu o podanej nazwie.
	 *
	 * @param name nazwa atrybutu
	 * @return wartosc podanego atrybutu
	 */
	public String getAttribute(String name) {
		return attributes.get( name );
	}
	
	/**
	 * Przypisuje atrybuty do kontekstu.
	 *
	 * @param attributes mapa atrybutow do przypisania
	 */
	public void setAttributes(Map<String,String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Zwraca gracza, ktorego obecnie jest tura.
	 *
	 * @return gracz ktorego obecnie jest tura
	 */
	public Player getCurrPlayer() {
		return currPlayer;
	}

	/**
	 * Zwraca plansze ktorej dotyczy kontekst.
	 *
	 * @return plansza ktorej dotyczy kontekst
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Zwraca liste wszystkich graczy ktorych dotyczy kontekst.
	 *
	 * @return lista wszystkich graczy ktorych dotyczy kontekst
	 */
	public List<Player> getAllPlayers() {
		return allPlayers;
	}
}
