package de.vitbund.vitmaze.players.ifschleife.karte;
/**
 * 
 * @author IFSchleife
 *
 *
 */
public class Wand extends Feld {

	public Wand(Koordinaten punkt, Karte karte, boolean erkundet) {
		super(punkt, karte);
		this.erkundet = true;
		// TODO Automatisch generierter Konstruktorstub
	}

	public boolean istBegehbar() {
		return false;
	}
}
