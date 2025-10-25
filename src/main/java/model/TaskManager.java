package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskManager{
    private static final String FILE_NAME = "tasks.json";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create(); //setPrettyPrinting is used to format the string and save it to the json file

    //To convert from json to List<Task>
    private static final Type TASK_LIST_TYPE = new TypeToken<ArrayList<Task>>() {}.getType();

    public static void saveTasks(List<Task> tasks){
        try {
            String jsonString = gson.toJson(tasks, TASK_LIST_TYPE);
            Path path = Paths.get(FILE_NAME);
            Files.writeString(path, jsonString);
            //System.out.println("Task successfully saved");
        } catch (IOException e) {
            System.err.println("Error writing the JSON file: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks(){
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            return new ArrayList<>(); //Returns an empty list if it does not exist
        }

        try {
            String content = Files.readString(path);

            //Converts the JSON string to java objects
            List<Task> loadedTasks = gson.fromJson(content, TASK_LIST_TYPE);

            //In case the file exists but it's empty
            if (loadedTasks == null) {
                return new ArrayList<>();
            }

            return loadedTasks;

        } catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
