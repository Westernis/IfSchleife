package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * Testbot f�r gezielte Testf�lle f�r das Verhalten der Umgebung, einzelner
 * Methoden.
 * 
 * @author Helmut.Rietz
 *
 */
public class TestBot extends Bot {

	int i;

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
			//this.aktuelleKarte.ausgabe();
			this.beenden();
		}
		if (i == 4) {
			System.err.println("l�schen");
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
