# TasksCli
CLI app to track tasks and manage a to-do list.

## Requirements
- Java (version 17 or above)
- Maven

## How to use it
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/JorgeLR0610/TasksCli.git]
    cd TasksCli
    ```

2.  **Compile the project:**
    Use Maven to compile and package the app, this will create the `.jar` file in the `target/` folder.
    ```bash
    mvn clean package
    ```

3.  **Give execution permissions to the script:**
    (Only Linux/macOS)
    ```bash
    chmod +x task-cli
    ```

4.  **Run the app!**
    Use the script `task-cli` from the root of the project

## Example
The list of commands and their usage is given below:

### Adding a new task
`task-cli add "Buy groceries"`
### Output: "Task added successfully (ID: " ID + ")"
### Updating and deleting tasks
`task-cli update 1 "Buy groceries and cook dinner"`
`task-cli delete 1`
### Marking a task as in progress or done
`task-cli mark-in-progress 1`
`task-cli mark-done 1`
### Listing all tasks
`task-cli list`
### Listing tasks by status
`task-cli list done`
`task-cli list todo`
`task-cli list in-progress`

## Explanation of the functions:

#### Add task function:
The program must read the json file first to know what tasks already exist, then modify that list in memory and finally save the entire list to the file.
That's why it is necessary the `List<Task> tasks = TaskManager.loadTasks();` to open tasks.json, then read the text inside and use Gson to convert that into an object list of type Task.
Then it comes the logic to find the id for the new task, that's explained in the code and either way it's easy to get the hang of it.
The line `tasks.add(new Task(nextId, description, "todo", LocalDate.now(), LocalDate.now()));` finally adds a new element on the list that was loaded from the json file, when a new objet Task is created using the constructor from the class Task inside model folder
Finally, the list with the new-added task is passed onto `TaskManager.saveTasks(tasks);` and this method converts the list into JSON string again and write it into the tasks.json file.

#### Update task function:
The update method receives the id and new description for the task as parameters, then iterates over the list once it's loaded into memory and modifies the respective task.

#### Delete task function:
Similar to the previous, the method receives the id as parameter and after loading the list, the `removeIf` method is used to delete all the elements that meet the condition, in this case just one will be eliminated since each task has a unique id

#### List task function:
In this case, a ternary operator is used to assign a value to the filter variable, if the number of arguments is bigger than 1, then that last argument is assigned to the filter variable (knowing that it must be: todo, done or in-progress); otherwise, if there's no second argument since the number of args is equal to 1, then "all" is assigned to the filter variable. This way, a boolean var named found is declared as false and we iterate over the list verifying if the filter is equal to `"all"` **or** if the status of the current task in the loop is equal to the filter, so that if one of the requirements is met, it prints the current task and assign ture the flag variable (`found`)

#### mark-in-progress and mark-done
Again, using a for loop, we iterate over the list until the id matches with the one to update and once it happens, we use the setters to assign the new status and also update the `updatedAt` attribute.

https://roadmap.sh/projects/task-tracker