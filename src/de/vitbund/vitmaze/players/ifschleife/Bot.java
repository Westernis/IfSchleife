package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 *
 *         Die Grundklasse für alle anderen Bots.
 */
public class Bot {

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
	protected int x;
	protected int y;

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
		this.x = x;
		this.y = y;
	}

	/**
	 * Die Methode wird von jeder Unterklasse individuell implementiert.
	 */
	public void machAktion() {

		// TODO hier das Hirn aufrufen oder einbauen, die Schleife befindet sich in der
		// Klasse Init

		// IDEE diese Klasse abstract machen genau wie diese Methode und
		// dann die verschiedenen Varianten von diesem Bot erben lassen?
		// mögliche Vorteile:
		// - Bewegungsfkt. könnten die Koordinatenänderung für bots übernehmen,
		// bei denen angenommen wird es klappt immer
		// - Die Level 2 Hirne/bots könnten zusätzliche bekannte Karten und Fkt.
		// bekommen,
		// ohne das sie für die nicht relevanten Level stören
		// - man könnte Bots durch weiter vererbung verfeinern, z.B. bräuchte man
		// eventuell
		// nur den Umgang mit neuen Sachen hinzufügen für höhere Lvl
		//
		// mögliche Nachteile:
		// - Bot und Ki nicht mehr getrennt, wobei immer noch jede Ki einzeln
		// implementiert werden kann
		//

		// Methode als boolean lassen??
	}
	
	/**
	 * mögliche Inputs Norden Sueden Osten Westen
	 * @return gegenteil, bei falscher eingabe -> null
	 */
	public String richtungUmkehren(String richtung){
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
		this.x--;
		System.out.println("go west");
	}

	protected void nachSueden() {
		this.y++;
		System.out.println("go south");
	}

	protected void nachOsten() {
		this.x++;
		System.out.println("go east");
	}

	protected void nachNorden() {
		this.y--;

		System.out.println("go north");
	}
	
	//bequemlichkeit übersetzung der strings in unsere Methoden
	public void fahren(String richtung) {


		if ("go west".equals(richtung)) {
			this.nachWesten();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.x + " " + this.y);
			System.err.println("nach Western");
		}
		if ("go north".equals(richtung)) {
			this.nachNorden();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.x + " " + this.y);
			System.err.println("nach Norden");
		}
		if ("go east".equals(richtung)) {
			this.nachOsten();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.x + " " + this.y);
			System.err.println("nach osten");
		} 
		if("go south".equals(richtung)) {
			this.nachSueden();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.x + " " + this.y);
			System.err.println("nach süden");
		}
		this.aktuelleKarte.ausgabe();
		System.err.flush();
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// TODO Methode finish
}
