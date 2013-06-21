/*
 * 
 */
package boardGamePlatform.game;

import java.io.Serializable;

/**
 * Interfejs umozliwiajacy wykonanie tury na podstawie zadanego kontekstu.
 * Klasa implementujaca musi specyfikowac w jaki sposob ma zostac wykonana tura, 
 * tj. jakie komunikaty maja pojawic sie u gracza, oraz w jaki sposob (jezeli to konieczne)
 * ma zostac pobrane wejscie.
 */
public interface TurnMakeable extends Serializable{

	/**
	 * Wykonuje ture na podstawie zadanego kontekstu.
	 *
	 * @param context kontekst
	 * @return rezultat tury
	 */
	public TurnResult makeTurn(Context context);
}
