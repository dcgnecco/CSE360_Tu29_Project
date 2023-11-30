package application;

public class Card {
	private double pointVal; //point value of card
	
	//initializes card with specified input value
	public Card(double inputVal)
	{
		this.pointVal = inputVal;
	}
	
	//gets the card's point value
	public double getPointVal()
	{
		return pointVal;
	}
}
