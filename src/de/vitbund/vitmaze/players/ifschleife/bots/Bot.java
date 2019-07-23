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

	/**
	 * Diese Methode verarbeitet zu erst die Rückgabe bzgl. der letzten Aktion und
	 * führt gegebenenfalls Korrekturen durch. Danach werden die umliegenden
	 * Kartenfelder aktualisiert.
	 */
	public void rundeInitialisiern() {

		this.letzteAktionPruefen();

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

	// TODO warum wird hier mit == geprüft statt .equals()?
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

	/*
	 * Angestrebte Teilung: letzteAktionPruefen letzteAktionAufOKpruefen - prueft
	 * erst auf ok/nok - wenn nok dann weitere Prüfung wenn nicht dann fertig
	 * letzteAktionNachNOKPruefen
	 */
	/**
	 * 
	 * @return
	 * 
	 *         prüft die letze Aktion auf OK/NOK und wenn NOK dann auf die
	 *         nachfolgenden Informationen und leitet uU gewisse Korrekturen ein.
	 */
	public void letzteAktionPruefen() { // TODO: Boolean vs. void
		if (!this.letzeAktionAufOKpruefen()) { // wenn nicht ok, dann weitere Prüfung
			System.err.println("letzteAktion: in Verzweigung zur weiteren Pruefung. gesprungen");
			this.letzteAktionNachNOKpruefen();

		} else { // wenn ok dann mache nichts
			System.err.println("letzteAktion: in Verzweigung ohne Pruefung. gesprungen");
		}
	}

	public boolean letzeAktionAufOKpruefen() {
		/*
		 * Ziel ist nur einen Boolean zurückzugeben... wenn false uU andere Methode à
		 * was ist nicht ok aufrufen
		 */

		// erstmal den String auswerten
		String status;

		// nun status mit pot. NOK füllen
		status = (Init.lastActionsResult).substring(0, 2);
		System.err.println(status + " nach dem Aufruf von .substring...");
		if ("NOK".equals(status)) {
			System.err.println("Springt in Verzweigung: NOK");
			return false;
		} else {
			System.err.println("Springt in Verzweigung: !NOK - also OK");
			return true;
		}

	}

	/*
	 * hier soll die weitere Prüfung nach einem NOK rein... Ziel ist dass je nach
	 * Aktion die Koordinaten wieder zurück geändert werden.
	 */
	public void letzteAktionNachNOKpruefen() {
		System.err.println("Nun befindet er sich in der weitere Pruefung nach NOK...");
		String[] statusNachNOK;
		statusNachNOK = (Init.lastActionsResult).split(" ");
		// Annahme: status*[0] = OK/NOK; status*[1] = WRONGORDER

//		//Fehlerausgabe
//		for (int i = 0; i < statusNachNOK.length; i++) {
//			System.err.println("Stelle " + i + " " + statusNachNOK[i]);
//		}

		/*
		 * in der Switch-Anweisung die entsprechenden Änderungen à Koordinanten
		 * zurückändern vornehmen
		 */
		// wenn statusNachNOK leer -> abbrechen
		if (!(statusNachNOK == null)) {
			switch (statusNachNOK[1]) {
			case "TALKING":
				// mit anderem Bot verquatscht
				this.bewegungRueckgaengigMachen();
				break;

			case "BLOCKED":
				// wenn Bot gegen eine Wand gefahren ist oder Formular gegen Wand gekickt wurde
				// TODO: Koordinaten zurückändern???
				break;

			case "NOTSUPPORTED":
				// unbekannter Befehl abgesetzt: tue nichts
				break;
			case "WRONGORDER":
				// falsche Reihenfolge der Formulare: tue nichts
				break;

			case "NOTYOURS":
				// falsches Formular: tue nichts
				break;
			case "EMPTY":
				// kein Formular: tue nichts
				break;

			default:
				System.err.println("unbekannter / noch nicht abgedeckter Status in der Switch-Case...");
				break;
			}
		} else {
			System.err.println("keineStatusNachNOK!!! In Bot letzteAktionNachNOKPruefen");
		}
	}

	/**
	 * Korrigiert evtl. Koordinaten-Änderungen die bspw. durch das Quatschen mit
	 * einem Bot nicht wirksam wurden. #innere und aeußere Wirksamkeit eines VAs
	 * #VIT
	 */
	public void bewegungRueckgaengigMachen() {
		System.err.println("Ort vor Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
		switch (richtungUmkehren(this.letzteRichtung)) {
		/*
		 * Wenn versucht hat in Norden zu bewegen x-Koordinate belassen und y-Koordinate
		 * um 1 erhöhen. Süden: x belassen und y - 1 Westen: x + 1 und y belassen Osten:
		 * x - 1 und y belassen.
		 * 
		 * Dafür gibts bereits die Methoden der Klasse Koordinaten à .norden() die den
		 * KoordSatz einer nördlichen Bewegung zurückgibt.
		 * 
		 * TODO 1.0 - Erstmal Richtung umkehren von letzter Richtung damit switch case
		 * übersichtlich ist 1.1 - dann in entsprechende Cases springen 2.0 -
		 * Koordinaten korrigieren 2.1 - Wegelisten anpassen? 2.2 - Karte aktualisieren?
		 * Oder beinhaltet sie nur Feldtypen?
		 */
		case "Norden":
//			TODO aktuelleKarte.aktualisiereFeld(WennNördlicheBewegung);? Benötigt man's oder reichts ort anzupassen?
			// das wären schonmal die südlichen Koordinaten TODO aber wie und
			// wohin speichern?
			// aktueller ort wird mit dem einer fiktiven nördlichen Bewegung überschrieben.
			this.ort = (aktuelleKarte.getFeld(getOrt().norden())).getPunkt();
			System.err.println("Ort nach Nord-Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;

		case "Osten":
			this.ort = (aktuelleKarte.getFeld(getOrt().osten())).getPunkt();
			System.err.println("Ort nach Ost-Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;

		case "Sueden":
			this.ort = (aktuelleKarte.getFeld(getOrt().sueden())).getPunkt();
			System.err.println("Ort nach Sued-Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;
		case "Westen":
			this.ort = (aktuelleKarte.getFeld(getOrt().westen())).getPunkt();
			System.err.println("Ort nach West-Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;
		default:
			System.err.println("bewegungRueckgaengigMachen hat nicht funktioniert....");
			System.err.println("Ort ohne Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;
		}

	}

}
