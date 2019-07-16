package de.vitbund.vitmaze.players.ifschleife.karte;

public class Flur extends Feld {

	public Flur(Koordinaten punkt, Karte karte) {
		super(punkt, karte, Feld.flur);
	}

	public boolean istBegehbar() {
		return true;
	}
}
