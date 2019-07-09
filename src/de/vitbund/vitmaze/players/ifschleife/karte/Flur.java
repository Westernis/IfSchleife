package de.vitbund.vitmaze.players.ifschleife.karte;

public class Flur extends Feld {

	public Flur(int x, int y, Karte karte) {
		super(x, y, karte);
		// TODO Automatisch generierter Konstruktorstub
	}

	public boolean istBegehbar() {
		return true;
	}
}
