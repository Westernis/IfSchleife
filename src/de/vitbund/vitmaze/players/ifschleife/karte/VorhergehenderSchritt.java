package de.vitbund.vitmaze.players.ifschleife.karte;

public class VorhergehenderSchritt {

	private int weglaenge; // speichert die Weglänge zu Startknoten des Weges
	private Feld vorgaenger;

	public VorhergehenderSchritt() {
		this.weglaenge = -1; // negative Weglängen entsprechen unbekanntem/ unendlichem Weg
	}

	public VorhergehenderSchritt(int weglaenge) {
		this.weglaenge = weglaenge; 
	}

	public VorhergehenderSchritt(int weglaenge, Feld vorgaenger) {
		this(weglaenge);
		this.vorgaenger = vorgaenger;
	}

	public int getWeglaenge() {
		return weglaenge;
	}

	public void setWeglaenge(int weglaenge) {
		this.weglaenge = weglaenge;
	}

	public Feld getVorgaenger() {
		return vorgaenger;
	}

	public void setVorgaenger(Feld vorgaenger) {
		this.vorgaenger = vorgaenger;
	}

}
