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
	private String letzteRichtung = "";

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
		System.err.println("ini bot" + ort);
		// this.x = x;
		// this.y = y;
	}

	/**
	 * Die Methode wird von jeder Unterklasse individuell implementiert und dient
	 * dem aufruf der Entscheidungsfindung und Ausführung durch den jeweiligen Bot
	 */
	public abstract void machAktion();

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
		System.err.println("\nBotstandort " + ort);
		this.ort.setX(ort.getX() - 1);
		System.out.println("go west");
	}

	protected void nachSueden() {
		// this.y++;
		System.err.println("\nBotstandort " + ort);
		this.ort.setY(ort.getY() + 1);
		System.out.println("go south");
	}

	protected void nachOsten() {
		// this.x++;
		System.err.println("\nBotstandort " + ort);
		this.ort.setX(ort.getX() + 1);
		System.out.println("go east");
	}

	protected void nachNorden() {
		// this.y--;
		System.err.println("\nBotstandort " + ort);
		this.ort.setY(ort.getY() - 1);
		System.out.println("go north");
	}

	// bequemlichkeit übersetzung der strings in unsere Methoden
	public void fahren(String richtung) {

		if ("go west".equals(richtung)) {
			this.nachWesten();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.ort.getX() + " " + this.ort.getY());
			System.err.println("nach Western");
		}
		if ("go north".equals(richtung)) {
			this.nachNorden();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.ort.getX() + " " + this.ort.getY());
			System.err.println("nach Norden");
		}
		if ("go east".equals(richtung)) {
			this.nachOsten();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.ort.getX() + " " + this.ort.getY());
			System.err.println("nach osten");
		}
		if ("go south".equals(richtung)) {
			this.nachSueden();
			System.err.println("AB HIER VORHERIGE KARTE");
			System.err.println("Bot Standort: " + this.ort.getX() + " " + this.ort.getY());
			System.err.println("nach süden");
		}
		this.aktuelleKarte.ausgabe();
		System.err.flush();
	}

	public int getId() {
		return id;
	}

	public Koordinaten getOrt() {
		return ort;
	}

	public void rundeInitialisiern() {

		aktuelleKarte.aktualisiereFeld(getOrt().norden()/* y - 1 */, Init.northCellStatus);
		aktuelleKarte.aktualisiereFeld(getOrt().sueden() /* y + 1 */, Init.southCellStatus);
		aktuelleKarte.aktualisiereFeld(getOrt().osten()/* x + 1 */, Init.eastCellStatus);
		aktuelleKarte.aktualisiereFeld(getOrt().westen()/* x - 1 */, Init.westCellStatus);
		aktuelleKarte.aktualisiereFeld(getOrt(), Init.currentCellStatus);
		if (aktuelleKarte.getFeld(getOrt()) != null) {
			aktuelleKarte.getFeld(getOrt()).pruefenErkundet();
		}
		;
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

	// TODO Methode finish für alle Bots bereitstellen

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
}
