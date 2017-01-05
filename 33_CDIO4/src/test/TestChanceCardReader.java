package test;

		import java.io.File;
		import java.io.FileNotFoundException;
		import java.util.Scanner;

		import entity.ChanceCard.*;
public class TestChanceCardReader {

	public static void main(String[] args) {



		class ChanceCardDeck {
			private int num = 0;

			private ChanceCard[] chanceCards;

			public ChanceCardDeck() {
					

				chanceCards = new ChanceCard[39];
				int card = 0;
				
				
				String fileName = "data.csv";
				File file = new File(fileName);

				try 
				{
					Scanner inputStream = new Scanner(file);
					while(inputStream.hasNext())
					{
						String data = inputStream.next();
						String[] values = data.split(",");
						
						if(values[0]=="Grant"){
						chanceCards[card] = new Grant(values[0],values[1]);
						card++;
						}
						if(values[0]=="Movement"){
						chanceCards[card] = new Movement(values[0],values[1],Integer.parseInt(values[2]),Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]),Integer.parseInt(values[6]),Boolean.parseBoolean(values[7]),Boolean.parseBoolean(values[8]));
						card++;
						}
						if(values[0]=="Party"){
						chanceCards[card] = new Party(values[0],values[1],Integer.parseInt(values[2]));
						card++;
						}
						if(values[0]=="Payment"){
						chanceCards[card] = new Payment(values[0],values[1],Integer.parseInt(values[2]));
						card++;
						}
						if(values[0]=="Prison"){
						chanceCards[card] = new Prison(values[0],values[1],Boolean.parseBoolean(values[7]),Boolean.parseBoolean(values[8]));
						card++;
						}
						if(values[0]=="Tax"){
						chanceCards[card] = new TaxCard(values[0],values[1],Integer.parseInt(values[2]));
						card++;
						}
						System.out.println(values[card]);

					}
					inputStream.close();

				} catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}

				// beskrivelser af Payment kort
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

				/**
				 * Movement cards
				 * 
				 * @contains (String card type, String description for card, int move,
				 *           int position1, int position2, int position3, int
				 *           position4,boolean inprisoned, boolean double rent)
				 */

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


	}

}
