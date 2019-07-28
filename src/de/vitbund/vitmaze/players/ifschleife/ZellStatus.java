package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.bots.Bot;
import de.vitbund.vitmaze.players.ifschleife.karte.Feld;

/**
 * Mit Zellstatus kann man den Status-String (die Rückgabe) zerlegen und gezielt
 * auswerten.
 * 
 * Die Auswertung des LastActionResults auf OK / NOK und folgende Teile befindet
 * sich in der Bot-Klasse als {@code letzteAktionPruefen()}
 * 
 * @see Bot
 * @author IFSchleife
 *
 */
public class ZellStatus {
	private String orginalText;
	private String typ;
	private int playerID;
	private int formID;
	private int botentfernung;

	/**
	 * Wertet die übergebene Rückgabe aus indem sie zerlegt wird.
	 * 
	 * @param rueckgabe
	 * @return {@code true} wenn keine Fehler aufgetreten sind. {@code false} wenn
	 *         bei der Zerlegung Fehler aufgetreten sind. Wenn der übergebene String
	 *         bspw. leer war.
	 */
	public boolean rueckgabeAuswerten(String rueckgabe) {
		this.orginalText = rueckgabe;// für testzwecke und Kontrollen beibehalten
		// System.err.println(orginalText);
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
				|| Feld.ziel.equals(zerlegt[i]) || Feld.zettel.equals(zerlegt[i]))) {
			return false; // muss von einem dieser Typen sein sonst ist was schief gegangen
		} else {
			typ = zerlegt[i];
			i++;
			// Spielernummer und Formularnummer/-anzahl abarbeiten
			if (Feld.formular.equals(typ) || Feld.ziel.equals(typ)) {
				this.playerID = Integer.valueOf(zerlegt[i]);
				i++;
				this.formID = Integer.valueOf(zerlegt[i]);
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

	/**
	 * Gibt den originalen Text, den die Game-Engine geliefert hat, zurück.
	 * 
	 * @return originalText
	 */
	public String getOrginalText() {
		return orginalText;
	}

	/**
	 * Gibt den Typ zurück, bspw. WALL. Zerlegung der Informationen:
	 * {@link #rueckgabeAuswerten(String)}
	 * 
	 * @return der Typ des Felds vom Status
	 */
	public String getTyp() {
		return typ;
	}

	/**
	 * Gibt die PlayerID raus. Zerlegung der Informationen:
	 * {@link #rueckgabeAuswerten(String)}
	 * 
	 * @return die ID des Spielers.
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * Gibt die FormularNr. zurück. Zerlegung der Informationen:
	 * {@link #rueckgabeAuswerten(String)}
	 * 
	 * @return die ID des Formulars.
	 */
	public int getFormID() {
		return formID;
	}

	/**
	 * 
	 * @return die Entfernung gegnerischer Bots
	 */
	public int getBotentfernung() {
		return botentfernung;
	}

	// zum testen der zerlegung
	/**
	 * Dient lediglich dem Test, ob die Zerlegung einwandfrei funktioniert. Zerlegt
	 * einen fiktiven Status und gibt die Einzelwerte à Typ, SpielerID, FormularNr
	 * und Botentfernung zurück.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ZellStatus a = new ZellStatus();
		a.rueckgabeAuswerten("FORM 1 2 !2");
		System.err.println(a.typ + "-" + a.playerID + "-" + a.formID + "-" + a.botentfernung);
	}
}

//Aussehen der Rueckgabe -> <status> ! int
//<status> -> typ playerID (formID || formcount)