package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.ZellStatus;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;
import de.vitbund.vitmaze.players.ifschleife.karte.VorhergehenderSchritt;
import de.vitbund.vitmaze.players.ifschleife.karte.Ziele;

public class ErkundenderBotLvl2 extends Bot {
	private int erledigteFormulare = 0; // speichert das höchste abgearbeitete Formular, Formulare sollten bei 1 starten
	private HashMap<Integer, Ziele> meineformulare;

	public ErkundenderBotLvl2(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		meineformulare = new HashMap<Integer, Ziele>();
		// TODO Automatisch generierter Konstruktorstub
	}

	private void aktualisiereMeineFormulare() {
		Koordinaten ort = this.getOrt();
		Ziele feld;
		for (Koordinaten xy : new Koordinaten[] { ort, ort.norden(), ort.osten(), ort.westen(), ort.sueden() }) {
			feld = (Ziele) getAktuelleKarte().getFormulare(xy);
			
			if (feld != null && feld.getPlayerID() == this.id) {
				meineformulare.put(feld.getFormID(), feld);

			}
		}

	}

	@Override
	public void machAktion() {
		String richtung = null;
		Feld ziel = null;

		this.rundeInitialisiern();
		this.aktualisiereMeineFormulare();

//		  INIT: rundeInitialisieren und dann aktualisiereMeineFormulare ausführen
//		1. Aktuelles Feld anschauen und abarbeiten falls gegeben. -> beendet diese
//		   Funktion 
//		2. prüfen welches das nächste nicht erkundete Feld ist -> richtung
//		   setzten 
//		3. prüfen ob man das nächste Ziel/Formular kennt -> richtung
//		   überschreiben
//		  
//		4. zum gewählten Ziel fahren -> beendet diese Funktion
//		  

		System.err.println("1");

		// aktuelle Felder überprüfen
		// 1. ist feld überhaupt eins vom bot
		if (Init.currentCell.getPlayerID() == id) {
			switch (Init.currentCell.getTyp()) {
			case Feld.ziel:
				// Ziel schon erlaubt? -> einsammeln
				if (Init.currentCell.getFormNumber() == erledigteFormulare) {
					System.err.println("1.1");
					this.beenden();
					return;
				}
				break;
			case Feld.formular:
				System.err.println("1.2");
				// aktuelles Formularfeld? -> aufheben
				// (gefundeneFormulare+1), da das letzte Formular und das Ziel die selbe Nummer
				// haben
				if (Init.currentCell.getFormNumber() == (erledigteFormulare + 1)) {
					erledigteFormulare++;
					System.err.println("1.3");
					this.aufsammeln();
					return;
				}
				break;
			default:
				break;
			}

		}

		// 2. nächstes erkundetes Feld
		// WegeKarte holen
		LinkedHashMap<Feld, VorhergehenderSchritt> wege = this.getAktuelleKarte().findeWege(getOrt());
		// unerkundetes Feld holen
		ziel = naechstesUnerkundetesFeld(wege);


		// 3. Formular/Ziel bekannt
		// Ziel
		// TODO alles, prüfen ob max. ziel überhaupt bekannt usw
		getAktuelleKarte().getAnzahlFormulare();
		if (getAktuelleKarte().getAnzahlFormulare() > 0
				&& erledigteFormulare == getAktuelleKarte().getAnzahlFormulare()) {
			if (getAktuelleKarte().getZiel(this.id) != null) {
				ziel = getAktuelleKarte().getZiel(this.id);//WICHTIG nur setzten wenn auch das eigene Ziel bekannt ist
			}
			System.err.println("3.1 |" + erledigteFormulare);
		}

		// Formular bekannt -> Ziel überschreiben
		for (Entry<Integer, Ziele> x : meineformulare.entrySet()) {
			System.err.println("3.3 " + x.getKey() + "|" + x.getValue().getPunkt());
		}
		if (meineformulare.get(erledigteFormulare + 1) != null) {
			ziel = meineformulare.get(erledigteFormulare + 1);
			System.err.println("3.3 |" + (erledigteFormulare + 1 + "|" + ziel.getPunkt()));
		}

		
		//4. Weg zu ausgewählten Ziel bestimmen und hinfahren
		if (ziel != null) {
			richtung = bestimmeRichtung(ziel, wege);
		}
		System.err.println("4 |" + richtung + "|");
		fahren(richtung);

	}

	private Feld naechstesUnerkundetesFeld(LinkedHashMap<Feld, VorhergehenderSchritt> wege) {
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
		return ziel;
	}

//		Weg zum Ziel bestimmen
//	 	dazu kann man die Methode werteListeAus nutzen, dann hat man den kompletten Weg in der Hand
	private String bestimmeRichtung(Feld ziel, LinkedHashMap<Feld, VorhergehenderSchritt> wege) {
		ArrayList<Feld> meinWeg = this.getAktuelleKarte().werteListeAus(wege, ziel);
		//sicherstellen das ein Weg zurückkam, wenn nicht null zurückgeben
		if (meinWeg == null) {
			return null;
		}

		// der 1. Eintrag ist unser aktuelles feld, der zweite Eintrag enthält das
		// nächste das man ansteuern muss
		
		String richtung = Koordinaten.getRichtung(meinWeg.get(0).getPunkt(), meinWeg.get(1).getPunkt());
		// jetzt hab ich in richtung eine der Werte "Norden", "Sueden", "Westen" oder
		// "Osten" drin

		// System.err.println(richtung);

		// Rückgabe
		return richtung;

	}
}
