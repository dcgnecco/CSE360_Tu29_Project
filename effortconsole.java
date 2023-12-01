import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class EffortType 
{
    int typeId;
    String typeName;

    public EffortType(int typeId, String typeName) 
    {
        this.typeId = typeId;
        this.typeName = typeName;
    }
}

class EffortLog 
{
    int effortId;
    EffortType workType;
    double effortAmount;
    Date timestamp;

    public EffortLog(int effortId, EffortType workType, double effortAmount, Date timestamp) 
    {
        this.effortId = effortId;
        this.workType = workType;
        this.effortAmount = effortAmount;
        this.timestamp = timestamp;
    }
}

class DevelopmentTeam 
{
    List<EffortLog> logs = new ArrayList<>();

    public void logEffort(EffortType workType, double effortAmount) 
    {
        Date timestamp = new Date();
        EffortLog effortLog = new EffortLog(logs.size() + 1, workType, effortAmount, timestamp);
        logs.add(effortLog);
        System.out.println("Effort logged: Work Type - " + workType.typeName +
                ", Amount - " + effortAmount + " hours, Timestamp - " + timestamp);
    }

    public void displayEfforts() 
    {
        System.out.println("===== Recorded Efforts =====");
        for (EffortLog log : logs) 
        {
            System.out.println("Work Type: " + log.workType.typeName +
                    ", Amount: " + log.effortAmount +
                    ", Timestamp: " + log.timestamp);
        }
    }
}


class EffortLogger 
{
    public static void recordEffort(DevelopmentTeam developmentTeam, EffortType workType, double effortAmount) 
    {
        developmentTeam.logEffort(workType, effortAmount);
    }
}

public class effortconsole 
{
    public static void main(String[] args) 
    {
        EffortLogger effortLogger = new EffortLogger();
        DevelopmentTeam developmentTeam = new DevelopmentTeam();

        EffortType deliverablesType = new EffortType(1, "Deliverables");
        EffortType defectsType = new EffortType(2, "Defects");

        Scanner scanner = new Scanner(System.in);

        int choice;
        do 
        {
            System.out.println("Select an option:");
            System.out.println("1. Log Effort");
            System.out.println("2. View Recorded Efforts");
            System.out.println("0. Exit");

            choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.println("Select the type of work: ");
                    System.out.println("1. Deliverables");
                    System.out.println("2. Defects");

                    int workTypeChoice = scanner.nextInt();
                    EffortType selectedWorkType = (workTypeChoice == 1) ? deliverablesType : defectsType;

                    System.out.println("Enter the amount of effort (hours): ");
                    double effortAmount = scanner.nextDouble();

                    EffortLogger.recordEffort(developmentTeam, selectedWorkType, effortAmount);
                    break;

                case 2:
                    developmentTeam.displayEfforts();
                    break;

                case 0:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}
