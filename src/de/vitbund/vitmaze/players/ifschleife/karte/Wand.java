package de.vitbund.vitmaze.players.ifschleife.karte;
/**
 * 
 * @author IFSchleife
 *
 *
 */
public class Wand extends Feld {

	public Wand(int x, int y, Karte karte, boolean erkundet) {
		super(x, y, karte);
		this.erkundet = true;
		// TODO Automatisch generierter Konstruktorstub
	}

	public boolean istBegehbar() {
		return false;
	}
}
