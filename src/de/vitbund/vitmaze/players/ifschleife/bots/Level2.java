package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.KartenGenerator;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;
import de.vitbund.vitmaze.players.ifschleife.karte.Ziele;

/**
 * Dieser Bot soll für Level 2, den Fall der bekannten Karten händeln. Sobald
 * festgestellt wird, dass die Karte unbekannt ist, wird komplett auf die
 * regulären Funktionen des ErkunderBots zurückgegriffen und die Karte für die
 * Entscheidungsfindung wieder auf die erkundeteten Teile beschränkt.
 * 
 * @author Helmut.Rietz
 *
 */
public class Level2 extends ErkundenderBot {
	private boolean karteErkannt;
	private boolean abweichungFestgestellt = false;

	private Karte ersatzkarte;

	public Level2(Karte karte, int playerId, int x, int y, int sizeX, int sizeY) {
		super(karte, playerId, x, y);
		ersatzkarte = new Karte(Koordinaten.getxMax(), Koordinaten.getyMax());// TODO + lvl2 testen
		// Karte rausfinden
		// TODO + lvl2 auf karten begrenzen mit 2. Karte nur für wegfindung
		// 1 zum befüllen 1 zum vergleichen
		switch (sizeX) {

		case 10:
			break; // 04 05

		case 13:
			this.aktuelleKarte = KartenGenerator.getKrankheit();
			formularListeErstellen();
			karteErkannt = true;
			break; // 06
		case 20:
			this.aktuelleKarte = KartenGenerator.getFriendly();
			formularListeErstellen();
			karteErkannt = true;
			break; // 07
		case 25:
			this.aktuelleKarte = KartenGenerator.getAmtsstube();
			formularListeErstellen();
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
		System.err.println("Abweicxhung" +abweichungFestgestellt);

		if (!karteErkannt && !abweichungFestgestellt) {// Annahme ist auf Karte am Anfang ein Ziel vorhanden handelt es sich um Karte
							// 04 Kreis
			Feld f = ersatzkarte.getFeld(getOrt());
			Feld fN = ersatzkarte.getFeld(getOrt().norden());
			Feld fS = ersatzkarte.getFeld(getOrt().sueden());
			Feld fW = ersatzkarte.getFeld(getOrt().westen());
			Feld fE = ersatzkarte.getFeld(getOrt().osten());
			if (Feld.ziel.equals(f.getTyp()) || Feld.ziel.equals(fN.getTyp()) || Feld.ziel.equals(fS.getTyp())
					|| Feld.ziel.equals(fW.getTyp()) || Feld.ziel.equals(fE.getTyp())) {
				this.aktuelleKarte = KartenGenerator.getKreis();
				formularListeErstellen();

			} else {
				this.aktuelleKarte = KartenGenerator.getKreis2();
				formularListeErstellen();
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
		
		if (this.letzeAktionAufOKpruefen()) {
			// prüfen ob formular aufgehoben
			String s = "OK FORM";
			// System.err.println("OK Form prüfen");
			if (Init.lastActionsResult.contains(s)) {
//				System.err.println("OK Form aufgehoben");
				erledigteFormulare++;
				aktuelleKarte.aktualisiereFeld(getOrt(), Init.currentCell);
			}
		}

		if (abweichungFestgestellt) {
			super.rundeInitialisiern();
		} else {
			// TODO + lvl2 Abweichung feststellen
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

			for (Koordinaten element : new Koordinaten[] { getOrt(), getOrt().norden(), getOrt().sueden(),
					getOrt().westen(), getOrt().osten() }) {
				if (ersatzkarte.getFeld(element).getTyp().equals(aktuelleKarte.getFeld(element).getTyp())) {

					System.err.println("feldtyp gleich"+ ersatzkarte.getFeld(element).getTyp());
					
					if (Feld.ziel.equals(ersatzkarte.getFeld(element).getTyp())
							|| Feld.formular.equals(ersatzkarte.getFeld(element).getTyp())) {
						Ziele ersatz = (Ziele) ersatzkarte.getFeld(element);
						Ziele bekannt = (Ziele) ersatzkarte.getFeld(element);
						System.err.println("Ersatz:"+ ersatz.getFormID() +" bekannt "+ bekannt.getFormID());
						if (ersatz.getFormID() != bekannt.getFormID()
								|| ersatz.getPlayerID() != bekannt.getPlayerID()) {
							// gleicher Feldtyp aber nicht dasselbe Formular/Ziel
							setAktuelleKarte(ersatzkarte);
							abweichungFestgestellt = true;
							this.meineformulare.clear();
							break;
						}
					}
				} else { // andere Feldtyp
					System.err.println("feldtyp gleich "+ ersatzkarte.getFeld(element).getTyp() + " aktuelle "+ aktuelleKarte.getFeld(element).getTyp());
					
					setAktuelleKarte(ersatzkarte);
					abweichungFestgestellt = true;
					this.meineformulare.clear();
					break;
				}
			}

		}
	}

	private void formularListeErstellen() {
		for (int x = 0; x < Koordinaten.getxMax(); x++) {
			for (int y = 0; y < Koordinaten.getyMax(); y++) {
				Ziele f = getAktuelleKarte().getFormulare(new Koordinaten(x, y));
				if(f != null && f.getPlayerID() == this.id ) {
					this.meineformulare.put(f.getFormID(), f);
				}
			}
		}
//		meineformulare
	}
	@Override
	protected void aufsammeln() {
		super.aufsammeln();

	}
}
