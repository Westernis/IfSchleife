package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
//TODO TK-JavaDocs: fertig

/**
 * Die Klasse stellt einen Bot dar, der ausschließlich nach
 * dem Zufall seine Wegfindung ableitet.
 * 
 * Aus Lernzwecken ist er noch enthalten.
 * 
 * @deprecated
 * @see ZufallsBot2Lvl1
 * @author IFSchleife
 * 
 * 
 */
public class ZufallsBotLvl1 extends Bot {

	/**
	 * Erstellt einen neuen ZufallsBotLvl1 mit Karte, PlayerID und Koordinaten.
	 * 
	 * @param karte    
	 * @param playerId Werte von 1-4
	 * @param x - die X-Koordinate
	 * @param y - die Y-Koordinate        
	 */
	public ZufallsBotLvl1(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	/**
	 * Überschreibt die Methode der Klasse Bot. Nutzt die Methode
	 * "nochSchlauereZufallsrichtung".
	 */
	public void machAktion() {

//		this.rundeInitialisiern(); //befindet sich nun in der Init
		nochSchlauereZufallsrichtung();
	}

	/**
	 * Wegfindung ausschließlich mit Zufallswerten. Prüft noch nicht mal auf Wände.
	 * 
	 * @return "go west", "go north", "go east" oder "go south" als String.
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
	 * Eine bessere Version der Zufallsrichtung. Nutzt die Methode
	 * {@code zufallsRichtung()} mit einer Schleife die auf Wände prüft
	 * ({@code wegGleichWand()}). Gibt nichts zurück sondern fährt direkt den Bot im
	 * Gegensatz zu {@link #zufallsRichtung()}.
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
	 * Die Methode überprüft ob eine übergebene Richtung eine Wand ist.
	 * 
	 * @param zufaelligeRichtung als String
	 * @return true wenn Richtung eine Wand ist; false wenn Richtung keine Wand ist.
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

	/**
	 * Lässt Bot in die Richtung weitergehen in die er im letzten Zug gegangen ist,
	 * damit ein ZufallsBot nicht im Kreis läuft wenn die Karte komplett leer wäre.
	 * Sondern gerade Wege läuft. Wertet die letzteRichtung des Bots aus.
	 */
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

	//TODO entfernen da leer?
	public void letztesFeld() {

	}
}
