package de.vitbund.vitmaze.players.ifschleife;

import java.util.Scanner;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * Diese Klasse soll die Initialisierung durchführen. Das heißt es wird ein Bot
 * angelegt, der die benötigten Karten und ein zum Level passendes Hirn
 * zugewiesen bekommt. Außerdem händelt die Klasse die initiale
 * Datenverarbeitung am Anfang jeder Runde.
 * 
 * @author helmut.rietz
 *
 */
public class Init {

	public static void main(String[] args) {
		// TODO Initialisierung (ersten Text einlesen, Karten initialisieren für lvl 2?)

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

		Bot unserBot = new Bot(karte, playerId, startX, startY);
		// TODO Bot aufrufen

		while (input.hasNext()) {

			unserBot.machAktion(); // man könnte hier auch mehrer Bots benutzen, z.B. einen zum Erkunden, einen zum
									// Einsammeln und einen um zum Ziel zu fahren
		}

		// Alles fertig -> aufräumen
		input.close();
	}

}
