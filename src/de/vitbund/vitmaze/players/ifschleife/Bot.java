package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

public class Bot {

	// die Karte, die er gerade erkundet
	private Karte aktuelleKarte;

	protected final int id;

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

	public void machAktion() {

		// TODO hier das Hirn aufrufen oder einbauen, die Schleife befindet sich in der
		// Klasse Init

		// IDEE diese Klasse abstract machen genau wie diese Methode und
		// dann die verschiedenen Varianten von diesem Bot erben lassen?
		// m�gliche Vorteile:
		// - Bewegungsfkt. k�nnten die Koordinaten�nderung f�r bots �bernehmen,
		// bei denen angenommen wird es klappt immer
		// - Die Level 2 Hirne/bots k�nnten zus�tzliche bekannte Karten und Fkt.
		// bekommen,
		// ohne das sie f�r die nicht relevanten Level st�ren
		// - man k�nnte Bots durch weiter vererbung verfeinern, z.B. br�uchte man
		// eventuell
		// nur den Umgang mit neuen Sachen hinzuf�gen f�r h�here Lvl
		//
		// m�gliche Nachteile:
		// - Bot und Ki nicht mehr getrennt, wobei immer noch jede Ki einzeln
		// implementiert werden kann
		//

		// Methode als boolean lassen??
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

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	//TODO Methode finish
}
