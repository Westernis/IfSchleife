package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 * 
 *         Die Klasse BotLevel2 ist eine Erweiterung des Bots und soll primär im
 *         zweiten Level zum Einsatz kommen.
 *
 */
public class BotLevel2 extends Bot {

	public BotLevel2(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	public void machAktion() {
		
		/*
		 * var aktuelles Formular, höchstes Formular(-1 wenn unbekannt?)
		 * 
		 * Anfangen
		 * 1. Karte erkunden
		 * 2. Abbruch wenn ein Ziel in Sicht
		 * 2.1 prüfen ob aktuelles Ziel
		 * 2.2 ja einsammeln
		 * 3. 	a) nächstes Ziel bekannt -> hinfahren ->2.2
		 *		b) -> 1.
		 * 
		 * 
		 * 3. Alles eingesammelt -> zu dem Ziel fahren
		 * 
		 */	
		

	}
}
