package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

/**
 * 
 * @author IFSchleife
 *
 *         BotLevel1 erweitert die Klasse Bot; Ziel hierbei ist es eine konkrete
 *         Aufgabenlösung für das Level 1 in diesem Bot darzustellen.
 */
public class BotLevel1 extends Bot {

	private String richtungsausgabe;

	public BotLevel1(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
	}

	/**
	 * Überschriebene Methode der Elternklasse.
	 * 
	 * @param lastActionsResult
	 * @param northCellStatus
	 * @param southCellStatus
	 * @param westCellStatus
	 * @param eastCellStatus
	 * @param currentCellStatus
	 * 
	 */
	public void machAktion(String lastActionsResult, String northCellStatus, String southCellStatus,
			String westCellStatus, String eastCellStatus, String currentCellStatus) {

		/**
		 * solange das lastActionResult "OK" beeinhaltet gehe in die Schleife....ähhh
		 * Verzweigung.
		 */
		if ("OK".equals(lastActionsResult)) {

			/**
			 * Wenn der Status des nördlichen Felds nicht "WALL" beeinhaltet gebe "go north"
			 * aus. TODO: FIXME Ist das schon eine IF-Else-Ladder oder ist es noch unsauber
			 * programmiert iS von "prüfe erst das dann das dann das.... wobei nur der
			 * letzte Status genommen bzw. überschrieben wird?
			 */
			if (!"WALL".equals(northCellStatus)) {
				richtungsausgabe = "go north";
			}
			if (!"WALL".equals(southCellStatus)) {
				richtungsausgabe = "go south";
			}
			if (!"WALL".equals(westCellStatus)) {
				richtungsausgabe = "go west";
			}
			if (!"WALL".equals(eastCellStatus)) {
				richtungsausgabe = "go east";
			}
		}

		/**
		 * Wenn der aktuelle Zellstatus "FINISH" + PlayerID + 0 (also z.B. "FINISH 1 0")
		 * lautet gebe "finish" aus. Wenn nicht gebe den Inhalt der String-Variablen
		 * "richtungsausgabe" aus, die Prüfung siehe oben.
		 */
		if (("FINISH " + super.id + " 0").equals(currentCellStatus)) {
			System.out.println("finish");
		} else {
			System.out.println(richtungsausgabe);
		}

	}

}
