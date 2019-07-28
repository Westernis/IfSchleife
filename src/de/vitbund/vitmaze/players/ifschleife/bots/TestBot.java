package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * Testbot für gezielte Testfälle für das Verhalten der Umgebung, einzelner
 * Methoden.
 * 
 * @author Helmut.Rietz
 *
 */
public class TestBot extends Bot {

	int i;

	/**
	 * 
	 * @param karte    - die Spielfeldkarte
	 * @param playerId - die ID des Spielers
	 * @param x        - die X-Koordinate also der Punkt auf der horizontalen Achse
	 * @param y        - die Y-Koordinate also der Punkt auf der vertikalen Achse
	 */
	public TestBot(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	@Override
	public void machAktion() {
		if (i < 3) {
			i++;
			this.nachNorden();
		}
		if (i == 3) {

			i++;
			// this.aktuelleKarte.ausgabe();
			this.beenden();
		}
		if (i == 4) {
			System.err.println("löschen");
			this.aktuelleKarte.flurFelderNullen();
			this.aktuelleKarte.ausgabe();
			i++;
			this.beenden();
		}
		if (i > 4) {
			System.err.println("setzen");
			this.aktuelleKarte.formularsucheEnde();
			this.aktuelleKarte.ausgabe();
			i++;
			this.beenden();
		}
	}

}
