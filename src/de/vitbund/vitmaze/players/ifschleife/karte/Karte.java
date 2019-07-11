package de.vitbund.vitmaze.players.ifschleife.karte;

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
			switch (feldbeschreibung){
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
			//TODO	case "FINISH"
			//TODO  case "FORM"
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
				if (felder[i][j] == null) {
					System.err.println("0");
				}
				else if (felder[i][j].istBegehbar() == true) {
					System.err.println("|");
				}
				else {
					System.err.println("W");
					
				}
				
			}
		}
	}
	

}







