package application;

public class Story {
	private String storyText; //the actual story
	private String[] historicalData; //array of data attached to the story
	private int dataCount = 0; //amount of data in the story's array
	
	//initializes story with specified story text and creates empty historical data array
	public Story(String text)
	{
		this.storyText = text;
		this.historicalData = new String[] {"<no historical data for this story>"};
	}
	
	//gets the story text
	public String getStoryText()
	{
		return storyText;
	}
	
	//adds a given piece of data into the historical data array
	public void addData(String newData)
	{
		historicalData[dataCount] = newData;
		dataCount++;
		String[] newHistoricalData = new String[dataCount + 1];
		for(int i = 0; i < dataCount; i++)
		{
			newHistoricalData[i] = historicalData[i];
		}
		this.historicalData = newHistoricalData;
	}
	
	//prints all elements of the historical data array
	public void printHistoricalData()
	{
		int j = dataCount;
		if(dataCount == 0)
		{
			j++;
		}
		
		System.out.println("\nHistorical Data:");
		for(int i = 0; i < j; i++)
		{
			System.out.println("\t" + (i + 1) + ". " + historicalData[i]);
		}
	}
}
