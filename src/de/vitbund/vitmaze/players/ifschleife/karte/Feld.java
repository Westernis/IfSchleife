package de.vitbund.vitmaze.players.ifschleife.karte;

public abstract class Feld {

	/*
	 * geht wahrscheinlich sch�ner �ber enums oder �hnlich, keine Lust mehr
	 * nachzulesen TODO sch�ner machen?
	 */
	/**
	 * Text f�r die Flurfelder.
	 */
	public static final String flur = "FLOOR";
	/**
	 * Text f�r die Wandfelder.
	 */
	public static final String wand = "WALL";
	/**
	 * Text f�r die Zielfelder (Sachbearbeiter).
	 */
	public static final String ziel = "FINISH";
	/**
	 * Text f�r die Formularfelder.
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
	private Karte karte; // Karte, zu der das Feld geh�rt

	public Feld(Koordinaten punkt, Karte karte, String typ) {
		this.punkt = punkt;
		this.x = punkt.getX();
		this.y = punkt.getY();
		this.karte = karte;
		this.typ = typ;
	}

	/**
	 * Diese Methode gibt ein zum {@code String typ} passendes Feldobjekt zur�ck.
	 * 
	 * @param punkt    Die Koordinaten, die das Feld haben soll. Nicht mehr
	 *                 �nderbar.
	 * @param karte    Die Karte, zu der das Feld geh�rt.
	 * @param typ      Typ legt die Art des konstruierten Feldes fest. F�r �bergaben
	 *                 die nicht einer der Konstanten aus der Feld Klasse
	 *                 entsprechen, wird {@code null} zur�ckgegeben. G�ltige Werte
	 *                 sind zum Bsp. {@code Feld.wand} oder {@code Feld.flur}
	 * @param playerID F�r relevante Felder hier den Spieler eintragen, dem es
	 *                 zugeordnet werden soll.
	 * @param formID   F�r Formulare hier die Formularnummer eintragen. F�r
	 *                 Sachbearbeiter(Ziel) die Anzahl der ben�tigten Formulare
	 *                 eintragen.
	 * @return Gibt die zum {@code String typ} passende Unterklasse von {@code Feld} zur�ck.
	 */
	public static Feld konstruiereFeld(Koordinaten punkt, Karte karte, String typ, int playerID, int formID) {
		
		switch (typ) {
		case Feld.flur:
			
			return null;
		case Feld.formular:
			
			return null;
		case Feld.wand:
			
			return null;
		case Feld.ziel:
			
			return null;

		default:
			return null;
		}

	}

	/**
	 * pr�ft anhand der benachbarten Felder (sind alle angelegt?) ob das Feld auf
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

	public Koordinaten getPunkt() {
		return punkt;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

}
