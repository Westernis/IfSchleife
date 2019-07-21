package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;

/**
 * 
 * @author IFSchleife
 *
 *         Die Grundklasse für alle anderen Bots.
 */
public abstract class Bot {

	// die Karte, die er gerade erkundet
	protected Karte aktuelleKarte;
	protected String letzteRichtung = "";

	public Karte getAktuelleKarte() {
		return aktuelleKarte;
	}

	public void setAktuelleKarte(Karte aktuelleKarte) {
		this.aktuelleKarte = aktuelleKarte;
	}

	protected final int id;

	// Die aktuellen Koordinaten des Bots
	// protected int x;
	// protected int y;
	private Koordinaten ort;

	/**
	 * 
	 * @param karte
	 * @param playerId
	 * @param x
	 * @param y
	 */
	public Bot(Karte karte, int playerId, int x, int y) {
		this.aktuelleKarte = karte;
		this.id = playerId;
		ort = new Koordinaten(x, y);
		// System.err.println("ini bot" + ort);
		// this.x = x;
		// this.y = y;
	}

	/**
	 * Die Methode wird von jeder Unterklasse individuell implementiert und dient
	 * dem aufruf der Entscheidungsfindung und Ausführung durch den jeweiligen Bot
	 */
	public abstract void machAktion();
	// LEARN Was ist der Unterschied zwischen einer abstrakten Methode und einer
	// statischen?
	// Beide sind Instanzunabhängig oder? Aber von Abstraktem kann man keine
	// Instanzen bilden -> muss man überschreiben
	// und eine statische kann man Instanzunabhängig ausführen...

	// Bei Erweiterung hier das Hirn einbauen, die Schleife befindet sich in der
	// Klasse Init

	// Methode als boolean lassen??

	/**
	 * mögliche Inputs Norden Sueden Osten Westen
	 * 
	 * @return gegenteil, bei falscher eingabe -> null
	 */
	public String richtungUmkehren(String richtung) {
		switch (richtung) {
		case "Norden":
			return "Sueden";
		case "Sueden":
			return "Norden";
		case "Osten":
			return "Westen";
		case "Westen":
			return "Osten";
		default:
			return null;
		}
	}

	// Bewegungsfunktionen
	protected void nachWesten() {
		// this.x--;
		// System.err.println("\nBotstandort " + ort);
		this.ort = this.ort.westen();
		System.out.println("go west");
	}

	protected void nachSueden() {
		// this.y++;
		// System.err.println("\nBotstandort " + ort);
		this.ort = this.ort.sueden();
		System.out.println("go south");
	}

	protected void nachOsten() {
		// this.x++;
		// System.err.println("\nBotstandort " + ort);
		this.ort = this.ort.osten();
		System.out.println("go east");
	}

	protected void nachNorden() {
		// this.y--;
		// System.err.println("\nBotstandort " + ort);
		this.ort = this.ort.norden();
		System.out.println("go north");
	}

	// bequemlichkeit übersetzung der strings in unsere Methoden
	public void fahren(String richtung) {
		if ("Westen".equals(richtung)) {
			this.nachWesten();
//			System.err.println("AB HIER VORHERIGE KARTE");
//			System.err.println("Bot Standort: " + this.ort.getX() + " " + this.ort.getY());
//			System.err.println(" nach Western");
		}
		if ("Norden".equals(richtung)) {
			this.nachNorden();
//			System.err.println("AB HIER VORHERIGE KARTE");
//			System.err.println("Bot Standort: " + this.ort.getX() + " " + this.ort.getY());
//			System.err.println(" nach Norden");
		}
		if ("Osten".equals(richtung)) {
			this.nachOsten();
//			System.err.println("AB HIER VORHERIGE KARTE");
//			System.err.println("Bot Standort: " + this.ort.getX() + " " + this.ort.getY());
//			System.err.println(" nach osten");
		}
		if ("Sueden".equals(richtung)) {
			this.nachSueden();
//			System.err.println("AB HIER VORHERIGE KARTE");
//			System.err.println("Bot Standort: " + this.ort.getX() + " " + this.ort.getY());
//			System.err.println(" nach süden");
		}
		// this.aktuelleKarte.ausgabe();
		// System.err.flush();
	}

	public int getId() {
		return id;
	}

	public Koordinaten getOrt() {
		return ort;
	}

	public void rundeInitialisiern() {

		aktuelleKarte.aktualisiereFeld(getOrt(), Init.currentCell);
		aktuelleKarte.aktualisiereFeld(getOrt().norden(), Init.northCell);
		aktuelleKarte.aktualisiereFeld(getOrt().sueden(), Init.southCell);
		aktuelleKarte.aktualisiereFeld(getOrt().osten(), Init.eastCell);
		aktuelleKarte.aktualisiereFeld(getOrt().westen(), Init.westCell);
		aktuelleKarte.aktualisiereFeld(getOrt(), Init.currentCell);
		if (aktuelleKarte.getFeld(getOrt()) != null) {
			aktuelleKarte.getFeld(getOrt()).pruefenErkundet();
		}
	}

	protected void aufsammeln() {
		System.out.println("take");
	}

	protected void beenden() {
		System.out.println("finish");
	}

//	public int getX() {
//		return this.punkt.getX();
//	}

//	public int getY() {
//		return this.punkt.getY();
//	}

	/**
	 * 
	 * @return
	 * 
	 *         Die Methode basiert auf einer zufälligen Weg"findung". Zusätzlich
	 *         implementiert wurde: - eine Erkennung ob eine Wand in der Nähe ist -
	 *         eine Entscheidung auf Basis der freien Richtungen.
	 */
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

		// hier wird kontrolliert wie viele freie Felder in der Nähe sind
		switch (richtungsliste.size()) {

		// wenn nur ein Weg frei ist, gehe den freien Weg
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
//				break; benötigt man nicht, weil alle Fälle mit Return beendet werden?

		case "go east":
			if ("WALL".equals(Init.eastCellStatus)) {
				return true;
			} else
				return false;
//				break;

		case "go north":
			if ("WALL".equals(Init.northCellStatus)) {
				return true;
			} else
				return false;
//				break;

		case "go south":
			if ("WALL".equals(Init.southCellStatus)) {
				return true;
			} else
				return false;
//				break;
		default:
			return true;
		}

//			return false; warum muss hier ein Return stehen? Für den Fall dass Case nicht
		// durchlaufen wird??? -> default hat gefehlt

	}

	/**
	 * @return letzteRichtung
	 */
	public String getLetzteRichtung() {
		return letzteRichtung;
	}

	/**
	 * @param letzteRichtung das zu setzende Objekt letzteRichtung
	 */
	public void setLetzteRichtung(String letzteRichtung) {
		this.letzteRichtung = letzteRichtung;
	}

}
