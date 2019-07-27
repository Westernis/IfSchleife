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

	private boolean sucheGestartet;
	private boolean flurFelderWiederEntnullen = false;
	private ArrayList<Feld> suchListe;

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
		System.err.println("|" + Init.lastActionsResult + "|");
		// neue hinzugekommener Teil
		if (this.letzeAktionAufOKpruefen()) {
			// prüfen ob formular aufgehoben
			String s = "OK FORM";
			System.err.println("OK Form prüfen");
			if (Init.lastActionsResult.contains(s)) {
				System.err.println("OK Form aufgehoben");
				erledigteFormulare++;
			}
		}

		if (suchListe != null) {
			ArrayList<Feld> zuEntfernen = new ArrayList<Feld>();

			for (Feld feld : suchListe) {
				if (feld.getPunkt().xyGleich(getOrt()) || feld.getPunkt().xyGleich(getOrt().norden())
						|| feld.getPunkt().xyGleich(getOrt().westen()) || feld.getPunkt().xyGleich(getOrt().osten())
						|| feld.getPunkt().xyGleich(getOrt().sueden())) {
					zuEntfernen.add(feld);

				}
			}
			suchListe.removeAll(zuEntfernen);
		}
	}

	/**
	 * Aktualisiert die Formularliste mit den Informationen des aktuellen Standorts
	 * und den direkten Nachbarn.
	 */
	private void aktualisiereMeineFormulare() {
//		System.err.println("Test 1|" + this.getOrt() + "|" + getAktuelleKarte().getFeld(this.getOrt()));
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
		// TODO ++ unbekannte Zettel als Ziel setzen !!
		// String letzteRichtung = null; // muss raus da man das im Bot nutzen soll
		Feld ziel = null;

		this.aktualisiereMeineFormulare();

//		System.err.println("1");
		// 1. aktuelle Felder überprüfen, ist feld überhaupt eins vom bot

		if (Init.currentCell.getPlayerID() == id) {
			switch (Init.currentCell.getTyp()) {
			case Feld.ziel:
				// Ziel schon erlaubt? -> einsammeln
				if (Init.currentCell.getFormID() == erledigteFormulare) {
//					System.err.println("1.1");
					this.beenden();
					return;
				}
				break;
			case Feld.formular:
//				System.err.println("1.2");
				// aktuelles Formularfeld? -> aufheben
				if (Init.currentCell.getFormID() == (erledigteFormulare + 1)) {
					// Formular hochzählen wird in dem Wrapper der rundeInitialisieren gemacht um
					// quatschen abzufangen
//					System.err.println("1.3");
					this.aufsammeln();
					return;
				}
				break;
			case Feld.zettel:
				String richtungKick = kickenPruefen();
				if (richtungKick != null) {
					kick(richtungKick);
					return;
				} else {
					this.aufsammeln();
					return;
				}
			default:
				break;
			}

		}

		// 2. nächstes unerkundetes Feld

		// WegeKarte holen
		LinkedHashMap<Feld, VorhergehenderSchritt> wege = this.getAktuelleKarte().findeWege(getOrt());
		// unerkundetes Feld holen
//		this.aktuelleKarte.ausgabe();
//		this.aktuelleKarte.ausgabeWegliste(wege);
		ziel = naechstesFeldGewichtet(wege);
//		System.err.println("2.");

		// 3. Formular/Ziel bekannt -> Ziel überschreiben

		// 3.1 Ziel
		// Erste Bedingung prüft ob bekannt ist wie viel Formulare wir brauchen,
		// zweite Bedingung ob wir genug haben
		if (getAktuelleKarte().getAnzahlFormulare() >= 0
				&& erledigteFormulare >= getAktuelleKarte().getAnzahlFormulare()) {

//			System.err.println("3.");

			// WICHTIG nur setzten wenn auch das eigene Ziel bekannt ist
			if (getAktuelleKarte().getZiel(this.id) != null) {
				ziel = getAktuelleKarte().getZiel(this.id);
			}
//			System.err.println("3.1 |" + erledigteFormulare);

		}
		// 3.2 Formulare
		else if (meineformulare.get(erledigteFormulare + 1) != null) {
			ziel = meineformulare.get(erledigteFormulare + 1);
//			System.err.println("3.2");
			// prüfen ob in der Karte noch das selbe Formular an der Stelle liegt
			Ziele zielFeldAusKarte = getAktuelleKarte().getFormulare(ziel.getPunkt());
			if (!((Ziele) ziel).selbesFormular(zielFeldAusKarte)) {
				// wenn kein Formular mehr da -> liefert getFormular null, daher muss das Feld
				// nochmal geholt werden
				if (zielFeldAusKarte == null) {
					ziel = formularSuche(getAktuelleKarte().getFeld(ziel.getPunkt()), wege);
				} else {
					ziel = formularSuche(zielFeldAusKarte, wege);
				}

			} else {

				formularSucheZuruecksetzen();
//				System.err.println("3.3 |" + (erledigteFormulare + 1 + "|" + ziel.getPunkt()));
			}
		}

		// 4. Weg zu dem ausgewählten Ziel bestimmen und hinfahren
		if (ziel != null) {
			letzteRichtung = bestimmeRichtung(ziel, wege); // Richtung für die NOK Korrektur speichern
//			System.err.println(4.1);
		} else {
//			System.err.println(4.2);
			this.aktuelleKarte.flurFelderNullen();
			flurFelderWiederEntnullen = true;

		}

//		System.err.println("5 |" + letzteRichtung + "|");

		fahren(letzteRichtung);

	}

	private void formularSucheZuruecksetzen() {
		sucheGestartet = false;
		suchListe = null;
		// TODO + alles zurücksetzen
	}

	/**
	 * Methode händelt die Suche nach einem bereits gesehenen Formular.
	 * 
	 * @return
	 */
	private Feld formularSuche(Feld gesuchtesFormular, LinkedHashMap<Feld, VorhergehenderSchritt> aktuelleWege) {

		System.err.println("Formularsuche ausgelöst");
		int suchweite = 4;
		LinkedHashMap<Feld, VorhergehenderSchritt> wegeVomFormular;

		Feld ergebnis = gesuchtesFormular; // Orginalfeld als vorläufiges Ziel

		// Liste mit abzusuchenden Feldern generieren
		if (suchListe == null) {

			suchListe = new ArrayList<Feld>();
			wegeVomFormular = this.getAktuelleKarte().findeWege(gesuchtesFormular.getPunkt());

			for (Entry<Feld, VorhergehenderSchritt> element : wegeVomFormular.entrySet()) {
				// wenn das Feld innerhalb unserer suchweite ist
				if (element.getValue().getWeglaenge() <= suchweite) {
					suchListe.add(element.getKey());
				}
			}
			System.err.println("suchListe gfüllt");
		}

		// Stehen wir am ursprünglich bekannten Punkt?
		if (gesuchtesFormular.getPunkt().xyGleich(this.getOrt())) {
			sucheGestartet = true;
			System.err.println("Am alten Ort des Formulars");
		}

		// Orginalfeld ist besucht und noch abzusuchende Felder vorhanden-> suche kann
		// laufen
		if (sucheGestartet && !suchListe.isEmpty()) {
			// nächste Feld aus suchListe finden
			for (Entry<Feld, VorhergehenderSchritt> set : aktuelleWege.entrySet()) {
				if (suchListe.contains(set.getKey())) {
					ergebnis = set.getKey();
					suchListe.remove(ergebnis);// TODO METHODE für alle sichtbaren Felder
					System.err.println("nächstes Feld aus der Suchliste ist " + ergebnis.getPunkt());
					break;// wichtig wir wollen nur das erste gefundene Feld rausschmeißen
				}
			}
			// Feld aus ArrayList entfernen

		}

		// falls unsere Suchliste irgendwann mal leer ist, haben wir nix gefunden
		if (suchListe.isEmpty()) {
			System.err.println("Alles nach formularen abgesucht");
			// wenn die Liste leer ist, wird davon ausgegangen, dass wir das Formular nicht
			// mehr kennen
			meineformulare.remove(erledigteFormulare + 1);
			formularSucheZuruecksetzen();

			ergebnis = null;
		}

		return ergebnis;
	}

	private Feld naechstesFeldGewichtet(LinkedHashMap<Feld, VorhergehenderSchritt> wege) {
		ArrayList<Feld> potFeld = new ArrayList<Feld>();
		Feld ziel = null;
		int weglaenge = 10000000; // so lang sollte der Weg nie sein können

		// alle unerkundeten Felder mit geringster Weglänge in potFeld holen
		for (Entry<Feld, VorhergehenderSchritt> element : wege.entrySet()) {
			if (!element.getKey().pruefenErkundet() && element.getValue().getWeglaenge() <= weglaenge) {
				weglaenge = element.getValue().getWeglaenge();
				potFeld.add(element.getKey());
			}
			if (element.getValue().getWeglaenge() > weglaenge) {
				// ab hier können wir abbrechen, weil keine relevanten Felder mehr auftauchen
				// können
				break;
			}
		}
		// gefundene Felder gewichten
		int note = 0;
		int zielNote = 0;
		for (Feld feld : potFeld) {
			note = 0;
			if (Feld.zettel.equals(feld.getTyp())) {
				note += 10; // neue Felder mit Zetteln sollen Vorrang haben
			}
			if (feld.getNord() == null)
				note++;
			if (feld.getWest() == null)
				note++;
			if (feld.getOst() == null)
				note++;
			if (feld.getSued() == null)
				note++;
			if (note > zielNote) {
				// zufälliges Aussuchen gleichwertiger Felder könnte zu Erhöhung der
				// Gesamtstrecke führen die der Bot fahren muss. Das müsste vor Einführung
				// gründlich getestet werden.
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

	@Override
	protected void aufsammeln() {
		super.aufsammeln();
		if (flurFelderWiederEntnullen) {

			System.err.println("ACHTUNG Karte ENTnullt");
			this.getAktuelleKarte().formularsucheEnde();
			flurFelderWiederEntnullen = false;
		}
	}

	/**
	 * Prüft ob auf ein benachbartes Feld ein Zettel gekickt werden sollte. Dafür
	 * muss das Feld dazu geeignet sein (Flur oder Formular ohne Zettel) und im
	 * Idealfall noch ein gegnerisches Formular sein.
	 * 
	 * @return Richtung in die gekickt werden sollte ("norden" TODO + nach todo konstanten) oder
	 *         {@code null} falls kein geeignetes Feld da ist.
	 */
	public String kickenPruefen() {
		Feld ziel = null;
		int bewertung = 0;
		int bewertungAlt = 0;

		// Zielliste mit erlaubten Zielen holen und bewerten
		for (Koordinaten ort : new Koordinaten[] { getOrt().norden(), getOrt().sueden(), getOrt().westen(),
				getOrt().osten() }) {

			Feld f = this.getAktuelleKarte().getFeld(ort);

			// prüfen ob 1. begehbar, nur auf solchen Feldern können überhaupt Zettel liegen
			if (f.istBegehbar()) {
				bewertung = 0;
				switch (f.getTyp()) {
				case Feld.ziel:
					break; // abbruch brauch nicht rein
				case Feld.zettel:
					break;// abbruch brauch nicht rein
				case Feld.formular:
					if (((Ziele) f).getPlayerID() == this.id) {
						break; // eigene Formulare sollen ausgeschlossen werden
					}
					// nicht eigene höher bewerten
					bewertung++;
				case Feld.flur:
					// nix machen
				default:
					if (bewertung >= bewertungAlt) {
						ziel = f;
						bewertungAlt = bewertung;
					}
				}
			}
		}

		if (ziel == null) {
			return null; // keine geeignetes Feld vorhanden
		}

		return Koordinaten.getRichtung(this.getOrt(), ziel.getPunkt());
	}

	public void kick(String richtung) {
		if ("Westen".equals(richtung)) {
			System.out.println("kick west");
		}
		if ("Norden".equals(richtung)) {
			System.out.println("kick north");
		}
		if ("Osten".equals(richtung)) {
			System.out.println("kick east");
		}
		if ("Sueden".equals(richtung)) {
			System.out.println("kick south");
		}
	}
}
