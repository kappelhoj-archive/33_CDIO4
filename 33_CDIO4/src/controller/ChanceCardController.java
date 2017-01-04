package controller;
import entity.ChanceCard.*;


public class ChanceCardController {

	private ChanceCard[] chanceCard;


	public void ChanceCards(){
		
		chanceCard = new ChanceCard[40];

		//beskrivelser af Grant kort
		String[] desc = {"De har modtaget deres tandlægeregning. Betal kr. 2.000.",
		"Betal kr. 200 for levering af 2 kasser øl.",
		"Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3.000.",
		"Betal kr. 3.000 for reparation af Deres vogn.",
		"Modtag udbytte af Deres aktier - kr. 1.000.",
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
		"De har vundet i Klasselotteriet. Modtag kr. 500.",
		"Betal for vognvask og smøring kr. 300.",
		"De har fået en parkeringsbøde. Betal kr. 200 i bøde."};
		int[] price = { -2000, -200, 3000, -3000, 1000, -1000,
		200, 1000, 1000, -1000, -200, -200, 1000, -1000,
		1000, -3000, 1000, 1000, 1000, 500, -300, -200 };

		for(int i = 0 ; i < 23 ; i++)
		{
			chanceCard[i] = new Grant("Grant",desc[i],price[i]);	
		}
		

		//shuffleDeck(chanceCard);	
		
		

	}

	static void shuffleDeck(int[] ar)
	{
		for (int i = ar.length - 1; i > 0; i--)
		{
			int index = (int) (Math.random()*ar.length);

			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}






}
