package de.vitbund.vitmaze.players.ifschleife.karte;

public class VorhergehenderSchritt implements Comparable<VorhergehenderSchritt> {

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

	@Override // eine Methode vom Interface "comparable"; sie vergleicht ein übergeb. Objekt mit dieser Instanz
	public int compareTo(VorhergehenderSchritt o) {
		if (o.getWeglaenge() > this.weglaenge) {
			return -1;
		} else if (o.getWeglaenge() == this.weglaenge) {
			return 0;
		}
		return 1;
	}

}
