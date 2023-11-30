package application;

public class User {
	private String name; //user's name
	private int emplid; //user's id
	private static int counter = 0; //counter of the number of users in database
	private Card[] deck; //deck of cards for given user
	private Card playedCard; //card played after a given round of planning poker
	
	//initializes user with specified name and sets the employee id
	public User(String inputName)
	{
		this.name = inputName;
		counter++;
		this.emplid = counter;
	}
	
	//getters and setters for user data
	public String getName()
	{
		return name;
	}
	public int getEmplid()
	{
		return emplid;
	}
	public double getPlayedCard()
	{
		return playedCard.getPointVal();
	}
	public Card[] getDeck()
	{
		return deck;
	}
	public void setDeck(Card[] newDeck)
	{
		deck = newDeck;
	}
	
	//sets the user's played card as the card with the given index 
	public void playCard(int index)
	{
		playedCard = deck[index - 1];
	}
	
	//prints the deck given to the user
	public void printDeck()
	{
		for(int i = 1; i <= deck.length; i++)
		{
			if(deck[i - 1].getPointVal() == 0.5)
			{
				System.out.println(""+i + ":\t" + deck[i - 1].getPointVal());
			}
			else
			{
				System.out.println(""+i + ":\t" + (int)(deck[i - 1].getPointVal()));
			}
		}
	}
}
