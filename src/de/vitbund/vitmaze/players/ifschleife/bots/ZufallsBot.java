package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.Init;
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
	}

	private String letzteRichtung = "";

	public void machAktion() {

		aktuelleKarte.aktualisiereFeld(getPunkt().norden()/* y - 1 */, Init.northCellStatus);
		aktuelleKarte.aktualisiereFeld(getPunkt().sueden() /* y + 1 */, Init.southCellStatus);
		aktuelleKarte.aktualisiereFeld(getPunkt().osten()/* x + 1 */, Init.eastCellStatus);
		aktuelleKarte.aktualisiereFeld(getPunkt().westen()/* x - 1 */, Init.westCellStatus);
		aktuelleKarte.aktualisiereFeld(getPunkt(), Init.currentCellStatus);

		nochSchlauereZufallsrichtung();
	}

	/**
	 * Eine Art eine Zufallsrichtung zu implementieren. TODO Prüfungen à "ist da
	 * eine Wand" oder "ist da ein SB" einbauen.
	 * 
	 * @return
	 */
	private String zufallsRichtung() {
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

	/**
	 * eine noch bessere Version des ZufallsWegFindungs-Algorithmus, nun mit Prüfung
	 * ob Wände im Weg sind...
	 */
	public void nochSchlauereZufallsrichtung() {
		/*
		 * hier dachte ich an eine do-while, die erstmal Zufallsweg generiert und dann
		 * prüft obs eine Wand war und dann neuen Zufallsweg sucht.
		 * 
		 * Schöner wärs mit Objekten als Rückgabe mit Methoden à linkeRichtung.gehe();
		 */
		String zufaelligeRichtung = "";
		do {
			zufaelligeRichtung = zufallsRichtung();

		} while (wegGleichWand(zufaelligeRichtung)); // prüfung ob wand

		this.fahren(zufaelligeRichtung);

	}

	/**
	 * Die Methode überprüft ob ein Weg eine Wand ist.
	 * 
	 * @return
	 */
	public boolean wegGleichWand(String zufaelligeRichtung) {
		switch (zufaelligeRichtung) {
		case "go west":
			if ("WALL".equals(Init.westCellStatus)) {
				return true;
			} else
				return false;
//			break; benötigt man nicht, weil alle Fälle mit Return beendet werden?

		case "go east":
			if ("WALL".equals(Init.eastCellStatus)) {
				return true;
			} else
				return false;
//			break;

		case "go north":
			if ("WALL".equals(Init.northCellStatus)) {
				return true;
			} else
				return false;
//			break;

		case "go south":
			if ("WALL".equals(Init.southCellStatus)) {
				return true;
			} else
				return false;
//			break;
		default:
			return true;
		}

		// return false; //warum muss hier ein Return stehen? Für den Fall dass
		// SCase nicht
		// durchlaufen wird??? -> default hat gefehlt

//		return true;
	}

	public void weiterGehen() {
		if (letzteRichtung == "Norden") {
			nachNorden();
		} else if (letzteRichtung == "Sueden") {
			nachSueden();
		} else if (letzteRichtung == "Westen") {
			nachWesten();
		} else if (letzteRichtung == "Osten") {
			nachOsten();
		}
	}

	public void letztesFeld() {

	}
}
