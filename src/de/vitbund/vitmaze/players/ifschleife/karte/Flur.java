package de.vitbund.vitmaze.players.ifschleife.karte;

public class Flur extends Feld {

	public Flur(Koordinaten punkt, Karte karte) {
		super(punkt, karte);
		// TODO Automatisch generierter Konstruktorstub
	}

	public boolean istBegehbar() {
		return true;
	}
}
