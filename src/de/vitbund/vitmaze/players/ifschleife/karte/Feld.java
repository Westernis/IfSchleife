package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Ein Feld stellt ein Quadrat auf einer Karte dar. Mit einer bestimmten
 * Position auf dem Spielfeld und einem Typ - in VITMaze kann auf einem
 * Feld bspw. ein Formular oder ein Ziel o.�. liegen. Au�erdem enth�lt das Feld
 * eine Eigenschaft f�r die Darstellung ob es bereits erkundet oder noch
 * unbekannt ist.
 * 
 * @author IFSchleife
 */
public abstract class Feld {

	/**
	 * Text f�r die Flurfelder: "FLOOR".
	 */
	public static final String flur = "FLOOR";
	/**
	 * Text f�r die Wandfelder : "WALL".
	 */
	public static final String wand = "WALL";
	/**
	 * Text f�r die Zielfelder (Sachbearbeiter): "FINISH".
	 */
	public static final String ziel = "FINISH";
	/**
	 * Text f�r die Formularfelder: "FORM".
	 */
	public static final String formular = "FORM";

	/**
	 * Text f�r Felder auf denen ein Zettel liegt: "SHEET".
	 */
	public static final String zettel = "SHEET";

//	private final int x;
//	private final int y;
	/**
	 * Jedes Feld hat eine bestimmte Position auf der horizontalen und der
	 * vertikalen Achse.
	 */
	private final Koordinaten punkt;

	/**
	 * Der Typ des Felds; wird bspw. mit flur oder wand gef�llt.
	 */
	private String typ = "";

	/**
	 * ist das Feld bereits erkundet?
	 */
	protected boolean erkundet;

	// Wege, keine echten Felder
	private Feld nord = null;
	private Feld sued = null;
	private Feld ost = null;
	private Feld west = null;
	private Karte karte; // Karte, zu der das Feld geh�rt

	/**
	 * Erstellt ein Feld mit einem Typ und konkreten Koordinaten auf einer Karte.
	 * 
	 * @param punkt - die Position des Felds auf der Karte (horizontale und
	 *              vertikale Achse). {@link #punkt}
	 * @param karte - die Karte auf der sich das Feld befindet.
	 * @param typ   - die Art des Felds, bspw. flur. {@link #typ}
	 */
	public Feld(Koordinaten punkt, Karte karte, String typ) {
		this.punkt = punkt;
//		this.x = punkt.getX();
//		this.y = punkt.getY();
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
	 * @return Gibt die zum {@code String typ} passende Unterklasse von {@code Feld}
	 *         zur�ck. Gibt {@code null} zur�ck bei unpassendem Feldtyp.
	 */
	public static Feld konstruiereFeld(Koordinaten punkt, Karte karte, String typ, int playerID, int formID) {

		switch (typ) {
		case Feld.flur:
			return new Flur(punkt, karte);

		case Feld.wand:
			return new Wand(punkt, karte);

		case Feld.formular:// fehlendes return/break ist Absicht, der einzige Uunterschied ist in Typ drin
		case Feld.ziel:
			return new Ziele(punkt, karte, playerID, formID, typ);
		case Feld.zettel:
			return new Zettel(punkt, karte);
		default:
			return null;
		}

	}

	/**
	 * Pr�ft anhand der benachbarten Felder (sind alle angelegt?) ob das Feld auf
	 * erkundet gesetzt werden kann, wenn ja wird das Feld als erkundet markiert.
	 * Erkundet bedeutet in diesem Zusammenhang, das alle relevanten Informationen
	 * �ber das Feld bekannt sind. Daher der Typ des Feldes und welche Wege besitzt
	 * es.
	 * 
	 * @return Ist das Feld als erkundet markiert.
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

	/**
	 * Mit Ausnahme von Feldern vom Typ Zettel sollte die Methode
	 * {@link #pruefenErkundet()} benutzt werden.
	 * 
	 * @param erkundet
	 */
	public void setErkundet(boolean erkundet) {
		this.erkundet = erkundet;
	}

	/**
	 * Eine Methode die von Unterklassen �berschrieben wird. Sie gibt zur�ck ob
	 * ein Feld begehbar ist.
	 */
	public abstract boolean istBegehbar();

	/**
	 * 
	 * @return das n�rdliche Feld.
	 */
	public Feld getNord() {
		return nord;
	}

	/**
	 * setzt das n�rdliche Feld auf das �bergebene Feld
	 * 
	 * @param nord
	 * 
	 */
	public void setNord(Feld nord) {
		this.nord = nord;
	}

	/**
	 * 
	 * @return das s�dliche Feld
	 */
	public Feld getSued() {
		return sued;
	}

	/**
	 * setzt das s�dliche Feld auf das �bergebene Feld
	 * 
	 * @param sued
	 * 
	 */
	public void setSued(Feld sued) {
		this.sued = sued;
	}

	/**
	 * 
	 * @return �stliche Feld
	 */
	public Feld getOst() {
		return ost;
	}

	/**
	 * setzt das �stliche Feld auf das �bergebene Feld
	 * 
	 * @param feld
	 * 
	 */
	public void setOst(Feld ost) {
		this.ost = ost;
	}

	/**
	 * 
	 * @return das westliche Feld
	 */
	public Feld getWest() {
		return west;
	}

	/**
	 * setzt das westliche Feld auf das �bergebene Feld
	 * 
	 * @param feld
	 * 
	 */
	public void setWest(Feld west) {
		this.west = west;
	}

	/**
	 * Sollte nur zur Ausgabe in Texten genutzt werden
	 * 
	 * @return Gibt die Position auf der horizontalen Achse zur�ck.
	 */
	public String getX() {
		return "" + punkt.getX();
	}

	/**
	 * Sollte nur zur Ausgabe in Texten genutzt werden
	 * 
	 * @return Gibt die Position auf der vertikalen Achse zur�ck.
	 */
	public String getY() {
		return "" + punkt.getY();
	}

	/**
	 * @return gibt den String "ABSTRACT FIELD" zur�ck.
	 */
	public String toString() {
		return "ABSTRACT FIELD";
	}

	/**
	 * Gibt den Typ des Feldes zur�ck. F�r die m�glichen Werte siehe Konstanten dieser Klasse.
	 * 
	 * @return den Typ des Felds
	 */
	public String getTyp() {
		return typ;
	}

	/**
	 * 
	 * @return die Position auf der Spielkarte.
	 */
	public Koordinaten getPunkt() {
		return punkt;
	}

	/**
	 * Setzt den Typ des Felds. Beispielhafte Felder sind {@link Feld#flur} oder {@link Feld#wand}.
	 * 
	 * @param typ
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

}
