package blackjack;

import java.util.Random;

public class CardDeck {
	Card Deck[];
	int CardsSize;
	
	public CardDeck(int mode)
	{
		//make card deck
		// 1 : General (52 cards without JOKER)
		// 2 :
		switch(mode)
		{
		case 1:
			CardsSize = 52;
			Deck = new Card[CardsSize];
			int i = 0;
			for(int shape=1; shape<=4; shape++)
			{
				for(int num=1; num<=13; num++)
				{
					Deck[i++] = new Card(shape,num);
				}
			}
		}
	}
	
	public static void ShowDeck_console(CardDeck nowdeck)
	{
		for(int i=0; i<52; i++)
		{
			System.out.print(Card.getShape(nowdeck.Deck[i]));
			System.out.print(Card.getNum(nowdeck.Deck[i]));
			System.out.print(" ");			
		}
	}
	
	public static void shuffle(CardDeck nowdeck, int TimesOfShuffle)
	{

		Random random = new Random();
		int cardsize = nowdeck.CardsSize;
		
		for(int i=0; i<TimesOfShuffle; i++)
		{
			int x=random.nextInt(cardsize-1);
			int y=random.nextInt(cardsize-1);
			
			if(x != y)
			{//XY(랜덤변수)가 다를 경우만 CARDSET의 인덱스를 이용해 자리를 바꿔줌
				Card temp = nowdeck.Deck[i];
				nowdeck.Deck[x] = nowdeck.Deck[y];
				nowdeck.Deck[y] = temp;
			}
			else
			{//XY가 같은 경우에 다시 섞도록 만들어줌
				i--;
			}
		}
	}
}
