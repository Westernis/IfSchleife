package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Die Klasse soll die Koordinaten repr�sentieren die f�r die Karte und die
 * Position des Bots ben�tigt werden. Eine wichtige Annahme ist, dass f�r die
 * gesamte Nutzungsdauer die max. Koordniaten gleich bleiben.
 * 
 * @author IFSchleife
 *
 */
public class Koordinaten {

	// speichert den h�chsten Erlaubten Koordinatensatz f�r ALLE Koordinaten
	private static int xMax;//
	private static int yMax;
	private static boolean maxKoordinatenGesetzt;
	// Koordinaten des Feldes
	private final int x;
	private final int y;

	/**
	 * Konstruktor f�r einen Satz Koordinaten.
	 * 
	 * @param x Gespeichert wird der korrigierte X Wert
	 * @param y Gespeichert wird der korrigierte Y Wert
	 */
	public Koordinaten(int x, int y) {
		this.x = korrigiereX(x);
		this.y = korrigiereY(y);
	}

	/**
	 * Setzt einmalig f�r den Programmablauf die Grenzen an denen die Kartengrenzen
	 * verlaufen und "umkippen". Die Koordinaten bilden zwei Restklassenringe ->
	 * modulo x bzw. y. Das wird f�r das �berschreiten der Kartengrenzen ben�tigt.
	 * Ein Bot kann sich damit auf einer Seite rausbewegen und kommt auf der anderen
	 * Seite wieder rein.
	 * 
	 * @param x Anzahl der Felder entlang der X-Achse. Die Z�hlung beginnt bei 1.
	 * @param y Anzahl der Felder entlang der Y-Achse. Die Z�hlung beginnt bei 1.
	 * @return true wenn Koordinaten gesetzt. false wenn nicht; bspw. wenn die
	 *         Koordinaten schon gesetzt wurden.
	 */
	// Setzt die max. Koordinaten und verhindert gleich zeitig das sie danach
	// ver�ndert werden k�nnen
	public static boolean setzeMaximaleKoordinaten(int x, int y) {
		if (!maxKoordinatenGesetzt) {
			Koordinaten.xMax = x;
			Koordinaten.yMax = y;
			maxKoordinatenGesetzt = true;

			return true;
		}

		return false;
	}

	/**
	 * 
	 * @return - gibt die X-Koordinate zur�ck
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return - gibt die Y-Koordinate zur�ck
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @return - gibt den maximalen X-Wert zur�ck. Das was das Spielfeld maximal
	 *         hergibt.
	 */
	public static int getxMax() {
		return xMax;
	}

	/**
	 * 
	 * @return - gibt den maximalen Y-Wert zur�ck. Das was das Spielfeld maximal
	 *         hergibt.
	 */
	public static int getyMax() {
		return yMax;
	}

	// Annahme maximum - 1 ist der h�chste Wert, der tats�chlich auftreten kann.
	/**
	 * Korrigiert Koordinaten - sinnvoll f�r den Fall das ein Bot �ber Grenzen von
	 * Spielfeldern f�hrt.
	 * 
	 * @param zahl    - die es zu korrigieren gilt
	 *                {@code zahl = (zahl % (maximum));...}
	 * @param maximum - ein Maximalwert idR der Wert den die Karte maximal hergibt.
	 * @return korrigierte Zahl - Also bspw. der neue Positionswert auf den Koordinaten
	 */
	public static int korrigiere(int zahl, int maximum) {
		zahl = (zahl % (maximum));
		if (zahl < 0) {// Zahl ins positive Korrigieren
			zahl += (maximum);
			// zahl*= -1;
		}
		return zahl;
	}

	/**
	 * Korrigiert eine �bergebene Zahl mit dem maximalen X-Wert. Korrigiert mit:
	 * {@link #korrigiere(int, int)}
	 * 
	 * @param x - die zu korrigierende Zahl
	 * @return den korrigierten X-Wert
	 */
	private int korrigiereX(int x) {
		return korrigiere(x, xMax);
	}

	/**
	 * Korrigiert eine �bergebene Zahl mit dem maximalen Y-Wert. Korrigiert mit:
	 * {@link #korrigiere(int, int)}
	 * 
	 * @param y - die zu korrigierende Zahl
	 * @return den korrigierten X-Wert
	 */
	private int korrigiereY(int y) {
		return korrigiere(y, yMax);
	}

	/**
	 * Berechnet die um Wert ge�nderte Koordinate.
	 * 
	 * @param wert
	 * @return Entspricht der X-Koordinaten die man erh�lt, wenn man die X-Achse um
	 *         den Wert entlang wandert.
	 */
	public int getXPlus(int wert) {
		return korrigiereX(this.x + wert);
	}

	/**
	 * Berechnet die um Wert ge�nderte Koordinate.
	 * 
	 * @param wert
	 * @return Entspricht der Y-Koordinaten die man erh�lt, wenn man die Y-Achse um
	 *         den Wert entlang wandert.
	 */
	public int getYPlus(int wert) {
		return korrigiereY(this.y + wert);
	}

	/*
	 * public void xhoch() {}
	 */

//	//nur zum testen der Koordinaten implementiert
//	public static void main(String[] args) {
//		setzeMaximaleKoordinaten(11, 11);
//
//		// System.err.println(korrigiere(-10, 10));
//		Koordinaten p1 = new Koordinaten(1, 2);
//
//		for (int i = 0; i < 24; i++) {
//			System.err.println(p1);
//			p1.setX(p1.getXPlus(1));
//		}
//	}

	/**
	 * 
	 * @return den Koordinatensatz den man erh�lt, w�rde man ein Schritt nach Norden
	 *         fahren.
	 */
	public Koordinaten norden() {
		return new Koordinaten(this.x, this.y - 1);
	}

	/**
	 * 
	 * @return den Koordinatensatz den man erh�lt, w�rde man ein Schritt nach S�den
	 *         fahren.
	 */
	public Koordinaten sueden() {
		return new Koordinaten(this.x, this.y + 1);
	}

	/**
	 * 
	 * @return den Koordinatensatz den man erh�lt, w�rde man ein Schritt nach Westen
	 *         fahren.
	 */
	public Koordinaten westen() {
		return new Koordinaten(this.x - 1, this.y);
	}

	/**
	 * 
	 * @return den Koordinatensatz den man erh�lt, w�rde man ein Schritt nach Osten
	 *         fahren.
	 */
	public Koordinaten osten() {
		return new Koordinaten(this.x + 1, this.y);
	}

	/**
	 * Gibt f�r direkt benachbarte Koordinaten die Richtung zur�ck.
	 * 
	 * @param start
	 * @param ziel
	 * @return Gibt {@code null} wenn es keine benachbarten Knoten sind.
	 */
	public static String getRichtung(Koordinaten start, Koordinaten ziel) {
		// TODO die Strings durch konstanten ersetzen
		if (start.getXPlus(1) == ziel.getX()) {
			return "Osten";
		}
		if (start.getXPlus(-1) == ziel.getX()) {
			return "Westen";
		}
		if (start.getYPlus(1) == ziel.getY()) {
			return "Sueden";
		}
		if (start.getYPlus(-1) == ziel.getY()) {
			return "Norden";
		}
		return null;
	}

	/**
	 * 
	 * Dient dem Testen der Koordinatenklasse
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Koordinaten.setzeMaximaleKoordinaten(10, 5);
		Koordinaten p1 = new Koordinaten(0, 0);
		Koordinaten p2 = new Koordinaten(8, 0);
		Koordinaten p3 = new Koordinaten(1, 0);
		Koordinaten p4 = new Koordinaten(9, 0);

		System.err.println(p1);
		System.err.println(p2);
		System.err.println(p3);
		System.err.println(p4);
		System.err.println(getRichtung(p1, p4));
	}

	/**
	 * Pr�ft ob ein �bergebener Ort die gleichen X- und Y-Koordinaten wie die
	 * aktuelle Instanz hat.
	 * 
	 * @param ort - den es zu �berpr�fen gilt.
	 * @return true - wenn die Koordinaten gleich sind. false wenn die Koordinaten
	 *         nicht gleich sind.
	 */
	public boolean xyGleich(Koordinaten ort) {
		if (this.x == ort.getX() && this.y == ort.getY()) {
			return true;
		}
		return false;
	}

	/**
	 * Gibt die konkreten Koordinaten aus.
	 * 
	 * @return die X- und Y-Koordinaten.
	 */
	public String toString() {
		return ("" + this.x + " " + this.y);
	}

}
