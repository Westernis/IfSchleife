package de.vitbund.vitmaze.players.ifschleife.karte;

public class Zettel extends Feld {

	public Zettel(Koordinaten punkt, Karte karte) {
		super(punkt, karte, Feld.zettel);
	}

	@Override
	public boolean istBegehbar() {
		return true;
	}

	/**
	 * �berschreibt die prufenErkundet um sicherzustellen, dass Zettel nur manuell auf erkundet = wahr gesetzt werden k�nnen.
	 * Es soll vor allem nicht reichen alle vier Nachbarfelder zu sehen, was f�r alle anderen begehbaren Felder gilt.
	 */
	public boolean pruefenErkundet() {
		return erkundet;
	}
	
	/**
	 * 
	 * @return gibt den Typ des Flurs zur�ck.
	 */
	public String toString() {
		return this.getTyp();
	}
}
