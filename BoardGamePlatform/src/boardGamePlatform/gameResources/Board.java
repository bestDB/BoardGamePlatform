/*
 * 
 */
package boardGamePlatform.gameResources;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 * Klasa reprezentujaca plansze na ktorej odbywa sie rozgrywka.
 */
public class Board implements Serializable{
	
	/** Pola planszy. */
	private List<Field> fields;
	
	/**
	 * Instancjonuje nowa plansze.
	 */
	public Board() {
		fields = new LinkedList<Field>();
	}
	
	/**
	 * Instancjonuje nowa plansze.
	 *
	 * @param fields lista pol 
	 */
	public Board( List<Field> fields) {
		this.fields = fields;
	}
	
	/**
	 * Zwraca liste pol z ktorych sklada sie plansza.
	 *
	 * @return lista pol
	 */
	public List<Field> getFields() {
		return fields;
	}
	
	/**
	 * Ustawia pola z ktorych sklada sie plansza.
	 *
	 * @param fields lista pol
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String boardState = new String();
		boardState += "Board:\n";
		for(Field f : fields) {
			boardState += f.toString("\t") + "\n";
		}
		boardState += "EndBoard\n";
		return boardState;
	}
}
