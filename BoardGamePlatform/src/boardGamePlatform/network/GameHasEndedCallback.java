/*
 * 
 */
package boardGamePlatform.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

// TODO: Auto-generated Javadoc
/**
 * Interfejs uzywany do zdalnej komunikacji serwera z klientem.
 */
public interface GameHasEndedCallback extends Remote {
	
	/**
	 * Sygnalizuje ze rozgrywka dobiegla konca.
	 *
	 * @throws RemoteException the remote exception
	 */
	public void signalGameHasEnded() throws RemoteException;
}
