package de.vitbund.vitmaze.players.ifschleife.bots;

import java.util.ArrayList;
import java.util.List;

import de.vitbund.vitmaze.players.ifschleife.Init;
import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 *
 *         Die Klasse stellt einen Bot dar (soll darstellen), der ausschließlich
 *         nach dem Zufall seine Wegfindung ableitet. Er dient auch für
 *         Testzwecke bei Änderungen an den Supportklassen (Karte, Felder usw.)
 */
public class ZufallsBot2Lvl1 extends Bot {

	public ZufallsBot2Lvl1(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}


	public void machAktion() {

		if (("FINISH " + super.id + " 0").equals(Init.currentCellStatus)) {
			System.out.println("finish");
		}

//		int x = this.getPunkt().getX();
//		int y = this.getPunkt().getY();

//		//in die Bot Klasse verschoben
//		aktuelleKarte.aktualisiereFeld(getOrt().norden()/* y - 1 */, Init.northCellStatus);
//		aktuelleKarte.aktualisiereFeld(getOrt().sueden() /* y + 1 */, Init.southCellStatus);
//		aktuelleKarte.aktualisiereFeld(getOrt().osten()/* x + 1 */, Init.eastCellStatus);
//		aktuelleKarte.aktualisiereFeld(getOrt().westen()/* x - 1 */, Init.westCellStatus);
//		aktuelleKarte.aktualisiereFeld(getOrt(), Init.currentCellStatus);
//		if (aktuelleKarte.getFeld(getOrt()) != null) {
//			aktuelleKarte.getFeld(getOrt()).pruefenErkundet();
//		}
		this.rundeInitialisiern(); // TODO überlegen muss das für jeden Bot gemacht werden, wenn ja in die Init
									// verschieben?

		// test Wegfindung
//		LinkedHashMap<Feld, VorhergehenderSchritt> wege = getAktuelleKarte().findeWege(getPunkt());
//		getAktuelleKarte().ausgabeWegliste(wege);

		// testen der Funktion pruefenErkundet der Karte
		// aktuelleKarte.toSysErrErkundeteFelder();

//		System.err.print(
//				" Ort: " + this.getPunkt() + " erkundet " + this.aktuelleKarte.getFeld(getPunkt()).pruefenErkundet());

		schlauereZufallsrichtung();
	}

	
	

//	public void letztesFeld() {
//		aktuelleKarte.isFeldBekannt(x, y);
//
//	}
}
