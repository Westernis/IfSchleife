package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * Der Flur ist eine Erweiterung des Felds.
 * 
 * @author IFSchleife
 * @see Feld
 */
public class Flur extends Feld {

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
	 * @return gibt den Typ des Flurs zuück.
	 * @Override
	 */
	public String toString() {
		return this.getTyp();
	}
}
