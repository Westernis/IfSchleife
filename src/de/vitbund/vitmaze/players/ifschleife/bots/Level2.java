package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.karte.BekannteKarten;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;

public class Level2 extends ErkundenderBotLvl2 {//
	private boolean karteErkannt;

	public Level2(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		// Karte rausfinden
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
			Feld f = aktuelleKarte.getFeld(getOrt());
			Feld fN = aktuelleKarte.getFeld(getOrt().norden());
			Feld fS = aktuelleKarte.getFeld(getOrt().sueden());
			Feld fW = aktuelleKarte.getFeld(getOrt().westen());
			Feld fE = aktuelleKarte.getFeld(getOrt().osten());
			if (Feld.ziel.equals(f.getTyp()) || Feld.ziel.equals(fN.getTyp()) || Feld.ziel.equals(fS.getTyp())
					|| Feld.ziel.equals(fW.getTyp()) || Feld.ziel.equals(fE.getTyp())) {
				this.aktuelleKarte = BekannteKarten.kreis();
				
			} else {
				this.aktuelleKarte = BekannteKarten.kreis2();
			}
			karteErkannt = true; //->erkannt das wir die unbekannte weiter nutzen müssen
		}
		super.machAktion();

	}
}
