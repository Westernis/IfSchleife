package de.vitbund.vitmaze.players.ifschleife.karte;

public abstract class Feld {

	final int x;
	final int y;
	
	boolean erkundet;
	
	private Feld nord;
	private Feld sued;
	private Feld ost;
	private Feld west;


	public Feld(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * prüft anhand der benachbarten Felder (sind alle angelegt?) ob das Feld auf erkundet gesetzt werden kann 
	 * Variable -> erkundet
	 */
	public void pruefenErkundet() {
		//TODO
	}


	public Feld getNord() {
		return nord;
	}


	public void setNord(Feld nord) {
		this.nord = nord;
	}


	public Feld getSued() {
		return sued;
	}


	public void setSued(Feld sued) {
		this.sued = sued;
	}


	public Feld getOst() {
		return ost;
	}


	public void setOst(Feld ost) {
		this.ost = ost;
	}


	public Feld getWest() {
		return west;
	}


	public void setWest(Feld west) {
		this.west = west;
	}
}
