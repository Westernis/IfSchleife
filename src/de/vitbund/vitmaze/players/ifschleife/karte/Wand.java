package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * 
 * @author IFSchleife
 *
 *
 */
public class Wand extends Feld {

	public Wand(Koordinaten punkt, Karte karte, boolean erkundet) {
		super(punkt, karte, Feld.wand);
		this.erkundet = true;
	}

	public boolean istBegehbar() {
		return false;
	}
}
