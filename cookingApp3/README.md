Project Structure
The project is organized into several packages and files:

org.example.app: Contains the main application classes.
org.example.controller: Contains the controllers for handling user interactions.
org.example.data: Contains classes for managing data (e.g., pantry and recipes).
org.example.models: Contains the data models (e.g., Recipe and Ingredient).
org.example.ui: Contains the console-based user interface.
org.example.utils: Contains utility classes (e.g., ShoppingListCalculator).

resources: Contains the FXML files for the JavaFX UI and CSS for styling.

Key Classes and Their Roles

App.java: The main entry point for the JavaFX application. It loads the initial FXML file 
and sets up the scene.

AppManager.java: Manages the app
lication logic, including loading and saving data, and switching between different views.

PrimaryController.java: Handles user interactions in the primary view (e.g., adding products to the pantry).

Database.java: Manages the SQLite database connection and operations (e.g., creating tables, inserting products).

Recipe.java: Represents a recipe with a name and a list of ingredients.

Ingredient.java: Represents an ingredient with a name and quantity.

ConsoleUI.java: Provides a console-based user interface for interacting with the application.

How the Project Works
Starting the Application: The application starts with the App.java class, which loads the primary.fxml file and sets up the initial scene.
User Interactions: The user interacts with the application through the JavaFX UI. For example, they can add products to the pantry using the PrimaryController.java.
Database Operations: The Database.java class handles all database operations, such as creating tables, inserting products, and retrieving products.
Data Management: The DataManager.java class is responsible for saving and loading data to and from files.
Calculating Shopping Lists: The ShoppingListCalculator.java class calculates the missing ingredients for a recipe based on the current pantry contents.