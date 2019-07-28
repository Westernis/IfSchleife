package de.vitbund.vitmaze.players.ifschleife.karte;

import de.vitbund.vitmaze.players.ifschleife.ZellStatus;

/**
 * Diese Klasse liefert Methoden um bereits vorgefüllt Karten zu bekommen.
 * 
 * @author IfSchleife
 *
 */
public class KartenGenerator {

//	Zeichen im json der Karten : 
//	/ -> Zeilenende
//	## -> wand
//	@1 -> Startpunkt Spieler 1
//  @2 -> Startpunkt Spieler 2 usw.
//	!1 -> Ziel Spieler 1
//  !2 -> Ziel Spieler 2 usw.
//	A1 bis D1 sind Formulare des Spielers 1		
	private static final String wand = "#";
	private static final String start = "@";
	private static final String feld = " ";
	private static final String ziel = "!";
	
	private final static String amtsstube = "##################################################/##          ##                      ##          ##/##  A1      ##                      ##      A2  ##/##          ##    ########    ##    ##          ##/##          ##    ##          ##    ##          ##/########    ##    ##          ##    ##    ########/##                ##  C1      ##                ##/##                ##@4  C2    ##                ##/##    ########    ##  @3      ##    ########    ##/##          ##    ##############    ##          ##/##    B4!2  ##              D4##    ##  !1B3    ##/##          ##              D3##    ##          ##/##############    ######  ######    ##############/##          ##    ##D1              ##          ##/##    B2!4  ##    ##D2              ##  !3B1    ##/##          ##    ##############    ##          ##/##    ########    ##      @1  ##    ########    ##/##                ##    C4  @2##                ##/##                ##      C3  ##                ##/########    ##    ##          ##    ##    ########/##          ##    ##          ##    ##          ##/##          ##    ##    ########    ##          ##/##  A3      ##                      ##      A4  ##/##          ##                      ##          ##/##################################################/";
	private final static String frog = "########      ########/####!3##  ##  ##!2####/##A3@4  B4##B1  @1A2##/####  ####  ####  ####/  ##      ##      ##  /  ######  ##  ######  /  ##      ##      ##  /####  ####  ####  ####/##A4@3  B3##B2  @2A1##/####!4##  ##  ##!1####/########      ########/";
	private final static String geradeaus = "##################/##@2!1A2  A1!2@1##/##################/########@3########/########!4########/########A3########/########A4########/########!3########/########@4########/##################/";
	private final static String abbiegung = "##################/##@2!1  A2      ##/##############  ##/##@3##########A1##/##!4##########  ##/##  ##########!2##/##A3##########@1##/##  ##############/##      A4  !3@4##/##################/";
	private final static String zickzack = "########      ########/####!3##########!2####/##A4@4  B3##B1  @1A2##/####  ####  ####  ####/  ##      ##      ##  /  ######  ##  ######  /  ##      ##      ##  /####  ####  ####  ####/##A3@3  B4##B2  @2A1##/####!4##########!1####/########      ########/";
	private final static String kreis = "####################/##@4!2A1      B4@1##/##B3######  ####!3##/##  ##        ##A2##/##      ####  ##  ##/##  ##  ####      ##/##A4##        ##  ##/##!1####  ######B1##/##@3B2      A3!4@2##/####################/";
	private final static String kreis2 = "####################/##@4          B4@1##/##B3######  ####  ##/##  ##!4A1  !3##  ##/##      ####A2##  ##/##  ##A4####      ##/##  ##!1  A3!2##  ##/##  ####  ######B1##/##@3B2          @2##/####################/";
	private final static String krankheit = "##########################/##    A3    ##    A4    ##/##  ######  ##  ######  ##/##  ######  ##  ######  ##/##  ######  ##  ######  ##/##    !3  @2  @1  !4##  ##/##  ##################  ##/##  ##A1!2@4  @3  A2    ##/##  ######  ##  ######  ##/##  ######  ##  ######  ##/##  ######  ##  ######  ##/##    !1    ##          ##/##########################/";
	private final static String friendly = "########################################/##@1  !3########################!4  @2##/##      ########################      ##/##  C4  ########################  C3  ##/##                ####                ##/##############            ##############/##########                    ##########/########        ##    ##        ########/######B1      ####    ####      B2######/######            A3A4            ######/######            A2A1            ######/######B4    ####        ####    B3######/########      ############      ########/##########                    ##########/##############            ##############/##                ####                ##/##  C1  ########################  C2  ##/##      ########################      ##/##@4  !2########################!1  @3##/########################################/";
	
	
	
	/**
	 * Erstellte eine Karte und füllt Sie entsprechen dem {@code kartentext}.
	 * 
	 * @param kartentext   eine Textbeschreibung der Karte
	 * @param sizeX        Anzahl der horizontalen Felder
	 * @param sizeY        Anzahl der vertikalen Felder
	 * @param formularZahl Anzahl der Formular pro Spieler
	 * @return die vorbereitete Karte.
	 */
	private static Karte karteEinlesen(String kartentext, int sizeX, int sizeY, int formularZahl) {
		Karte karte = new Karte(sizeX, sizeY);
		int x = 0;
		int y = 0;
		Koordinaten ort;

		ZellStatus feldStatus = new ZellStatus();
		feldStatus.rueckgabeAuswerten("FLOOR");

		ZellStatus wandStatus = new ZellStatus();
		wandStatus.rueckgabeAuswerten("WALL");

		ZellStatus zielStatus = new ZellStatus();

		// in Zeilen aufteilen
		String[] zeilen = kartentext.split("/");

		// Zeilenweise durchgehen
		for (String zeile : zeilen) {
			for (int i = 0; i < zeile.length(); i += 2) {
				ort = new Koordinaten(x, y);
				// erstes Zeichen zuordnen
				switch (zeile.substring(i, i + 1)) {
				case wand:
					karte.aktualisiereFeld(ort, wandStatus);
					break;
				case start: // ist in der Karte zu einem Feld identisch
				case feld:
					karte.aktualisiereFeld(ort, feldStatus);
					break;
				case ziel:
					zielStatus.rueckgabeAuswerten("FINISH " + zeile.substring(i + 1, i + 2) + " " + formularZahl);
					karte.aktualisiereFeld(ort, zielStatus);
					break;

				default:// WICHTIGE ANNAHME es bleiben nur die Fälle mit Buchstaben am anfang übrig
					zielStatus.rueckgabeAuswerten(
							"FORM " + zeile.substring(i + 1, i + 2) + " " + formID(zeile.substring(i, i + 1)));
					karte.aktualisiereFeld(ort, zielStatus);
					break;
				}

				x++;
			//	System.err.print(karte.getFeld(ort)+ "\t" + ort +" | ");
			}
			y++;
			//System.err.println("\n");
		}
	//	System.err.println(zeilen.length + "|");
		return karte;
	}

	/**
	 * Rechnet die Buchstaben der Formulare in Zahlen um.
	 * 
	 * @param s
	 * @return
	 */
	private static int formID(String s) {
		return Integer.valueOf(s.charAt(0)) - 64;
	}

	public static void main(String[] args) {
		Koordinaten.setzeMaximaleKoordinaten(25, 25);
		
		Karte karte = getAmtsstube();
		//System.err.println("");
		//karte.ausgabe();
	}
	
	public static Karte getAmtsstube() {
		return karteEinlesen(amtsstube, 25, 25, 4);
	}
	
	public static Karte getFrog() {
		return karteEinlesen(frog, 11, 11, 2);
	}
	
	public static Karte getFriendly() {
		return karteEinlesen(friendly, 20, 20, 3);
	}
	
	public static Karte getKrankheit() {
		return karteEinlesen(krankheit, 13, 13, 1);
	}
	
	public static Karte getKreis2() {
		return karteEinlesen(kreis2, 10, 10, 2);
	}
	
	public static Karte getKreis() {
		return karteEinlesen(kreis, 10, 10, 2);
	}
	
	public static Karte getZickZack() {
		return karteEinlesen(zickzack, 11, 11, 2);
	}
	
	public static Karte getAbbiegung() {
		return karteEinlesen(abbiegung, 9, 10, 1);
	}
	
	public static Karte getGeradeaus() {
		return karteEinlesen(geradeaus, 9, 10, 1);
	}
	

}
