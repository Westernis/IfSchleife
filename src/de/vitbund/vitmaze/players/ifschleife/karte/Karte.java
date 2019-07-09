package de.vitbund.vitmaze.players.ifschleife.karte;

/**
 * TODO 
 * @author helmut.rietz
 *
 */
public class Karte {

	// 1. Index ist die x-Koordinate(Breite), der 2. Index ist die
	// y-Koordinate(H�he)
	private final Feld[][] felder;

	public Karte(int x, int y) {
		this.felder = new Feld[x][y];
	}

	public void getFeldtyp(int x, int y) {
		// FIXME nicht sicher ob wir die brauchen ^^
		// TODO soll Eigenschaften zum Feld zur�ck geben, eventuell das Feld selbst?
	}

	/**
	 * Diese Methode soll den k�rzesten Weg zwischen 2 Felder bestimmen.
	 * Ber�cksichtigt werden dabei nur Felder, die zum Zeitpunkt des Aufrufs bekannt
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
	 * Gibt eine Reihe von Anweisungen als String[] zur�ck die den mittels
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
	 * Gibt das 2-dimensinale Array �ber die Felder der Karte zur�ck.
	 * 
	 * @return Feld[][]
	 */
	public Feld[][] getFelder() {
		return felder;
	}
	// Festestellen, ob bei den Koordinaten schon ein Feldobjekt existiert. Wenn nein->Anlegen. Welche Wege m�ssen hinzugef�gt werden?
	public void pruefeFeld(int x, int y, String feldbeschreibung) {
		if(felder[x][y] == null );
		felder[x][y] = new Flur(x, y, this); //TODO
		
	}
	
	public boolean isFeldBekannt(int x, int y) {
		if (felder[x][y] == null) {
			return false;
		}
		return true;
	}
	
	
	//Vielleicht brauchen wir das noch
	public Feld getFeld(int x, int y) {
		//TODO Arraygrenzen abfangen
		return felder[x][y];
	}
	
}
