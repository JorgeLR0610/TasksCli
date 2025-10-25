package io.github.JorgeLR0610;

import model.Task;
import service.TaskManager;

import java.time.LocalDate;
import java.util.List;

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

                    tasks.add(new Task(nextId, description, "todo", LocalDate.now().toString(), LocalDate.now().toString()));
                    TaskManager.saveTasks(tasks);
                    System.out.println("Task added successfully (ID: " + nextId + ")");
                    break;

                case "update":
                    int idToUpdate = Integer.parseInt(args[1]);
                    String newDescription = args[2];
                    tasks = TaskManager.loadTasks();
                    for (Task t : tasks){
                        if(t.getId() == idToUpdate){
                            t.setDescription(newDescription);
                            t.setUpdatedAt(LocalDate.now().toString());
                            break;
                        }
                    }
                    TaskManager.saveTasks(tasks);
                    System.out.println("Task with ID: " + idToUpdate + " successfully updated");
                    break;

                case "delete":
                    int idToDelete = Integer.parseInt(args[1]);
                    tasks = TaskManager.loadTasks();

                    boolean removed = tasks.removeIf(t -> t.getId() == idToDelete); //Even though removeIf iterates over the entire list and removes all the elements that meet the condition, since each task has a unique ID, only one will be removed
                    if(removed){
                        System.out.println("Task with ID: " + idToDelete + " successfully deleted");
                    } else{
                        System.out.println("The ID: " + idToDelete + " does not exist");
                    }
                    TaskManager.saveTasks(tasks);
                    break;

                case "list":
                    tasks = TaskManager.loadTasks();
                    String filter = (args.length > 1) ? args[1] : "all";

                    System.out.println("---List of tasks---");
                    boolean found = false;
                    for (Task t : tasks){
                        if(filter.equals("all") || t.getStatus().equals(filter)){
                            System.out.println(t); //Since t is an objet from the class Task, using sout will use the .toString() method defined in that class
                            found = true;
                        }
                    }
                    if(!found){
                        System.out.println("There are no tasks marked as " + filter);
                    }
                    break;

                case "mark-in-progress":
                    int idToMarkInProgress = Integer.parseInt(args[1]);
                    tasks = TaskManager.loadTasks();
                    for (Task t : tasks){
                        if(t.getId() == idToMarkInProgress){
                            t.setStatus("in-progress");
                            t.setUpdatedAt(LocalDate.now().toString());
                            break;
                        }
                    }
                    TaskManager.saveTasks(tasks);
                    System.out.println("Task with ID: " + idToMarkInProgress + " as in progress");
                    break;

                case "mark-done":
                    int idToMarkDone = Integer.parseInt(args[1]);
                    tasks = TaskManager.loadTasks();
                    for (Task t : tasks){
                        if(t.getId() == idToMarkDone){
                            t.setStatus("done");
                            t.setUpdatedAt(LocalDate.now().toString());
                            break;
                        }
                    }
                    TaskManager.saveTasks(tasks);
                    System.out.println("Task with ID: " + idToMarkDone + " marked as done");
                    break;
            }

        }catch(IndexOutOfBoundsException e){
            System.err.println("Error, there are some arguments missing for '" + action + "'");
        }
        catch(NumberFormatException e){
            System.err.println("Error: The ID must be integer.");
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