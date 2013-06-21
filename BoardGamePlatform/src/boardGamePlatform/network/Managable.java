/*
 * 
 */
package boardGamePlatform.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

// TODO: Auto-generated Javadoc
/**
 * Interfejs umozliwiajacy zarzadzanie pokojem. Implementowany przez klase Room.
 * Wykorzystywany jest przez graczy ktorzy sa wlascicielami pokoju.
 */
public interface Managable extends Remote{
	
	/**
	 * Zwraca true, jezeli udalo sie rozpoczac rozgrywke w pokoju.
	 *
	 * @return true, jezeli udalo sie rozpoczac rozgrywke w pokoju
	 * @throws RemoteException the remote exception
	 */
	public boolean start() throws RemoteException; 
}
