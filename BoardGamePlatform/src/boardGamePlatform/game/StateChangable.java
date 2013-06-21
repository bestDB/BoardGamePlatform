/*
 * 
 */
package boardGamePlatform.game;

// TODO: Auto-generated Javadoc
/**
 * Interfejs reprezentujacy obiekt ktorego stan moze byc zmieniany.  
 */
public interface StateChangable {
	
	/**
	 * Zmienia obecny stan obiektu.
	 *
	 * @param newState nowy stan obiektu
	 */
	public void changeState(State newState);
}
