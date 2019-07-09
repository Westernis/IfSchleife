package de.vitbund.vitmaze.players.ifschleife;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

public class BotLevel1 extends Bot {

	private String richtungsausgabe;

	public BotLevel1(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		// TODO Automatisch generierter Konstruktorstub
	}

	public void machAktion(String lastActionsResult, String northCellStatus, String southCellStatus,
			String westCellStatus, String eastCellStatus, String currentCellStatus) {

		if ("OK".equals(lastActionsResult)) {

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

		if (("FINISH " + super.id + " 0").equals(currentCellStatus)) {
			System.out.println("finish");
		} else {
			System.out.println(richtungsausgabe);
		}

	}

}
