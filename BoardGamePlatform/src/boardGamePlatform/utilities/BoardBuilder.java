/*
 * 
 */
package boardGamePlatform.utilities;

import java.util.LinkedList;
import java.util.List;

import boardGamePlatform.gameResources.Board;
import boardGamePlatform.gameResources.Field;
import boardGamePlatform.platformExceptions.UnsupportedBoardSizeException;


/**
 * Klasa ulatwiajaca tworzenie nowej planszy.
 */
public class BoardBuilder {
	
	/** Plansza. */
	private Board board;
	
	/**
	 * Instancjonuje nowa klase pomocnicza.
	 *
	 * @param board obiekt planszy na ktorym ma operowac klasa pomocnicza
	 */
	public BoardBuilder(Board board) {
		this.board = board;
	}
	
	/**
	 * Tworzy plansze z jedna mozliwa sciezka.
	 *
	 * @param size ilosc pol planszy
	 * @throws UnsupportedBoardSizeException jezeli podany zostal nieprawidlowy rozmiar planszy ( size <= 1)
	 */
	public void makeSimpleLinearBoard( int size ) throws UnsupportedBoardSizeException {
		if(size < 2)
			throw new UnsupportedBoardSizeException();
	
		List<Field> fields = new LinkedList<Field>();
		
		for(int i = 0; i<size; i++) {
			fields.add(new Field(i));
		}
		
		for(int i = 0; i<size; i++) {
			fields.get(i).addNeighbourField(fields.get((i+1) % size));
		}
		
		board.setFields(fields);
	}
}
