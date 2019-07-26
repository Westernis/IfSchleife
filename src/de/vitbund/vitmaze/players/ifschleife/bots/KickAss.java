package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
//TODO TK-JavaDocs: HR-Anpassung

/**
 * Die Klasse erweitert den Bot und hat als Ziel Formulare zu kicken.
 * @see Bot
 * 
 * @author IFSchleife
 */
public class KickAss extends Bot {

	/**
	 * Erstellt einen KickAss-Bot mit Karte, PlayerID und Startkoordinaten.
	 * 
	 * 
	 * @param karte    - die Spielfeldkarte
	 * @param playerId - Die ID des Spielers - üblicherweise Werte von 1 bis 4
	 * @param x        die X-Koordinate - die Position auf der horizontalen Achse
	 * @param y        die Y-Koordinate - die Position auf der vertikalen Achse
	 */
	public KickAss(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	/**
	 * Überschreibt die Methode des Bots. Die Methode prüft ob auf dem aktuellen
	 * Feld ein Formular ist was nicht unserem Bot gehört und kickt es in eine freie
	 * Richtung, bspw. mit {@code System.out.println("kick east");}. Die Navigation
	 * läuft über die Methode {@link #schlauereZufallsrichtung()}
	 */
	public void machAktion() {

//		this.rundeInitialisiern(); //befindet sich nun in der Init

		// Vergleich ob aktuelles Feld ein Ziel ist, wenn ja dann Ende

//		Wenn Helmuts Feld wieder funktioniert, dann damit Ziel prüfen: this.aktuelleKarte.getFeld(getOrt()).toString()

		if (Feld.formular.equals(Init.currentCell.getTyp()) && this.id != Init.currentCell.getPlayerID()) {

			if (Feld.flur.equals(Init.northCellStatus)) {
				System.out.println("kick north");
				return;
			}
			if (Feld.flur.equals(Init.southCellStatus)) {
				System.out.println("kick south");
				return;
			}
			if (Feld.flur.equals(Init.eastCellStatus)) {
				System.out.println("kick east");
				return;
			}
			if (Feld.flur.equals(Init.westCellStatus)) {
				System.out.println("kick west");
				return;
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
