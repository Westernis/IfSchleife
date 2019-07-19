package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;
import de.vitbund.vitmaze.players.ifschleife.karte.VorhergehenderSchritt;

public class BspBotKartennutzung extends Bot {

	public BspBotKartennutzung(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	@Override
	public void machAktion() {
		// input verarbeiten
		this.rundeInitialisiern();
		// Wegeliste generieren
		LinkedHashMap<Feld, VorhergehenderSchritt> wege = this.getAktuelleKarte().findeWege(this.getOrt());
		
		
		//testausgaben
		this.getAktuelleKarte().ausgabe();
		this.getAktuelleKarte().toSysErrErkundeteFelder();

		
		fahreZumUnerkundetenFeld(wege);


	}
	
	private void fahreZumUnerkundetenFeld(LinkedHashMap<Feld, VorhergehenderSchritt> wege) {
		// this.aktuelleKarte.ausgabeWegliste(wege);
		// Zielfeld
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

			// zu testzwecken erst mal einfach finish versuchen damit keine
			// Zeitüberschreitung auftritt
			// FIXME sinnvolle alternative
			System.err.println("finish");
			return;
		}

//		Weg zum Ziel bestimmen
//	 	dazu kann man die Methode werteListeAus nutzen, dann hat man den kompletten Weg in der Hand

		ArrayList<Feld> meinWeg = this.getAktuelleKarte().werteListeAus(wege, ziel);
		// der 1. Eintrag ist unser aktuelles feld, der zweite Eintrag enthält das
		// nächste das man ansteuern muss

		String richtung = Koordinaten.getRichtung(meinWeg.get(0).getPunkt(), meinWeg.get(1).getPunkt());
		// jetzt hab ich in richtung "Norden", "Sueden", "Westen" oder "Osten" drin
		System.err.println(richtung);

		// anweisung ausführen
		fahren(richtung);

	}

}
