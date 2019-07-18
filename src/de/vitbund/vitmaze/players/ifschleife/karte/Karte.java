package de.vitbund.vitmaze.players.ifschleife.karte;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.vitbund.vitmaze.players.ifschleife.ZellStatus;

/**
 * TODO javadoc
 * 
 * @author IFSchleife
 *
 */
public class Karte {

	// 1. Index ist die x-Koordinate(Breite), der 2. Index ist die
	// y-Koordinate(H�he)
	private final Feld[][] felder;

	public Karte(int sizeX, int sizeY) {
		this.felder = new Feld[sizeX][sizeY];
	}

	public void getFeldtyp(int x, int y) {
		// TODO soll Eigenschaften zum Feld zur�ck geben, eventuell das Feld selbst?
	}

	/**
	 * Gibt das 2-dimensinale Array �ber die Felder der Karte zur�ck.
	 * 
	 * @return Feld[][]
	 */
	public Feld[][] getFelder() {
		return felder;
	}


	public void aktualisiereFeld(Koordinaten punkt, ZellStatus feldtyp) {
		Feld ort = this.getFeld(punkt);
		Feld nachbar;

		System.err.println("Punkt " + punkt);
		
		// Wenn kein Feld vorhanden ist ODER das vorhandene Feld ein Formular und das
		// neu ein Flur ist (oder andersherum) wird eine neue passende Feldinstanz
		// angelegt und gespeichert
		if (ort == null || ((Feld.formular.equals(feldtyp.getTyp()) || Feld.flur.equals(feldtyp.getTyp()))
				&& !ort.getTyp().equals(feldtyp.getTyp()))) {

			System.err.println("anzulegen: "+feldtyp.getTyp());
			// Feldkonstruktionsmethode aufrufen und im Array speichern
			Feld test =  Feld.konstruiereFeld(punkt, this, feldtyp.getTyp(),
					feldtyp.getPlayerID(), feldtyp.getFormNumber());
			System.err.println("Feld anlegen "+ test);
			felder[punkt.getX()][punkt.getY()] = test;
		}
		//abfangen falls zwei Formular durch verschieben die Pl�tze getauscht haben
		if(Feld.formular.equals(feldtyp.getTyp())) {
			//TODO abfangen fertig machen
		}

		// Wege setzen
		// Osten
		nachbar = getFeld(punkt.osten()); // das zu betrachtende Nachbarfeld holen
		if (this.isFeldBekannt(punkt.osten()) && nachbar.istBegehbar()) { // Ost
			ort.setOst(nachbar);
			nachbar.setWest(ort);
		}
		// Westen
		nachbar = getFeld(punkt.westen()); // das zu betrachtende Nachbarfeld holen
		if (this.isFeldBekannt(punkt.westen()) && nachbar.istBegehbar()) { // Ost
			ort.setWest(nachbar);
			nachbar.setOst(ort);
		}
		// Norden
		nachbar = getFeld(punkt.norden()); // das zu betrachtende Nachbarfeld holen
		if (this.isFeldBekannt(punkt.norden()) && nachbar.istBegehbar()) { // Ost
			ort.setNord(nachbar);
			nachbar.setSued(ort);
		}
		// S�den
		nachbar = getFeld(punkt.sueden()); // das zu betrachtende Nachbarfeld holen
		if (this.isFeldBekannt(punkt.sueden()) && nachbar.istBegehbar()) { // Ost
			ort.setSued(nachbar);
			nachbar.setNord(ort);
		}
	}

	public boolean isFeldBekannt(Koordinaten ort) {
		// return isFeldBekannt(ort.getX(), ort.getY());
		if (felder[ort.getX()][ort.getY()] == null) {
			return false;
		}
		return true;
	}

	public Feld getFeld(Koordinaten punkt) {
		// Arraygrenzen abfangen sollte nicht n�tig sein, daf�r ist die
		// koordinatenklassse zust�ndig7
		// System.err.println("helmut " + felder[punkt.getX()][punkt.getY()] + " xy " +
		// punkt.getX() + " " + punkt.getY());
		return felder[punkt.getX()][punkt.getY()];
	}

	public void ausgabe() {
		int x = felder.length;
		int y = felder[0].length;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (felder[j][i] == null) {
					System.err.print("0");
				} else if (felder[j][i].istBegehbar() == true) {
					if (felder[j][i].getTyp().equals(Feld.flur)) {
						System.err.print("_");
					} else
						System.err.print("|");
				} else {
					System.err.print("W");

				}
			}
			System.err.println("");
		}
	}

//	public LinkedHashMap<Feld, VorhergehenderSchritt> findeWege(int startX, int startY) {
//		return this.findeWege(new Koordinaten(startX, startY));
//	}

	/**
	 * 
	 * @param startX
	 * @param startY
	 * @return
	 */
	/*
	 * LinkedHashMap f�r wege wurde gew�hlt, weil die Reihenfolge, in der die Felder
	 * hinzugef�gt wurden, erhalten bleibt und ein Iterator die Werte in dieser
	 * Reihenfolge zur�ckgeben kann. Das ist in soweit hilfreich, da der
	 * Dijkstra-Algorithmus die Felder nach Wegl�nge sortiert findet. Somit ist auch
	 * unsere Liste nach Wegl�nge sortiert
	 * 
	 * https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
	 *
	 */
	public LinkedHashMap<Feld, VorhergehenderSchritt> findeWege(Koordinaten startpunkt) {
//		Start und Zielfeld pr�fen ob korrekt

		if (felder[startpunkt.getX()][startpunkt.getY()] == null
				|| !felder[startpunkt.getX()][startpunkt.getY()].istBegehbar()) {
			return null;
		}

//		Wegetabelle anlegen

		LinkedHashMap<Feld, VorhergehenderSchritt> wege = new LinkedHashMap<Feld, VorhergehenderSchritt>(
				felder.length * felder[0].length * 2);
		// Grund f�r den 2. parameter siehe hashmaps und Verhalten "rehash"
		List<Feld> arbeitsliste = new ArrayList<Feld>();
		List<Feld> fertigFelder = new ArrayList<Feld>();

//		Startknoten abarbeiten

		wege.put(felder[startpunkt.getX()][startpunkt.getY()], new VorhergehenderSchritt(0, null));
		arbeitsliste.add(felder[startpunkt.getX()][startpunkt.getY()]);

// 		Algorithmus abarbeiten
		Feld arbeit = null;
		if (!arbeitsliste.isEmpty()) {
			arbeit = arbeitsliste.get(0);
		}
		while (!arbeitsliste.isEmpty()) {
			arbeit = arbeitsliste.get(0);

//			Arbeitsliste aktualisieren

			arbeitslisteAktualisieren(arbeit.getNord(), fertigFelder, arbeitsliste);
			arbeitslisteAktualisieren(arbeit.getSued(), fertigFelder, arbeitsliste);
			arbeitslisteAktualisieren(arbeit.getWest(), fertigFelder, arbeitsliste);
			arbeitslisteAktualisieren(arbeit.getOst(), fertigFelder, arbeitsliste);

			// aktuellen Knoten umsetzen
			arbeitsliste.remove(0);
			fertigFelder.add(arbeit);

			wegelisteAktualisieren(arbeit, arbeit.getNord(), wege);
			wegelisteAktualisieren(arbeit, arbeit.getSued(), wege);
			wegelisteAktualisieren(arbeit, arbeit.getWest(), wege);
			wegelisteAktualisieren(arbeit, arbeit.getOst(), wege);
		}

		return wege;
	}

	public void arbeitslisteAktualisieren(Feld arbeitnachbar, List<Feld> fertigFelder, List<Feld> arbeitsliste) {
		if (arbeitnachbar != null) {
			if (!fertigFelder.contains(arbeitnachbar) && !arbeitsliste.contains(arbeitnachbar)) {
				arbeitsliste.add(arbeitnachbar);
			}
		}
	}

	public void wegelisteAktualisieren(Feld arbeit, Feld nachbar, Map<Feld, VorhergehenderSchritt> wege) {
		if (nachbar != null) {
			int weglaenge = wege.get(arbeit).getWeglaenge();

			if (!wege.containsKey(nachbar)) {
				wege.put(nachbar, new VorhergehenderSchritt(weglaenge + 1, arbeit));
			} else {
				/*
				 * keine Arbeit n�tig, da wir immer bidirektionale Wege der Gewichtung 1 haben.
				 * Beim Dijkstra-Algorithmus f�hrt das dazu, dass immer einer der k�rzesten Wege
				 * zu einem Feld zuerst gefunden wird. -> keine Aktualisierungen n�tig
				 */
			}
		}
	}

	public void ausgabeWegliste(Map<Feld, VorhergehenderSchritt> wege) {

		for (Entry<Feld, VorhergehenderSchritt> feld : wege.entrySet()) {
			if (feld.getValue().getVorgaenger() == null) {
				System.err.println("Ziel " + feld.getKey().getX() + " " + feld.getKey().getY() + " Startknoten \t Weg "
						+ feld.getValue().getWeglaenge());
			} else {

				System.err.println("Ziel " + feld.getKey().getX() + " " + feld.getKey().getY() + "\t Vorgaenger \t"
						+ feld.getValue().getVorgaenger().getX() + " " + feld.getValue().getVorgaenger().getY()
						+ "\t Weg " + feld.getValue().getWeglaenge());
			}
		}

	}

	/**
	 * Diese main dient dem Testen der Karte und Wegfindung.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Karte karte = new Karte(4, 4);
		// x Richtung
//		for (int i = 0; i < 4; i++) {
//			karte.aktualisiereFeld(new Koordinaten(i, 0), "WALL");
//			karte.aktualisiereFeld(new Koordinaten(i, 3), "WALL");
//		}
//		// y Richtung
//		for (int i = 0; i < 4; i++) {
//			karte.aktualisiereFeld(new Koordinaten(0, i), "WALL");
//			karte.aktualisiereFeld(new Koordinaten(3, i), "WALL");
//		}
//
//		karte.aktualisiereFeld(new Koordinaten(1, 1), "FLOOR");
//		karte.aktualisiereFeld(new Koordinaten(2, 1), "FLOOR");
//		karte.aktualisiereFeld(new Koordinaten(1, 2), "FLOOR");
//		karte.aktualisiereFeld(new Koordinaten(2, 2), "FLOOR");
//		karte.findeWege(new Koordinaten(2, 2));

	}

	// Testfunktion Koordinatennutzung nicht n�tig
	public void toSysErrErkundeteFelder() {
		for (int y = 0; y < felder[0].length; y++) {
			for (int x = 0; x < felder.length; x++) {
				if (felder[x][y] == null) {
					System.err.print(" |");

				} else if (felder[x][y].pruefenErkundet()) {
					System.err.print("E|");
				} else {
					System.err.print("U|");
				}
			}
			System.err.println("");
		}

	}

	public ArrayList<Feld> werteListeAus(Map<Feld, VorhergehenderSchritt> wege, Feld wunschZiel) {
		ArrayList<Feld> rueckgabe = new ArrayList<Feld>();
		rueckgabe.add(wunschZiel);

		Feld arbeitsVariable = wege.get(wunschZiel).getVorgaenger();

		// der erste Wert (Startknoten) hat als Feld null, daher pr�fen wir gegen null.
		while (arbeitsVariable != null) {
			rueckgabe.add(0, arbeitsVariable);
			arbeitsVariable = wege.get(arbeitsVariable).getVorgaenger();
		}

		return rueckgabe;

	}

}
