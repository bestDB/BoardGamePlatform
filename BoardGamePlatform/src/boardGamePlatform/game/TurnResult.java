/*
 * 
 */
package boardGamePlatform.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import boardGamePlatform.platformExceptions.NotAttributeFoundException;

// TODO: Auto-generated Javadoc
/**
 * Klasa przechowujaca rezultat tury za pomoca mapy nazw akcji oraz ich wartosci przechowywanych jako lancuchy znakowe.
 */
public class TurnResult implements Serializable{
	
	/** Rezultat tury. */
	private Map<String,String> action;
	
	/**
	 * Instancjonuje nowy obiekt reprezentujacy rezultat tury.
	 */
	public TurnResult() {
		action = new HashMap<String,String>();
	}
	
	/**
	 * Instancjonuje nowy obiekt reprezentujacy rezultat tury zawierajacy jedna akcje i jej wartosc.
	 *
	 * @param name nazwa akcji
	 * @param value rezultat
	 */
	public TurnResult(String name,String value) {
		action = new HashMap<String,String>();
		action.put(name, value);
	}
	
	/**
	 * Instancjonuje nowy obiekt reprezentujacy rezultat tury.
	 *
	 * @param action mapa akcji i ich wartosci wykonanych w czasie tury
	 */
	public TurnResult(Map<String,String> action) {
		this.action = action;
	}
	
	/**
	 * Ustawia akcje jakie zostaly wykonany w czasie tury.
	 *
	 * @param action mapa akcji i ich wartosci wykonanych w czasie tury
	 */
	public void setProperties( Map<String,String> action ) {
		this.action = action;
	}
	
	/**
	 * Zwraca wartosc akcji o zadanej nazwie.
	 *
	 * @param name nazwa akcji
	 * @return the wartosc akcji
	 * @throws NotAttributeFoundException the not attribute found exception
	 */
	public String getProperty(String name) throws NotAttributeFoundException{
		if(!action.containsKey(name))
			throw new NotAttributeFoundException();
		return action.get( name );
	}
	
	/**
	 * Dodaje akcje o podanej nazwie i rezultacie do rezultatow tury.
	 *
	 * @param name nazwa akcji
	 * @param value rezultat akcji
	 */
	public void setProperty(  String name , String value ) {
		action.put(name , value) ;
	}
	
	
}
