/*
 * 
 */
package boardGamePlatform.gameResources;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import boardGamePlatform.platformExceptions.NotAttributeFoundException;
import boardGamePlatform.platformExceptions.NotItemFoundException;


// TODO: Auto-generated Javadoc
/**
 * Klasa reprezentujaca dodatkowe przedmioty niezbedne do przebiegu rozgrywki.
 */
public class Item implements Serializable{
	
	/** Identyfikator przedmiotu. */
	private int itemID;
	
	/** Nazwa przedmiotu. */
	private String name;
	
	/** Dodatkowe atrybuty przedmiotu. */
	private Map<String,String> attributes;
	
	/**
	 * Instancjonuje nowy przedmiot dodatkowy.
	 *
	 * @param itemID ID przedmiotu
	 */
	public Item(int itemID) {
		this.setItemID(itemID);
		this.setName(null);
		attributes = new HashMap<String,String>();
	}
	
	/**
	 * Instancjonuje nowy przedmiot dodatkowy.
	 *
	 * @param itemID ID przedmiotu
	 * @param name nazwa atrybutu jaki ma zostac dodany
	 * @param value wartosc dodawanego atrybutu
	 */
	public Item(int itemID, String name , String value) {
		this(itemID);
		attributes.put(name, value);
	}
	
	/**
	 * Instancjonuje nowy przedmiot dodatkowy.
	 *
	 * @param itemID ID przedmiotu
	 * @param name nazwa przedmiotu
	 */
	public Item(int itemID, String name ) {
		this.setItemID(itemID);
		this.setName(name);
		attributes = new HashMap<String,String>();
	}
	
	/**
	 * Instancjonuje nowy przedmiot dodatkowy.
	 *
	 * @param itemID ID przedmiotu
	 * @param name nazwa przedmiotu
	 * @param attributes atrybuty dodatkowe
	 */
	public Item(int itemID, String name, Map<String, String> attributes){
		this(itemID, name);
		this.attributes = attributes;
	}
	
	/**
	 * Dodaje atrybut przedmiotu.
	 *
	 * @param name nazwa atrybutu
	 * @param value wartosc atrybutu
	 * @return ostatnia wartosc ktora byla przypisana od danej nazwy, lub null jezeli nie bylo zadnej
	 */
	public String setAttribute(String name,String value) {
		return attributes.put(name, value);
	}
	
	/**
	 * Sprawdza czy atrybut o podanej nazwie jest przypisany do pola.
	 *
	 * @param name nazwa atrybutu
	 * @return true, jezeli istnieje
	 */
	public boolean containsAttribute(String name) {
		return attributes.containsKey( name );
	}
	
	/**
	 * Zwraca wartosc atrybutu o podanej nazwie.
	 *
	 * @param name nazwa atrybutu
	 * @return wartosc atrybutu
	 * @throws NotAttributeFoundException jezeli atrybut o podanej nazwie nie jest przypisany o pola
	 */
	public String getAttribute(String name) throws NotAttributeFoundException{
		if(!attributes.containsKey(name))
			throw new NotAttributeFoundException();
		return attributes.get( name );
	}
	
	/**
	 * Ustawia atrybuty ktore maja byc przypisane do przedmiotu.
	 *
	 * @param attrs atrybuty do dodania
	 */
	public void setAttributes(Map<String, String> attrs) {
		this.attributes = attrs;
	}
	
	/**
	 * Zwraca atrybuty przypisane od przedmiotu.
	 *
	 * @return przypisane atrybuty
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * Zwraca identyfikator przedmiotu.
	 *
	 * @return ID przedmiotu
	 */
	public int getID() {
		return itemID;
	}

	/**
	 * Ustawia identyfikator przedmiotu.
	 *
	 * @param itemID ID przedmiotu
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	/**
	 * Zwraca nazwe przedmiotu.
	 *
	 * @return nazwa przedmiotu
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ustawia nazwe przedmiotu.
	 *
	 * @param name nowa nazwa przedmiotu
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Zwraca tekstowa reprezentacje przedmiotu dodatkowego.
	 *
	 * @param tabs przesuniecie reprezentacji tekstowej wzgledem poczatku linii
	 * @return tekstowa reprezentacja przedmiotu dodatkowego
	 */
	public String toString(String tabs) {
		String itemState = new String();
		itemState += tabs + "Item\n";
		itemState += tabs + tabs + Integer.toString(itemID) + "\n" + name + "\n";
		itemState += tabs + tabs +  "Attributes:\n";
		for(String i : attributes.keySet()) {
			itemState += tabs +  attributes.get(i) + "\n";
		}
		itemState += tabs +  tabs +  "EndAttributes\n";
		itemState += tabs + tabs +  "EndItem";
		return itemState;
	}
}
