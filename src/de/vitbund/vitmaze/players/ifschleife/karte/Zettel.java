package de.vitbund.vitmaze.players.ifschleife.karte;
/**
 * Repräsentiert ein Feld auf dem ein Zettel liegt.
 * @author Niklas.Schindler
 *
 */
public class Zettel extends Feld {

	/**
	 * Erstellt ein Feld mit einem Typ und konkreten Koordinaten auf einer Karte.
	 * 
	 * @param punkt - die Position des Felds auf der Karte (horizontale und
	 *              vertikale Achse). {@link #punkt}
	 * @param karte - die Karte auf der sich das Feld befindet.
	 */
	public Zettel(Koordinaten punkt, Karte karte) {
		super(punkt, karte, Feld.zettel);
	}

	/**
	 * Gibt zurück das der Zettel begehbar ist.
	 */
	public boolean istBegehbar() {
		return true;
	}

	/**
	 * Überschreibt die prufenErkundet um sicherzustellen, dass Zettel nur manuell auf erkundet = wahr gesetzt werden können.
	 * Es soll vor allem nicht reichen alle vier Nachbarfelder zu sehen, was für alle anderen begehbaren Felder gilt.
	 */
	public boolean pruefenErkundet() {
		return erkundet;
	}
	
	/**
	 * 
	 * @return gibt den Typ des Flurs zurück.
	 */
	public String toString() {
		return this.getTyp();
	}
}
