package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Wand erweitert das Feld.
 * 
 * @author IFSchleife
 *
 * @see Feld
 */
public class Wand extends Feld {

	/**
	 * Erstellt ein neues Wand-Feld mit konkreten Koordinaten und einer konkreten
	 * Karte.
	 * 
	 * @param punkt - die Position auf dem Spielfeld in Form von X- und
	 *              Y-Koordinaten.
	 * @param karte - die Karte auf der sich das Wand-Feld befindet.
	 */
	public Wand(Koordinaten punkt, Karte karte) {
		super(punkt, karte, Feld.wand);
		this.erkundet = true;
	}

	/**
	 * Prüft die Begehbarkeit eines Wand-Feldes
	 *
	 * @return false - da eine Wand nicht begehbar ist...
	 */
	public boolean istBegehbar() {
		return false;
	}

	/**
	 * @return gibt des Typ des Feldes zurück. Hier "wand".
	 */
	public String toString() {
		return this.getTyp();
	}

	/**
	 * @return null - da nördlich von einer Wand kein einsehbares Feld ist. Aus der
	 *         aktuellen Position.
	 */
	public Feld getNord() {
		return null;
	}

	@Override
	/**
	 * return null - da von der aktuellen Position kein einsehbares Feld ist.
	 */
	public Feld getWest() {
		return null;
	}

	@Override
	/**
	 * return null - da von der aktuellen Position kein einsehbares Feld ist.
	 */
	public Feld getOst() {
		return null;
	}

	@Override
	/**
	 * return null - da von der aktuellen Position kein einsehbares Feld ist.
	 */
	public Feld getSued() {
		return null;
	}
}
