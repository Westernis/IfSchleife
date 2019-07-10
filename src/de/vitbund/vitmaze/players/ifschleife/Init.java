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

	public static String lastActionsResult = "";
	public static String currentCellStatus = "";
	public static String northCellStatus = "";
	public static String southCellStatus = "";
	public static String westCellStatus = "";
	public static String eastCellStatus = "";

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
		/*
		 * Erstellen einer Bot-Instanz (namentlich unserBot). Klasse ist BotLevel1 (erbt
		 * von Bot). TODO: Hier könnte man eine einfache Überprüfung des Levels für die
		 * Erstellung eines passenden Bots erstellen à Level2 > BotLevel2 unserBot = new
		 * BotLevel2 Vorschläge: (BotLevel(level)) unserBot2 = new (BotLevel(level))???
		 * 
		 * String botTyp = "BotLevel" + level; botTyp unserBot2 =
		 * botTyp(blabla,soso,aha);
		 */

		// TODO Auswahl welcher Bot benutzt wird
		//TODO: hier noch eine Verzweigung für Level -> passender Bot bauen

		Bot unserBot = erstelleBotLevel1(karte, playerId, startX, startY);

		while (input.hasNext()) {

			// Rundeninformationen auslesen

			lastActionsResult = input.nextLine();
			currentCellStatus = input.nextLine();
			northCellStatus = input.nextLine();
			eastCellStatus = input.nextLine();
			southCellStatus = input.nextLine();
			westCellStatus = input.nextLine();

			// Karte befüllen

			// Debug Information ausgeben (optional möglich)
			/*
			 * Darstellung eines kleinen Kompass mit den jeweils zugehörigen Zellstati.
			 */
			System.err.println("Ergebnis Vorrunde Aktion: " + lastActionsResult);
			System.err.println("Ergebnis Vorrunde Norden: " + "               " + northCellStatus);
			System.err.println("Ergebnis Vorrunde Westen / Osten: " + "   " + westCellStatus + " / " + eastCellStatus);
			System.err.println("Ergebnis Vorrunde Sueden: " + "                " + southCellStatus);

			/*
			 * Aufruf der machAktion (in BotLevel1 überschriebene Methode).
			 */

// aus Testgründen deaktiviert für den ZufallsBOT			
//			unserBot.machAktion(); // man könnte hier auch mehrer Bots benutzen, z.B. einen zum Erkunden, einen zum
			// Einsammeln und einen um zum Ziel zu fahren
			
			ZufallsBot zufallsBot = new ZufallsBot(karte, playerId, startX, startY) ;
			zufallsBot.nochSchlauereZufallsrichtung();
		}

		// Alles fertig -> aufräumen
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
// Rückgängig machen: unserBot wieder aktivieren (Zeile 87 und Zeile 60: Erstellung des unserBot)
