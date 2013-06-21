/*
 * 
 */
package boardGamePlatform.gameResources;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import boardGamePlatform.platformExceptions.FieldNotHasNeihgboursException;
import boardGamePlatform.platformExceptions.NotAttributeFoundException;
import boardGamePlatform.platformExceptions.NotItemFoundException;


/**
 * Klasa reprezentujaca pojedyncze pole planszy.
 */
public class Field implements Serializable{
	
	/** Identyfikator pola. */
	private int fieldID;
	
	/** Pionki znajdujace sie na polu. */
	private List<Pawn> fieldPawns;
	
	/** Dodatkowe przedmioty przypisane do pola. */
	private Map<String, Item> fieldItems;
	
	/** Pola sasiadujace. */
	private List<Field> neighbourFields;
	
	/** The attributes. */
	private Map<String, String> attributes;
	
	/**
	 * Instancjonuje nowe pole.
	 *
	 * @param fieldID identyfikator pola
	 */
	public Field(int fieldID) {
		this.fieldID = fieldID;
		fieldItems = new HashMap<String, Item>();
		neighbourFields = new LinkedList<Field>();
		fieldPawns = new LinkedList<Pawn>();
		attributes = new HashMap<String, String>();
	}
	
	
	/**
	 * Instancjonuje nowe pole.
	 *
	 * @param fieldID identyfikator pola
	 * @param pawns pionki znajdujace sie na polu
	 */
	public Field(int fieldID, List<Pawn> pawns ) {
		this.fieldID = fieldID;
		if ( pawns != null)
			this.fieldPawns = pawns;
		else
			this.fieldPawns = new LinkedList<Pawn>();
		neighbourFields = new LinkedList<Field>();
		attributes = new HashMap<String, String>();
	}
	
	/**
	 * Instancjonuje nowe pole.
	 *
	 * @param fieldID identyfikator pola
	 * @param pawns pionki znajdujace sie na polu
	 * @param fieldItems dodatkowe przedmioty znajdujace sie na polu
	 */
	public Field(int fieldID,List<Pawn> pawns ,Map<String, Item> fieldItems ) {
		this.fieldID = fieldID;
		neighbourFields = new LinkedList<Field>();
		
		if ( pawns != null)
			this.fieldPawns = pawns;
		else
			this.fieldPawns = new LinkedList<Pawn>();
		
		if ( fieldItems != null )
			this.fieldItems = fieldItems;
		else
			this.fieldItems = new HashMap<String, Item>();
		attributes = new HashMap<String, String>();
	}
	
	/**
	 * Usuwa pionek z pola.
	 *
	 * @param p pionek do usuniecia
	 * @return true, jezeli zakonczone powodzeniem
	 */
	public boolean removePawn(Pawn p) {
		return fieldPawns.remove( p );
	}
	
	/**
	 * Dodaje pionek na pole.
	 *
	 * @param p pionek do dodania
	 */
	public void addPawn( Pawn p ) {
		fieldPawns.add( p );
	}
	
	/**
	 * Ustawia pionki znajdujace sie na polu.
	 *
	 * @param pawns pionki do dodania
	 */
	public void setPawns( List<Pawn> pawns ) {
		fieldPawns = pawns;
	}
	
	/**
	 * Sprawdza czy pionek znajduje sie na polu.
	 *
	 * @param p pionek do sprawdzenia
	 * @return true, jezeli zakonczone powodzeniem
	 */
	public boolean containPawn( Pawn p ) {
		return fieldPawns.contains( p );
	}
	
	/**
	 * Zwraca pionki znajdujace sie na polu.
	 *
	 * @return lista pionkow na polu
	 */
	public List<Pawn> getFieldsPawns() {
		return fieldPawns;
	}
	
	/**
	 * Zwraca pola sasiadujace.
	 *
	 * @return lista pol sasiadujacych
	 */
	public List<Field> getNeighbourFields() {
		return neighbourFields;
	}
	
	/**
	 * Ustawia pola sasiadujace.
	 *
	 * @param neighbours lista pol sasiadujacych
	 */
	public void setNeighbourFields(List<Field> neighbours) {
		neighbourFields = neighbours;
	}
	
	/**
	 * Dodaje pole sasiadujace do listy sasiadow.
	 *
	 * @param neighbour pole ktore ma byc nowym sasiadem
	 */
	public void addNeighbourField(Field neighbour) {
		neighbourFields.add( neighbour );
	}
	
	/**
	 * Usuwa pole sasiadujace z listy sasiadow.
	 *
	 * @param field pole sasiadujace
	 * @return true, jezeli zakonczone powodzeniem
	 */
	public boolean removeNeighbourField(Field field) {
		return neighbourFields.remove( field );
	}
	
	/**
	 * Zwraca pierwsze pole z listy sasiadow.
	 *
	 * @return pierwsze pole sasiadujace
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	public Field getFirstNeighbour() throws FieldNotHasNeihgboursException{
		if(neighbourFields.size() > 0)
			return neighbourFields.get(0);
		
		throw new FieldNotHasNeihgboursException();
	}
	
	/**
	 * Zwraca ostatnie pole z listy sasiadow.
	 *
	 * @return ostatnie pole sasiadujace
	 * @throws FieldNotHasNeihgboursException the field not has neihgbours exception
	 */
	public Field getLastNeighbour() throws FieldNotHasNeihgboursException{
		if ( neighbourFields.size() == 0 )
			throw new FieldNotHasNeihgboursException();
		
		return neighbourFields.get( neighbourFields.size() - 1);
	}
	
	/**
	 * Dodaje przedmiot dodatkowy do pola.
	 *
	 * @param name nazwa przedmiotu
	 * @param item przedmiot
	 */
	public void addItem(String name, Item item) {
		fieldItems.put(name, item );
	}
	
	/**
	 * Usuwa przedmiot dodatkowy z pola.
	 *
	 * @param name nazwa przedmiotu
	 */
	public void removeItem(String name) {
		fieldItems.remove( name );
	}
	
	/**
	 * Zwraca identyfikator pola.
	 *
	 * @return ID pola
	 */
	public int getID() {
		return this.fieldID;
	}
	
	/**
	 * Zwraca pierwszego pionka znajdujacego sie na polu.
	 *
	 * @return pierwszy pionek na polu, null jezeli pole puste
	 */
	public Pawn getFirstPawn() {
		if(fieldPawns.size() > 0)
			return fieldPawns.get(0);
		return null;
	}
	
	/**
	 * Zwraca ostatniego pionka znajdujacego sie na polu.
	 *
	 * @return ostatni pionek na polu, null jezeli pole puste
	 */
	public Pawn getLastPawn() {
		if(fieldPawns.size() > 0)
			return fieldPawns.get(fieldPawns.size()-1);
		return null;
	}
	
	/**
	 * Zwraca przedmioty dodatkowe znajdujace sie na polu.
	 *
	 * @return przedmioty dodatkowe na polu
	 */
	public Map<String, Item> getItems() {
		return fieldItems;
	}
	
	/**
	 * Ustawia przedmioty dodatkowe ktore maja znalezc sie na polu.
	 *
	 * @param items przedmioty dodatkowe do dodadnia.
	 */
	public void setItems(Map<String, Item> items) {
		this.fieldItems = items;
	}

	/**
	 * Zwraca atrybuty pola.
	 *
	 * @return atrybuty pola
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * Ustawia atrybuty pola na podstawie podanego parametru.
	 *
	 * @param attributes atrybuty ktore maja zostac ustawione
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	/**
	 * Zwraca wartosc atrybutu o podanej w parametrze nazwie.
	 *
	 * @param name nazwa atrybutu
	 * @return wartosc atrybutu
	 * @throws NotAttributeFoundException jezeli atrybut o podanej nazwie nie jest przypisany do pola
	 */
	public String getAttribute(String name) throws NotAttributeFoundException{
		if(!attributes.containsKey(name))
			throw new NotAttributeFoundException();
		return attributes.get(name);
	}
	
	/**
	 * Dodaje nowy atrybut o podanych w parametrach nazwie i wartosci.
	 *
	 * @param name nazwa atrybutu
	 * @param value wartosc atrybutu
	 */
	public void addAttribute(String name, String value) {
		attributes.put(name, value);
	}
	
	/**
	 * Usuwa wszystkie przedmioty dodatkowe przypisane do pola.
	 */
	public void clearFieldItems() {
		attributes = new HashMap<String, String>();
	}
	
	/**
	 * Zwraca przedmiot dodatkowy o podanej w parametrze nazwie.
	 *
	 * @param name nazwa przedmiotu dodatkowego
	 * @return przedmiot dodatkowy
	 * @throws NotItemFoundException jezeli przedmiot dodatkowy o podanej nazwie nie jest przypisany do pola
	 */
	public Item getItem(String name) throws NotItemFoundException{
		if(!fieldItems.containsKey(name))
			throw new NotItemFoundException();
		return fieldItems.get(name);
	}
	
	/**
	 * Dodaje atrybut o podanej nazwie i wartosci.
	 *
	 * @param name nazwa atrybutu do dodania
	 * @param value wartosc atrybutu do dodania
	 */
	public void setAttribute(String name, String value) {
		attributes.put(name, value);
	}
	
	/**
	 * Zwraca tekstowa reprezentacje pola
	 *
	 * @param tabs przesuniecie reprezentacji tekstowej wzgledem poczatku linii
	 * @return tekstowa reprezentacja pola
	 */
	public String toString(String tabs) {
		String fieldState = new String();
		fieldState += tabs + "Field\n";
		fieldState += tabs + tabs + Integer.toString(fieldID) + "\n";
		fieldState += tabs + tabs + "Pawns:\n";
		for(Pawn p : fieldPawns) {
			fieldState += p.toString(tabs + "\t") + "\n";
		}
		fieldState += tabs + tabs + "EndPawns\n";
		fieldState += tabs + tabs + "Items:\n";
		for(String i : fieldItems.keySet()) {
			fieldState += tabs + i + " " + fieldItems.get(i).toString(tabs + "\t") + "\n";
		}
		fieldState += tabs + tabs + "EndItems\n";
		fieldState += tabs + tabs + "Attributes\n";
		for(String i : attributes.keySet()) {
			fieldState += tabs + i + " " + attributes.get(i) + "\n";
		}
		fieldState += tabs + "EndField";
		return fieldState;
	}
}
