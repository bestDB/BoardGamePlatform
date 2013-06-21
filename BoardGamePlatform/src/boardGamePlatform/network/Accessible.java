/*
 * 
 */
package boardGamePlatform.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import boardGamePlatform.game.TurnHandler;


// TODO: Auto-generated Javadoc
/**
 * Interfejs umozliwiajacy dostep do pokoju. Implementowany przez klase Room. Daje mozliwosc dolaczenia do niego oraz opuszczenia go. 
 * Wykorzystywany jest przez graczy ktorzy nie sa wlascicielami pokoju, a chca tylko wziac udzial w rozgrywce.
 */
public interface Accessible extends Remote{
	
	/**
	 * Zwraca true jezeli udalo sie dolaczyc do pokoju. 
	 *
	 * @param nick nick z ktorym chcemy dolaczyc do pokoju
	 * @param currTurnHandler obiekt klasy implementujacej RemoteTurnHandlable wykorzystywany do odpytywania gracza o akcje w czasie jego tury
	 * @param otherTurnHandler obiekt klasy implementujacej RemoteTurnHandlable wykorzystywany do odpytywania gracza o akcje w czasie tury innego gracza
	 * @param callback obiekt callback uzywany do komunikacji z graczem przez serwer
	 * @return true, jezeli dolaczanie do pokoju zakonczone powodzeniem
	 * @throws RemoteException the remote exception
	 */
	public boolean join(String nick,RemoteTurnHandlable currTurnHandler, RemoteTurnHandlable otherTurnHandler , GameHasEndedCallback callback) throws RemoteException;
	
	/**
	 * Opuszczanie pokoju.
	 *
	 * @param nick nick gracza ktory chce opuscic pokoj
	 * @throws RemoteException the remote exception=
	 */
	public void leave(String nick) throws RemoteException;
	
	/**
	 * Zwraca true jezeli mozna dolaczyc do pokoju.
	 *
	 * @return true, jezeli mozna dolaczyc do pokoju
	 * @throws RemoteException the remote exception
	 */
	public boolean isJoinable() throws RemoteException;
}
