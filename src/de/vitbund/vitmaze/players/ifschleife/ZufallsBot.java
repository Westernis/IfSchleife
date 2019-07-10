package de.vitbund.vitmaze.players.ifschleife;

import java.util.ArrayList;
import java.util.List;

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
//		System.out.println(zufallsRichtung());

		aktuelleKarte.aktualisiereFeld(x, y--, Init.northCellStatus);
		aktuelleKarte.aktualisiereFeld(x, y++, Init.southCellStatus);
		aktuelleKarte.aktualisiereFeld(x++, y, Init.eastCellStatus);
		aktuelleKarte.aktualisiereFeld(x--, y, Init.westCellStatus);
		aktuelleKarte.aktualisiereFeld(x, y, Init.currentCellStatus);

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

	public String schlauereZufallsrichtung() {

		int moeglicheRichtungen = 0;
		double zufallsZahl = Math.random();
		List<String> richtungsliste = new ArrayList<String>();

		if (!"WALL".equals(Init.northCellStatus)) {
			moeglicheRichtungen++;
			richtungsliste.add("Norden");
		}
		if (!"WALL".equals(Init.southCellStatus)) {
			moeglicheRichtungen++;
			richtungsliste.add("Sueden");
		}
		if (!"WALL".equals(Init.westCellStatus)) {
			moeglicheRichtungen++;
			richtungsliste.add("Westen");
		}
		if (!"WALL".equals(Init.eastCellStatus)) {
			moeglicheRichtungen++;
			richtungsliste.add("Osten");
		}

		switch (richtungsliste.size()) {

		case 1:

			if (richtungsliste.contains("Norden")) {
				nachNorden();
			} else if (richtungsliste.contains("Sueden")) {
				nachSueden();
			} else if (richtungsliste.contains("Westen")) {
				nachWesten();
			} else if (richtungsliste.contains("Osten")) {
				nachOsten();
			}

			break;

		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		}

		return "hallooooo";
	}

	public void nochSchlauereZufallsrichtung() {
		
	}
}
