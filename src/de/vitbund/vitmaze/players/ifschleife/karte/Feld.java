package de.vitbund.vitmaze.players.ifschleife.karte;

public abstract class Feld {

	final int x;
	final int y;
	
	boolean erkundet;
	
	
	
	//Wege, keine echten Felder
	private Feld nord;
	private Feld sued;
	private Feld ost;
	private Feld west;
	private Karte karte; //Karte, zu der das Feld gehört

	public Feld(int x, int y, Karte karte) {
		this.x = x;
		this.y = y;
		this.karte = karte;
	}
	
	/**
	 * prüft anhand der benachbarten Felder (sind alle angelegt?) ob das Feld auf erkundet gesetzt werden kann 
	 * Variable -> erkundet
	 */
	public boolean pruefenErkundet() {
		//TODO
		//Das Feld ist bereits erkundet
		if (erkundet) {
			return erkundet;
		}
		//FIXME Arraygrenzen /Mapborders
		
		if (karte.isFeldBekannt(x+1, y) && 
				karte.isFeldBekannt(x-1, y) &&
				karte.isFeldBekannt(x, y+1) &&
				karte.isFeldBekannt(x, y-1)) {
			erkundet = true;
			return erkundet;
		}
		
		return false;
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
