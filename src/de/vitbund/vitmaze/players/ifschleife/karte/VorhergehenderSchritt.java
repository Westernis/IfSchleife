package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Der Vorherhergehende Schritt wird f�r die Wegberechnung ben�tigt.
 * 
 * @author IFSchleife
 * @see Karte
 */
public class VorhergehenderSchritt implements Comparable<VorhergehenderSchritt> {

	private int weglaenge; // speichert die Wegl�nge zu Startknoten des Weges
	private Feld vorgaenger;

	/**
	 * Erstellt einen neuen vorhergehenden Schritt mit Weglaenge -1. Damit wird
	 * signalisiert dass es sich um einen unbekannten / unendlichen Weg handelt.
	 */
	public VorhergehenderSchritt() {
		this.weglaenge = -1; // negative Wegl�ngen entsprechen unbekanntem/ unendlichem Weg
	}

	/**
	 * Erstellt einen neuen vorhergehenden Schritt. Die Wegl�nge wird auf den
	 * �bergebenen Wert gesetzt.
	 * 
	 * @param weglaenge - die Wegl�nge die der vorhergehende Schritt aufweisen soll.
	 */
	public VorhergehenderSchritt(int weglaenge) {
		this.weglaenge = weglaenge;
	}

	/**
	 * Erstellt einen neuen VorhergehendenSchritt mit einer weglaenge und einem
	 * Vorg�nger.
	 * 
	 * @param weglaenge  - die Wegl�nge die der vorhergehende Schritt aufweisen
	 *                   soll.
	 * @param vorgaenger - das Feld das der Vorg�nger sein soll.
	 */
	public VorhergehenderSchritt(int weglaenge, Feld vorgaenger) {
		this(weglaenge);
		this.vorgaenger = vorgaenger;
	}

	/**
	 * 
	 * 
	 * @return weglaenge Gibt eine ganzzahligen Wert gr��er Null zur�ck.
	 */
	public int getWeglaenge() {
		return weglaenge;
	}

	/**
	 * Setzt die Wegl�nge auf den �bergebenen Wert.
	 * 
	 * @param weglaenge Es d�rfen nur Werte gr��er Null �bergeben werden.
	 */
	public void setWeglaenge(int weglaenge) {
		this.weglaenge = weglaenge;
	}

	/**
	 * Gibt den Vor�nger zur�ck.
	 * 
	 * @return vorgaenger
	 */
	public Feld getVorgaenger() {
		return vorgaenger;
	}

	// eine Methode vom Interface "comparable"; sie vergleicht ein �bergeb. Objekt
	// mit dieser Instanz
	/**
	 * Vergleicht die Wegl�nge des vorhergehenden Schritts mit der Wegl�nge diesen
	 * Schritts.
	 * 
	 * @param vorhergehenderSchritt - der Schritt der mit dem aktuellen verglichen
	 *                              werden soll.
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
