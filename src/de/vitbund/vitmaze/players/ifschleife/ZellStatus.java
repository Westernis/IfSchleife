package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Feld;

public class ZellStatus {
	private String orginalText;
	private String typ;
	private int playerID;
	private int formNumber;
	private int botentfernung;

	public boolean rueckgabeAuswerten(String rueckgabe) {
		this.orginalText = rueckgabe;// für testzwecke und Kontrollen beibehalten
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
				this.formNumber = Integer.valueOf(zerlegt[i]);
				i++;
			}
			// i ist hier 1 oder 3 je nach dem ob Ziel/Form oder wand/freies Feld, dann
			// prüfen ob noch 1 Feld mit dem ! und der nummer da sind
			if (zerlegt.length > i) {
				this.botentfernung = Integer.valueOf(zerlegt[i].substring(1));
				// .substring(1) gibt String ohne erstes Zeichen zurück -> ! wird entfernt
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

	public int getFormNumber() {
		return formNumber;
	}

	public int getBotentfernung() {
		return botentfernung;
	}

	// zum testen der zerlegung
	public static void main(String[] args) {
		ZellStatus a = new ZellStatus();
		a.rueckgabeAuswerten("FORM 1 2 !2");
		System.err.println(a.typ + "-" + a.playerID + "-" +a.formNumber + "-" +a.botentfernung);
	}
}

//Aussehen der Rueckgabe -> <status> ! int
//<status> -> typ playerID (formID || formcount)