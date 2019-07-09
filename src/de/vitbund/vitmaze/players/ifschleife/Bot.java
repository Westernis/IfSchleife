package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

public class Bot {

	// die Karte, die er gerade erkundet
	private Karte aktuelleKarte;

	private final int id;

	// Die aktuellen Koordinaten des Bots
	private int x;
	private int y;

	/**
	 * 
	 * @param karte    Die zu erkundende Karte
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

	public boolean machAktion() {
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

		return true; // Methode als boolean lassen??
	}

	// Bewegungsfunktionen
	private void nachWesten() {
		System.out.println("go west");
	}

	private void nachSueden() {
		System.out.println("go south");
	}

	private void nachOsten() {
		System.out.println("go east");
	}

	private void nachNorden() {
		System.out.println("go north");
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
}
