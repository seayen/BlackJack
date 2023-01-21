package blackjack;


public class Card {
	static String[] shapeArray = {"♠","◆","♥","♣"};
	int Shape;
	int Num;
	
	//1 : [♠] 2 : [◆] 3 : [♥] 4: [♣] 
	public Card(int Shape, int Num)
	{
		this.Shape = Shape;
		this.Num = Num;
	}
	
	public static String getShape(Card card)
	{
		return shapeArray[card.Shape-1];
	}
	
	public static int getNum(Card card)
	{
		return card.Num;
	}
	
}
