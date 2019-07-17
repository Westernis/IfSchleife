package de.vitbund.vitmaze.players.ifschleife.karte;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * TODO javadoc
 * 
 * @author IFSchleife
 *
 */
public class Karte {

	// 1. Index ist die x-Koordinate(Breite), der 2. Index ist die
	// y-Koordinate(Höhe)
	private final Feld[][] felder;

	public Karte(int sizeX, int sizeY) {
		this.felder = new Feld[sizeX][sizeY];
	}

	public void getFeldtyp(int x, int y) {
		// TODO soll Eigenschaften zum Feld zurück geben, eventuell das Feld selbst?
	}

	/**
	 * Gibt das 2-dimensinale Array über die Felder der Karte zurück.
	 * 
	 * @return Feld[][]
	 */
	public Feld[][] getFelder() {
		return felder;
	}

	// Festestellen, ob bei den Koordinaten schon ein Feldobjekt existiert. Wenn
	// nein->Anlegen. Welche Wege müssen hinzugefügt werden?
	public void aktualisiereFeld(Koordinaten punkt, String feldbeschreibung) {
		Feld ort = this.getFeld(punkt);
		Feld nachbar;
		// FIXME UMBAU hier soll nur noch die Wege Aktualisierung stattfinden, das
		// anlegen eines Korrektenfeldtyps wird in eine statische Methode der Feld
		// Klasse verlegt -> Feld.konstruiereFeld(String input)

		if (ort == null) {
			switch (feldbeschreibung) {
			case "WALL":
				felder[punkt.getX()][punkt.getY()] = new Wand(punkt, this, true); // Bei Wand keine Wege nötig
				break;
			case "FLOOR":
			default:
				ort = new Flur(punkt, this); // neues Feld anlegen
				felder[punkt.getX()][punkt.getY()] = ort; // Feld sichern
				// Wege erstellen

				/*
				 * prüfen, ob das benachbarte Feld auch schon bekannt (!=null) ist UND ob das
				 * Feld begehbar ist. Wenn ja, dann Weg setzen (set"Himmelsrichtun"g()), und
				 * auch umgekehrt. Wenn nein, dann bleibt die Variable auf null, daher es muss
				 * nix gemacht werden
				 */
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
				// Süden
				nachbar = getFeld(punkt.sueden()); // das zu betrachtende Nachbarfeld holen
				if (this.isFeldBekannt(punkt.sueden()) && nachbar.istBegehbar()) { // Ost
					ort.setSued(nachbar);
					nachbar.setNord(ort);
				}

				// break;

				// TODO case "FINISH"
				// TODO case "FORM"
				// TODO gegnerischer Bot?
			}

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
		// Arraygrenzen abfangen sollte nicht nötig sein, dafür ist die
		// koordinatenklassse zuständig7
		//System.err.println("helmut " + felder[punkt.getX()][punkt.getY()] + " xy " + punkt.getX() + " " + punkt.getY());
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
	 * LinkedHashMap für wege wurde gewählt, weil die Reihenfolge, in der die Felder
	 * hinzugefügt wurden, erhalten bleibt und ein Iterator die Werte in dieser
	 * Reihenfolge zurückgeben kann. Das ist in soweit hilfreich, da der
	 * Dijkstra-Algorithmus die Felder nach Weglänge sortiert findet. Somit ist auch
	 * unsere Liste nach Weglänge sortiert
	 * 
	 * https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
	 *
	 */
	public LinkedHashMap<Feld, VorhergehenderSchritt> findeWege(Koordinaten startpunkt) {
//		Start und Zielfeld prüfen ob korrekt

		if (felder[startpunkt.getX()][startpunkt.getY()] == null
				|| !felder[startpunkt.getX()][startpunkt.getY()].istBegehbar()) {
			return null;
		}

//		Wegetabelle anlegen

		LinkedHashMap<Feld, VorhergehenderSchritt> wege = new LinkedHashMap<Feld, VorhergehenderSchritt>(
				felder.length * felder[0].length * 2);
		// Grund für den 2. parameter siehe hashmaps und Verhalten "rehash"
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
				 * keine Arbeit nötig, da wir immer bidirektionale Wege der Gewichtung 1 haben.
				 * Beim Dijkstra-Algorithmus führt das dazu, dass immer einer der kürzesten Wege
				 * zu einem Feld zuerst gefunden wird. -> keine Aktualisierungen nötig
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
		for (int i = 0; i < 4; i++) {
			karte.aktualisiereFeld(new Koordinaten(i, 0), "WALL");
			karte.aktualisiereFeld(new Koordinaten(i, 3), "WALL");
		}
		// y Richtung
		for (int i = 0; i < 4; i++) {
			karte.aktualisiereFeld(new Koordinaten(0, i), "WALL");
			karte.aktualisiereFeld(new Koordinaten(3, i), "WALL");
		}

		karte.aktualisiereFeld(new Koordinaten(1, 1), "FLOOR");
		karte.aktualisiereFeld(new Koordinaten(2, 1), "FLOOR");
		karte.aktualisiereFeld(new Koordinaten(1, 2), "FLOOR");
		karte.aktualisiereFeld(new Koordinaten(2, 2), "FLOOR");
		karte.findeWege(new Koordinaten(2, 2));

	}

	// Testfunktion Koordinatennutzung nicht nötig
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

	public ArrayList<Feld> werteListeAus(Map<Feld, VorhergehenderSchritt> karte, Feld wunschZiel) {
		ArrayList<Feld> rueckgabe = new ArrayList<Feld>();
		rueckgabe.add(wunschZiel);

		Feld arbeitsVariable = karte.get(wunschZiel).getVorgaenger();

		// der erste Wert (Startknoten) hat als Feld null, daher prüfen wir gegen null.
		while (arbeitsVariable != null) {
			rueckgabe.add(0, arbeitsVariable);
			arbeitsVariable = karte.get(arbeitsVariable).getVorgaenger();
		}

		return rueckgabe;

	}

}
