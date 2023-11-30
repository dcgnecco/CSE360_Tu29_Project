//PLANNING POKER EXECUTION LINE
//	Runs through the stages of a Planning Poker session.
//AUTHOR: Dominic Gnecco, Team Tu29

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.util.Scanner;
import java.lang.Math;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		try {
			/*BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();*/
			
			//scanner for user input. end user will interact with javafx scenes to enter input
			Scanner scan = new Scanner(System.in);
				//System.out.println("Enter number of users in Planning Poker session:");
				//int numUsers = scan.nextInt();
			
			//set number of users for testing purposes
			int numUsers = 5;
			//Starting poker deck will always be the same for each user
			Card[] deck = new Card[]{new Card(0), new Card(0.5), new Card(1),
									new Card(2), new Card(3), new Card(5),
									new Card(8), new Card(13), new Card(20),
									new Card(40), new Card(100)};

			//*********User data will be kept track of and imported from mainline program
			//*********Values in test run are test values
			
			//get username for testing purposes
			System.out.println("Username:");
			String signInUsername = scan.nextLine();
			
			System.out.println("\nUsers in this session:");
			User[] scrumGroup = new User[numUsers];
		
			//set user that signed in to the first in the group and fill rest of group with dummies for testing purposes 
			scrumGroup[0] = new User(signInUsername);
			for(int i = 0; i < numUsers; i++)
			{
				if(i > 0)
				{
					scrumGroup[i] = new User("User" + Integer.toString(i + 1) + "_name");
					
				}
				scrumGroup[i].setDeck(new Card[]{new Card(0), new Card(0.5), new Card(1),
						new Card(2), new Card(3), new Card(5),
						new Card(8), new Card(13), new Card(20),
						new Card(40), new Card(100)});
				System.out.println("#" + scrumGroup[i].getEmplid() + ": " + scrumGroup[i].getName());
			}
			
			//Stories for a given session will be imported from mainline program
			//Defaulting to 4 stories for this test run
			Story[] stories = new Story[]{new Story("This is a placeholder where the first story would go"),
					new Story("This is a placeholder where the second story would go"),
					new Story("This is a placeholder where the third story would go"),
					new Story("This is a placeholder where the fourth story would go")};
			
			System.out.println("\nPlanning Poker session beginning in 10 seconds. There are [" + stories.length + "] stories to estimate today!");
			Thread.sleep(10000);
			//this loop controls the planning poker routine. i is permanently set to 0 for testing purposes
			int i = 0;
			for(int altI = 0; altI < stories.length; altI++)
			{
				//for testing purposes, once reaching the final story, we access the second story
				if(altI == 3)
				{
					i = 1;
				}
				
				//prompts user with accessing historical data and acquires each member's estimates
				for(int j = 0; j < numUsers; j++)
				{
					int index;
					if(j == 0)
					{
						//prompt for historical data
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\nStory " + (i+1) + ": " + stories[i].getStoryText()+"\n");
						System.out.println(scrumGroup[j].getName() + ", enter V to view historical data for estimate.");
						String view = "";
						view = scan.nextLine();
						//loops until user opts to view data
						while((view.toUpperCase()).compareTo("V") != 0)
						{
							System.out.println("You may not play a card until having viewed historical data\n"
									+ "Please enter V to view historical data for estimate.");
							view = scan.nextLine();
						}
						
						//prints historical data for story
						System.out.println("\n\n\n\n\n\n\n\n\n\nStory " + (i+1) + ": " + stories[i].getStoryText());
						stories[i].printHistoricalData();
						System.out.println("\nPress ENTER to continue.");
						String dummy2 = scan.nextLine();
						
						//gets card from user
						System.out.println("\n\n\nStory " + (i+1) + ": " + stories[i].getStoryText()+"\n");
						System.out.println(scrumGroup[j].getName() + ", enter index of card you want to play.");
						scrumGroup[j].printDeck();
						index = scan.nextInt();
						System.out.println("You played your card. Other members cannot see your card.\n");
					}
					else //condition for other users turns
					{	
						index = (int)(Math.random() * 11) + 1;
						Thread.sleep(3000);
						System.out.println(scrumGroup[j].getName()+" played their card.\n");
					}
					//plays the card
					scrumGroup[j].playCard(index);
				}
				Thread.sleep(3000);
				
				//shows everyone's cards to the group and calculates estimate (other Tu29 member's prototype)
				System.out.println("All cards have been played for this story, flipping everyone's cards:\n");
				double totalPoints = 0;
				//compute based on each user's estimate
				for(int k = 0; k < numUsers; k++)
				{
					totalPoints += scrumGroup[k].getPlayedCard();
					if(scrumGroup[k].getPlayedCard() == 0.5)
					{
						System.out.println(scrumGroup[k].getName() + " played a " + scrumGroup[k].getPlayedCard());
					}
					else
					{
						System.out.println(scrumGroup[k].getName() + " played a " + (int)(scrumGroup[k].getPlayedCard()));
					}
					
				}
				double estimate = totalPoints / (double)numUsers;
				System.out.printf("Estimate for this story: %.2f \n\n", estimate);
				
				//checks for if group agrees with score
				int agreeCount = 0;
				for(int k = 0; k < numUsers; k++)
				{
					System.out.println(scrumGroup[k].getName() + ", enter D if you disagree with the scores, or any other character if you agree.");
					String agreeance = scan.next();
					agreeance = agreeance.toUpperCase();
					if(!(agreeance.equals("D")))
					{
						agreeCount++;
					}
				}
				
				if((double)(agreeCount) <= (double)(numUsers / 2.0)) //condition for group consensus is disagree
				{
					System.out.println("The consensus disagrees that the estimate is accurate. Reestimating story in 10 seconds.");
					altI--;
					String dummy4 = scan.nextLine();
					Thread.sleep(10000);
				}
				else //condition for group consensus is agree
				{
					if(altI < stories.length - 1)
					{
						//allows user to add additional notes to story's historical data after adding the estimate
						System.out.println("The consensus agrees that the estimate is accurate.\n"
								+ "\nWould you like to add a further note? Enter it below. Press ENTER to skip.");
						String dummy3 = scan.nextLine();
						String furtherData = scan.nextLine();
						stories[i].addData("Estimate: " + estimate);
						if(furtherData.compareTo("") != 0)
						{
							stories[i].addData("Note by " + scrumGroup[0].getName() + ": " + furtherData);
							System.out.print("Note Added. ");
						}
						
						//moves on to next story
						System.out.println("Moving onto next story in 10 seconds.\n");
						Thread.sleep(10000);
					}
					else //condition for when all stories have been estimated
					{
						System.out.println("\nAll user stories have been estimated. The Planning Poker session has concluded.");
					}
				}
			}
			
			//exit program
			scan.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
