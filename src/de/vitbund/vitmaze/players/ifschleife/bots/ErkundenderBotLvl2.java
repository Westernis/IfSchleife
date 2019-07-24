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
		/*
		 * es macht einen Unterschied ob man hier den gen. Typ weglässt - dann HashMap
		 * mit Object, Object...
		 */
		meineformulare = new HashMap<Integer, Ziele>();
	}

	@Override
	public void rundeInitialisiern() {
		super.rundeInitialisiern(); // ich will alles aus der Elternklasse machen

		// neue hinzugekommener Teil
		if (this.letzeAktionAufOKpruefen()) {
			// prüfen ob formular aufgehoben
			String s = "OK FORM";
			if (Init.lastActionsResult.contains(s)) {
				erledigteFormulare++;
			}
		}
	}

	private void aktualisiereMeineFormulare() {
		System.err.println("Test 1|" + this.getOrt() + "|" + getAktuelleKarte().getFeld(this.getOrt()));
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
		// String letzteRichtung = null; // muss raus da man das im Bot nutzen soll
		Feld ziel = null;

//		this.rundeInitialisiern();  //befindet sich in der Init
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
				if (Init.currentCell.getFormID() == erledigteFormulare) {
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
				if (Init.currentCell.getFormID() == (erledigteFormulare + 1)) {
					// Formular hochzählen wird in dem Wrapper der rundeInitialisieren gemacht
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
		// ziel = naechstesUnerkundetesFeld(wege);
		ziel = naechstesFeldGewichtet(wege);

		// 3. Formular/Ziel bekannt -> Ziel überschreiben
		// Ziel
		getAktuelleKarte().getAnzahlFormulare();
		if (getAktuelleKarte().getAnzahlFormulare() >= 0
				&& erledigteFormulare == getAktuelleKarte().getAnzahlFormulare()) {
			if (getAktuelleKarte().getZiel(this.id) != null) {
				ziel = getAktuelleKarte().getZiel(this.id);// WICHTIG nur setzten wenn auch das eigene Ziel bekannt ist
			}
			System.err.println("3.1 |" + erledigteFormulare);
		}

		// Formular //TODO umstellen auf Karte oder Prüfen ob in der Karte das Feld noch
		// passt
		for (Entry<Integer, Ziele> x : meineformulare.entrySet()) {
			System.err.println("3.3 " + x.getKey() + "|" + x.getValue().getPunkt());
		}
		if (meineformulare.get(erledigteFormulare + 1) != null) {
			ziel = meineformulare.get(erledigteFormulare + 1);
			System.err.println("3.3 |" + (erledigteFormulare + 1 + "|" + ziel.getPunkt()));
		}

		// 4. Weg zu dem ausgewählten Ziel bestimmen und hinfahren
		if (ziel != null) {
			letzteRichtung = bestimmeRichtung(ziel, wege); // Richtung für die NOK Korrektur speichern
		}
		System.err.println("4 |" + letzteRichtung + "|");
		fahren(letzteRichtung);

	}

	private Feld naechstesFeldGewichtet(LinkedHashMap<Feld, VorhergehenderSchritt> wege) {
		ArrayList<Feld> potFeld = new ArrayList<Feld>();
		Feld ziel = null;
		int weglaenge = 10000000; // so lang sollte der Weg nie sein können

		for (Entry<Feld, VorhergehenderSchritt> element : wege.entrySet()) {
			if (!element.getKey().pruefenErkundet() && element.getValue().getWeglaenge() <= weglaenge) {
				weglaenge = element.getValue().getWeglaenge();
				potFeld.add(element.getKey());// enthält dann alle unerkundeten Felder mit geringster Weglänge
				// ziel = element.getKey();
			}
			if (element.getValue().getWeglaenge() > weglaenge) {// ab hier können wir abbrechen, weil keine relevanten
																// Felder mehr auftauchen können
				break;
			}
		}

		int note = 0;
		int zielNote = 0;
		for (Feld feld : potFeld) {
			note = 0;
			if (feld.getNord() == null)
				note++;
			if (feld.getWest() == null)
				note++;
			if (feld.getOst() == null)
				note++;
			if (feld.getSued() == null)
				note++;
			if (note > zielNote) {
				ziel = feld;
				zielNote = note;
			}
		}

		if (ziel == null) {
			System.err.println("Alles erkundet");
			return null;
		}
		return ziel;
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
		// sicherstellen das ein Weg zurückkam, wenn nicht null zurückgeben
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
