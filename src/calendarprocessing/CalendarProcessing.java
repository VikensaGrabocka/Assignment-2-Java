package calendarprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CalendarProcessing {
    private static final Comparator<Date> dateComparator = Comparator.comparing(Date::getYear).thenComparing(Date::getMonth).thenComparing(Date::getDay);
    private static final Comparator<Task> timeComparator = Comparator.comparing(Task::getStartTimeInMinutes);
    private TreeMap<Date, PriorityQueue<Task>> tasks = new TreeMap<>(dateComparator);

    public CalendarProcessing() {

    }

    public void readFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line =reader.readLine()) != null) {
                addTask(line);
            }

        } catch (IOException e) {
            System.out.println("File is currently not available. " +
                    "Exiting. Please try again!");
            System.exit(0);
        }
    }

    public void displayDay(String dateToCheck) {
        Date date = new Date(dateToCheck);
        if (date.getDay()!=-1){
            if (tasks.containsKey(date)) {
                PriorityQueue<Task> dailyTasks = tasks.get(date);
                PriorityQueue<Task> readingCopy = new PriorityQueue<>(dailyTasks);
                System.out.println("Tasks to be completed on " + date);
                while (!readingCopy.isEmpty()) {
                    System.out.println(readingCopy.poll());
                }
                System.out.println();
            } else {
                System.out.println("No tasks to be completed on " + date);
            }
        }
    }

    public void deleteTask(String data) {

        String[] dateId = data.split(",");
        String userDate = dateId[0];
        String taskId = dateId[1];
        Date date = new Date(userDate);

        if (tasks.containsKey(date)) {
            PriorityQueue<Task> dateTasks = tasks.get(date);
            for (Task t : dateTasks) {
                if (t.getTaskId().equals(taskId)) {
                    dateTasks.remove(t);
                    return;
                }
            }
        }
        if(!date.toString().equals("-1/-1/-1")){
            System.out.println("There is no task with id " + taskId + " on " + date);
        }
        System.out.println();
    }

    public void export(String path) {

        try (Formatter output = new Formatter(path)) {
            for(Date date : tasks.keySet()){
                PriorityQueue<Task> dailyTasks = tasks.get(date);
                PriorityQueue<Task> readingCopy = new PriorityQueue<>(dailyTasks);
                while(!readingCopy.isEmpty()){
                    output.format(readingCopy.poll().toString());
                    output.format("\n");
                }

            }
        } catch (Exception e) {
            System.out.println("An error occurred while processing the file!");
        }
    }


    public void addTask(String record) {
        String[] taskParts = record.split(",");
        try {
            Date date = new Date(taskParts[0]);
            String taskId = taskParts[1];
            Time time = new Time(taskParts[2], Integer.parseInt(taskParts[3]));
            String description = taskParts[4];
            Task task = new Task(date, taskId, time, description);


            if (tasks.containsKey(date)) {
                PriorityQueue<Task> dailyTasks = tasks.get(date);
                for (Task t : dailyTasks) {
                    if (t.isConflictingTask(task)) {
                        System.out.println("Task " + task.getTaskId() + " is in conflict with " +
                                t.getTaskId() + " on " + t.getDate());
                        return;
                    }
                }
                dailyTasks.offer(task);

            } else {
                PriorityQueue<Task> dailyTasks = new PriorityQueue<>(timeComparator);
                Date taskDate = task.getDate();
                dailyTasks.offer(task);
                tasks.put(taskDate, dailyTasks);
            }
        }
        catch (ArrayIndexOutOfBoundsException are){
            System.out.println("Not enough values to unpack! Task ignored!");
        }
        catch (NumberFormatException ne){
            System.out.println("Data not in correct format! Task ignored!");
        }
        catch (Exception e){
            System.out.println("The data should be in correct form. Task ignored!");
        }

    }
}
