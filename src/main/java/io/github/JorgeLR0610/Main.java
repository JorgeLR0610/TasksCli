package io.github.JorgeLR0610;

import model.Task;
import model.TaskManager;

import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //In case there are no args
        if (args.length == 0){
            System.out.println("An action is required.");
            printUsage();
            return;
        }
        //(add, list, update, etc.)
        String action = args[0];

        try{
            switch (action){
                case "add":
                    String description = args[1];
                    List<Task> tasks = TaskManager.loadTasks();

                    //To assign a unique id, find the biggest id and add 1
                    int nextId = 0;
                    for (Task t : tasks){ //Iterates over each task (t) that was loaded from the list
                        if(t.getId() > nextId){
                            nextId = t.getId();
                        }
                    } //At the end of this loop, nextId will have the biggest id, so it only remains to add 1
                    nextId++;

                    tasks.add(new Task(nextId, description, "todo", LocalDate.now(), LocalDate.now()));
                    TaskManager.saveTasks(tasks);
                    System.out.println("Task added successfully (ID: " + nextId + ")");
                    break;

                case "update":
                    int idToUpdate = Integer.parseInt(args[1]);
                    String newDescription = args[2];
                    //Logic
                    System.out.println("Task with ID" + idToUpdate + "successfully updated");
                    break;
                case "delete":
                    int idToDelete = Integer.parseInt(args[1]);

                    System.out.println("Task with ID" + idToDelete + "successfully deleted");
                    break;
                case "list":
                    //Show tasks in json file
                    break;
                case "list done":

                    break;
                case "list todo":

                    break;
                case "list in-progress":

                    break;
                case "mark-in-progress":
                    int idToMarkInProgress = Integer.parseInt(args[1]);

                    System.out.println("Task marked as in progress");
                    break;
                case "mark-done":
                    int idToMarkDone = Integer.parseInt(args[1]);

                    System.out.println("Task marked as done");
                    break;
            }

        }catch(IndexOutOfBoundsException e){
            System.err.println("Error, there are some arguments missing for '" + action + "'.");
        }
        catch(NumberFormatException e){
            System.err.println("Error: The ID must be String.");
        }
        catch(Exception e){
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void printUsage() {
        System.out.println("\nUsage: java TaskCli <action> [args...]");
        System.out.println("Examples:");
        System.out.println("  java TaskCli add \"Buy milk\"");
        System.out.println("  java TaskCli list");
        System.out.println("  java TaskCli list done");
        System.out.println("  java TaskCli delete 1");
        System.out.println("  java TaskCli mark-done 1");
    }
}