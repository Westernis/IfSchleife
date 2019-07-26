package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
//TODO TK-JavaDocs: fertig
/**
 * Die Klasse stellt einen Bot dar, der ausschließlich nach dem Zufall seine
 * Wegfindung ableitet. Er dient auch für Testzwecke bei Änderungen an den
 * Supportklassen (Karte, Felder usw.)
 * 
 * 
 * Aktualisiert und verbessert den ZufallsBotLvl1
 * 
 * @see ZufallsBotLvl1 
 * @author IFSchleife
 */
public class ZufallsBot2Lvl1 extends Bot {
	/**
	 * Erstellt einen ZufallsBot2Lvl1 mit Karte, PlayerID und Koordinaten.
	 * 
	 * @param karte    
	 * @param playerId (Werte von 1-4)
	 * @param x - die X-Koordinate
	 * @param y - die X-Koordinate
	 */
	public ZufallsBot2Lvl1(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	/**
	 * Überschreibt die Methode der Elternklasse Bot. Wenn das aktuelle Feld ein
	 * Sachbearbeiter ist {@code if (("FINISH " + super.id + " 0")...} beende das Spiel. Sonst nutze
	 * die Methode {@link #schlauereZufallsrichtung()}).
	 * 
	 */
	public void machAktion() {

		if (("FINISH " + super.id + " 0").equals(Init.currentCellStatus)) {
			System.out.println("finish");
		}

		//TODO löschen, weil nicht benötigt?
		
//		this.rundeInitialisiern(); //befindet sich nun in der Init									
		// test Wegfindung
//		LinkedHashMap<Feld, VorhergehenderSchritt> wege = getAktuelleKarte().findeWege(getPunkt());
//		getAktuelleKarte().ausgabeWegliste(wege);

		// testen der Funktion pruefenErkundet der Karte
//		 aktuelleKarte.toSysErrErkundeteFelder();
//		 aktuelleKarte.ausgabe();

//		System.err.print(
//				" Ort: " + this.getPunkt() + " erkundet " + this.aktuelleKarte.getFeld(getPunkt()).pruefenErkundet());

		schlauereZufallsrichtung();
	}

//	public void letztesFeld() {
//		aktuelleKarte.isFeldBekannt(x, y);
//
//	}
}
