/*
 * 
 */
package boardGamePlatform.gameResources;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Klasa reprezentujaca pionek.
 */
public class Pawn implements Serializable{
	
	/** Identyfikator pionka. */
	private int pawnID;
	
	/** Dodatkowe przedmioty pionka. */
	private Map<String, Item> pawnItems;
	
	/**
	 * Instancjonuje nowy pionek.
	 *
	 * @param pawnID identyfikator pionka
	 */
	public Pawn(int pawnID ) {
		this.setPawnID(pawnID);
		pawnItems = new HashMap<String, Item>();
	}
	
	/**
	 * Instancjonuje nowy pionek.
	 *
	 * @param pawnID identyfikator pionka
	 * @param pawnItems przedmioty dodatkowe pionka
	 */
	public Pawn( int pawnID, Map<String, Item> pawnItems ) {
		this.setPawnID(pawnID);
		this.pawnItems = pawnItems;
	}
	
	/**
	 * Dodaje nowy przedmiot dodatkowy do pionka.
	 *
	 * @param name nazwa przedmiotu dodatkowego
	 * @param item przedmiot dodatkowy
	 */
	public void addItem(String name, Item item) {
		pawnItems.put( name, item );
	}
	
	/**
	 * Usuwa przedmiot dodatkowy.
	 *
	 * @param name nazwa przedmiotu dodatkowego
	 */
	public void removeItem( String name ) {
		pawnItems.remove( name ) ;
	}
	
	/**
	 * Przypisuje przedmioty dodatkowe do pionka.
	 *
	 * @param items przedmioty dodatkowe
	 */
	public void setItems(Map<String, Item> items) {
		this.pawnItems = items;
	}
	
	/**
	 * Zwraca przedmioty dodatkowe przypisane do pionka.
	 *
	 * @return przedmioty dodatkowe pionka
	 */
	public Map<String, Item> getItems() {
		return pawnItems;
	}

	/**
	 * Zwraca identyfikator pionka.
	 *
	 * @return ID pionka
	 */
	public int getPawnID() {
		return pawnID;
	}

	/**
	 * Ustawia identyfikator pionka.
	 *
	 * @param pawnID nowe ID pionka
	 */
	public void setPawnID(int pawnID) {
		this.pawnID = pawnID;
	}
	
	/**
	 * Zwraca tekstowa reprezentacje pionka.
	 *
	 * @param tabs przesuniecie reprezentacji tekstowej wzgledem poczatku linii
	 * @return tekstowa reprezentacja pionka
	 */
	public String toString(String tabs) {
		String pawnState = new String();
		pawnState += tabs + "Pawn\n";
		pawnState += tabs + tabs + Integer.toString(pawnID) + "\n";
		pawnState += tabs +tabs + "Items:\n";
		for(String i : pawnItems.keySet()) {
			pawnState += tabs + i + " " + pawnItems.get(i).toString() + "\n";
		}
		pawnState += tabs +tabs + "EndItems\n";
		pawnState += tabs + "EndPawn";
		return pawnState;
	}
}
