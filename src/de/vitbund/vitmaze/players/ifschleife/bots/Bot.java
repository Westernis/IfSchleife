package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;

/**
 *
 * Die Grundklasse für alle anderen Bots. Die Prüfung auf korrekte Ausführung
 * der Felder muss ab Level 5 im jeweiligen Bot überschrieben bzw ergänzt
 * werden, da hierfür Informationen rundenübergreifend bereitstehen müssen.
 * 
 * @author IFSchleife
 */
public abstract class Bot {

	protected Karte aktuelleKarte;

	/**
	 * letzteRichtung unterstützt Strings à "Norden", "Osten", "Sueden" und
	 * "Westen".
	 */
	protected String letzteRichtung = "";

	/**
	 * 
	 * @return aktuelleKarte
	 */
	public Karte getAktuelleKarte() {
		return aktuelleKarte;
	}

	/**
	 * Setzt die aktuelleKarte
	 * 
	 * @param aktuelleKarte die neue Karte
	 */
	public void setAktuelleKarte(Karte aktuelleKarte) {
		this.aktuelleKarte = aktuelleKarte;
	}

	/**
	 * Die PlayerID unterstützt beliebige Zahlenwerte. Sie stellt die SpielerID dar.
	 * In Vitmaze wurden zu dem Datum Spieler-IDs zwischen 1 und 4 vergeben.
	 */
	protected final int id;

	private Koordinaten ort;

	/**
	 * Erstellt einen Bot mit einer Karte, einer PlayerID und den aktuellen
	 * Koordinaten (Startkoordinaten).
	 * 
	 * @param karte    - die Spielfeldkarte
	 * @param playerId - die ID des Spielers
	 * @param x        - die X-Koordinate also der Punkt auf der horizontalen Achse
	 * @param y        - die Y-Koordinate also der Punkt auf der vertikalen Achse
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
	 * dem Aufruf der Entscheidungsfindung und Ausführung durch den jeweiligen Bot
	 * 
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
	 * Die Methode wandelt eine Richtung in die gegenteilige Richtung um. Mögliche
	 * Inputs sind "Norden", "Sueden", "Osten", oder "Westen"
	 * 
	 * @param richtung das was von der Methode umgekehrt werden soll.
	 * @return Gegenteil der eingegebenen Richtung, bei falscher Eingabe wird null
	 *         zurückgegeben.
	 * 
	 */
	public String richtungUmkehren(String richtung) {
//		System.err.println("fahre in: "+richtung);
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

	/**
	 * Bewegt den Bot nach Westen und ändert die Koordinaten.
	 */
	protected void nachWesten() {
		this.ort = this.ort.westen();
		System.out.println("go west");
	}

	/**
	 * Bewegt den Bot nach Sueden und ändert die Koordinaten.
	 */
	protected void nachSueden() {
		this.ort = this.ort.sueden();
		System.out.println("go south");
	}

	/**
	 * Bewegt den Bot nach Osten und ändert die Koordinaten.
	 */
	protected void nachOsten() {
		this.ort = this.ort.osten();
		System.out.println("go east");
	}

	/**
	 * Bewegt den Bot nach Norden und ändert die Koordinaten.
	 */
	protected void nachNorden() {
		this.ort = this.ort.norden();
		System.out.println("go north");
	}

	/**
	 * Uebersetzt je nach uebergebener Richtung in Bewegungs-Methoden.
	 * 
	 * @param richtung in die der Bot fahren soll. Mögliche Richtungen sind
	 *                 "Westen", "Norden", "Osten" und "Sueden".
	 */
	public void fahren(String richtung) {
		if ("Westen".equals(richtung)) {
			this.nachWesten();
		}
		if ("Norden".equals(richtung)) {
			this.nachNorden();
		}
		if ("Osten".equals(richtung)) {
			this.nachOsten();
		}
		if ("Sueden".equals(richtung)) {
			this.nachSueden();
		}
		// this.aktuelleKarte.ausgabe();
		// System.err.flush();
	}

	/**
	 * 
	 * @return id - die PlayerID des Spielers.
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return ort an dem sich der Bot aktuell befindet in Form von X- und
	 *         Y-Koordinaten
	 */
	public Koordinaten getOrt() {
		return ort;
	}

	/**
	 * Diese Methode verarbeitet zuerst die Rückgabe bzgl. der letzten Aktion und
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
			// Falls der aktuelle Standort ein Zettel ist auf erkundet setzen
			if (aktuelleKarte.getFeld(getOrt()).getTyp().equals(Feld.zettel)) {
				aktuelleKarte.getFeld(getOrt()).setErkundet(true);
			}
		}
	}

	/**
	 * Animiert den Bot etwas aufzuheben mit der Ausgabe "take".
	 */
	protected void aufsammeln() {
		System.out.println("take");
	}

	/**
	 * Animiert den Bot einen Zug zu beenden mit der Ausgabe "finish".
	 */
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
	 * Die Methode basiert auf einer zufälligen Wegfindung. Zusätzlich implementiert
	 * wurde eine Erkennung ob eine Wand in der Nähe ist und eine Entscheidung auf
	 * Basis der freien Richtungen. Diese Entscheidung berücksichtig wie viele freie
	 * Felder in der Nähe sind. Wenn eins: gehe in die Richtung. Wenn mehr als 1:
	 * gehe nicht zurück sondern in neue Richtung. Die neue Richtung wird zufällig
	 * ausgewählt. Bewegt den Bot nach der Entscheidung.
	 * 
	 * @return Gibt die Richtung, in die gefahren wurde zurück.
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

//		System.err.println(richtungsliste.size());

		if ("OK NORTH".equals(Init.lastActionsResult)) {
			letzteRichtung = "Norden";
		} else if ("OK SOUTH".equals(Init.lastActionsResult)) {
			letzteRichtung = "Sueden";
		} else if ("OK EAST".equals(Init.lastActionsResult)) {
			letzteRichtung = "Osten";
		} else if ("OK WEST".equals(Init.lastActionsResult)) {
			letzteRichtung = "Westen";
		} // else letzteRichtung ="Westen";

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
		return letzteRichtung;
	}

	/**
	 * Bewegt den Bot weiter in die Richtung in die er im letzten Zug gegangen ist.
	 * Berücksichtigt dabei das Attribut {@code letzteRichtung}.
	 */
	public void weiterGehen() {
		if ("Norden".equals(letzteRichtung)) {
			nachNorden();
		} else if ("Sueden".equals(letzteRichtung)) {
			nachSueden();
		} else if ("Westen".equals(letzteRichtung)) {
			nachWesten();
		} else if ("Osten".equals(letzteRichtung)) {
			nachOsten();
		}
	}

	/**
	 * Die Methode überprüft ob ein Weg eine Wand ist.
	 * 
	 * @param zufaelligeRichtung Die Richtung die überprüft werden soll
	 * @return {@code true}, wenn uebergebene Richtung eine Wand ist. {@code false}
	 *         wenn uebergebene Richtung eine Wand ist.
	 */
	public boolean wegGleichWand(String zufaelligeRichtung) {
		switch (zufaelligeRichtung) {
		case "go west":
			if (Feld.wand.equals(Init.westCellStatus)) {
				return true;
			} else
				return false;

		case "go east":
			if (Feld.wand.equals(Init.eastCellStatus)) {
				return true;
			} else
				return false;

		case "go north":
			if (Feld.wand.equals(Init.northCellStatus)) {
				return true;
			} else
				return false;

		case "go south":
			if (Feld.wand.equals(Init.southCellStatus)) {
				return true;
			} else
				return false;

		default:
			return true;
		}

		// return false; warum muss hier ein Return stehen? Für den Fall dass Case nicht
		// durchlaufen wird??? -> default hatte gefehlt

	}

	/**
	 * @return letzteRichtung in die der Bot gefahren ist. String à "Norden",
	 *         "Osten", "Sueden" oder "Westen".
	 */
	public String getLetzteRichtung() {
		return letzteRichtung;
	}

	/**
	 * Setzt die letzte Richtung des Bots
	 * 
	 * @param letzteRichtung à "Norden", "Osten", "Sueden" oder "Westen".
	 */
	public void setLetzteRichtung(String letzteRichtung) {
		this.letzteRichtung = letzteRichtung;
	}

	/**
	 * Prueft die letze Aktion auf OK/NOK. Wenn NOK: prueft dann auf die
	 * nachfolgenden Informationen und leitet Korrekturen bzgl. der Koordinaten ein.
	 */
	public void letzteAktionPruefen() { // Boolean vs. void
		if (!this.letzeAktionAufOKpruefen()) { // wenn nicht ok, dann weitere Prüfung
			// System.err.println("letzteAktion: in Verzweigung zur weiteren Pruefung.
			// gesprungen");
			this.letzteAktionNachNOKpruefen();

		} else { // wenn ok dann mache nichts
			// System.err.println("letzteAktion: in Verzweigung ohne Pruefung. gesprungen");
		}
	}

	/**
	 * Prueft den ersten Teil der Rueckgabe der letzten Aktion. Mögliche Werte des
	 * Status sind OK und NOK.
	 * 
	 * @return true wenn der Status der letzen Aktion ok ist; false wenn der Status
	 *         NOK ist.
	 */
	public boolean letzeAktionAufOKpruefen() {
		/*
		 * Ziel ist nur einen Boolean zurückzugeben... wenn false uU andere Methode à
		 * was ist nicht ok aufrufen
		 */

		// erstmal den String auswerten
		String status;

		// nun status mit pot. NOK füllen
		// status = (Init.lastActionsResult).substring(0, 2);
		// .substring problematische weil OK und NOK unterschiedliche Länge haben

		status = (Init.lastActionsResult).split(" ", 2)[0];
		// split teilt hier am Leerzeichen in max 2 Strings,
//		System.err.println(status + " nach dem Aufruf von .substring...");

		if ("NOK".equals(status)) {
//			System.err.println("Springt in Verzweigung: NOK");
			return false;
		} else {
//			System.err.println("Springt in Verzweigung: !NOK - also OK");
			return true;
		}

	}

	/*
	 * hier soll die weitere Prüfung nach einem NOK rein... Ziel ist dass je nach
	 * Aktion die Koordinaten wieder zurück geändert werden.
	 */
	/**
	 * Prueft den Teil des Status der letzten Aktion nach dem NOK. Mögliche Werte
	 * sind TALKING, BLOCKED, NOTSUPPORTED, WRONGORDER, NOTYOURS, EMPTY.
	 */
	public void letzteAktionNachNOKpruefen() {
//		System.err.println("Nun befindet er sich in der weitere Pruefung nach NOK...");
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
//				System.err.println("koord korrigieren");
				this.bewegungRueckgaengigMachen();
				break;

			case "BLOCKED":
				// wenn Bot gegen eine Wand gefahren ist oder Formular gegen Wand gekickt wurde
				// TODO - Koordinaten zurückändern beim gegen die Wand fahren, nicht dringend,
				// rest tue nix
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
//				System.err.println("unbekannter / noch nicht abgedeckter Status in der Switch-Case...");
				break;
			}
		} else {
//			System.err.println("keineStatusNachNOK!!! In Bot letzteAktionNachNOKPruefen");
		}
	}

	/**
	 * Korrigiert Koordinaten-Änderungen die bspw. durch das Quatschen mit einem Bot
	 * nicht wirksam wurden. Nicht verwechseln mit der inneren und aeußeren
	 * Wirksamkeit eines VAs #VIT. Berücksichtigt die letzteRichtung und wandelt sie
	 * in die entgegengesetze um, damit sich der Bot nicht in die falsche Richtung
	 * zerkorrigiert.
	 */
	public void bewegungRueckgaengigMachen() {
//		System.err.println("Ort vor Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
		String korrekturRichtung = richtungUmkehren(this.letzteRichtung);
		if (korrekturRichtung == null) {
			return; // Annahme wenn RichtungUmkehren null liefert sind wir letzte Runde gar nicht
					// gefahren
		}
		switch (korrekturRichtung) {
		/*
		 * Wenn versucht hat in Norden zu bewegen x-Koordinate belassen und y-Koordinate
		 * um 1 erhöhen. Süden: x belassen und y - 1 Westen: x + 1 und y belassen Osten:
		 * x - 1 und y belassen.
		 * 
		 * Dafür gibts bereits die Methoden der Klasse Koordinaten à .norden() die den
		 * KoordSatz einer nördlichen Bewegung zurückgibt.
		 * 
		 * Erstmal Richtung umkehren von letzter Richtung damit switch case
		 * übersichtlich ist 1.1 - dann in entsprechende Cases springen 2.0 -
		 * Koordinaten korrigieren 2.1 - Wegelisten anpassen? 2.2 - Karte aktualisieren?
		 * Oder beinhaltet sie nur Feldtypen?
		 */
		case "Norden":
			this.ort = (aktuelleKarte.getFeld(getOrt().norden())).getPunkt();
//			System.err.println("Ort nach Nord-Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;

		case "Osten":
			this.ort = (aktuelleKarte.getFeld(getOrt().osten())).getPunkt();
//			System.err.println("Ort nach Ost-Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;

		case "Sueden":
			this.ort = (aktuelleKarte.getFeld(getOrt().sueden())).getPunkt();
//			System.err.println("Ort nach Sued-Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;
		case "Westen":
			this.ort = (aktuelleKarte.getFeld(getOrt().westen())).getPunkt();
//			System.err.println("Ort nach West-Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;
		default:
//			System.err.println("bewegungRueckgaengigMachen hat nicht funktioniert....");
//			System.err.println("Ort ohne Aenderung: " + this.ort.getX() + " + " + this.ort.getY());
			break;
		}

	}

}
