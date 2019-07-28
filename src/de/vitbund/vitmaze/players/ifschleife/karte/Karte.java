package de.vitbund.vitmaze.players.ifschleife.karte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.vitbund.vitmaze.players.ifschleife.ZellStatus;

/**
 * Die Karte ist eine Sammlung von Informationen bzgl. des Spielfelds.
 * Bestandteile sind u.A. die Felder und die Anzahl der Formulare. In der Karte
 * befinden sich außerdem Methoden zur Wegfindung.
 * 
 * @author IFSchleife
 *
 */
public class Karte {

	// 1. Index ist die x-Koordinate(Breite), der 2. Index ist die
	// y-Koordinate(Höhe)
	private final Feld[][] felder;
	private int anzahlFormulare = -1;
	// Ziele sind statische Eigenschaften der Karte, Formulare nicht, deshalb werden
	// letztere hier nicht als zusätzliche Liste mitgeführt.
	private HashMap<Integer, Ziele> ziele; // HashMap akzeptiert keine primitiven Typen daher Integer hier

	/**
	 * Erstellt eine Karte mit einer übergebenen Größe. Diese Karte hat Felder und
	 * eine Liste mit bekannten Zielen.
	 * 
	 * @param sizeX - die Größe die die Karte in der Horizontalen ausweißt.
	 * @param sizeY - die Größe die die Karte in der Vertikalen ausweißt.
	 */
	public Karte(int sizeX, int sizeY) {
		this.felder = new Feld[sizeX][sizeY];
		this.ziele = new HashMap<Integer, Ziele>();
	}

	/**
	 * Gibt das 2-dimensinale Array über die Felder der Karte zurück.
	 * 
	 * @return Feld[][]
	 */
	public Feld[][] getFelder() {
		return felder;
	}

	/**
	 * Gibt ein Feld in übergebenen Koordinaten zurück.
	 * 
	 * @param punkt - die Koordinaten die das erwünschte Feld aufweist.
	 * @return die felder.
	 */
	public Feld getFeld(Koordinaten punkt) {
		// Arraygrenzen abfangen sollte nicht nötig sein, dafür ist die
		// koordinatenklassse zuständig7
		return felder[punkt.getX()][punkt.getY()];
	}

	/**
	 * Gibt die Ziele mit der übergebenen Spieler-ID zurück.
	 * 
	 * @param playerID - Die ID des Spielers. In VITMaze gibts auch Ziele für andere
	 *                 Spieler.
	 * @return {@code null} wenn es kein passendes Ziel gibt, ansonsten die Ziele
	 */
	public Ziele getZiel(int playerID) {
		return ziele.get(playerID);
	}

	/**
	 * Prüft ob ein Feld (wird mit den Koordinaten übergeben) bekannt ist.
	 * 
	 * @param ort - den es zu überprüfen gilt
	 * @return {@code false} wenn Feld nicht bekannt. {@code True} wenn Feld
	 *         bekannt.
	 */
	public boolean isFeldBekannt(Koordinaten ort) {
		if (felder[ort.getX()][ort.getY()] == null) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return die Anzahl der Formulare die sich in der Karte befinden, bzw. die
	 *         bisher entdeckt wurden.
	 */
	public int getAnzahlFormulare() {
		return anzahlFormulare;
	}

	/**
	 * Aktualisiert ein gewünschtes Feld auf einen angegebenen feldTyp.
	 * 
	 * @param punkt   - Die Koordinaten den zu aktualisierenden Felds.
	 * @param feldtyp - Der Typ den das erwähnte Feld aufweisen soll.
	 */
	public void aktualisiereFeld(Koordinaten punkt, ZellStatus feldtyp) {
		Feld ort = this.getFeld(punkt);
		boolean warBereitsErkundet = false;
		boolean feldErneuern = false; // dient rein der lesbarkeit, die bedingungen unten waren sonst fast 10 zeilig

		// kein Feld bisher vorhanden?
		if (ort == null) {
			feldErneuern = true;
		} else {
			// Feldvorhanden aber Typ hat sich geändert? //TODO ZETTEL
			if (!ort.getTyp().equals(feldtyp.getTyp())) {
				feldErneuern = true;
				// Ich muss bei Zetteln den Status bzgl. erkundet manuell mitnehmen
				if (Feld.zettel.equals(feldtyp.getTyp())) {
					warBereitsErkundet = ort.pruefenErkundet();
				}

			} else {
				// Typ gleich und es ist ein Formular? -> prüfen ob tatsächlich selbes Formular
				// ->
				// ID oder spieler !=
				Ziele form = this.getFormulare(punkt);
				if (form != null
						&& (form.getFormID() != feldtyp.getFormID() || form.getPlayerID() != feldtyp.getPlayerID())) {

				}
			}
		}

		// Feld ändern
		if (feldErneuern) {

			// Feldkonstruktionsmethode aufrufen und Ergebnis im Array speichern
			felder[punkt.getX()][punkt.getY()] = Feld.konstruiereFeld(punkt, this, feldtyp.getTyp(),
					feldtyp.getPlayerID(), feldtyp.getFormID());

			// altes Formular entfernen um doppelte Formulare zu vermeiden
			if (Feld.formular.equals(feldtyp.getTyp())) {
				Ziele f;
				// Array nach dem Formular durchsuchen
				for (Feld[] felds : felder) {
					for (Feld feld : felds) {

						if (feld != null) {
							f = getFormulare(feld.getPunkt()); // feld noch mal als "Ziele" holen
							if (f != null && feldtyp.getPlayerID() == f.getPlayerID()
									&& feldtyp.getFormID() == f.getFormID()) {
								int x = f.getPunkt().getX();
								int y = f.getPunkt().getY();
								// damit das neu eingefügte Formular nicht entfernt wird
								if (x != punkt.getX() && y != punkt.getY()) {
									// Formular raus nehmen und durch Floor ersetzen

									felder[x][y] = Feld.konstruiereFeld(f.getPunkt(), this, Feld.flur, 0, 0);
									// Wege für das neue Feld setzen
									wegeSetzen(new Koordinaten(x, y));
									break;
								}
							}
						}

					}
				}
			}
			// neues Feld ist ein Zettel -> ich muss den erkundet Status setzen
			if (Feld.zettel.equals(feldtyp.getTyp())) {
				felder[punkt.getX()][punkt.getY()].setErkundet(warBereitsErkundet);
			}
		}

		/*
		 * ort muss noch mal geholt werden, es kann ja sein das oben gerade ein neues
		 * Feld angelegt wurde,
		 */
		ort = this.getFeld(punkt);

		if (ort != null && ort.istBegehbar()) {

//			Ziel hinzufügen
			if (Feld.ziel.equals(ort.getTyp())) {
				addZiel(ort);
			}
			this.wegeSetzen(punkt);
		}
	}

	/**
	 * Aktualisiert die Wege der einzelnen Felder
	 * 
	 * @param punkt Feld für das die Wege aktualisiert werden
	 */
	private void wegeSetzen(Koordinaten punkt) {
		Feld ort = this.getFeld(punkt);
		Feld nachbar;
		// Wege setzen
		// Osten
		nachbar = getFeld(punkt.osten()); // das zu betrachtende Nachbarfeld holen
		if (this.isFeldBekannt(punkt.osten()) && nachbar.istBegehbar()) { // Ost
			ort.setOst(nachbar);
			nachbar.setWest(ort);
		} else {
			ort.setOst(null);
		}
		// Westen
		nachbar = getFeld(punkt.westen()); // das zu betrachtende Nachbarfeld holen
		if (this.isFeldBekannt(punkt.westen()) && nachbar.istBegehbar()) { // Ost
			ort.setWest(nachbar);
			nachbar.setOst(ort);
		} else {
			ort.setWest(null);
		}

		// Norden
		nachbar = getFeld(punkt.norden()); // das zu betrachtende Nachbarfeld holen
		if (this.isFeldBekannt(punkt.norden()) && nachbar.istBegehbar()) { // Ost
			ort.setNord(nachbar);
			nachbar.setSued(ort);
		} else {
			ort.setNord(null);
		}

		// Süden
		nachbar = getFeld(punkt.sueden()); // das zu betrachtende Nachbarfeld holen
		if (this.isFeldBekannt(punkt.sueden()) && nachbar.istBegehbar()) { // Ost
			ort.setSued(nachbar);
			nachbar.setNord(ort);
		} else {
			ort.setSued(null);
		}
	}

	/**
	 * Fügt ein Ziel zur Zieleliste hinzu und setzt die Anzahl der nötigen
	 * Formulare.
	 * 
	 * @param f - das Feld das es anzupassen gilt.
	 * @return {@code false} falls das Übergebene Feld nicht vom Typ
	 *         {@link Feld#ziel} ist.
	 */
	public boolean addZiel(Feld f) {
		if (f.getTyp().equals(Feld.ziel))// Prüfen ob ich umwandeln darf
		{
			Ziele z = ((Ziele) f); // umwandeln
			int id = z.getPlayerID();

			this.ziele.put(id, z);
			this.anzahlFormulare = z.getFormID();

			return true;
		}
		return false;
	}

	/**
	 * Gibt die Karte in der Standard-Ausgabe aus. Nützlich für Debug-Zwecke.
	 */
	public void ausgabe() {
		// TODO - Umbau auf StringBuilder wäre schön
		int x = Koordinaten.getxMax();
		int y = Koordinaten.getyMax();
		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				if (felder[i][j] == null) {
					System.err.print("0");
				} else if (felder[i][j].istBegehbar() == true) {
					if (felder[i][j].getTyp().equals(Feld.flur)) {
						System.err.print("_");
					} else if (Feld.zettel.equals(felder[i][j].getTyp())) {
						System.err.print("Z");
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
	 * Gibt Wege zu einem bestimmten Punkt zurück. Verwendet dafür:
	 * {@link #arbeitslisteAktualisieren(Feld, List, List)},
	 * {@link #wegelisteAktualisieren(Feld, Feld, Map)}
	 * 
	 * @param startX - Die Startposition auf der X-Achse.
	 * @param startY - Die Startposition auf der Y-Achse
	 * @return Gibt eine LinkedHashMap mit vorhergehenden Schritten zurück.
	 */
	/*
	 * Für den Rest des Teams: LinkedHashMap für wege wurde gewählt, weil die
	 * Reihenfolge, in der die Felder hinzugefügt wurden, erhalten bleibt und ein
	 * Iterator die Werte in dieser Reihenfolge zurückgeben kann. Das ist in soweit
	 * hilfreich, da der Dijkstra-Algorithmus die Felder nach Weglänge sortiert
	 * findet. Somit ist auch unsere Liste nach Weglänge sortiert
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

	/**
	 * Aktualisiert die Arbeitsliste - für die Wegfindung benötigt.
	 * {@link #findeWege(Koordinaten)}
	 * 
	 * @param arbeitnachbar
	 * @param fertigFelder
	 * @param arbeitsliste
	 */
	private void arbeitslisteAktualisieren(Feld arbeitnachbar, List<Feld> fertigFelder, List<Feld> arbeitsliste) {
		if (arbeitnachbar != null) {
			if (!fertigFelder.contains(arbeitnachbar) && !arbeitsliste.contains(arbeitnachbar)) {
				arbeitsliste.add(arbeitnachbar);
			}
		}
	}

	/**
	 * Aktualisiert die Wegeliste - für die Wegfindung benötigt.
	 * {@link #findeWege(Koordinaten)}
	 * 
	 * @param arbeit
	 * @param nachbar
	 * @param wege
	 */
	private void wegelisteAktualisieren(Feld arbeit, Feld nachbar, Map<Feld, VorhergehenderSchritt> wege) {
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

	/**
	 * Gibt die Wegeliste, die mit {@link #findeWege(Koordinaten)} erstellt wurde,
	 * über die Standardausgabe für Fehler aus.
	 * 
	 * @param wege
	 */
	public void ausgabeWegliste(Map<Feld, VorhergehenderSchritt> wege) {

		for (Entry<Feld, VorhergehenderSchritt> feld : wege.entrySet()) {
			if (feld.getValue().getVorgaenger() == null) {
				System.err.println("Ziel " + feld.getKey().getX() + " " + feld.getKey().getY()
						+ "\t Startknoten \t Weg " + feld.getValue().getWeglaenge());

			} else {

				System.err.print("Ziel " + feld.getKey().getX() + " " + feld.getKey().getY() + "\t Vorgaenger \t"
						+ feld.getValue().getVorgaenger().getX() + " " + feld.getValue().getVorgaenger().getY()
						+ "\t Weg " + feld.getValue().getWeglaenge());
				if (feld.getKey().pruefenErkundet()) {
					System.err.println("\tE");
				} else {
					System.err.println(" ");
				}
			}
		}

	}

	/**
	 * Diese main dient dem Testen der Karte und der Wegfindung.
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

	// Testfunktion Koordinatennutzung nicht nötig
	/**
	 * Dient lediglich zur Ausgabe der ErkundetenFelder auf der
	 * StandardFehlerausgabe.
	 */
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

	/**
	 * Kontrolliert ob sich an dem übergebenen Ort ein Formular befindet.
	 * 
	 * @param ort
	 * @return formular, falls an den Koordinaten ein Formular ist, ansonsten null
	 */
	public Ziele getFormulare(Koordinaten ort) {
		Feld feld = this.getFeld(ort);
		Ziele formular = null;

		if (Feld.formular.equals(feld.getTyp())) {
			formular = (Ziele) feld;
		}

		return formular;
	}

	/**
	 * Durchsucht die Liste die in {@link #findeWege(Koordinaten)} erstellt wird
	 * nach dem gewünschten Zielfeld. Wird das Ziel gefunden, wird eine Liste mit
	 * den abzufahrenden Felder erstellt um am Wunschziel anzukommen. Die Liste ist
	 * in der Reihenfolge sortiert in der man die Felder abfahren muss, mit dem
	 * Feld, das man in {@link #findeWege(Koordinaten)} als Startpunkt übergeben
	 * hat, an Index 0.
	 * 
	 * @param wege       Die Liste mit den möglichen Wegen.
	 * @param wunschZiel Das Feld zu dem man möchte.
	 * @return null falls das Wunschziel nicht in der Liste ist, ansonsten eine
	 *         Liste mit abzufahrenden Felder.
	 */
	public ArrayList<Feld> werteListeAus(Map<Feld, VorhergehenderSchritt> wege, Feld wunschZiel) {
		if (wege == null || wunschZiel == null || wege.isEmpty()) {
			return null;
		}
		ArrayList<Feld> rueckgabe = new ArrayList<Feld>();
		rueckgabe.add(wunschZiel);

		Feld arbeitsVariable = wege.get(wunschZiel).getVorgaenger();

		// der erste Wert (Startknoten) hat als Feld null, daher prüfen wir gegen null.
		while (arbeitsVariable != null) {
			rueckgabe.add(0, arbeitsVariable);
			arbeitsVariable = wege.get(arbeitsVariable).getVorgaenger();
		}

		return rueckgabe;

	}

	/**
	 * Diese Methode tauscht alle Flurfelder gegen null aus. Außerdem werden die
	 * Wege passend geändert. Bekannte Zettel werden auch auf unerkundet gesetzt
	 */
	public void flurFelderNullen() {
		// Karte durchschauen
		Feld feld;
		Koordinaten ort;
		for (int x = 0; x < Koordinaten.getxMax(); x++) {
			for (int y = 0; y < Koordinaten.getyMax(); y++) {
				ort = new Koordinaten(x, y);
				feld = this.getFeld(ort);
				if (feld != null) {
					if (Feld.zettel.equals(feld.getTyp())) {
						//Sicherstellen das auch unter Zetteln nochmal gesucht wird 
						felder[ort.getX()][ort.getY()].setErkundet(false);
					}
					if (feld.getTyp().equals(Feld.flur)) {
						felder[ort.getX()][ort.getY()] = null;

					} else if (!feld.getTyp().equals(Feld.wand)) {
						wegeSetzen(feld.getPunkt());
					}
				}

			}
		}
	}

	/**
	 * Füllt alle nicht gesetzten Felder mit Flurfeldern;
	 */
	public void formularsucheEnde() {
		Feld arbeitsfeld;
		Koordinaten ort;
		ZellStatus zelle = new ZellStatus();
		zelle.rueckgabeAuswerten(Feld.flur);

		for (int x = 0; x < Koordinaten.getxMax(); x++) {
			for (int y = 0; y < Koordinaten.getyMax(); y++) {
				ort = new Koordinaten(x, y);
				arbeitsfeld = this.getFeld(ort);
				if (arbeitsfeld == null) {
					aktualisiereFeld(ort, zelle);
				} else if(Feld.zettel.equals(arbeitsfeld.getTyp())) {
					felder[ort.getX()][ort.getY()].setErkundet(true);
				}
			}
		}

	}
}
