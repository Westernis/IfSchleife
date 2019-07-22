package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Feld;

public class ZellStatus {
	private String orginalText;
	private String typ;
	private int playerID;
	private int formularNr; //TODO umbennen #Amtssprache ist deutsch gem. 111 GG
	private int botentfernung;

	public boolean rueckgabeAuswerten(String rueckgabe) {
		this.orginalText = rueckgabe;// für testzwecke und Kontrollen beibehalten
		System.err.println(orginalText);
		String[] zerlegt; // ACHTUNG ARRAY von STRINGS
		int i = 0; // index den wir anschauen

		if (rueckgabe == null || rueckgabe.isEmpty()) {
			return false;
		}

		// zerlegt enthält den auseinander gepflückten Text, das Leerzeichen " " stellt
		// das Trennzeichen dar. (ein ssv^^)
		zerlegt = rueckgabe.split(" ");
		// Aufruf auf der Konstanten erfolgt, damit nicht ausversehen auf einem null
		// Objekt die equals Methode aufgerufen wird
		// Wäre hier wegen der Prüfung weiter oben zwar nicht relevant, aber gleich
		// angewöhnen
		if (!(Feld.flur.equals(zerlegt[i]) || Feld.wand.equals(zerlegt[i]) || Feld.formular.equals(zerlegt[i])
				|| Feld.ziel.equals(zerlegt[i]))) {
			return false; // muss von einem dieser Typen sein sonst ist was schief gegangen
		} else {
			typ = zerlegt[i];
			i++;
			// Spielernummer und Formularnummer/-anzahl abarbeiten
			if (Feld.formular.equals(typ) || Feld.ziel.equals(typ)) {
				this.playerID = Integer.valueOf(zerlegt[i]);
				i++;
				this.formularNr = Integer.valueOf(zerlegt[i]);
				i++;
			}
			// i ist hier 1 oder 3 je nach dem ob Ziel/Form oder wand/freies Feld, dann
			// prüfen ob noch 1 Feld mit dem ! und der nummer da sind
			if (zerlegt.length > i) {
				String entfernung = zerlegt[i].substring(1);
//				ACHTUNG wenn man auf dem selben Feld steht kommt nicht "!0" zurück sondern "!"
//				deshalb erst länge prüfen um NumberFormatException zu vermeiden
				if (entfernung.length() > 0)
					this.botentfernung = Integer.valueOf(entfernung);
				else {
					this.botentfernung = 0; // -> selbes Feld, wir quatschen...
				}
			} else {
				this.botentfernung = -1; // daher nix zu sehen
			}
		}

		return true;
	}

	public String getOrginalText() {
		return orginalText;
	}

	public String getTyp() {
		return typ;
	}

	public int getPlayerID() {
		return playerID;
	}

	public int getformularNr() {
		return formularNr;
	}

	public int getBotentfernung() {
		return botentfernung;
	}

	// zum testen der zerlegung
	public static void main(String[] args) {
		ZellStatus a = new ZellStatus();
		a.rueckgabeAuswerten("FORM 1 2 !2");
		System.err.println(a.typ + "-" + a.playerID + "-" + a.formularNr + "-" + a.botentfernung);
	}
}

//Aussehen der Rueckgabe -> <status> ! int
//<status> -> typ playerID (formID || formcount)