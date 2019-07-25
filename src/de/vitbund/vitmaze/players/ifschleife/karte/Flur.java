package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Der Flur ist eine Erweiterung des Felds.
 * 
 * @author IFSchleife
 * @see Feld
 */
public class Flur extends Feld {
	/**
	 * Erstellt einen neuen Flur mit konkreter Position auf dem Spielfeld und
	 * konkreter Karte auf der er sich befindet.
	 * 
	 * @param punkt - die Position auf dem Spielfeld
	 * @param karte - die Karte auf der er sich befindet
	 */
	public Flur(Koordinaten punkt, Karte karte) {
		super(punkt, karte, Feld.flur);
	}

	/**
	 * Einen Flur auf begehbarkeit abfragen
	 * 
	 * @return true
	 */
	public boolean istBegehbar() {
		return true;
	}

	/**
	 * 
	 * @return gibt den Typ des Flurs zurück.
	 */
	public String toString() {
		return this.getTyp();
	}
}
