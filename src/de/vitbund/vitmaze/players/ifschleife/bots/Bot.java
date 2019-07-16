package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;

/**
 * 
 * @author IFSchleife
 *
 *         Die Grundklasse für alle anderen Bots.
 */
public abstract class Bot {

	// die Karte, die er gerade erkundet
	protected Karte aktuelleKarte;

	public Karte getAktuelleKarte() {
		return aktuelleKarte;
	}

	public void setAktuelleKarte(Karte aktuelleKarte) {
		this.aktuelleKarte = aktuelleKarte;
	}

	protected final int id;

	// Die aktuellen Koordinaten des Bots
	// protected int x;
	// protected int y;
	private Koordinaten punkt;

	/**
	 * 
	 * @param karte
	 * @param playerId
	 * @param x
	 * @param y
	 */
	public Bot(Karte karte, int playerId, int x, int y) {
		this.aktuelleKarte = karte;
		this.id = playerId;
		punkt = new Koordinaten(x, y);
		System.err.println("ini bot" + punkt);
		// this.x = x;
		// this.y = y;
	}

	/**
	 * Die Methode wird von jeder Unterklasse individuell implementiert und dient
	 * dem aufruf der Entscheidungsfindung und Ausführung durch den jeweiligen Bot
	 */
	public abstract void machAktion();

	// Bei Erweiterung hier das Hirn einbauen, die Schleife befindet sich in der
	// Klasse Init

	// Methode als boolean lassen??

	/**
	 * mögliche Inputs Norden Sueden Osten Westen
	 * 
	 * @return gegenteil, bei falscher eingabe -> null
	 */
	public String richtungUmkehren(String richtung) {
		switch (richtung) {
		case "Norden":
			return "Sueden";
		case "Sueden":
			return "Norden";
		case "Osten":
			return "Westen";
		case "Westen":
			return "Osten";
		default:
			return null;
		}
	}

	// Bewegungsfunktionen
	protected void nachWesten() {
		// this.x--;
		System.err.println("\nBotstandort " + punkt);
		this.punkt.setX(punkt.getX() - 1);
		System.out.println("go west");
	}

	protected void nachSueden() {
		// this.y++;
		System.err.println("\nBotstandort " + punkt);
		this.punkt.setY(punkt.getY() + 1);
		System.out.println("go south");
	}

	protected void nachOsten() {
		// this.x++;
		System.err.println("\nBotstandort " + punkt);
		this.punkt.setX(punkt.getX() + 1);
		System.out.println("go east");
	}

	protected void nachNorden() {
		// this.y--;
		System.err.println("\nBotstandort " + punkt);
		this.punkt.setY(punkt.getY() - 1);
		System.out.println("go north");
	}

	// bequemlichkeit übersetzung der strings in unsere Methoden
	public void fahren(String richtung) {

		if ("go west".equals(richtung)) {
			this.nachWesten();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.punkt.getX() + " " + this.punkt.getY());
			System.err.println("nach Western");
		}
		if ("go north".equals(richtung)) {
			this.nachNorden();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.punkt.getX() + " " + this.punkt.getY());
			System.err.println("nach Norden");
		}
		if ("go east".equals(richtung)) {
			this.nachOsten();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.punkt.getX() + " " + this.punkt.getY());
			System.err.println("nach osten");
		}
		if ("go south".equals(richtung)) {
			this.nachSueden();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.punkt.getX() + " " + this.punkt.getY());
			System.err.println("nach süden");
		}
		this.aktuelleKarte.ausgabe();
		System.err.flush();
	}

	public int getId() {
		return id;
	}

	public Koordinaten getPunkt() {
		return punkt;
	}

//	public int getX() {
//		return this.punkt.getX();
//	}

//	public int getY() {
//		return this.punkt.getY();
//	}

	// TODO Methode finish für alle Bots bereitstellen
}
