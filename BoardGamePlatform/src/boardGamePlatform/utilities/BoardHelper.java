/*
 * 
 */
package boardGamePlatform.utilities;
import boardGamePlatform.game.Player;
import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Field;
import boardGamePlatform.gameResources.Pawn;
import boardGamePlatform.platformExceptions.FieldNotHasNeihgboursException;
import boardGamePlatform.platformExceptions.NotAvalaibleMoveException;
import boardGamePlatform.platformExceptions.NotFieldFoundException;
import boardGamePlatform.platformExceptions.NotPawnFoundException;
import boardGamePlatform.platformExceptions.PlayerNotHasPawnsException;
import boardGamePlatform.platformExceptions.UnsupportedMoveNumberException;


// TODO: Auto-generated Javadoc
/**
 * Klasa ulatwiajaca dokonywania podstawowych operacji na planszy.
 */
public class BoardHelper {
	
	/** Plansza. */
	Board board;
	
	/**
	 * Instancjonuje nowa klase pomocnicza.
	 *
	 * @param board obiekt planszy na ktorym ma operowac klasa pomocnicza
	 */
	public BoardHelper(Board board) {
		this.board = board; 
	}
	
	/**
	 * Przestawia pionek z podanego pola na inne.
	 *
	 * @param p pionek ktory ma zostac przestawiony
	 * @param start pole z ktorego ma zostac wziety pionek
	 * @param end pole na ktore pionek zostanie przeniesiony
	 * @throws NotPawnFoundException na podanym polu startowym nie znaleziono podanego pionka
	 */
	public void movePawn(Pawn p , Field start , Field end ) throws NotPawnFoundException {
		if ( !start.removePawn( p ) ) {
			throw new NotPawnFoundException();
		} else
			end.addPawn( p );
	}
	
	/**
	 * Przestawia pionek o podana ilosc pol, na podstawie pierwszych sasiadow poszczegolnych pol.
	 *
	 * @param playerPawn pionek ktory ma zostac przestawiony
	 * @param num liczba pol o ile pionek ma zostac przesuniety
	 * @return the field
	 * @throws UnsupportedMoveNumberException podano bledno wartosc przesuniecia ( num < 0 )
	 * @throws NotAvalaibleMoveException nie mozna wykonac zadanego ruchu
	 * @throws NotPawnFoundException playerPawn = null
	 * @throws FieldNotHasNeihgboursException  pola na ktorym stoi pionek nie posiada sasiadow
	 */
	public Field movePawn(Pawn playerPawn, int num) 
			throws UnsupportedMoveNumberException, NotAvalaibleMoveException, NotPawnFoundException, FieldNotHasNeihgboursException {
		if (playerPawn  == null) { 
			throw new  NotPawnFoundException();
		}
		Field playerField = null;
		
		if (num < 0)
			throw new UnsupportedMoveNumberException();
		
		for (Field f : board.getFields() ) 
			if  ( f.containPawn( playerPawn ) ) {
				playerField = f;
				break;
			}
		if (playerField != null) {
			int i = num;
			Field endField = playerField;
			while ( i > 0 && endField != null ) {
				endField = endField.getFirstNeighbour();
				i--;
			}
			
			if ( endField != null) {
				playerField.removePawn( playerPawn );
				endField.addPawn( playerPawn );
				return endField;
				
			} else throw new NotAvalaibleMoveException();
			
		} else 
			throw new NotPawnFoundException();
		
	}
	
	/**
	 * Przesuwa pierwszy pionek podanego gracza o podana ilosc pol.
	 *
	 * @param p gracz ktorego pionek ma zostac przesuniety
	 * @param num ilosc pol o jaka ma nastapic przesuniecie
	 * @return the field
	 * @throws NotPawnFoundException gracz nie posiada pionkow
	 * @throws NotAvalaibleMoveException nie mozna wykonac zadanego ruchu
	 * @throws UnsupportedMoveNumberException podano bledno wartosc przesuniecia ( num < 0 )
	 * @throws PlayerNotHasPawnsException gracz nie posiada pionkow
	 * @throws FieldNotHasNeihgboursException pole na ktorym stoi pierwszy pionek gracza nia posiada sasiadow
	 */
	public Field movePlayerFirstPawnByNum(Player p , int num) 
			throws NotPawnFoundException,NotAvalaibleMoveException, UnsupportedMoveNumberException, PlayerNotHasPawnsException, FieldNotHasNeihgboursException {
		Pawn playerPawn = p.getPlayerFirstPawn();
		
		return movePawn(playerPawn, num);
	}
	
	/**
	 * Zwraca pole o podanym identyfikatorze.
	 *
	 * @param id ID pola
	 * @return pole o podanym ID
	 * @throws NotFieldFoundException pole o podanym ID nie istnieje
	 */
	public Field getFieldById(int id) throws NotFieldFoundException {
		for( Field f : board.getFields())
			if( f.getID() == id)
				return f;
		throw new NotFieldFoundException();
	}
	
	/**
	 * Umieszcza pionek na polu o podanym identyfikatorze.
	 *
	 * @param p pionek do umieszczenia
	 * @param fieldID ID pola na ktorym ma zostac umieszczony pionek
	 * @throws NotFieldFoundException pole o podanym ID nie istnieje
	 */
	public void placePawnOnField( Pawn p, int fieldID ) throws NotFieldFoundException {
		getFieldById(fieldID).addPawn(p);
	}
	
	/**
	 * Zwraca plansze na ktorej operuje instancja klasy pomocniczej.
	 *
	 * @return obiekt planszy
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Zwraca pole na ktorym znajduje sie podany w parametrze pionek.
	 *
	 * @param p pionek
	 * @return pole na ktorym znajduje sie pionek p
	 * @throws NotPawnFoundException jezeli pionek p nie znajduje sie na zadnym polu
	 */
	public Field getPawnsField(Pawn p) throws NotPawnFoundException {
		for( Field f : board.getFields())
			if(f.containPawn(p))
				return f;
		throw new NotPawnFoundException();
	}
	
	/**
	 * Usuwa przedmioty dodatkowe znajdujace sie na polach o przypisanym 
	 * atrybucie o nazwie i wartosci podanych w parametrach wywolania.
	 *
	 * @param name nazwa atrybutu
	 * @param value wartosc atrybutu
	 */
	public void clearFieldsWithGivenAttribute(String name, String value) {
		for( Field f : board.getFields() ) {
			if(f.getAttribute(name).equals(value))
				f.clearFieldItems();
		}
	}
	
	/**
	 * Zwraca pole na ktorym znajduje sie pierwszy pionek nalezacy do gracza podanego w parametrze.
	 *
	 * @param player gracz ktorego pionek chcemy zlokalizowac
	 * @return pole na ktorym znajduje sie pierwszy pionek gracza p
	 * @throws NotPawnFoundException jezeli pierwszy pionek gracza nie jest przypisany do zadnego z pol
	 * @throws PlayerNotHasPawnsException jezeli podany gracz nie ma zadnych pionkow
	 */
	public Field getPlayersFirstPawnField(Player player) throws NotPawnFoundException, PlayerNotHasPawnsException {
		return getPawnsField(player.getPlayerFirstPawn());
	}

}
