package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * 
 * @author IFSchleife
 *
 *
 */
public class Wand extends Feld {

	public Wand(Koordinaten punkt, Karte karte) {
		super(punkt, karte, Feld.wand);
		this.erkundet = true;
	}

	public boolean istBegehbar() {
		return false;
	}
	
	@Override
	public String toString() {
		return this.getTyp();
	}

	@Override
	public Feld getNord() {
		return null;
	}
	@Override
	public Feld getWest() {
		return null;
	}
	@Override
	public Feld getOst() {
		return null;
	}
	@Override
	public Feld getSued() {
		return null;
	}
}
