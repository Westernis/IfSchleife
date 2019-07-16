package de.vitbund.vitmaze.players.ifschleife.karte;

public abstract class Feld {

	/*
	 * geht wahrscheinlich schöner über enums oder ähnlich, keine Lust mehr
	 * nachzulesen TODO schöner machen?
	 */
	/**
	 * Text für die Flurfelder.
	 */
	public static final String flur = "FLOOR";
	/**
	 * Text für die Wandfelder.
	 */
	public static final String wand = "WALL";
	/**
	 * Text für die Zielfelder (Sachbearbeiter).
	 */
	public static final String ziel = "FINISH";
	/**
	 * Text für die Formularfelder.
	 */
	public static final String formular = "FORM";

	private final int x;
	private final int y;
	private final Koordinaten punkt;

	private String typ = "";

	protected boolean erkundet;

	// Wege, keine echten Felder
	private Feld nord = null;
	private Feld sued = null;
	private Feld ost = null;
	private Feld west = null;
	private Karte karte; // Karte, zu der das Feld gehört

	public Feld(Koordinaten punkt, Karte karte, String typ) {
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
		if (erkundet) {
			return erkundet;
		}

		if (karte.isFeldBekannt(punkt.norden()) && karte.isFeldBekannt(punkt.osten())
				&& karte.isFeldBekannt(punkt.sueden()) && karte.isFeldBekannt(punkt.westen())) {
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
	 * 
	 * @return
	 */
	public String getX() {
		return "" + x;
	}

	/**
	 * Sollte nur zur Ausgabe in Texten genutzt werden
	 * 
	 * @return
	 */
	public String getY() {
		return "" + y;
	}

	@Override
	public String toString() {
		return "ABSTRACT FIELD";
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

}
