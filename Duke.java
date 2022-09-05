import java.util.Scanner;

public class Duke {
    // static variables
    private static Task[] taskList = new Task[100];
    private static int taskCounter = 0;

    static String HORIZONTAL_LINE = "------------------------------------------------------------";

    // class methods
    static void errorMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("I don't know what that means :(");
        System.out.println(HORIZONTAL_LINE);
    }

    static boolean checkSpace(String line) {
        // in the case of "todo"
        if (!line.contains(" ")) {
            return false;
        }

        // in the case of "todo "
        int spaceLocation = line.indexOf(" ");
        if (spaceLocation == line.length()) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String command = line.split(" ")[0];

        while (!command.matches("bye")) {
            switch (command) {
            case "list":
                listTasks();
                break;
            case "mark":
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                markTask(taskList[index]);
                break;
            case "unmark":
                index = Integer.parseInt(line.split(" ")[1]) - 1;
                unmarkTask(taskList[index]);
                break;
            case "todo":
                addTaskSpecial(line, "todo");
                break;
            case "deadline":
                addTaskSpecial(line, "deadline");
                break;
            case "event":
                addTaskSpecial(line, "event");
                break;
                

            default:
                errorMessage();
                break;
            }
            line = input.nextLine();
            command = line.split(" ")[0];
        }
        exit(); 
    }

    // Method to greet user when they enter Duke
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        
    }

    // Method to print the same command as user input
    public static void echo(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(command);
        System.out.println(HORIZONTAL_LINE);
    }

    // Function to exit out of Duke
    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to add normal tasks
    public static void addTask(Task task) {
        taskList[taskCounter] = task;
        taskCounter += 1;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("added: " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to list tasks
    public static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; ++i) {
            Task task = taskList[i];
            System.out.println(task.toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to mark a task as done
    public static void markTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to unmark task as undone
    public static void unmarkTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to add todo, deadline and event tasks
    public static void addTaskSpecial(String name, String type) {
        String divider = "/";
        if (!checkSpace(name)) {
            errorMessage();
            return;
        }

        if (!name.contains(divider)) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Please add a date");
            System.out.println(HORIZONTAL_LINE);
            greet();
            return;
        }

        name = name.substring(name.indexOf(" "), name.length());
        switch (type) {
        case "todo":
            Todo todoTask = new Todo(name);
            taskList[taskCounter] = todoTask;
            taskCounter += 1;
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(todoTask.toString());
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
            break;
        case "deadline":
            String deadlineDate = name.substring(name.indexOf(divider) + 3, name.length());
            Deadline deadlineTask = new Deadline(name, deadlineDate);
            taskList[taskCounter] = deadlineTask;
            taskCounter += 1;
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(deadlineTask.toString());
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
            break;
        case "event":
            String eventDate = name.substring(name.indexOf(divider) + 3, name.length());
            Event eventTask = new Event(name, eventDate);
            taskList[taskCounter] = eventTask;
            taskCounter += 1;
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(eventTask.toString());
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
            break;
        }
    }
}