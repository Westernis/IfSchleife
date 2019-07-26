package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 *
 *         Die Klasse stellt einen Bot dar (soll darstellen), der ausschließlich
 *         nach dem Zufall seine Wegfindung ableitet.
 */
public class CharlySheet extends Bot {

	public CharlySheet(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	public void machAktion() {

		if (Feld.formular.equals(Init.currentCell.getTyp()) /* && this.id != Init.currentCell.getPlayerID() */) {

			System.out.println("put");
		}

		if (Feld.flur.equals(Init.currentCell.getTyp())) {

			double x = Math.random();

			if (x > 0.97) {

				System.out.println("put");

			}

		}

		schlauereZufallsrichtung();
	}
}
