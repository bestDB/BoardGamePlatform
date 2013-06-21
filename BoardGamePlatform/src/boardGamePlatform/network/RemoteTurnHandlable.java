/*
 * 
 */
package boardGamePlatform.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import boardGamePlatform.game.Context;
import boardGamePlatform.game.TurnResult;

// TODO: Auto-generated Javadoc
/**
 * Interfejs wykorzystywany do zdalnego odpytywania graczy bioracych udzial w rozgrywce o kolejne tury. 
 * Implementowany przez RemoteTurnHandler.
 */
public interface RemoteTurnHandlable extends Remote{
	
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
	 *
	 * @param context the context
	 * @return the turn result
	 * @throws RemoteException the remote exception
	 */
	public TurnResult makeTurn(Context context) throws RemoteException;
}
