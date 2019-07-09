package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

public class ZufallsBot extends Bot {

	public ZufallsBot(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		// TODO Automatisch generierter Konstruktorstub
	}

	public boolean machAktion() {
		return true;
	}
}
