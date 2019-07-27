package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.BekannteKarten;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;

public class Level2 extends ErkundenderBotLvl2 {
	private boolean karteErkannt;
	private boolean abweichungFestgestellt = false;

	private Karte ersatzkarte;

	public Level2(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		ersatzkarte = new Karte(Koordinaten.getxMax(), Koordinaten.getyMax());// TODO + lvl2 testen
		// Karte rausfinden
		// TODO + lvl2 auf karten begrenzen mit 2. Karte nur für wegfindung
		// 1 zum befüllen 1 zum vergleichen
		switch (x) {

		case 10:
			break; // 04 05

		case 13:
			this.aktuelleKarte = BekannteKarten.krankheit();
			karteErkannt = true;
			break; // 06
		case 20:
			this.aktuelleKarte = BekannteKarten.friendly();
			karteErkannt = true;
			break; // 07
		case 25:
			// this.aktuelleKarte = BekannteKarten.krankheit();
			karteErkannt = true;
			break; // 08

		case 9:
			karteErkannt = true;
			break; // 01 02
		case 11:
			karteErkannt = true;
			break; // 03 09

		default:
			break;
		}
	}

	@Override
	public void machAktion() {
		System.err.println("Größe " + Koordinaten.getxMax() + " " + Koordinaten.getyMax());
		System.err.println(this.getOrt());

		if (!karteErkannt) {// Annahme ist auf Karte am Anfang ein Ziel vorhanden handelt es sich um Karte
							// 04 Kreis
			Feld f = ersatzkarte.getFeld(getOrt());
			Feld fN = ersatzkarte.getFeld(getOrt().norden());
			Feld fS = ersatzkarte.getFeld(getOrt().sueden());
			Feld fW = ersatzkarte.getFeld(getOrt().westen());
			Feld fE = ersatzkarte.getFeld(getOrt().osten());
			if (Feld.ziel.equals(f.getTyp()) || Feld.ziel.equals(fN.getTyp()) || Feld.ziel.equals(fS.getTyp())
					|| Feld.ziel.equals(fW.getTyp()) || Feld.ziel.equals(fE.getTyp())) {
				this.aktuelleKarte = BekannteKarten.kreis();

			} else {
				this.aktuelleKarte = BekannteKarten.kreis2();
			}
			karteErkannt = true;
		}
		super.machAktion();

	}

	/**
	 * Diese Methode verarbeitet zuerst die Rückgabe bzgl. der letzten Aktion und
	 * führt gegebenenfalls Korrekturen durch. Danach werden die umliegenden
	 * Kartenfelder aktualisiert.
	 */
	public void rundeInitialisiern() {

		if (abweichungFestgestellt) {
			super.rundeInitialisiern();
		} else {
			//TODO + lvl2 Abweichung feststellen
			this.letzteAktionPruefen();

			ersatzkarte.aktualisiereFeld(getOrt(), Init.currentCell);
			ersatzkarte.aktualisiereFeld(getOrt().norden(), Init.northCell);
			ersatzkarte.aktualisiereFeld(getOrt().sueden(), Init.southCell);
			ersatzkarte.aktualisiereFeld(getOrt().osten(), Init.eastCell);
			ersatzkarte.aktualisiereFeld(getOrt().westen(), Init.westCell);
			ersatzkarte.aktualisiereFeld(getOrt(), Init.currentCell);
			if (ersatzkarte.getFeld(getOrt()) != null) {
				ersatzkarte.getFeld(getOrt()).pruefenErkundet();
			}
		}
	}
}
