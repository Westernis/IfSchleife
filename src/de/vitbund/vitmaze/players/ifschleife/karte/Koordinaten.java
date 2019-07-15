package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Die Klasse soll die Koordinaten repräsentieren die für die Karte und die
 * Position des Bots benötigt werden. Eine wichtige Annahme ist, dass für die
 * gesamte Nutzungsdauer die max. Koordniaten gleich bleiben.
 * 
 * @author helmut.rietz
 *
 */
public class Koordinaten {

	// speichert den höchsten Erlaubten Koordinatensatz für ALLE Koordinaten
	private static int xMax;// TODO prüfen ob das Labyrinth den index wert oder die Anzahl liefert
	private static int yMax;
	private static boolean maxKoordinatenGesetzt;

	private int x;
	private int y;

	/**
	 * Konstruktor für ein Satz Koordinaten.
	 * 
	 * @param x Gespeichert wird der korrigierte X Wert
	 * @param y Gespeichert wird der korrigierte Y Wert
	 */
	public Koordinaten(int x, int y) {
		this.x = korrigiereX(x);
		this.y = korrigiereY(y);
	}

	// Setzt die max. Koordinaten und verhindert gleich zeitig das sie danach
	// verändert werden können
	public static boolean setzeMaximaleKoordinaten(int x, int y) {
		if (!maxKoordinatenGesetzt) {
			Koordinaten.xMax = x;
			Koordinaten.yMax = y;
			maxKoordinatenGesetzt = true;

			return true;
		}

		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = korrigiereX(x);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = korrigiereY(y);
	}

	public static int getxMax() {
		return xMax;
	}

	public static int getyMax() {
		return yMax;
	}

	// TODO Annahme maximum ist der höchste Wert, der tatsächlich auftreten kann.
	public static int korrigiere(int zahl, int maximum) {
		zahl = (zahl % (maximum + 1));
		if (zahl < 0) {// Zahl ins positive Korrigieren
			zahl += (maximum + 1);
			// zahl*= -1;
		}
		return zahl;
	}

	private int korrigiereX(int x) {
		return korrigiere(x, xMax);
	}

	private int korrigiereY(int y) {
		return korrigiere(y, yMax);
	}

	/**
	 * Berechnet die um Wert geänderte Koordinate.
	 * 
	 * @param wert
	 * @return Entspricht der X-Koordinaten die man erhält, wenn man die X-Achse um
	 *         den Wert entlang wandert.
	 */
	public int getXPlus(int wert) {
		return korrigiereX(this.x + wert);
	}

	/**
	 * Berechnet die um Wert geänderte Koordinate.
	 * 
	 * @param wert
	 * @return Entspricht der Y-Koordinaten die man erhält, wenn man die Y-Achse um
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
	 * @return den Koordinatensatz den man erhält, würde man ein Schritt nach Norden fahren.
	 */
	public Koordinaten norden() {
		return new Koordinaten(this.x, this.y - 1);
	}

	/**
	 * 
	 * @return den Koordinatensatz den man erhält, würde man ein Schritt nach Süden fahren.
	 */
	public Koordinaten sueden() {
		return new Koordinaten(this.x, this.y + 1);
	}

	/**
	 * 
	 * @return den Koordinatensatz den man erhält, würde man ein Schritt nach Westen fahren.
	 */
	public Koordinaten westen() {
		return new Koordinaten(this.x - 1,this.y -1);
	}

	/**
	 * 
	 * @return den Koordinatensatz den man erhält, würde man ein Schritt nach Osten fahren.
	 */
	public Koordinaten osten() {
		return new Koordinaten(this.x + 1,this.y);
	}

	@Override
	public String toString() {
		return "" + x + " " + y;
	}

}
