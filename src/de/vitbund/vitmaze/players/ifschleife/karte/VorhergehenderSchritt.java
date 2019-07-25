package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Der Vorherhergehende Schritt wird f�r die Wegberechnung ben�tigt.
 * 
 * @author IFSchleife
 *
 */
public class VorhergehenderSchritt implements Comparable<VorhergehenderSchritt> {

	private int weglaenge; // speichert die Wegl�nge zu Startknoten des Weges
	private Feld vorgaenger;

	public VorhergehenderSchritt() {
		this.weglaenge = -1; // negative Wegl�ngen entsprechen unbekanntem/ unendlichem Weg
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

	// eine Methode vom Interface "comparable"; sie vergleicht ein �bergeb. Objekt
	// mit dieser Instanz
	/**
	 * 
	 * @Override 
	 * @param vorhergehenderSchritt
	 * @return -1 wenn Wegl�nge von �bergebenem Objekt gr��er als die von dieser
	 *         Instanz. 0 wenn Wegl�ngen gleich sind. 1 in anderen F�llen.
	 */
	public int compareTo(VorhergehenderSchritt vorhergehenderSchritt) {
		if (vorhergehenderSchritt.getWeglaenge() > this.weglaenge) {
			return -1;
		} else if (vorhergehenderSchritt.getWeglaenge() == this.weglaenge) {
			return 0;
		}
		return 1;
	}

}
