package de.vitbund.vitmaze.players.ifschleife;

import java.util.Scanner;

import de.vitbund.vitmaze.players.ifschleife.bots.*;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;

/**
 * Diese Klasse f�hrt die Initialisierung durch. Das hei�t es wird ein
 * passender Bot angelegt und die entsprechende Aktion des Bots aufgerufen.
 * Au�erdem behandelt die Klasse die initiale Datenverarbeitung am Anfang jeder
 * Runde.
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

	public static ZellStatus currentCell = new ZellStatus();
	public static ZellStatus northCell = new ZellStatus();
	public static ZellStatus southCell = new ZellStatus();
	public static ZellStatus westCell = new ZellStatus();
	public static ZellStatus eastCell = new ZellStatus();

	/**
	 * 
	 * 1. Auswerten der Level-Informationen, die wir �ber die Game-Engine bekommen.
	 * 2. Passenden Bot mithilfe der LevelID deklarieren und instanziieren. 3.
	 * Methoden f�r weitere Aktionen aufrufen (u.a. Bewegung des Bots).
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
		// maximale Kartenparameter setzen f�r die umrechnung der koordinaten
		Koordinaten.setzeMaximaleKoordinaten(sizeX, sizeY);

		/*
		 * Hier entscheiden wir anhand der Level-ID welchen Bot wir als "unserBot"
		 * verwenden. Danach wird der Bot initialisiert und ihm eine Karte und die
		 * playerID zugeordnet.
		 */

		Bot unserBot;

		switch (level) {
		case 1:
			unserBot = new CharlySheet(karte, playerId, startX, startY);
			break;
		case 2:
			unserBot = new CharlySheet(karte, playerId, startX, startY);
			break;
		case 3:
			unserBot = new CharlySheet(karte, playerId, startX, startY);
			break;
		case 4:
			unserBot = new CharlySheet(karte, playerId, startX, startY);
			break;
		case 5:
			unserBot = new CharlySheet(karte, playerId, startX, startY);
			break;

		default:
			unserBot = new ErkundenderBotLvl2(karte, playerId, startX, startY);
			break;
		}

		while (true/* input.hasNext() */) {

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
			// Karte bef�llen

			// Debug Information ausgeben (optional m�glich)
			/*
			 * Darstellung eines kleinen Kompass mit den jeweils zugeh�rigen Zellstati.
			 */
//			System.err.println("Ergebnis Vorrunde Aktion: " + lastActionsResult);
//			System.err.println("Ergebnis Vorrunde Norden: " + "               " + northCellStatus);
//			System.err.println("Ergebnis Vorrunde Westen / Osten: " + "   " + westCellStatus + " / " + eastCellStatus);
//			System.err.println("Ergebnis Vorrunde Sueden: " + "                " + southCellStatus);

			// TODO: initialisieren und Pr�fung reinschreiben
			unserBot.rundeInitialisiern();
			unserBot.machAktion();
		}

		// Alles fertig -> aufr�umen
		/* input.close(); */
	}
}