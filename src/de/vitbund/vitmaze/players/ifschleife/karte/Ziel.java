package de.vitbund.vitmaze.players.ifschleife.karte;

public class Ziel extends Flur {

	private int playerID;
	private int formID;
	
	public Ziel(int x, int y, Karte karte, int playerID, int formID) {
		super(x, y, karte);
		this.playerID = playerID;
		this.formID = formID;
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

}
