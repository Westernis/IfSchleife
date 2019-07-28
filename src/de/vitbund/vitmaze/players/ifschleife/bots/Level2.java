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
	/**
	 * Dient zur prüfung ob eine Kartenzuordnung schon erfolgt ist, unbekannt ist
	 * auch eine gültige Zuordnung, daher sobald man weiß die karte ist unbekannt
	 * auch auf true setzen. Achtung dann auch {@link #abweichungFestgestellt} auf
	 * true setzten.
	 */
	private boolean karteErkannt;

	/**
	 * Soll auf true gesetzt werden, falls Abweichungen zur gespeicherten Karte
	 * festgestellt werden oder die Karte komplett unbekannt ist. Das aktiviert das
	 * normale Verhalten von {@link ErkundenderBot}.
	 */
	private boolean abweichungFestgestellt = false;

	private Karte ersatzkarte;

	/**
	 * 
	 * @param karte    - die Spielfeldkarte
	 * @param playerId - die ID des Spielers
	 * @param x        - die X-Koordinate also der Punkt auf der horizontalen Achse
	 * @param y        - die Y-Koordinate also der Punkt auf der vertikalen Achse
	 * @param sizeX    - Die horizontale Größe der Karte.
	 * @param sizeY    - Die vertikale Größe der Karte.
	 */
	public Level2(Karte karte, int playerId, int x, int y, int sizeX, int sizeY) {
		super(karte, playerId, x, y);
		ersatzkarte = new Karte(Koordinaten.getxMax(), Koordinaten.getyMax());
		// Karte rausfinden
		switch (sizeX) {

		case 10:
			if (sizeY != 10) {
				abweichungFestgestellt = true;
				karteErkannt = true;
			}
			break; // 04 05

		case 13:
			if (sizeY == 13) {
				this.aktuelleKarte = KartenGenerator.getKrankheit();
				formularListeErstellen();
			} else
				abweichungFestgestellt = true;

			karteErkannt = true;
			break; // 06
		case 20:
			if (sizeY == 20) {
				this.aktuelleKarte = KartenGenerator.getFriendly();
				formularListeErstellen();
			} else
				abweichungFestgestellt = true;
			karteErkannt = true;
			break; // 07
		case 25:
			if (sizeY == 25) {
				this.aktuelleKarte = KartenGenerator.getAmtsstube();
				formularListeErstellen();
			} else
				abweichungFestgestellt = true;
			karteErkannt = true;
			break; // 08

		case 9:
			karteErkannt = true;
			abweichungFestgestellt = true;
			break; // 01 02
		case 11:
			abweichungFestgestellt = true;
			karteErkannt = true;
			break; // 03 09

		default:
			abweichungFestgestellt = true;
			karteErkannt = true;
			break;
		}

	}

	@Override
	public void machAktion() {
//		System.err.println("Größe " + Koordinaten.getxMax() + " " + Koordinaten.getyMax());
//		System.err.println(this.getOrt());
//		System.err.println("Abweicxhung" + abweichungFestgestellt);

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

//			Formulare aktualisieren
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

			this.letzteAktionPruefen();

			// Selbsterkundete karte Pflegen
			ersatzkarte.aktualisiereFeld(getOrt(), Init.currentCell);
			ersatzkarte.aktualisiereFeld(getOrt().norden(), Init.northCell);
			ersatzkarte.aktualisiereFeld(getOrt().sueden(), Init.southCell);
			ersatzkarte.aktualisiereFeld(getOrt().osten(), Init.eastCell);
			ersatzkarte.aktualisiereFeld(getOrt().westen(), Init.westCell);
			ersatzkarte.aktualisiereFeld(getOrt(), Init.currentCell);
			if (ersatzkarte.getFeld(getOrt()) != null) {
				ersatzkarte.getFeld(getOrt()).pruefenErkundet();
			}

//			Test ob karte 4 oder 5 unsere aktuelle ist, kann erst festgestellt werden, nachdem die umliegenden Felder angeschaut wurden
//			Annahme ist auf Karte am Anfang ein Ziel vorhanden handelt es sich um Karte 04 Kreis
			if (!karteErkannt && !abweichungFestgestellt) {
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

			for (Koordinaten element : new Koordinaten[] { getOrt(), getOrt().norden(), getOrt().sueden(),
					getOrt().westen(), getOrt().osten() }) {
				if (ersatzkarte.getFeld(element).getTyp().equals(aktuelleKarte.getFeld(element).getTyp())) {

//					System.err.println("feldtyp gleich" + ersatzkarte.getFeld(element).getTyp());

					if (Feld.ziel.equals(ersatzkarte.getFeld(element).getTyp())
							|| Feld.formular.equals(ersatzkarte.getFeld(element).getTyp())) {
						Ziele ersatz = (Ziele) ersatzkarte.getFeld(element);
						Ziele bekannt = (Ziele) ersatzkarte.getFeld(element);
//						System.err.println("Ersatz:" + ersatz.getFormID() + " bekannt " + bekannt.getFormID());
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
//					System.err.println("feldtyp gleich " + ersatzkarte.getFeld(element).getTyp() + " aktuelle "
//							+ aktuelleKarte.getFeld(element).getTyp());

					setAktuelleKarte(ersatzkarte);
					abweichungFestgestellt = true;
					this.meineformulare.clear();
					break;
				}
			}

		}
	}

	/**
	 * Füllt die Formularliste mit allen eigenen Fomularen die zum zeitpunkt des
	 * Aufrufens in der aktuellenKarte vorhanden sind.
	 */
	private void formularListeErstellen() {
		for (int x = 0; x < Koordinaten.getxMax(); x++) {
			for (int y = 0; y < Koordinaten.getyMax(); y++) {
				Ziele f = getAktuelleKarte().getFormulare(new Koordinaten(x, y));
				if (f != null && f.getPlayerID() == this.id) {
					this.meineformulare.put(f.getFormID(), f);
				}
			}
		}
	}
}
