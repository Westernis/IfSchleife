package de.vitbund.vitmaze.players.ifschleife;

import java.util.Scanner;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

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

	public static void main(String[] args) {
		// TODO Initialisierung (ersten Text einlesen, Karten initialisieren für lvl 2?)

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

		// Hier wird der Bot initialisiert, der Karte, der playerID und dem Level
		// zugeordnet
		/**
		 * Erstellen einer Bot-Instanz (namentlich unserBot). Klasse ist BotLevel1 (erbt
		 * von Bot).
		 * TODO: Hier könnte man eine einfache Überprüfung des Levels für die Erstellung eines passenden Bots erstellen
		 * à Level2 > BotLevel2 unserBot = new BotLevel2
		 * Vorschläge:
		 * (BotLevel(level)) unserBot2 = new (BotLevel(level))???
		 * 
		 * String botTyp = "BotLevel" + level;
		 * botTyp unserBot2 = botTyp(blabla,soso,aha);
		 */
		
		
		BotLevel1 unserBot = new BotLevel1(karte, playerId, startX, startY);

		while (input.hasNext()) {

			// Rundeninformationen auslesen

			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();

			// Debug Information ausgeben (optional möglich)
			/**
			 * Darstellung eines kleinen Kompass mit den jeweils zugehörigen Zellstati.
			 */
			System.err.println("Ergebnis Vorrunde Aktion: " + lastActionsResult);
			System.err.println("Ergebnis Vorrunde Norden: " + "               " + northCellStatus);
			System.err.println("Ergebnis Vorrunde Westen / Osten: " + "   " + westCellStatus + " / " + eastCellStatus);
			System.err.println("Ergebnis Vorrunde Sueden: " + "                " + southCellStatus);

			/**
			 * Aufruf der machAktion (in BotLevel1 überschriebene Methode).
			 */
			unserBot.machAktion(lastActionsResult, northCellStatus, southCellStatus, westCellStatus, eastCellStatus,
					currentCellStatus); // man könnte hier auch mehrer Bots benutzen, z.B. einen zum Erkunden, einen zum
			// Einsammeln und einen um zum Ziel zu fahren
		}

		// Alles fertig -> aufräumen
		input.close();
	}

}
