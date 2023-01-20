package blackjack;

import java.util.Random;
import java.util.Scanner;

public class BlackJack {
	
	final static int CardSize=53;
	static String CardSet[];
	static int CardSetIndex; //현재 카드셋을 어디까지 사용했는지
	
	static User Player;
	static User Dealer;
	
	public BlackJack()
	{
		CardSet = new String[CardSize];
		CardSetIndex = 0;
		SetCard(); //카드 셋 생성
		ShuffleCard(1000); //카드 섞기 함수 인자는 섞는 횟수
		
		Player = new User();
		Dealer = new User();
		
	}
	
	public void StartGame()
	{
		//두장씩 카드 분배
		AddCard(Dealer);
		AddCard(Player);
		AddCard(Dealer);
		AddCard(Player);
		int input=2;
		
		while(true)
		{//게임 반복문
			//점수계산 변수
			int DealerScore = User.score(Dealer.cards);
			int PlayerScore = User.score(Player.cards);

			System.out.println("<Dealer>");
			User.ShowCard(Dealer.cards);
			System.out.println("<Player>");
			User.ShowCard(Player.cards);
			
			System.out.printf("현재 점수 : %d\n", PlayerScore);
			
			if(CheckScore(DealerScore, PlayerScore, input)==true)
			{//게임 종료 조건 //보기 편하게 하기 위해 함수로 빼서 구현
				break;
			}

			
			System.out.printf("블랙잭 확률 : %4.2f%%\n", User.BlackP(Player.cards, CardSet, CardSetIndex));
			System.out.println();
			
			if(input==2)
			{//GO일 경우만 동작
				Scanner sc = new Scanner(System.in);
	
				System.out.print("1.Stay or 2.Go : ");
				while(true)
				{//1 또는 2만 입력받기 위한 반복문
					
					input = sc.nextInt();
					if(input == 1 || input == 2)
					{
						break;
					}
					else
					{
						System.out.println("1또는 2 만 입력해주세요");
						sc = new Scanner(System.in);
					}
				}//1 또는 2만 입력받기 위한 반복문
				
				if(input==2)
				{//Go 일 경우 카드 추가
					AddCard(Player);
				}
			}
			
			if(User.score(Dealer.cards)<=16)
			{//딜러의 카드가 16이하일 경우 카드 추가
				AddCard(Dealer);
			}
		}
	}
	
	private boolean CheckScore(int DealerScore, int PlayerScore, int input)
	{//gamestrat 함수를 잘 보이게 하기 위해 따로 구현한 함수 //딜러가 버스트한 경우는 그대로 진행
		//종료 조건 1
		if(PlayerScore>21)
		{
			System.out.println("Player Bust");
			return true;
		}
		if(PlayerScore==21)
		{
			System.out.println("Black Jack!");
			if(DealerScore == 21)
			{
				System.out.println("Draw");
			}
			return true;
		}
		//종료 조건 2
		if(input==1 && DealerScore>16)
		{
			if(DealerScore>PlayerScore && DealerScore<=21)
			{
				System.out.println("Dealer Win");
			}
			if(DealerScore<PlayerScore || DealerScore>21)
			{
				System.out.println("Player win");
			}
			if(DealerScore==PlayerScore)
			{
				System.out.println("Draw");
			}
			return true;
		}
		
		return false;
	}
	
	public void showcardset()
	{//현재 카드 셋을 보여주는 함수 디버깅용
		for(String card : CardSet)
		{
			System.out.printf("%s ",card);
		}
		System.out.println();
	}
	
	private void SetCard()
	{//카드 셋을 생성하는 함수
		
		String temp[] = {"A","2","3","4","5","6","7","8","9"
						,"10","J","Q","K"};
		
		int index = 0;
		for(String card : temp)
		{
			for(int i=0; i<4; i++)
			{
				CardSet[index++] = card;
			}
		}
		CardSet[index]="Joker";
		
		
	}
	
	private void ShuffleCard(int NumOfShuffle)
	{
		Random random = new Random();
		for(int i=0; i<NumOfShuffle; i++)
		{
			int x=random.nextInt(CardSize);
			int y=random.nextInt(CardSize);
			
			if(x != y)
			{//XY(랜덤변수)가 다를 경우만 CARDSET의 인덱스를 이용해 자리를 바꿔줌
				String temp = CardSet[x];
				CardSet[x] = CardSet[y];
				CardSet[y] = temp;
			}
			else
			{//XY가 같은 경우에 다시 섞도록 만들어줌
				i--;
			}
		}
	}
	
	private void AddCard(User who)
	{//입력된 객체에 카드 추가
		User.AddCard(who.cards, CardSet[CardSetIndex++]);
	}
}

