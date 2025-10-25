# TaskCli
CLI app to track tasks and manage a to-do list.

## Add task function:
The program must read the json file first to know what tasks already exist, then modify that list in memory and finally save the entire list to the file.
That's why it is necessary the `List<Task> tasks = TaskManager.loadTasks();` to open tasks.json, then read the text inside and use Gson to convert that into an object list of type Task.
Then it comes the logic to find the id for the new task, that's explained in the code and either way it's easy to get the hang of it.
The line `tasks.add(new Task(nextId, description, "todo", LocalDate.now(), LocalDate.now()));` finally adds a new element on the list that was loaded from the json file, when a new objet Task is created using the constructor from the class Task inside model folder
Finally, the list with the new-added task is passed onto `TaskManager.saveTasks(tasks);` and this method converts the list into JSON string again and write it into the tasks.json file.

## Update task function:
