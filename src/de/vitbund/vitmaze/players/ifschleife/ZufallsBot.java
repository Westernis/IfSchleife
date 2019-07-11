package de.vitbund.vitmaze.players.ifschleife;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 *
 *         Die Klasse stellt einen Bot dar (soll darstellen), der ausschlie�lich
 *         nach dem Zufall seine Wegfindung ableitet.
 */
public class ZufallsBot extends Bot {

	public ZufallsBot(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		// TODO Automatisch generierter Konstruktorstub
	}

	/**
	 * TODO: testen f�ge in machAktion() die aktuelle Implementierung von
	 * zufallsRichtung() aus und gebe es aus.
	 */

	private String letzteRichtung = "";

	public void machAktion() {

		aktuelleKarte.aktualisiereFeld(x, y - 1, Init.northCellStatus);
		aktuelleKarte.aktualisiereFeld(x, y + 1, Init.southCellStatus);
		aktuelleKarte.aktualisiereFeld(x + 1, y, Init.eastCellStatus);
		aktuelleKarte.aktualisiereFeld(x - 1, y, Init.westCellStatus);
		aktuelleKarte.aktualisiereFeld(x, y, Init.currentCellStatus);

		nochSchlauereZufallsrichtung();
	}

	/**
	 * Eine Art eine Zufallsrichtung zu implementieren. TODO Pr�fungen � "ist da
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

	/*
	 * TODO: fertig implementieren
	 */

	public String schlauereZufallsrichtung() {

		int moeglicheRichtungen = 0;
		letzteRichtung = "";
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

		if ("OK NORTH".equals(Init.lastActionsResult)) {
			letzteRichtung = "Norden";
		} else if ("OK SOUTH".equals(Init.lastActionsResult)) {
			letzteRichtung = "Sueden";
		} else if ("OK EAST".equals(Init.lastActionsResult)) {
			letzteRichtung = "Osten";
		} else if ("OK WEST".equals(Init.lastActionsResult)) {
			letzteRichtung = "Westen";
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

			if (richtungsliste.contains(letzteRichtung)) {
				weiterGehen();
			} else

				break;
		case 3:
			if (richtungsliste.contains(letzteRichtung)) {
				weiterGehen();
			} else

				break;
		case 4:
			if (richtungsliste.contains(letzteRichtung)) {
				weiterGehen();
			} else

				break;
		}

		return "hallooooo";
	}

	/**
	 * eine noch bessere Version des ZufallsWegFindungs-Algorithmus, nun mit Pr�fung
	 * ob W�nde im Weg sind...
	 */
	public void nochSchlauereZufallsrichtung() {
		/*
		 * hier dachte ich an eine do-while, die erstmal Zufallsweg generiert und dann
		 * pr�ft obs eine Wand war und dann neuen Zufallsweg sucht.
		 * 
		 * Sch�ner w�rs mit Objekten als R�ckgabe mit Methoden � linkeRichtung.gehe();
		 */
		String zufaelligeRichtung = "";
		do {
			zufaelligeRichtung = zufallsRichtung();

		} while (wegGleichWand(zufaelligeRichtung)); // pr�fung ob wand

		this.fahren(zufaelligeRichtung);

	}

	/**
	 * Die Methode �berpr�ft ob ein Weg eine Wand ist.
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
//			break; ben�tigt man nicht, weil alle F�lle mit Return beendet werden?

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

		// return false; // TODO: warum muss hier ein Return stehen? F�r den Fall dass
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
