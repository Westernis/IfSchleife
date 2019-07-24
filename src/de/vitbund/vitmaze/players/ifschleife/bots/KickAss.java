package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 *
 *         Die Klasse stellt einen Bot dar (soll darstellen), der ausschließlich
 *         nach dem Zufall seine Wegfindung ableitet.
 */
public class KickAss extends Bot {

	public KickAss(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	public void machAktion() {

		this.rundeInitialisiern();

		// Vergleich ob aktuelles Feld ein Ziel ist, wenn ja dann Ende

//		Wenn Helmuts Feld wieder funktioniert, dann damit Ziel prüfen: this.aktuelleKarte.getFeld(getOrt()).toString()

		int kickcounter = 0;

		if (kickcounter < 10) {
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++) {

					if (Init.currentCellStatus.equals("FORM " + x + " " + y)) {

						if (!"WALL".equals(Init.northCellStatus)) {
							System.out.println("kick north");
							kickcounter++;
							break;
						}
						if (!"WALL".equals(Init.southCellStatus)) {
							System.out.println("kick south");
							kickcounter++;
							break;
						}
						if (!"WALL".equals(Init.eastCellStatus)) {
							System.out.println("kick east");
							kickcounter++;
							break;
						}
						if (!"WALL".equals(Init.westCellStatus)) {
							System.out.println("kick west");
							kickcounter++;
							break;
						}

						return;
					}
				}
			}
		}

		schlauereZufallsrichtung(); // das ist nun unser erkunden;

		/*
		 * var aktuelles Formular, höchstes Formular(-1 wenn unbekannt?)
		 * 
		 * Anfangen 1. Karte erkunden 2. Abbruch wenn ein Ziel in Sicht 2.1 prüfen ob
		 * aktuelles Ziel 2.2 ja einsammeln 3. a) nächstes Ziel bekannt -> hinfahren
		 * ->2.2 b) -> 1.
		 * 
		 * 
		 * 3. Alles eingesammelt -> zu dem Ziel fahren
		 * 
		 */

	}
}
