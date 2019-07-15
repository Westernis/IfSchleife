package de.vitbund.vitmaze.players.ifschleife;

import java.util.Scanner;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;
import de.vitbund.vitmaze.players.ifschleife.karte.Koordinaten;

/**
 * Diese Klasse soll die Initialisierung durchf�hren. Das hei�t es wird ein Bot
 * angelegt, der die ben�tigten Karten und ein zum Level passendes Hirn
 * zugewiesen bekommt. Au�erdem handelt die Klasse die initiale
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

	public static void main(String[] args) {
		// TODO Initialisierung (ersten Text einlesen, Karten initialisieren f�r lvl 2?)

		Scanner input = new Scanner(System.in);
		// TODO Java-Doc-Comments statt Einzeiler?
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

		// Hier wird der Bot initialisiert, der Karte, der playerID und dem Level
		// zugeordnet
		/*
		 * hier entscheiden wir anhand der Level-ID welchen Bot wir als "unserBot"
		 * verwenden.
		 */

		Bot unserBot;

		switch (level) {
		case 1:
			unserBot = new ZufallsBot2(karte, playerId, startX, startY);
			break;
		case 2:
			unserBot = new ZufallsBot2(karte, playerId, startX, startY);
			break;
		case 3:
			unserBot = new ZufallsBot2(karte, playerId, startX, startY);
			break;
		case 4:
			unserBot = new ZufallsBot2(karte, playerId, startX, startY);
			break;
		case 5:
			unserBot = new ZufallsBot2(karte, playerId, startX, startY);
			break;

		default:
			unserBot = new ZufallsBot2(karte, playerId, startX, startY);
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

			// Karte bef�llen

			// Debug Information ausgeben (optional m�glich)
			/*
			 * Darstellung eines kleinen Kompass mit den jeweils zugeh�rigen Zellstati.
			 */
			System.err.println("Ergebnis Vorrunde Aktion: " + lastActionsResult);
			System.err.println("Ergebnis Vorrunde Norden: " + "               " + northCellStatus);
			System.err.println("Ergebnis Vorrunde Westen / Osten: " + "   " + westCellStatus + " / " + eastCellStatus);
			System.err.println("Ergebnis Vorrunde Sueden: " + "                " + southCellStatus);

			unserBot.machAktion();
		}

		// Alles fertig -> aufr�umen
		input.close();
	}

	public static Bot erstelleBotLevel1(Karte karte, int playerId, int startX, int startY) {
		return new BotLevel1(karte, playerId, startX, startY);
	}

	public static Bot erstelleBotLevel2(Karte karte, int playerId, int startX, int startY) {
		return new BotLevel2(karte, playerId, startX, startY);
	}

}
// TODO 
// R�ckg�ngig machen: unserBot wieder aktivieren (Zeile 87 und Zeile 60: Erstellung des unserBot)
