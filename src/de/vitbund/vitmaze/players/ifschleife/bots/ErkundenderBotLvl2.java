package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;
import de.vitbund.vitmaze.players.ifschleife.karte.VorhergehenderSchritt;

public class ErkundenderBotLvl2 extends Bot {
	int gefundeneFormulare = 0; // speichert das höchste abgearbeitete Formular, Formulare sollten bei 1 starten

	public ErkundenderBotLvl2(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		// TODO Automatisch generierter Konstruktorstub
	}

	@Override
	public void machAktion() {
		this.rundeInitialisiern();
		String richtung = null;

		// aktuelle Felder überprüfen
		// 1. ist feld überhaupt eins vom bot
		if (Init.currentCell.getPlayerID() == id) {
			switch (Init.currentCell.getTyp()) {
			case Feld.ziel: //ist es ein Zielfeld
				if (Init.currentCell.getFormNumber() == gefundeneFormulare) {
					this.beenden();
					return;
				}
				break;
			case Feld.formular:
				if (Init.currentCell.getFormNumber() == (gefundeneFormulare+1)) {
					this.aufsammeln();
					return;
				}
				break;
			default:
				break;
			}
			
			//WegeKarte holen
			LinkedHashMap<Feld, VorhergehenderSchritt> wege = this.getAktuelleKarte().findeWege(getOrt());
			
			richtung = fahreZumUnerkundetenFeld(wege);
			
			fahren(richtung);
		}
	}

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
