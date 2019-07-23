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
 * Ein Bot an dem die Verwendung der navigation deutlich werden sollte
 * 
 * @author helmut.rietz
 *
 */
public class BspBotKartennutzung extends Bot {

	public BspBotKartennutzung(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	@Override
	public void machAktion() {
		String richtung = null;

		// input verarbeiten //TODO in die init verschieben weil in allen Bots drin?
//		this.rundeInitialisiern(); //befindet sich in der Init

		// Wegeliste generieren
		LinkedHashMap<Feld, VorhergehenderSchritt> wege = this.getAktuelleKarte().findeWege(this.getOrt());

		// testausgaben
		// this.getAktuelleKarte().ausgabe();
		// this.getAktuelleKarte().toSysErrErkundeteFelder();
		System.err.println(Init.currentCellStatus);

		richtung = fahreZumUnerkundetenFeld(wege);//null => alles erkundet 
		if (richtung != null) {
			fahren(richtung);
		}

		// zu testzwecken erst mal einfach finish versuchen damit keine
		// Zeit�berschreitung auftritt
		// FIXME sinnvolle alternative
		System.err.println("finish");
		System.err.println(this.aktuelleKarte.getZiel(id));
	}

	// TODO sollte wahrscheinlich in die Botklasse rein, da es alle brauchen
	private String fahreZumUnerkundetenFeld(LinkedHashMap<Feld, VorhergehenderSchritt> wege) {
		Feld ziel = null;

//		 Ich will jetzt die Felder durchgehen bis ich eins mit unerkundet gefunden habe.

		// LinkedHashMap selbst hat das Interface iterable nicht implementiert daher
		// geht wege nicht in der foreach, aber wege.entrySet gibt mir die Inhalte in
		// einer Form zur�ck die mir das erlaubt.
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
		// der 1. Eintrag ist unser aktuelles feld, der zweite Eintrag enth�lt das
		// n�chste das man ansteuern muss

		String richtung = Koordinaten.getRichtung(meinWeg.get(0).getPunkt(), meinWeg.get(1).getPunkt());
		// jetzt hab ich in richtung eine der Werte "Norden", "Sueden", "Westen" oder
		// "Osten" drin

		System.err.println(richtung);

		// R�ckgabe
		return richtung;

	}

}
