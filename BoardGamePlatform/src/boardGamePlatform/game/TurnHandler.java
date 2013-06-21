/*
 * 
 */
package boardGamePlatform.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import boardGamePlatform.platformExceptions.NotSupportedContextException;


// TODO: Auto-generated Javadoc
/**
 * Klasa zajmujaca sie przechowywaniem odwzorowan nazw kontekstow na obiekty zajmujace sie obsluga tur, ktorych one dotycza, 
 * oraz oddelegowywaniem wykonania tury do odpowiednich obiektow implementujacych TurnMakeable. 
 * Pozwala na ustawienie domyslnego obiektu zajmujacego sie obsluga tur.
 */
public class TurnHandler implements Serializable{
	
	/** Obiekty implementujace TurnMakeable zajmujace sie wykonaniem tury, rozroznialne na podstawie nazwy kontekstu ktory obsluguja. */
	private Map<String,TurnMakeable> turnMakers;
	
	/** Flaga informuja o tym, czy ustawiony zostal domyslny obiekt do obslugi tur. */
	private boolean isDefaultTurnMaker;
	
	/** Domyslny obiekt do obslugi tur. */
	private TurnMakeable defaultTurnMaker;
	
	/**
	 * Instancjonuje nowy obiekt klasy.
	 */
	public TurnHandler() {
		turnMakers = new HashMap<String,TurnMakeable>();
		isDefaultTurnMaker = false;
		defaultTurnMaker = null;
	}
	
	/**
	 * Instancjonuje nowy obiekt klasy z domyslnym, podanym w parametrze obiektem do obslugi tur.
	 *
	 * @param defaultTurnMaker domyslny obiekt do obslugi tur
	 */
	public TurnHandler(TurnMakeable defaultTurnMaker) {
		turnMakers = new HashMap<String,TurnMakeable>();
		isDefaultTurnMaker = true;
		this.defaultTurnMaker = defaultTurnMaker;
	}

	/**
	 * Dodaje nowy obiekt implementujacy TurnMakeable sluzacy do obslugi tur dla kontekstu o nazwie
	 * podanej w parametrze.
	 *
	 * @param name nazwa kontekstu ktorego dotyczy obiekt podany w parametrze turnMakeable
	 * @param turnMakeable obiekt implementujacy TurnMakeable
	 */
	public void setTurnMaker(String name,TurnMakeable turnMakeable) {
		turnMakers.put(name, turnMakeable);
	}
	
	/**
	 * Ustawia domyslny obiekt implementujacy TurnMakeable sluzacy do obslugi tur.
	 *
	 * @param defaultTurnMaker nowy domyslny obiekt do obslugi tur
	 */
	public void setDefaultTurnMaker(TurnMakeable defaultTurnMaker) {
		isDefaultTurnMaker = true;
		this.defaultTurnMaker = defaultTurnMaker;
	}
	

	/**
	 * Zwraca rezultat tury w zaleznosci od podanego kontekstu. 
	 * 
	 * Jezeli nie zostal okreslony obiekt
	 * do obslugi tury dla podanego kontekstu, oraz nie zostal ustawiony domyslny obiekt do obslugi tur
	 * rzucony zostaje wyjatek czasu wykonania NotSupportedContextException. 
	 * 
	 * Jezeli obiekt do obslugi tury dla podanego kontekstu nie zostal ustawiony, ale zostal okreslony
	 * domyslny obiekt do obslugi tury, to zostaje uzyty domyslny obiekt obslugujacy ture.
	 *
	 * @param context kontekst dla ktorego ma zostac wykonana tura
	 * @return rezultat wykonania tury
	 */
	public TurnResult makeTurn(Context context) {
		TurnMakeable turnMakeable = turnMakers.get( context.getName() );
		if(turnMakeable == null && isDefaultTurnMaker ) {
			return defaultTurnMaker.makeTurn(context);
		} else if(turnMakeable == null && !isDefaultTurnMaker)
			throw new NotSupportedContextException( context.getName() );
		else 			
			return turnMakeable.makeTurn(context);
			
	}
}
