package de.vitbund.vitmaze.players.ifschleife.karte;

public class BekannteKarten {

	//In Klasse felder auf public gestellt
	
	//Folgend werden die bekannten Karten erstellt, zunächst wird alles zur Wand, danach werden entsprechende Felder geändert
	
	public static Karte geradeaus() {
		Karte geradeaus = new Karte(9, 10);
		Feld[][] felder = geradeaus.getFelder();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				felder[i][j] = new Wand(new Koordinaten(i, j), geradeaus);
			}
		}
			
		felder[1][1] = new Flur(new Koordinaten(1, 1), geradeaus);
		felder[2][1] = new Ziele(new Koordinaten(2 ,1), geradeaus, 1, 1, "FINISH"); //Ziel ID 1
		felder[3][1] = new Ziele(new Koordinaten(3 ,1), geradeaus, 2, 1, "FORM"); //Formular ID 2
		felder[4][1] = new Flur(new Koordinaten(4, 1), geradeaus); 
		felder[5][1] = new Ziele(new Koordinaten(5 ,1), geradeaus, 1, 1, "FORM"); //Formular ID 1
		felder[6][1] = new Ziele(new Koordinaten(6 ,1), geradeaus, 2, 1, "FINISH"); //Ziel ID 2
		felder[7][1] = new Flur(new Koordinaten(7, 1), geradeaus);
		felder[4][3] = new Flur(new Koordinaten(4, 3), geradeaus);
		felder[4][4] = new Ziele(new Koordinaten(4, 4), geradeaus, 4, 1, "FINISH"); //Ziel ID 4
		felder[4][5] = new Ziele(new Koordinaten(4, 5), geradeaus, 3, 1, "FORM"); //Formular ID 3
		felder[4][6] = new Ziele(new Koordinaten(4, 6), geradeaus, 4, 1, "FORM"); //Formular ID 4
		felder[4][7] = new Ziele(new Koordinaten(4,7), geradeaus, 3, 1, "FINISH"); //Ziel ID 3
		felder[4][8] = new Flur(new Koordinaten(4, 8), geradeaus);
		
		
		return geradeaus;
	}

	
	
	
	public static Karte abbiegung() {
		Karte abbiegung = new Karte(9, 10);
		Feld[][] felder = abbiegung.getFelder();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				felder[i][j] = new Wand(new Koordinaten(i, j), abbiegung);
			}
		}
			
		felder[1][1] = new Flur(new Koordinaten(1, 1), abbiegung);
		felder[2][1] = new Ziele(new Koordinaten(2, 1), abbiegung, 1, 1, "FINISH"); //Ziel ID 1
		felder[3][1] = new Flur(new Koordinaten(3, 1), abbiegung);
		felder[4][1] = new Ziele(new Koordinaten(4, 1), abbiegung, 2, 1, "FORM"); //Formular ID 2
		felder[5][1] = new Flur(new Koordinaten(5, 1), abbiegung);
		felder[6][1] = new Flur(new Koordinaten(6, 1), abbiegung);
		felder[7][1] = new Flur(new Koordinaten(7, 1), abbiegung);
		felder[7][2] = new Flur(new Koordinaten(7, 1), abbiegung);
		felder[7][3] = new Ziele(new Koordinaten(7, 1), abbiegung, 1, 1, "FORM"); //Formular ID 1
		felder[7][4] = new Flur(new Koordinaten(7, 1), abbiegung);
		felder[7][5] = new Ziele(new Koordinaten(7, 1), abbiegung, 2, 1, "FINISH"); //Ziel ID 2
		felder[7][6] = new Flur(new Koordinaten(7, 1), abbiegung);
		
		felder[1][3] = new Flur(new Koordinaten(1, 3), abbiegung);
		felder[1][4] = new Ziele(new Koordinaten(1, 4), abbiegung, 4, 1, "FINISH"); //Ziel ID 4
		felder[1][5] = new Flur(new Koordinaten(1, 5), abbiegung);
		felder[1][6] = new Ziele(new Koordinaten(1, 6), abbiegung, 3, 1, "FORM"); //Formular ID 3
		felder[1][7] = new Flur(new Koordinaten(1, 7), abbiegung);
		felder[1][8] = new Flur(new Koordinaten(1, 8), abbiegung);
		felder[2][8] = new Flur(new Koordinaten(2, 8), abbiegung);
		felder[3][8] = new Flur(new Koordinaten(3, 8), abbiegung);
		felder[4][8] = new Ziele(new Koordinaten(4, 8), abbiegung, 4, 1, "FORM"); //Formular ID 4
		felder[5][8] = new Flur(new Koordinaten(5, 8), abbiegung);
		felder[6][8] = new Ziele(new Koordinaten(6, 8), abbiegung, 3, 1, "FINISH"); //Ziel ID 3
		felder[7][8] = new Flur(new Koordinaten(7, 8), abbiegung);
		
		return abbiegung;
	}
	
	
	
	public static Karte zickzack() {
		Karte zickzack = new Karte(11, 11);
		Feld[][] felder = zickzack.getFelder();
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				felder[i][j] = new Wand(new Koordinaten(i, j), zickzack);
			}
		}
		
		felder[1][2] = new Ziele(new Koordinaten(1, 2), zickzack, 4, 1, "FORM"); //Formular1 ID4
		felder[1][8] = new Ziele(new Koordinaten(1, 8), zickzack, 3, 1, "FORM"); //Formular1 ID3 - 1 8
		felder[2][1] = new Ziele(new Koordinaten(2, 1), zickzack, 3, 2, "FINISH"); //Ziel ID3 - 2 1
		felder[2][2] = new Flur(new Koordinaten(2, 2), zickzack);
		felder[2][3] = new Flur(new Koordinaten(2, 3), zickzack);
		felder[2][4] = new Flur(new Koordinaten(2, 4), zickzack);
		felder[2][6] = new Flur(new Koordinaten(2, 6), zickzack);
		felder[2][7] = new Flur(new Koordinaten(2, 7), zickzack);
		felder[2][8] = new Flur(new Koordinaten(2, 8), zickzack);
		felder[2][9] = new Ziele(new Koordinaten(2, 9), zickzack, 4, 2, "FINISH");; //Ziel ID4 - 2 9
		felder[3][2] = new Flur(new Koordinaten(3, 2), zickzack);
		felder[3][4] = new Flur(new Koordinaten(3, 4), zickzack);
		felder[3][6] = new Flur(new Koordinaten(3, 6), zickzack);
		felder[3][8] = new Flur(new Koordinaten(3, 8), zickzack);
		felder[4][2] = new Ziele(new Koordinaten(4, 2), zickzack, 3, 2, "FORM"); //Formular2 ID3 - 4 2
		felder[4][4] = new Flur(new Koordinaten(4, 4), zickzack);
		felder[4][5] = new Flur(new Koordinaten(4, 5), zickzack);
		felder[4][6] = new Flur(new Koordinaten(4, 6), zickzack);
		felder[4][8] = new Ziele(new Koordinaten(4, 8), zickzack, 4, 2, "FORM"); //Formular2 ID4 - 4 8
		
		felder[6][2] = new Ziele(new Koordinaten(6, 2), zickzack, 1, 2, "FORM"); //Formular2 ID1 - 6 2
		felder[6][4] = new Flur(new Koordinaten(6, 4), zickzack);
		felder[6][5] = new Flur(new Koordinaten(6, 5), zickzack);
		felder[6][6] = new Flur(new Koordinaten(6, 6), zickzack);
		felder[6][8] = new Ziele(new Koordinaten(6, 8), zickzack, 2, 2, "FORM"); //Formular2 ID2 - 6 8
		felder[7][2] = new Flur(new Koordinaten(7, 2), zickzack);
		felder[7][4] = new Flur(new Koordinaten(7, 4), zickzack);
		felder[7][6] = new Flur(new Koordinaten(7, 6), zickzack);
		felder[7][8] = new Flur(new Koordinaten(7, 8), zickzack);
		felder[8][1] = new Ziele(new Koordinaten(8, 1), zickzack, 2, 2, "FINISH"); //Ziel ID2 - 8 1
		felder[8][2] = new Flur(new Koordinaten(8, 2), zickzack);
		felder[8][3] = new Flur(new Koordinaten(8, 3), zickzack);
		felder[8][4] = new Flur(new Koordinaten(8, 4), zickzack);
		felder[8][6] = new Flur(new Koordinaten(8, 6), zickzack);
		felder[8][7] = new Flur(new Koordinaten(8, 7), zickzack);
		felder[8][8] = new Flur(new Koordinaten(8, 8), zickzack);
		felder[8][9] = new Ziele(new Koordinaten(8, 9), zickzack, 1, 2, "FINISH"); //Ziel ID1 - 8 9
		felder[9][2] = new Ziele(new Koordinaten(9, 2), zickzack, 2, 2, "FORM"); //Formular1 ID2 - 9 2
		felder[9][8] = new Ziele(new Koordinaten(9, 8), zickzack, 1, 1, "FORM"); //Formular1 ID1 - 9 8
		
		
		return zickzack;
	}
			
			
	public static Karte kreis() {
		Karte kreis = new Karte(10, 10);
		Feld[][] felder = kreis.getFelder();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				felder[i][j] = new Wand(new Koordinaten(i, j), kreis);
			}
		}
			
		felder[1][1] = new Flur(new Koordinaten(1, 1), kreis);
		felder[1][2] = new Ziele(new Koordinaten(1, 2), kreis, 3, 2, "FORM"); //Formular2 ID3
		felder[1][3] = new Flur(new Koordinaten(1, 3), kreis);
		felder[1][4] = new Flur(new Koordinaten(1, 4), kreis);
		felder[1][5] = new Flur(new Koordinaten(1, 5), kreis);
		felder[1][6] = new Ziele(new Koordinaten(1, 6), kreis, 4, 1, "FORM"); //Formular1 ID4
		felder[1][7] = new Ziele(new Koordinaten(1, 7), kreis, 1, 2, "FINISH"); //Ziel ID1
		felder[1][8] = new Flur(new Koordinaten(1, 8), kreis);
		felder[2][1] = new Ziele(new Koordinaten(2, 1), kreis, 2, 2, "FINISH"); //Ziel ID2
		felder[2][4] = new Flur(new Koordinaten(2, 4), kreis);
		felder[2][8] = new Ziele(new Koordinaten(2, 8), kreis, 2, 2, "FORM"); //Formular2 ID2
		felder[3][1] = new Ziele(new Koordinaten(3, 1), kreis, 1, 1, "FORM"); //Formular1 ID1
		felder[3][3] = new Flur(new Koordinaten(3, 3), kreis);
		felder[3][4] = new Flur(new Koordinaten(3, 4), kreis);
		felder[3][5] = new Flur(new Koordinaten(3, 5), kreis);
		felder[3][6] = new Flur(new Koordinaten(3, 6), kreis);
		felder[3][8] = new Flur(new Koordinaten(3, 8), kreis);
		felder[4][1] = new Flur(new Koordinaten(4, 1), kreis);
		felder[4][3] = new Flur(new Koordinaten(4, 3), kreis);
		felder[4][6] = new Flur(new Koordinaten(4, 6), kreis);
		felder[4][7] = new Flur(new Koordinaten(4, 7), kreis);
		felder[4][8] = new Flur(new Koordinaten(4, 8), kreis);
		
		felder[5][1] = new Flur(new Koordinaten(5, 1), kreis);
		felder[5][2] = new Flur(new Koordinaten(5, 2), kreis);
		felder[5][3] = new Flur(new Koordinaten(5, 3), kreis);
		felder[5][6] = new Flur(new Koordinaten(5, 6), kreis);
		felder[5][8] = new Flur(new Koordinaten(5, 8), kreis);
		felder[6][1] = new Flur(new Koordinaten(6, 1), kreis);
		felder[6][3] = new Flur(new Koordinaten(6, 3), kreis);
		felder[6][4] = new Flur(new Koordinaten(6, 4), kreis);
		felder[6][5] = new Flur(new Koordinaten(6, 5), kreis);
		felder[6][6] = new Flur(new Koordinaten(6, 6), kreis);
		felder[6][8] = new Ziele(new Koordinaten(6, 8), kreis, 3, 1, "FORM"); //Formular1 ID3
		felder[7][1] = new Ziele(new Koordinaten(7, 1), kreis, 4, 2, "FORM"); //Formular2 ID4
		felder[7][5] = new Flur(new Koordinaten(7, 5), kreis);
		felder[7][8] = new Ziele(new Koordinaten(7, 8), kreis, 4, 2, "FINISH"); //Ziel ID4
		felder[8][1] = new Flur(new Koordinaten(8, 1), kreis);
		felder[8][2] = new Ziele(new Koordinaten(8, 2), kreis, 3, 2, "FINISH"); //Ziel ID3
		felder[8][3] = new Ziele(new Koordinaten(8, 3), kreis, 2, 1, "FORM"); //Formular1 ID2
		felder[8][4] = new Flur(new Koordinaten(8, 4), kreis);
		felder[8][5] = new Flur(new Koordinaten(8, 5), kreis);
		felder[8][6] = new Flur(new Koordinaten(8, 6), kreis);
		felder[8][7] = new Ziele(new Koordinaten(8, 7), kreis, 1, 2, "FORM"); //Formular2 ID1
		felder[8][8] = new Flur(new Koordinaten(8, 8), kreis);
		
		return kreis;
	}

	
	
	public static Karte kreis2() {
		Karte kreis2 = new Karte(10, 10);
		Feld[][] felder = kreis2.getFelder();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				felder[i][j] = new Wand(new Koordinaten(i, j), kreis2);
			}
		}
			
		felder[1][1] = new Flur(new Koordinaten(1, 1), kreis2);
		felder[1][2] = new Ziele(new Koordinaten(1, 2), kreis2, 3, 2, "FORM"); //Formular2 ID3 
		felder[1][3] = new Flur(new Koordinaten(1, 3), kreis2);
		felder[1][4] = new Flur(new Koordinaten(1, 4), kreis2);
		felder[1][5] = new Flur(new Koordinaten(1, 5), kreis2);
		felder[1][6] = new Flur(new Koordinaten(1, 6), kreis2); 
		felder[1][7] = new Flur(new Koordinaten(1, 7), kreis2);
		felder[1][8] = new Flur(new Koordinaten(1, 8), kreis2);
		felder[2][1] = new Flur(new Koordinaten(2, 1), kreis2); 
		felder[2][4] = new Flur(new Koordinaten(2, 4), kreis2);
		felder[2][8] = new Ziele(new Koordinaten(2, 8), kreis2, 2, 2, "FORM"); //Formular2 ID2
		felder[3][1] = new Flur(new Koordinaten(3, 1), kreis2);
		felder[3][3] = new Ziele(new Koordinaten(3, 3), kreis2, 4, 2, "FINISH"); //Ziel ID4
		felder[3][4] = new Flur(new Koordinaten(3, 4), kreis2);
		felder[3][5] = new Ziele(new Koordinaten(3, 5), kreis2, 4, 1, "FORM"); //Formular1 ID4
		felder[3][6] = new Ziele(new Koordinaten(3, 6), kreis2, 1, 2, "FINISH"); //Ziel ID1
		felder[3][8] = new Flur(new Koordinaten(3, 8), kreis2);
		felder[4][1] = new Flur(new Koordinaten(4, 1), kreis2);
		felder[4][3] = new Ziele(new Koordinaten(4, 3), kreis2, 1, 1, "FORM"); //Formular1 ID1
		felder[4][6] = new Flur(new Koordinaten(4, 6), kreis2);
		felder[4][7] = new Flur(new Koordinaten(4, 7), kreis2);
		felder[4][8] = new Flur(new Koordinaten(4, 8), kreis2);
		
		felder[5][1] = new Flur(new Koordinaten(5, 1), kreis2);
		felder[5][2] = new Flur(new Koordinaten(5, 2), kreis2);
		felder[5][3] = new Flur(new Koordinaten(5, 3), kreis2);
		felder[5][6] = new Ziele(new Koordinaten(5, 6), kreis2, 3, 1, "FORM"); //Formular1 ID3
		felder[5][8] = new Flur(new Koordinaten(5, 8), kreis2);
		felder[6][1] = new Flur(new Koordinaten(6, 1), kreis2);
		felder[6][3] = new Ziele(new Koordinaten(6, 3), kreis2, 3, 2, "FINISH"); //Ziel ID3
		felder[6][4] = new Ziele(new Koordinaten(6, 4), kreis2, 2, 1, "FORM"); //Formular1 ID2
		felder[6][5] = new Flur(new Koordinaten(6, 5), kreis2);
		felder[6][6] = new Ziele(new Koordinaten(6, 6), kreis2, 2, 2, "FINISH"); //Ziel ID2
		felder[6][8] = new Flur(new Koordinaten(6, 8), kreis2); 
		felder[7][1] = new Ziele(new Koordinaten(7, 1), kreis2, 4, 2, "FORM"); //Formular2 ID4
		felder[7][5] = new Flur(new Koordinaten(7, 5), kreis2);
		felder[7][8] = new Flur(new Koordinaten(7, 8), kreis2);
		felder[8][1] = new Flur(new Koordinaten(8, 1), kreis2);
		felder[8][2] = new Flur(new Koordinaten(8, 2), kreis2);
		felder[8][3] = new Flur(new Koordinaten(8, 3), kreis2); 
		felder[8][4] = new Flur(new Koordinaten(8, 4), kreis2);
		felder[8][5] = new Flur(new Koordinaten(8, 5), kreis2);
		felder[8][6] = new Flur(new Koordinaten(8, 6), kreis2);
		felder[8][7] = new Ziele(new Koordinaten(8, 7), kreis2, 1, 2, "FORM"); //Formular2 ID1
		felder[8][8] = new Flur(new Koordinaten(8, 8), kreis2);
		
		return kreis2;
	}
	
	
	
	public static Karte krankheit() {
		Karte krankheit = new Karte(13, 13);
		Feld[][] felder = krankheit.getFelder();
		
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				felder[i][j] = new Wand(new Koordinaten(i, j), krankheit);
			}
		}
			
		felder[1][1] = new Flur(new Koordinaten(1, 1), krankheit);
		felder[1][2] = new Flur(new Koordinaten(1, 2), krankheit);
		felder[1][3] = new Flur(new Koordinaten(1, 3), krankheit);
		felder[1][4] = new Flur(new Koordinaten(1, 4), krankheit);
		felder[1][5] = new Flur(new Koordinaten(1, 5), krankheit);
		felder[1][6] = new Flur(new Koordinaten(1, 6), krankheit);
		felder[1][7] = new Flur(new Koordinaten(1, 7), krankheit);
		felder[1][8] = new Flur(new Koordinaten(1, 8), krankheit);
		felder[1][9] = new Flur(new Koordinaten(1, 9), krankheit);
		felder[1][10] = new Flur(new Koordinaten(1,10), krankheit);
		felder[1][11] = new Flur(new Koordinaten(1, 11), krankheit);
		felder[2][1] = new Flur(new Koordinaten(12, 1), krankheit);
		felder[2][5] = new Flur(new Koordinaten(2, 5), krankheit);
		felder[2][11] = new Flur(new Koordinaten(2, 11), krankheit);
		felder[3][1] = new Ziele(new Koordinaten(3, 1), krankheit, 3, 1, "FORM"); //Formular ID3
		felder[3][5] = new Ziele(new Koordinaten(3, 5), krankheit, 3, 1, "FINISH"); //Ziel ID3
		felder[3][7] = new Ziele(new Koordinaten(3, 7), krankheit, 1, 1, "FORM"); //Formular ID1
		felder[3][11] = new Ziele(new Koordinaten(3, 11), krankheit, 1, 1, "FINISH"); //Ziel ID1
		felder[4][1] = new Flur(new Koordinaten(4, 1), krankheit);
		felder[4][5] = new Flur(new Koordinaten(4, 5), krankheit);
		felder[4][7] = new Ziele(new Koordinaten(4, 7), krankheit, 2, 1, "FINISH"); //Ziel ID2
		felder[4][11] = new Flur(new Koordinaten(4, 11), krankheit);
		felder[5][1] = new Flur(new Koordinaten(5, 1), krankheit);
		felder[5][2] = new Flur(new Koordinaten(5, 2), krankheit);
		felder[5][3] = new Flur(new Koordinaten(5, 3), krankheit);
		felder[5][4] = new Flur(new Koordinaten(5, 4), krankheit);
		felder[5][5] = new Flur(new Koordinaten(5, 5), krankheit);
		felder[5][7] = new Flur(new Koordinaten(5, 7), krankheit);
		felder[5][8] = new Flur(new Koordinaten(5, 8), krankheit);
		felder[5][9] = new Flur(new Koordinaten(5, 9), krankheit);
		felder[5][10] = new Flur(new Koordinaten(5, 10), krankheit);
		felder[5][11] = new Flur(new Koordinaten(5, 11), krankheit);
		
		felder[6][5] = new Flur(new Koordinaten(6, 5), krankheit);
		felder[6][7] = new Flur(new Koordinaten(6, 7), krankheit);
		
		felder[7][1] = new Flur(new Koordinaten(7, 1), krankheit);
		felder[7][2] = new Flur(new Koordinaten(7, 2), krankheit);
		felder[7][3] = new Flur(new Koordinaten(7, 3), krankheit);
		felder[7][4] = new Flur(new Koordinaten(7, 4), krankheit);
		felder[7][5] = new Flur(new Koordinaten(7, 5), krankheit);
		felder[7][7] = new Flur(new Koordinaten(7, 7), krankheit);
		felder[7][8] = new Flur(new Koordinaten(7, 8), krankheit);
		felder[7][9] = new Flur(new Koordinaten(7, 9), krankheit);
		felder[7][10] = new Flur(new Koordinaten(7, 10), krankheit);
		felder[7][11] = new Flur(new Koordinaten(7, 11), krankheit);
		felder[8][1] = new Flur(new Koordinaten(8, 1), krankheit);
		felder[8][5] = new Flur(new Koordinaten(8, 5), krankheit);
		felder[8][7] = new Flur(new Koordinaten(8, 7), krankheit);
		felder[8][11] = new Flur(new Koordinaten(8, 11), krankheit);
		felder[9][1] = new Ziele(new Koordinaten(9, 1), krankheit, 4, 1, "FORM"); //Formular ID4
		felder[9][5] = new Ziele(new Koordinaten(9, 5), krankheit, 4, 1, "FINISH"); //Ziel ID4
		felder[9][7] = new Ziele(new Koordinaten(9, 7), krankheit, 2, 1, "FORM"); //Formular ID2
		felder[9][11] = new Flur(new Koordinaten(9, 11), krankheit);
		felder[10][1] = new Flur(new Koordinaten(10, 1), krankheit);
		felder[10][7] = new Flur(new Koordinaten(10, 7), krankheit);
		felder[10][11] = new Flur(new Koordinaten(10, 11), krankheit);
		felder[11][1] = new Flur(new Koordinaten(11, 1), krankheit);
		felder[11][2] = new Flur(new Koordinaten(11, 2), krankheit);
		felder[11][3] = new Flur(new Koordinaten(11, 3), krankheit);
		felder[11][4] = new Flur(new Koordinaten(11, 4), krankheit);
		felder[11][5] = new Flur(new Koordinaten(11, 5), krankheit);
		felder[11][6] = new Flur(new Koordinaten(11, 6), krankheit);
		felder[11][7] = new Flur(new Koordinaten(11, 7), krankheit);
		felder[11][8] = new Flur(new Koordinaten(11, 8), krankheit);
		felder[11][9] = new Flur(new Koordinaten(11, 9), krankheit);
		felder[11][10] = new Flur(new Koordinaten(11, 10), krankheit);
		felder[11][11] = new Flur(new Koordinaten(11, 11), krankheit);
		
		return krankheit;
	}
	
	
	
	public static Karte friendly() {
		Karte friendly = new Karte(20, 20);
		Feld[][] felder = friendly.getFelder();
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				felder[i][j] = new Wand(new Koordinaten(i, j), friendly);
			}
		}
			
		felder[1][1] = new Flur(new Koordinaten(1, 1), friendly); 
		felder[2][1] = new Flur(new Koordinaten(1, 1), friendly);
		felder[3][1] = new Ziele(new Koordinaten(3, 1), friendly, 3, 3, "FINISH"); //Ziel ID3
		felder[16][1] = new Ziele(new Koordinaten(3, 1), friendly, 4, 3, "FINISH"); //Ziel ID4
		felder[17][1] = new Flur(new Koordinaten(1, 1), friendly);
		felder[18][1] = new Flur(new Koordinaten(1, 1), friendly);
		felder[1][2] = new Flur(new Koordinaten(1, 2), friendly);
		felder[2][2] = new Flur(new Koordinaten(1, 2), friendly);
		felder[3][2] = new Flur(new Koordinaten(1, 2), friendly);
		felder[16][2] = new Flur(new Koordinaten(1, 2), friendly);
		felder[17][2] = new Flur(new Koordinaten(1, 2), friendly);
		felder[18][2] = new Flur(new Koordinaten(1, 2), friendly);
		felder[1][3] = new Flur(new Koordinaten(1, 3), friendly);
		felder[2][3] = new Ziele(new Koordinaten(3, 1), friendly, 4, 3, "FORM"); //Formular3 ID4
		felder[3][3] = new Flur(new Koordinaten(1, 3), friendly);
		felder[16][3] = new Flur(new Koordinaten(1, 3), friendly);
		felder[17][3] = new Ziele(new Koordinaten(3, 1), friendly, 4, 3, "FORM"); //Formular3 ID3
		felder[18][3] = new Flur(new Koordinaten(1, 3), friendly);
		felder[1][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[2][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[3][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[4][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[5][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[6][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[7][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[8][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[11][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[12][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[13][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[14][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[15][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[16][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[17][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[18][4] = new Flur(new Koordinaten(1, 4), friendly);
		felder[7][5] = new Flur(new Koordinaten(1, 5), friendly);
		felder[8][5] = new Flur(new Koordinaten(1, 5), friendly);
		felder[9][5] = new Flur(new Koordinaten(1, 5), friendly);
		felder[10][5] = new Flur(new Koordinaten(1, 5), friendly);
		felder[11][5] = new Flur(new Koordinaten(1, 5), friendly);
		felder[12][5] = new Flur(new Koordinaten(1, 5), friendly);
		felder[5][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[6][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[7][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[8][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[9][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[10][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[11][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[12][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[13][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[14][6] = new Flur(new Koordinaten(1, 6), friendly);
		felder[4][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[5][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[6][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[7][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[9][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[10][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[12][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[13][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[14][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[15][7] = new Flur(new Koordinaten(1, 7), friendly);
		felder[3][8] = new Ziele(new Koordinaten(3, 1), friendly, 1, 2, "FORM"); //Formular2 ID1
		felder[4][8] = new Flur(new Koordinaten(1, 8), friendly);
		felder[5][8] = new Flur(new Koordinaten(1, 8), friendly);
		felder[6][8] = new Flur(new Koordinaten(1, 8), friendly);
		felder[9][8] = new Flur(new Koordinaten(1, 8), friendly);
		felder[10][8] = new Flur(new Koordinaten(1, 8), friendly);
		felder[13][8] = new Flur(new Koordinaten(1, 8), friendly);
		felder[14][8] = new Flur(new Koordinaten(1, 8), friendly);
		felder[15][8] = new Flur(new Koordinaten(1, 8), friendly);
		felder[16][8] = new Ziele(new Koordinaten(3, 1), friendly, 2, 2, "FORM"); //Formular2 ID2
		felder[3][9] = new Flur(new Koordinaten(1, 9), friendly); 
		felder[4][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[5][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[6][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[7][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[8][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[9][9] = new Ziele(new Koordinaten(3, 1), friendly, 3, 1, "FORM"); //Formular1 ID3
		felder[10][9] = new Ziele(new Koordinaten(3, 1), friendly, 4, 1, "FORM"); //Formular1 ID4
		felder[11][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[12][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[13][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[14][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[15][9] = new Flur(new Koordinaten(1, 9), friendly);
		felder[16][9] = new Flur(new Koordinaten(1, 9), friendly);
		
		felder[3][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[4][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[5][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[6][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[7][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[8][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[9][10] = new Ziele(new Koordinaten(3, 1), friendly, 2, 1, "FORM"); //Formular1 ID2
		felder[10][10] = new Ziele(new Koordinaten(3, 1), friendly, 1, 1, "FORM"); //Formular1 ID1
		felder[11][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[12][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[13][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[14][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[15][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[16][10] = new Flur(new Koordinaten(1, 10), friendly);
		felder[3][11] = new Ziele(new Koordinaten(3, 1), friendly, 4, 2, "FORM"); //Formular2 ID4
		felder[4][11] = new Flur(new Koordinaten(1, 11), friendly);
		felder[5][11] = new Flur(new Koordinaten(1, 11), friendly);
		felder[8][11] = new Flur(new Koordinaten(1, 11), friendly);
		felder[9][11] = new Flur(new Koordinaten(1, 11), friendly);
		felder[10][11] = new Flur(new Koordinaten(1, 11), friendly);
		felder[11][11] = new Flur(new Koordinaten(1, 11), friendly);
		felder[14][11] = new Flur(new Koordinaten(1, 11), friendly);
		felder[15][11] = new Flur(new Koordinaten(1, 11), friendly);
		felder[16][11] = new Ziele(new Koordinaten(3, 1), friendly, 3, 2, "FORM"); //Formular2 ID3
		felder[4][12] = new Flur(new Koordinaten(1, 12), friendly);
		felder[5][12] = new Flur(new Koordinaten(1, 12), friendly);
		felder[6][12] = new Flur(new Koordinaten(1, 12), friendly);
		felder[13][12] = new Flur(new Koordinaten(1, 12), friendly);
		felder[14][12] = new Flur(new Koordinaten(1, 12), friendly);
		felder[15][12] = new Flur(new Koordinaten(1, 12), friendly);
		felder[5][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[6][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[7][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[8][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[9][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[10][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[11][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[12][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[13][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[14][13] = new Flur(new Koordinaten(1, 13), friendly);
		felder[7][14] = new Flur(new Koordinaten(1, 14), friendly);
		felder[8][14] = new Flur(new Koordinaten(1, 14), friendly);
		felder[9][14] = new Flur(new Koordinaten(1, 14), friendly);
		felder[10][14] = new Flur(new Koordinaten(1, 14), friendly);
		felder[11][14] = new Flur(new Koordinaten(1, 14), friendly);
		felder[12][14] = new Flur(new Koordinaten(1, 14), friendly);
		felder[1][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[2][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[3][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[4][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[5][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[6][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[7][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[8][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[11][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[12][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[13][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[14][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[15][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[16][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[17][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[18][15] = new Flur(new Koordinaten(1, 15), friendly);
		felder[1][16] = new Flur(new Koordinaten(1, 16), friendly);
		felder[2][16] = new Ziele(new Koordinaten(3, 1), friendly, 1, 3, "FORM"); //Formular3 ID1
		felder[3][16] = new Flur(new Koordinaten(1, 16), friendly);
		felder[16][16] = new Flur(new Koordinaten(1, 16), friendly);
		felder[17][16] = new Ziele(new Koordinaten(3, 1), friendly, 2, 3, "FORM"); //Formular3 ID2
		felder[18][16] = new Flur(new Koordinaten(1, 16), friendly);
		felder[1][17] = new Flur(new Koordinaten(1, 17), friendly); 
		felder[2][17] = new Flur(new Koordinaten(1, 17), friendly);
		felder[3][17] = new Flur(new Koordinaten(1, 17), friendly);
		felder[16][17] = new Flur(new Koordinaten(1, 17), friendly);
		felder[17][17] = new Flur(new Koordinaten(1, 17), friendly);
		felder[18][17] = new Flur(new Koordinaten(1, 17), friendly);
		felder[1][18] = new Flur(new Koordinaten(1, 18), friendly);
		felder[2][18] = new Flur(new Koordinaten(1, 18), friendly);
		felder[3][18] = new Ziele(new Koordinaten(3, 1), friendly, 2, 3, "FINISH"); //Ziel ID2
		felder[16][18] = new Ziele(new Koordinaten(3, 1), friendly, 1, 3, "FINISH"); //Ziel ID1
		felder[17][18] = new Flur(new Koordinaten(1, 18), friendly);
		felder[18][18] = new Flur(new Koordinaten(1, 18), friendly);
		
		return friendly;
	}
	
	
	
}









