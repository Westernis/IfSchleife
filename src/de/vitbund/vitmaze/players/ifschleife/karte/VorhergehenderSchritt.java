package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Der Vorherhergehende Schritt wird für die Wegberechnung benötigt.
 * 
 * @author IFSchleife
 * @see Karte
 */
public class VorhergehenderSchritt implements Comparable<VorhergehenderSchritt> {

	private int weglaenge; // speichert die Weglänge zu Startknoten des Weges
	private Feld vorgaenger;

	/**
	 * Erstellt einen neuen vorhergehenden Schritt mit Weglaenge -1. Damit wird
	 * signalisiert dass es sich um einen unbekannten / unendlichen Weg handelt.
	 */
	public VorhergehenderSchritt() {
		this.weglaenge = -1; // negative Weglängen entsprechen unbekanntem/ unendlichem Weg
	}

	/**
	 * Erstellt einen neuen vorhergehenden Schritt. Die Weglänge wird auf den
	 * übergebenen Wert gesetzt.
	 * 
	 * @param weglaenge - die Weglänge die der vorhergehende Schritt aufweisen soll.
	 */
	public VorhergehenderSchritt(int weglaenge) {
		this.weglaenge = weglaenge;
	}

	/**
	 * Erstellt einen neuen VorhergehendenSchritt mit einer weglaenge und einem
	 * Vorgänger.
	 * 
	 * @param weglaenge  - die Weglänge die der vorhergehende Schritt aufweisen
	 *                   soll.
	 * @param vorgaenger - das Feld das der Vorgänger sein soll.
	 */
	public VorhergehenderSchritt(int weglaenge, Feld vorgaenger) {
		this(weglaenge);
		this.vorgaenger = vorgaenger;
	}

	/**
	 * 
	 * 
	 * @return weglaenge Gibt eine ganzzahligen Wert größer Null zurück.
	 */
	public int getWeglaenge() {
		return weglaenge;
	}

	/**
	 * Setzt die Weglänge auf den übergebenen Wert.
	 * 
	 * @param weglaenge Es dürfen nur Werte größer Null übergeben werden.
	 */
	public void setWeglaenge(int weglaenge) {
		this.weglaenge = weglaenge;
	}

	/**
	 * Gibt den Voränger zurück.
	 * 
	 * @return vorgaenger
	 */
	public Feld getVorgaenger() {
		return vorgaenger;
	}

	// eine Methode vom Interface "comparable"; sie vergleicht ein übergeb. Objekt
	// mit dieser Instanz
	/**
	 * Vergleicht die Weglänge des vorhergehenden Schritts mit der Weglänge diesen
	 * Schritts.
	 * 
	 * @param vorhergehenderSchritt - der Schritt der mit dem aktuellen verglichen
	 *                              werden soll.
	 * @return -1 wenn Weglänge von übergebenem Objekt größer als die von dieser
	 *         Instanz. 0 wenn Weglängen gleich sind. 1 in anderen Fällen.
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
