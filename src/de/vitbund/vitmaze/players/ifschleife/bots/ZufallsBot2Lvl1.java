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
 *         nach dem Zufall seine Wegfindung ableitet. Er dient auch für
 *         Testzwecke bei Änderungen an den Supportklassen (Karte, Felder usw.)
 */
public class ZufallsBot2Lvl1 extends Bot {

	public ZufallsBot2Lvl1(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	private String letzteRichtung = "";

	public void machAktion() {

		if (("FINISH " + super.id + " 0").equals(Init.currentCellStatus)) {
			System.out.println("finish");
		}

//		int x = this.getPunkt().getX();
//		int y = this.getPunkt().getY();

//		//in die Bot Klasse verschoben
//		aktuelleKarte.aktualisiereFeld(getOrt().norden()/* y - 1 */, Init.northCellStatus);
//		aktuelleKarte.aktualisiereFeld(getOrt().sueden() /* y + 1 */, Init.southCellStatus);
//		aktuelleKarte.aktualisiereFeld(getOrt().osten()/* x + 1 */, Init.eastCellStatus);
//		aktuelleKarte.aktualisiereFeld(getOrt().westen()/* x - 1 */, Init.westCellStatus);
//		aktuelleKarte.aktualisiereFeld(getOrt(), Init.currentCellStatus);
//		if (aktuelleKarte.getFeld(getOrt()) != null) {
//			aktuelleKarte.getFeld(getOrt()).pruefenErkundet();
//		}
		this.rundeInitialisiern(); // TODO überlegen muss das für jeden Bot gemacht werden, wenn ja in die Init
									// verschieben?

		// test Wegfindung
//		LinkedHashMap<Feld, VorhergehenderSchritt> wege = getAktuelleKarte().findeWege(getPunkt());
//		getAktuelleKarte().ausgabeWegliste(wege);

		// testen der Funktion pruefenErkundet der Karte
		// aktuelleKarte.toSysErrErkundeteFelder();

//		System.err.print(
//				" Ort: " + this.getPunkt() + " erkundet " + this.aktuelleKarte.getFeld(getPunkt()).pruefenErkundet());

		schlauereZufallsrichtung();
	}

	public String schlauereZufallsrichtung() {

//		letzteRichtung = "";
		List<String> richtungsliste = new ArrayList<String>();

		if (!"WALL".equals(Init.northCellStatus)) {
			richtungsliste.add("Norden");
		}
		if (!"WALL".equals(Init.southCellStatus)) {
			richtungsliste.add("Sueden");
		}
		if (!"WALL".equals(Init.westCellStatus)) {
			richtungsliste.add("Westen");
		}
		if (!"WALL".equals(Init.eastCellStatus)) {
			richtungsliste.add("Osten");
		}

		System.err.println(richtungsliste.size());

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
// 			case 2,3,4 zusammengefasst weil die aufgrund der entfernung der letzten Richtung das selbe machen
		case 2:
		case 3:
		case 4:
			// removeIf um letzte Richtung aus der Liste zu entfernen und danach in eine
			// neue zu gehen

//				richtungsliste.removeIf(n -> (letzteRichtung.equals(n)));
//				kann man nich benutzen geht nicht -> TODO noch mal testen

			int index = -1;
			// System.err.println(richtungUmkehren(letzteRichtung));
			for (String string : richtungsliste) {
				// System.err.println("Liste: " + string);
				if (string.equals(richtungUmkehren(letzteRichtung))) {
					// zu riskant im foreach was zu entfernen, daher index speichern.
					index = richtungsliste.indexOf(string);

				}
			}
			// Element entfernen
			if (index > -1) {
				richtungsliste.remove(index);
			}

			// System.err.println("das ist der zweite spass: " + richtungsliste.size());

			int x = (int) (Math.random() * richtungsliste.size());
			letzteRichtung = richtungsliste.get(x);
			weiterGehen();

		default:
		}

		return "hallooooo";
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

//		return false; warum muss hier ein Return stehen? Für den Fall dass Case nicht
		// durchlaufen wird??? -> default hat gefehlt

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

//	public void letztesFeld() {
//		aktuelleKarte.isFeldBekannt(x, y);
//
//	}
}
