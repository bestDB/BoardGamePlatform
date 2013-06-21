/*
 * 
 */
package boardGamePlatform.game;

import java.util.List;
import java.util.Map;

import boardGamePlatform.platformExceptions.UninitializedInitialStateException;


// TODO: Auto-generated Javadoc
/**
 * Klasa implementujaca StateChangable.
 * Pozwala na ustawienie stanu poczatkowego rozgrywki oraz przechowuje jej stan obecny.
 * Daje takze mozliwosc wykonania tury dla obecnego stanu oraz sprawdzenia czy gra dobiegla konca.
 */
public class TurnLogic implements StateChangable{
	
	/** Obecny stan rozgrywki. */
	private State currState;
	
	/**
	 * Instancjonuje nowy obiekt.
	 */
	public TurnLogic() {
		currState = null;
	}
	
	/**
	 * Ustawia stan poczatkowy rozgrywki.
	 *
	 * @param startState nowy stan poczatkowy gry
	 */
	public void setStartState(State startState) {
		currState = startState;
	}

	/* (non-Javadoc)
	 * @see boardGamePlatform.game.StateChangable#changeState(boardGamePlatform.game.State)
	 */
	@Override
	public void changeState(State tmp) {
		currState = tmp;
	}
	
	/**
	 * Zwraca obecny kontekst gry.
	 *
	 * @return obecny kontekst gry
	 */
	public Context getContext() {
		if (currState == null )
			throw new UninitializedInitialStateException();
		return currState.getContext();
	}
	
	/**
	 * Zwraca gracza ktorego obecnie jest tura.
	 *
	 * @return gracz ktorego obecnie jest tura
	 */
	public Player getCurrentPlayer() {
		if (currState == null )
			throw new UninitializedInitialStateException();
		return currState.getCurrentPlayer();
	}
	
	/**
	 * Zwraca liste wszystkich graczy bioracych udzial w grze.
	 *
	 * @return lista wszystkich graczy bioracych udzial w grze
	 */
	public List<Player> getAllPlayers() {
		if (currState == null )
			throw new UninitializedInitialStateException();
		return currState.getAllPlayers();
	}
	
	/**
	 * Dokonuje zmian w modelu gry (tj. w modelu planszy, graczy bioracych udzial w grze, dodatkowych przedmiotow)
	 * w zaleznosci od rezultatow wykonania tury gracza ktorego obecnie jest tura, jak i pozostalych graczy,
	 * przekazanych w parametrze.
	 *
	 * @param currTurnResult rezultat tury gracza ktorego obecnie jest tura
	 * @param otherTurnResults lista rezultatow tury pozostalych graczy
	 */
	public void makeTurn(TurnResult currTurnResult,Map<Player,TurnResult> otherTurnResults) {
		if (currState == null )
			throw new UninitializedInitialStateException();
		currState.makeTurn(currTurnResult, otherTurnResults);
	}
	
	/**
	 * Zwraca true, jezeli rozgrywka dobiegla konca.
	 *
	 * @return true, jezeli rozgrywka dobiegla konca
	 */
	public boolean gameEnded() {
		if (currState == null )
			throw new UninitializedInitialStateException();
		return currState.gameEnded();
	}
}
