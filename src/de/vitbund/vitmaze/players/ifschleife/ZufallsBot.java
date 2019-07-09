package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 *
 *         Die Klasse stellt einen Bot dar (soll darstellen), der ausschließlich
 *         nach dem Zufall seine Wegfindung ableitet.
 */
public class ZufallsBot extends Bot {

	public ZufallsBot(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		// TODO Automatisch generierter Konstruktorstub
	}

	/**
	 * TODO: testen füge in machAktion() die aktuelle Implementierung von
	 * zufallsRichtung() aus und gebe es aus.
	 */
	public void machAktion() {
		System.out.println(zufallsRichtung());

	}

	/**
	 * Eine Art eine Zufallsrichtung zu implementieren. TODO Prüfungen à "ist da
	 * eine Wand" oder "ist da ein SB" einbauen.
	 * 
	 * @return
	 */
	public String zufallsRichtung() {
		double zufallsZahl = Math.random();

		if (zufallsZahl < 0.25) {
			return "go west";
		}
		if (zufallsZahl >= 0.25 && zufallsZahl < 0.50) {
			return "go north";
		}
		if (zufallsZahl >= 0.50 && zufallsZahl < 0.75) {
			return "go east";
		} else {
			return "go south";
		}
	}

}
