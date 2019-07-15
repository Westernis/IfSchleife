package de.vitbund.vitmaze.players.ifschleife.karte;

public abstract class Feld {

	final int x;
	final int y;
	final Koordinaten punkt;

	boolean erkundet;

	// Wege, keine echten Felder
	private Feld nord = null;
	private Feld sued = null;
	private Feld ost = null;
	private Feld west = null;
	private Karte karte; // Karte, zu der das Feld gehört

	public Feld(Koordinaten punkt, Karte karte) {
		this.punkt = punkt;
		this.x = punkt.getX();
		this.y = punkt.getY();
		this.karte = karte;
	}

	/**
	 * prüft anhand der benachbarten Felder (sind alle angelegt?) ob das Feld auf
	 * erkundet gesetzt werden kann Variable -> erkundet
	 */
	public boolean pruefenErkundet() {
		// TODO
		// Das Feld ist bereits erkundet
		if (erkundet) {
			return erkundet;
		}
		// FIXME Arraygrenzen /Mapborders

		if (karte.isFeldBekannt(punkt.norden()) && karte.isFeldBekannt(punkt.osten()) && karte.isFeldBekannt(punkt.sueden())
				&& karte.isFeldBekannt(punkt.westen())) {
			erkundet = true;
			return erkundet;
		}

		return false;
	}

	public abstract boolean istBegehbar();

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

	/**
	 * Sollte nur zur Ausgabe in Texten genutzt werden
	 * @return
	 */
	public String getX() {
		return ""+x;
	}

	/**
	 * Sollte nur zur Ausgabe in Texten genutzt werden
	 * @return
	 */
	public String getY() {
		return ""+y;
	}
	
	
}
