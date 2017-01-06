package entity;

import entity.ChanceCard.*;

public class ChanceCardDeck {
	private int num = 0;

	private ChanceCard[] chanceCards;

	public ChanceCardDeck() {

		chanceCards = new ChanceCard[39];

		// beskrivelser af Grant kort
		String[] description = { "De har modtaget deres tandlægeregning. Betal kr. 2.000.",
				"Betal kr. 200 for levering af 2 kasser øl.",
				"Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3.000.",
				"Betal kr. 3.000 for reparation af Deres vogn.", "Modtag udbytte af Deres aktier - kr. 1.000.",
				"De har købt 4 nye dæk til deres vogn. Betal kr. 1.000.",
				"Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken.",
				"De modtager Deres aktieudbytte. Modtag kr. 1.000 af banken.",
				"Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1.000.",
				"Betal Deres bilforsikring - kr. 1.000.",
				"De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal told kr. 200.",
				"De har fået en parkeringsbøde. Betal kr. 200 i bøde.",
				"De har solgt nogle gamle møbler på auktion. Modtag kr. 1.000 af banken.",
				"De har kørt frem for Fuldt stop. Betal kr. 1.000 i bøde.",
				"Deres præmieobligationer er udtrukket. De modtager kr. 1.000 af banken.",
				"Betal kr. 3.000 for reparation af Deres vogn.",
				"De havde en række med elleve rigtige i tipning. Modtag kr. 1.000.",
				"Modtag udbytte af Deres aktier - kr. 1.000.",
				"Deres præmieobligationer er udtrukket. De modtager kr. 1.000 af banken.",
				"De har vundet i Klasselotteriet. Modtag kr. 500.", "Betal for vognvask og smøring kr. 300.",
				"De har fået en parkeringsbøde. Betal kr. 200 i bøde." };
		int[] price = { -2000, -200, 3000, -3000, 1000, -1000, 200, 1000, 1000, -1000, -200, -200, 1000, -1000, 1000,
				-3000, 1000, 1000, 1000, 500, -300, -200 };

		for (int i = 0; i < 23; i++) {
			chanceCards[i] = new Grant("Grant", description[i], price[i]);
		}

		/**
		 * Movement cards
		 * 
		 * @contains (String card type, String description for card, int move,
		 *           int position1, int position2, int position3, int
		 *           position4,boolean inprisoned, boolean double rent)
		 */
		chanceCards[24] = new Movement("Movement", "Ryk frem til START.", 0, 1, 0, 0, 0, false, false);
		chanceCards[25] = new Movement("Movement", "Ryk frem til START.", 0, 1, 0, 0, 0, false, false);
		chanceCards[26] = new Movement("Movement",
				"Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer de ikke kr. 4.000.", 0,
				11, 0, 0, 0, true, false);
		chanceCards[27] = new Movement("Movement",
				"Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer de ikke kr. 4.000.", 0,
				11, 0, 0, 0, true, false);
		chanceCards[28] = new Movement("Movement",
				"Ryk frem til Grønningen. Hvis De passerer START, indkassér da kr. 4.000.", 0, 25, 0, 0, 0, false,
				false);
		chanceCards[29] = new Movement("Movement", "Tag ind på Rådhuspladsen.", 0, 40, 0, 0, 0, false, false);
		chanceCards[30] = new Movement("Movement",
				"Ryk frem til Strandvejen. Hvis De passerer START, indkassér da kr. 4.000.", 0, 20, 0, 0, 0, false,
				false);
		chanceCards[31] = new Movement("Movement",
				"Ryk frem til Vimmelskaftet. Hvis De passerer START, indkassér da kr. 4.000.", 0, 33, 0, 0, 0, false,
				false);
		chanceCards[32] = new Movement("Movement",
				"Ryk frem til Frederiksberg Allé. Hvis De passerer START, indkassér da kr. 4.000.", 0, 12, 0, 0, 0,
				true, false);
		chanceCards[33] = new Movement("Movement",
				"Tag med Mols-Linien. Flyt brikken frem, og hvis De passerer START, indkassér da kr. 4.000.", 0, 16, 0,
				0, 0, false, false);
		chanceCards[34] = new Movement("Movement",
				"Ryk brikken frem til det  nærmeste rederi og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken.",
				0, 6, 16, 26, 36, false, true);
		chanceCards[35] = new Movement("Movement",
				"Ryk brikken frem til det  nærmeste rederi og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken.",
				0, 6, 16, 26, 36, false, true);
		chanceCards[36] = new Movement("Movement",
				"Tag med den nærmeste færge. Flyt brikken frem, og hvis De passerer START, indkassér da kr. 4.000.", 0,
				6, 16, 26, 36, false, false);
		chanceCards[37] = new Movement("Movement", "Ryk tre felter tilbage.", -3, 0, 0, 0, 0, false, false);
		chanceCards[38] = new Movement("Movement", "Ryk tre felter tilbage.", -3, 0, 0, 0, 0, false, false);
		chanceCards[39] = new Movement("Movement", "Ryk tre felter frem.", 3, 0, 0, 0, 0, false, false);

	}

	public void shuffleDeck() {
		for (int i = chanceCards.length - 1; i > 0; i--) {
			int index = (int) (Math.random() * chanceCards.length);

			ChanceCard a = chanceCards[index];
			chanceCards[index] = chanceCards[i];
			chanceCards[i] = a;
		}
	}

	public ChanceCard drawCard() {
				
		if (num == chanceCards.length+1) {
			shuffleDeck();
			num = 0;
		}
		return chanceCards[num++];

	}

}
