package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * Dieser Bot verteilt zufällig Zettel auf der Karte.
 * 
 * @see Bot
 * @author IFSchleife
 */
public class CharlySheet extends Bot {

	private int sheetCount;

	/**
	 * 
	 * @param karte      - die Spielfeldkarte
	 * @param playerId   - Die ID des Spielers - üblicherweise Werte von 1 bis 4
	 * @param x          die X-Koordinate - die Position auf der horizontalen Achse
	 * @param y          die Y-Koordinate - die Position auf der vertikalen Achse
	 * @param sheetCount Anzahl der Zettel die man legen kann.
	 */
	public CharlySheet(Karte karte, int playerId, int x, int y, int sheetCount) {
		super(karte, playerId, x, y);
		this.sheetCount = sheetCount;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void machAktion() {

		if (Feld.formular.equals(Init.currentCell.getTyp()) && this.id != Init.currentCell.getPlayerID()) {

			legeZettel();
		}

		if (Feld.flur.equals(Init.currentCell.getTyp())) {

			double x = Math.random();
			if (x > 0.97) {

				legeZettel();
			}
		}
		this.nachWesten();
		// schlauereZufallsrichtung();
	}

	/**
	 * Legt Zettel auf die Karte, solange Zettel vorhanden sind.
	 */
	private void legeZettel() {// TODO NOK berücksichtigen, ist aber eh nur testbot
		if (sheetCount > 0) {
			System.out.println("put");
			sheetCount--;
		}
	}
}
