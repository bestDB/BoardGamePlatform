/*
 * 
 */
package boardGamePlatform.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import boardGamePlatform.game.TurnHandler;
import boardGamePlatform.platformExceptions.RoomMakingException;

// TODO: Auto-generated Javadoc
/**
 * Interfejs udostepniany klientom chcacym dolaczyc do pokoju lub stworzyc nowy. Implementowany przez Rooms.
 */
public interface RoomsManager extends Remote{

	/**
	 * Tworzenie nowego pokoju. Zwraca interfejs Managable umozliwiajacy tworzacemu zarzadzanie utworzonym pokojem.
	 *
	 * @param roomName nazwa pokoju 
	 * @param playersCount liczba graczy dla ktorej tworzony jest pokoj
	 * @param currentTurnHandler obiekt RemoteTurnHandler zalozyciela pokoju do obslugi tury gracza
	 * @param otherTurnHandler obiekt RemoteTurnHandler zalozyciela pokoju do obslugi tury innego gracza
	 * @param gameHasEndedCallback callback do sygnalizowania zalozyciela pokoju o zakonczeniu gry
	 * @param nick nick wlasciciela pokoju
	 * @return interfejs Managable umozliwiajacy tworzacemu zarzadzanie utworzonym pokojem
	 * @throws RemoteException the remote exception
	 * @throws RoomMakingException the room making exception
	 */
	public Managable makeRoom(String roomName, int playersCount,
			RemoteTurnHandlable currentTurnHandler, RemoteTurnHandlable otherTurnHandler,
			GameHasEndedCallback gameHasEndedCallback, String nick)
			throws RemoteException, RoomMakingException;
	
	/**
	 * Dolaczanie do pierwszego z listy dostepnych pokoi.
	 *
	 * @param nick nick dolaczajacego gracza
	 * @param currTurnHandler obiekt klasy implementujacej RemoteTurnHandlable wykorzystywany do odpytywania gracza o akcje w czasie jego tury
	 * @param otherTurnHandler obiekt klasy implementujacej RemoteTurnHandlable wykorzystywany do odpytywania gracza o akcje w czasie tury innego gracza
	 * @param gameHasEndedCallback callback do sygnalizowania gracza o zakonczeniu gry
	 * @return interfejs Managable umozliwiajacy dolaczajacemu opuszczenie pokoju
	 * @throws RemoteException the remote exception
	 */
	Accessible joinFirstAvailableRoom(String nick, RemoteTurnHandlable currTurnHandler,
			RemoteTurnHandlable otherTurnHandler,
			GameHasEndedCallback gameHasEndedCallback) throws RemoteException;

}