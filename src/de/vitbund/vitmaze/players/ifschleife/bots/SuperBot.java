package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 *
 *         Die Klasse stellt einen Bot dar (soll darstellen), der ausschlie�lich
 *         nach dem Zufall seine Wegfindung ableitet.
 */
public class SuperBot extends Bot {

	public SuperBot(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	public void machAktion() {
		
		this.rundeInitialisiern();
		
		// Vergleich ob aktuelles Feld ein Ziel ist, wenn ja dann Ende

//		Wenn Helmuts Feld wieder funktioniert, dann damit Ziel pr�fen: this.aktuelleKarte.getFeld(getOrt()).toString()
		if (Init.currentCellStatus.startsWith(("FINISH"/* + super.id + " 0"*/))) {
			System.err.println("ZIEL");
			beenden();
			return; // mit beenden ist eine Aktion gew�hlt!
		}
		
		schlauereZufallsrichtung();
		
		
		
		/*
		 * var aktuelles Formular, h�chstes Formular(-1 wenn unbekannt?)
		 * 
		 * Anfangen
		 * 1. Karte erkunden
		 * 2. Abbruch wenn ein Ziel in Sicht
		 * 2.1 pr�fen ob aktuelles Ziel
		 * 2.2 ja einsammeln
		 * 3. 	a) n�chstes Ziel bekannt -> hinfahren ->2.2
		 *		b) -> 1.
		 * 
		 * 
		 * 3. Alles eingesammelt -> zu dem Ziel fahren
		 * 
		 */	
		
		
		
	}
}
