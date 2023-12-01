import java.util.HashMap;
import java.util.Map;

class Authentication {
    private Map<String, String> userCredentials;

    public Authentication() {
        userCredentials = new HashMap<>();
        userCredentials.put("projectManager", "pmPassword");
        userCredentials.put("teamMember", "tmPassword");
    }

    public boolean authenticateUser(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
}

class AccessControl {
    public void setAccessControls(User user, Task task) {
        System.out.println("Access controls set for " + user.getUsername() + " on task " + task.getTaskName());
    }
}

class Estimation {
    public void individualEstimate(User user, Task task, int estimate) {
        System.out.println(user.getUsername() + " estimates " + estimate + " for task " + task.getTaskName());
    }
}

class ProjectManager extends AccessControl {
    public void approveEstimates() {
        System.out.println("Estimates approved by the project manager");
    }
}

class TeamMember extends Estimation {
    // Additional methods specific to TeamMember
}

class PlanningPoker {
    private AccessControl accessControl;
    private Estimation estimation;

    public PlanningPoker(AccessControl accessControl, Estimation estimation) {
        this.accessControl = accessControl;
        this.estimation = estimation;
    }

    public void conductSession(User user, Task task, int estimate) {
        accessControl.setAccessControls(user, task);
        estimation.individualEstimate(user, task, estimate);
    }

    public void finalizeEstimates(ProjectManager projectManager) {
        projectManager.approveEstimates();
    }
}

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

class Task {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
}

public class Main {
    public static void main(String[] args) {
        Authentication authentication = new Authentication();

        // Example: Authenticate a user
        String username = "projectManager";
        String password = "pmPassword";
        if (authentication.authenticateUser(username, password)) {
            System.out.println("Authentication successful");
        } else {
            System.out.println("Authentication failed");
            return; // Exit the program if authentication fails
        }

        // Create instances for testing
        User user = new User("projectManager");
        Task task = new Task("Task 1");
        int estimate = 5;

        // Instantiate classes
        AccessControl accessControl = new AccessControl();
        Estimation estimation = new Estimation();
        ProjectManager projectManager = new ProjectManager();
        TeamMember teamMember = new TeamMember();

        PlanningPoker planningPoker = new PlanningPoker(accessControl, estimation);

        // Conduct a planning poker session
        planningPoker.conductSession(user, task, estimate);

        // Finalize estimates by the project manager
        planningPoker.finalizeEstimates(projectManager);
    }
}
