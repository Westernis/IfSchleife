package de.vitbund.vitmaze.players.ifschleife.karte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;

/**
 * TODO
 * 
 * @author IFSchleife
 *
 */
public class Karte {

	// 1. Index ist die x-Koordinate(Breite), der 2. Index ist die
	// y-Koordinate(Höhe)
	private final Feld[][] felder;

	public Karte(int x, int y) {
		this.felder = new Feld[x][y];
	}

	public void getFeldtyp(int x, int y) {
		// FIXME nicht sicher ob wir die brauchen ^^
		// TODO soll Eigenschaften zum Feld zurück geben, eventuell das Feld selbst?
	}

	/**
	 * Diese Methode soll den kürzesten Weg zwischen 2 Felder bestimmen.
	 * Berücksichtigt werden dabei nur Felder, die zum Zeitpunkt des Aufrufs bekannt
	 * sind.
	 * 
	 * @param xStart
	 * @param yStart
	 * @param xZiel
	 * @param yZiel
	 * @return Ein Array mit den zu gehenden Feldern, angefangen mit dem Feld
	 *         (xStart,yStart).
	 */
	public Feld[] getWeg(int xStart, int yStart, int xZiel, int yZiel) {
		// TODO
		return null;
	}

	/**
	 * Gibt eine Reihe von Anweisungen als String[] zurück die den mittels
	 * {@code getWeg(int xStart, int yStart, int xZiel, int yZiel)} ermittelten Weg
	 * abfahren.
	 * 
	 * @param xStart
	 * @param yStart
	 * @param xZiel
	 * @param yZiel
	 * @return
	 */
	public String[] getWegAnweisungen(int xStart, int yStart, int xZiel, int yZiel) {
		// TODO
		return null;
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
	public void aktualisiereFeld(int x, int y, String feldbeschreibung) {
		if (felder[x][y] == null) {
			// TODO switch mit feldbeschreibung, greift entsprechend auf Klassen Flur und

			// Wand zu
			switch (feldbeschreibung) {
			case "FLOOR":
				felder[x][y] = new Flur(x, y, this);
				// Wege erstellen

				// prüfen, ob das Feld auch wirklich schon bekannt (!=null) ist UND das Feld
				// begehbar ist.
				// wenn ja dann Weg setzen (setHimmelsrichtung), und auch umgekehrt
				// wenn nein dann bleibt die
				// Variable auf null

				// TODO Himmelsrichtungen überprüfen, Arraygrenzen

				if (this.isFeldBekannt(x + 1, y) && felder[x + 1][y].istBegehbar()) { // Ost
					felder[x][y].setOst(felder[x + 1][y]);
					felder[x + 1][y].setWest(felder[x][y]);
					// else nicht nötig, da die Variable Ost auf null bleibt (siehe Feld ost)
				}
				if (this.isFeldBekannt(x - 1, y) && felder[x - 1][y].istBegehbar()) { // West
					felder[x][y].setWest(felder[x - 1][y]);
					felder[x - 1][y].setOst(felder[x][y]);
				}
				if (this.isFeldBekannt(x, y + 1) && felder[x][y + 1].istBegehbar()) { // Süd
					felder[x][y].setSued(felder[x][y + 1]);
					felder[x][y + 1].setNord(felder[x][y]);
				}
				if (this.isFeldBekannt(x, y - 1) && felder[x][y - 1].istBegehbar()) { // Nord
					felder[x][y].setNord(felder[x][y - 1]);
					felder[x][y - 1].setSued(felder[x][y]);
				}
				break;
			case "WALL":
				felder[x][y] = new Wand(x, y, this, true); // Bei Wand keine Wege nötig
				break;
			// TODO case "FINISH"
			// TODO case "FORM"
			}

		}
	}

	public boolean isFeldBekannt(int x, int y) {
		if (felder[x][y] == null) {
			return false;
		}
		return true;
	}

	// Vielleicht brauchen wir das noch
	public Feld getFeld(int x, int y) {
		// TODO Arraygrenzen abfangen
		return felder[x][y];
	}

	public void ausgabe() {
		int x = felder.length;
		int y = felder[0].length;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (felder[j][i] == null) {
					System.err.print("0");
				} else if (felder[j][i].istBegehbar() == true) {
					System.err.print("|");
				} else {
					System.err.print("W");

				}
			}
			System.err.println("");
		}
	}

	public Map<Feld, VorhergehenderSchritt> findeWege(int startX, int startY) {
//		Start und Zielfeld prüfen ob korrekt

		if (felder[startX][startY] == null || !felder[startX][startY].istBegehbar()) {
			return null;
		}

//		Wegetabelle anlegen

		Map<Feld, VorhergehenderSchritt> wege = new HashMap(felder.length * felder[0].length * 2);
		List<Feld> arbeitsliste = new ArrayList<Feld>();
		List<Feld> fertigFelder = new ArrayList<Feld>();

//		Startknoten abarbeiten

		wege.put(felder[startX][startY], new VorhergehenderSchritt(0, null));
		arbeitsliste.add(felder[startX][startY]);

// 		Algorithmus abarbeiten
		Feld arbeit = null;
		if(!arbeitsliste.isEmpty()) {
			 arbeit = arbeitsliste.get(0);
		}
		while (!arbeitsliste.isEmpty() && arbeit.pruefenErkundet()) {
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

		// zum testen:
		//ausgabeWegliste(wege);
		// test ende
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
				// TODO ist es nötig bereits vorhandene Felder zu aktualisieren? Oder ist das
				// zuerst gefundene Feld immer der kürzste Weg?
			}
		}
	}

	public void ausgabeWegliste(Map<Feld, VorhergehenderSchritt> wege) {

		for (Entry<Feld, VorhergehenderSchritt> feld : wege.entrySet()) {
			if (feld.getValue().getVorgaenger() == null) {
				System.err.println("Ziel " + feld.getKey().getX() + " " + feld.getKey().getY() + " Startknoten \t Weg "
						+ feld.getValue().getWeglaenge());
			} else {

				System.err.println("Ziel " + feld.getKey().getX() + " " + feld.getKey().getY() + " Vorgaenger "
						+ feld.getValue().getVorgaenger().getX() + " " + feld.getValue().getVorgaenger().getY()
						+ "\t Weg " + feld.getValue().getWeglaenge());
			}
		}

	}

	public static void main(String[] args) {
		Karte karte = new Karte(4, 4);
		// x Richtung
		for (int i = 0; i < 4; i++) {
			karte.aktualisiereFeld(i, 0, "WALL");
			karte.aktualisiereFeld(i, 3, "WALL");
		}
		// y Richtung
		for (int i = 0; i < 4; i++) {
			karte.aktualisiereFeld(0, i, "WALL");
			karte.aktualisiereFeld(3, i, "WALL");
		}

		karte.aktualisiereFeld(1, 1, "FLOOR");
		karte.aktualisiereFeld(2, 1, "FLOOR");
		karte.aktualisiereFeld(1, 2, "FLOOR");
		karte.aktualisiereFeld(2, 2, "FLOOR");
		karte.findeWege(2, 2);

	}
	
	public void toSysErrErkundeteFelder() {
		for (int y = 0; y < felder[0].length; y++) {
			for (int x = 0; x < felder.length; x++) {
				if(felder[x][y] == null) {
					System.err.print(" |");
					
				}else if(felder[x][y].pruefenErkundet()) {
					System.err.print("E|");
				}else {
					System.err.print("U|");
				}
			}
			System.err.println("");
		}

		
	}
}
