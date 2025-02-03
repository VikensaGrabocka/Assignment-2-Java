package calendarprocessing;

import java.util.Scanner;

public class MainApp {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please enter the path of the calendar: ");
        String path = input.nextLine();
        CalendarProcessing calendar = new CalendarProcessing();
        calendar.readFile(path);


        String choice = "";
        do {
            try {
                displayMenu();
                String[] responseParts = findChoice();
                choice = responseParts[0];
                if (choice.equals("display")) {
                    calendar.displayDay(responseParts[1]);
                } else if (choice.equals("add")) {
                    calendar.addTask(responseParts[1]);
                } else if (choice.equals("delete")) {
                    calendar.deleteTask(responseParts[1]);
                } else if (choice.equals("export")) {
                    calendar.export(responseParts[1]);
                } else if (!choice.equals("quit")) {
                    System.out.println("Invalid choice!");
                }
            }catch(Exception e){
                System.out.println("Please provide the data in the correct format!");

            }
        }while(!choice.equals("quit"));
    }



    private static void displayMenu(){
        System.out.println("quit");
        System.out.println("display <day>");
        System.out.println("add <task>");
        System.out.println("delete <day>,<task id>");
        System.out.println("export <filename>");

    }

    private static String[] findChoice(){
        String response = input.nextLine();
        String[] responseParts = new String[2];
        int spacePosition = response.indexOf(" ");
        if(spacePosition!=-1){
            responseParts[0] = response.substring(0, spacePosition);
            responseParts[1] = response.substring(spacePosition + 1);
        }else{
            responseParts[0] = response;
        }
        return responseParts;
    }
}
