package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;
import de.vitbund.vitmaze.players.ifschleife.karte.VorhergehenderSchritt;

/**
 * Ein Bot an dem die Verwendung der navigation deutlich werden soll. Erbt von
 * der Klasse Bot.
 * 
 * @deprecated
 * 
 * @author IFSchleife
 * @see ErkundenderBotLvl2
 */
public class BspBotKartennutzung extends Bot {

	/**
	 * Erstellt einen neuen BspBotKartennutzung mit Karte, PlayerID und
	 * Start-Koordinaten.
	 * 
	 * @param karte    - die Spielfeldkarte
	 * @param playerId - Die ID des Spielers - üblicherweise Werte von 1 bis 4
	 * @param x        - Die X-Koordinate - die Position auf der horizontalen Achse
	 * @param y        - Die Y-Koordinate - die Position auf der vertikalen Achse
	 */
	public BspBotKartennutzung(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	@Override
	/**
	 * Überschreibt die Methode des Bots. Nutzt zur Wegfindung bereits den
	 * Wegfindungsalgorithmus.
	 */
	public void machAktion() {
		String richtung = null;

//		this.rundeInitialisiern(); //befindet sich in der Init

		// Wegeliste generieren
		LinkedHashMap<Feld, VorhergehenderSchritt> wege = this.getAktuelleKarte().findeWege(this.getOrt());

		// testausgaben
		// this.getAktuelleKarte().ausgabe();
		// this.getAktuelleKarte().toSysErrErkundeteFelder();
		System.err.println(Init.currentCellStatus);

		richtung = fahreZumUnerkundetenFeld(wege);// null => alles erkundet
		if (richtung != null) {
			fahren(richtung);
		}

		// zu testzwecken erst mal einfach finish versuchen damit keine
		// Zeitüberschreitung auftritt
		System.err.println("finish");
		System.err.println(this.aktuelleKarte.getZiel(id));
	}

	/**
	 * Die Methode lässt den Bot zu bisher unerkundeten Feldern fahren.
	 * 
	 * @param wege
	 * @return richtung - die Richtung in die der Bot fahren soll. Null wenn kein
	 *         unerkundetes Feld mehr übrig ist.
	 */
	private String fahreZumUnerkundetenFeld(LinkedHashMap<Feld, VorhergehenderSchritt> wege) {
		Feld ziel = null;

//		 Ich will jetzt die Felder durchgehen bis ich eins mit unerkundet gefunden habe.

		// LinkedHashMap selbst hat das Interface iterable nicht implementiert daher
		// geht wege nicht in der foreach, aber wege.entrySet gibt mir die Inhalte in
		// einer Form zurück die mir das erlaubt.
		for (Entry<Feld, VorhergehenderSchritt> element : wege.entrySet()) {

			// ich hab jetzt in "element" immer ein Eintrag in der Form
			// <Feld,VorhergehenderSchritt>, da hole ich mir jetzt das Feld raus und schaue
			// ob erkundet != true
			if (!element.getKey().pruefenErkundet()) {
				ziel = element.getKey();
				break;// for Schleife abbrechen, wir haben ja ein Ziel
			}
		}

		// falls kein Ziel gefunden wurde ist noch immer null drin, abfangen
		if (ziel == null) {
			System.err.println("Alles erkundet");
			return null;
		}

//		Weg zum Ziel bestimmen
//	 	dazu kann man die Methode werteListeAus nutzen, dann hat man den kompletten Weg in der Hand

		ArrayList<Feld> meinWeg = this.getAktuelleKarte().werteListeAus(wege, ziel);
		// der 1. Eintrag ist unser aktuelles feld, der zweite Eintrag enthält das
		// nächste das man ansteuern muss

		String richtung = Koordinaten.getRichtung(meinWeg.get(0).getPunkt(), meinWeg.get(1).getPunkt());
		// jetzt hab ich in richtung eine der Werte "Norden", "Sueden", "Westen" oder
		// "Osten" drin

		System.err.println(richtung);

		// Rückgabe
		return richtung;

	}

}
