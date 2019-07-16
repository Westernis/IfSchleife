package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * 
 * @author IFSchleife
 *
 */
public class Ziele extends Flur {

	private int playerID;
	private int formID;

	public Ziele(Koordinaten punkt, Karte karte, int playerID, int formID) {
		super(punkt, karte);
		this.playerID = playerID;
		this.formID = formID;
		this.setTyp(Feld.ziel);
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getFormID() {
		return formID;
	}

	public void setFormID(int formID) {
		this.formID = formID;
	}

	public String toString() {// TODO form und player id mit rausgeben oder nur ziel??
		//TODO Ziel vom Dokument unterscheiden bei der Rückgabe
		return this.getTyp() + " " + playerID + " formID";
	}

}
