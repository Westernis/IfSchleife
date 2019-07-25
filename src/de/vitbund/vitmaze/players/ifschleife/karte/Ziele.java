package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * TODO: Jenachdem hier als Art noch den Zettel einfügen. Das Ziel erweitert den
 * Flur und kann bisher entweder ein Sachbearbeiter oder ein Formular sein.
 * 
 * @author IFSchleife
 * @see Flur
 */
public class Ziele extends Flur {

	private int playerID;
	private int formID;

	/**
	 * Erstellt ein neues Zielfeld.
	 * 
	 * @param punkt    die konkrete Position des Ziels auf dem Spielfeld
	 * @param karte    die Karte auf der das Ziel existiert
	 * @param playerID die ID des Spielers
	 * @param formID   die Nummer des Formulars
	 * @param typ      der Typ à formular oder ziel
	 */
	public Ziele(Koordinaten punkt, Karte karte, int playerID, int formID, String typ) {
		super(punkt, karte);
		this.playerID = playerID;
		this.formID = formID;
		this.setTyp(typ);
	}

	/**
	 * 
	 * @return die SpielerID; u.a. für Formulare relevant, da sie für jeden Spieler
	 *         individuell sind.
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * 
	 * @param playerID - Die SpielerID in dem aktuellen Spiel
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * 
	 * @return die FormularNr. In VITMaze besteht die Möglichkeit, dass verschiedene
	 *         Formulare auf einer Spielkarte liegen.
	 */
	public int getFormID() {
		return formID;
	}

	/**
	 * @return der Typ des Ziels + ID des Spielers + Nummer des Formulars
	 */
	public String toString() {
		// TODO Ziel vom Dokument unterscheiden bei der Rückgabe
		return this.getTyp() + " " + playerID + " " + formID;
	}

	/**
	 * 
	 * @param value
	 * @return {@code false} wenn das Ziel nicht die gleichen Inhalte hat.
	 *         {@code true} wenn die Inhalte des Formulars identisch sind.
	 */
	public boolean selbesFormular(Ziele value) {
		if (value == null) {
			return false;
		}
		if (value.getFormID() == this.getFormID() && value.getPlayerID() == this.getPlayerID()) {
			return true;
		}
		return false;

	}

}
