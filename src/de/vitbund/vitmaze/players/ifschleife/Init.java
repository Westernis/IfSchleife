package de.vitbund.vitmaze.players.ifschleife;

import java.util.Scanner;

import de.vitbund.vitmaze.players.ifschleife.bots.Bot;
import de.vitbund.vitmaze.players.ifschleife.bots.BspBotKartennutzung;
import de.vitbund.vitmaze.players.ifschleife.bots.ErkundenderBotLvl2;
import de.vitbund.vitmaze.players.ifschleife.bots.KickAss;
import de.vitbund.vitmaze.players.ifschleife.bots.ZufallsBot2Lvl1;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;

/**
 * Diese Klasse soll die Initialisierung durchführen. Das heißt es wird ein Bot
 * angelegt, der die benötigten Karten und ein zum Level passendes Hirn
 * zugewiesen bekommt. Außerdem handelt die Klasse die initiale
 * Datenverarbeitung am Anfang jeder Runde.
 * 
 * @author IFSchleife
 *
 */

public class Init {

	public static String lastActionsResult = "";
	public static String currentCellStatus = "";
	public static String northCellStatus = "";
	public static String southCellStatus = "";
	public static String westCellStatus = "";
	public static String eastCellStatus = "";
	public static int playerId = 0;

	public static ZellStatus currentCell = new ZellStatus();
	public static ZellStatus northCell = new ZellStatus();
	public static ZellStatus southCell = new ZellStatus();
	public static ZellStatus westCell = new ZellStatus();
	public static ZellStatus eastCell = new ZellStatus();

	/**
	 * TODO JAVADOC
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		int sizeX = input.nextInt(); // X-Groesse des Spielfeldes (Breite)
		int sizeY = input.nextInt(); // Y-Groesse des Spielfeldes (Hoehe)
		int level = input.nextInt(); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile
		// 2. Zeile: Player Infos
		int playerId = input.nextInt(); // id dieses Players / Bots
		int startX = input.nextInt(); // X-Koordinate der Startposition dieses Player
		int startY = input.nextInt(); // Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile

		Karte karte = new Karte(sizeX, sizeY);
		// maximale Kartenparameter setzen für die umrechnung der koordinaten
		Koordinaten.setzeMaximaleKoordinaten(sizeX, sizeY);

		/*
		 * Hier entscheiden wir anhand der Level-ID welchen Bot wir als "unserBot"
		 * verwenden. Danach wird der Bot initialisiert und ihm eine Karte und die
		 * playerID zugeordnet.
		 */

		Bot unserBot;

		switch (level) {
		case 1:
			unserBot = new KickAss(karte, playerId, startX, startY);
			break;
		case 2:
			unserBot = new KickAss(karte, playerId, startX, startY);
			break;
		case 3:
			unserBot = new KickAss(karte, playerId, startX, startY);
			break;
		case 4:
			unserBot = new KickAss(karte, playerId, startX, startY);
			break;
		case 5:
			unserBot = new KickAss(karte, playerId, startX, startY);
			break;

		default:
			unserBot = new ZufallsBot2Lvl1(karte, playerId, startX, startY);
			break;
		}

		while (input.hasNext()) {

			// Rundeninformationen auslesen

			lastActionsResult = input.nextLine();
			currentCellStatus = input.nextLine();
			northCellStatus = input.nextLine();
			eastCellStatus = input.nextLine();
			southCellStatus = input.nextLine();
			westCellStatus = input.nextLine();

			currentCell.rueckgabeAuswerten(currentCellStatus);
			northCell.rueckgabeAuswerten(northCellStatus);
			eastCell.rueckgabeAuswerten(eastCellStatus);
			southCell.rueckgabeAuswerten(southCellStatus);
			westCell.rueckgabeAuswerten(westCellStatus);
			// Karte befüllen

			// Debug Information ausgeben (optional möglich)
			/*
			 * Darstellung eines kleinen Kompass mit den jeweils zugehörigen Zellstati.
			 */
//			System.err.println("Ergebnis Vorrunde Aktion: " + lastActionsResult);
//			System.err.println("Ergebnis Vorrunde Norden: " + "               " + northCellStatus);
//			System.err.println("Ergebnis Vorrunde Westen / Osten: " + "   " + westCellStatus + " / " + eastCellStatus);
//			System.err.println("Ergebnis Vorrunde Sueden: " + "                " + southCellStatus);

			//TODO: initialisieren und Prüfung reinschreiben
			unserBot.rundeInitialisiern();
			unserBot.machAktion();
		}

		// Alles fertig -> aufräumen
		input.close();
	}
}