package de.vitbund.vitmaze.players.ifschleife.bots;

import de.vitbund.vitmaze.players.ifschleife.karte.Karte;

public class ErkundenderBotLvl2 extends Bot {
	int gefundeneFormulare = 0; // speichert das höchste abgearbeitete Formular
	int anzahlFormulare = -1; // speichert ob bekannt ist wieviele Formulare benötigt werden und wie viele

	public ErkundenderBotLvl2(Karte karte, int playerId, int x, int y) {
		super(karte, playerId, x, y);
		// TODO Automatisch generierter Konstruktorstub
	}

	@Override
	public void machAktion() {
		this.rundeInitialisiern();
		//this.ak
		
		// TODO Automatisch generierter Methodenstub

	}

}
