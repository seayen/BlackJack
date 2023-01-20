package blackjack;

import java.util.ArrayList;
import java.util.List;

public class User {
	List<String> cards; //string의 동적 할당을 위해 ArrayList 사용
	
	public User()
	{
		cards = new ArrayList<String>();
	}
	
	public static void AddCard(List<String> cards, String card)
	{//카드 리스트에 뽑은 카드 추가
		cards.add(card);
	}
	
	public static void ShowCard(List<String> cards)
	{//현재 가지고 있는 카드를 출력함
		for(int i=0; i<cards.size(); i++)
		{
			System.out.printf("<--- %-2d ---> ",i+1);
		}
		System.out.println();
		
		for(int i=0; i<cards.size(); i++)
		{
			System.out.printf("Card : %-5s ",cards.get(i));
		}
		System.out.println();
	}
	
	public static int score(List<String> cards)
	{//가지고 있는 카드들로 점수를 계산해서 반환하는 함수
		int sum=0;
		int A=0; //A의 개수
		int Joker=0;//조커의 개수
		
		for(int i=0; i<cards.size(); i++)
		{//카드 갯수 세기
			String temp = cards.get(i);
			
			switch(temp)
			{ 
			case "2": case "3": case "4": case "5": 
			case "6": case "7": case "8": case "9":
				sum += temp.charAt(0) - '0';
				break;
			case "10": case "J": case "Q": case "K":
				sum += 10;
				break;
			case "A":
				A++;
				break;
			case "Joker":
				Joker++;
				break;
			default:
				System.out.println("Error : Unknown Card");
				break;
			}	
		}
		
		
		for(int i=0; i<A; i++)
		{//A 카드 처리
			if((sum+11) <= 21)
			{
				sum += 11;
			}
			else
			{
				sum += 1;
			}
		}
		
		if(Joker==1)
		{//Joker 카드 처리
			if((sum+11) <= 21)
			{
				sum += 11;
			}
			else
			{
				sum = 21;
			}
		}
		
		return sum;
	}
	
	public static float BlackP(List<String> cards, String CardSet[], int CardSetIndex)
	{//특정 카드가 나올 확률을 구함
		int NeedScore = 21 - User.score(cards);
		int remaincards=5;
		
		if (NeedScore<0)
		{
			return 0;
		}
		if(NeedScore==0)
		{
			return 100;
		}
		if (NeedScore>0)
		{
			if(NeedScore>11)
			{
				return 0;
			}
			if(NeedScore==11)
			{//A 4개 조커 1개
				remaincards=5;
			}
			if(NeedScore==10)
			{// J,Q,K 4개씩, 조커 1개
				remaincards=13;
			}
			if(NeedScore<10)
			{//A,2~9 1개, 조커 1개
				remaincards=5;
			}
		}

		
		
		for(int i=0; i<CardSetIndex; i++)
		{//사용한 카드를 셈
			String temp = CardSet[i];
			
			switch(temp)
			{ 
			case "2": case "3": case "4": case "5": 
			case "6": case "7": case "8": case "9":
				if(NeedScore == temp.charAt(0)-'0')
				{//2~9가 필요한 경우 그 카드를 가지고 있다면
					remaincards--;
				}
				break;
			case "10": case "J": case "Q": case "K":
				if(NeedScore == 10)
				{//10이 필요한 경우 10,J,Q,K 중 가지고 있다면
					remaincards--;
				}
				break;
			case "A":
				if(NeedScore == 1 || NeedScore == 11)
				{//1 또는 11이 필요한 경우 A 를 이미 가지고 있다면
					remaincards--;
				}
				break;
			case "Joker": //조커는 모든 곳에 있음
				remaincards--;
				break;
			default:
				System.out.println("Error : Unknown Card");
				break;
			}
		}
		
		
		
		
		return (float)remaincards / (float)(53-CardSetIndex) * 100;
	}
}
