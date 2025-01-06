package org.example.app;

//          !!!!!!!!!!!!!!! WAŻNE  !!!!!!!!!!!!!!!

/*

Based on the provided code, everything should be working correctly. Here are a few steps to ensure everything is set up properly:

SQLite JDBC Driver: Ensure the SQLite JDBC driver is added to your project's classpath. You can download it from here.

Database Files: Ensure the database files (database1.db and database2.db) are accessible and located in the correct directory.

JavaFX Setup: Ensure JavaFX is correctly set up in your project. You need to add the JavaFX SDK to your project's classpath and configure the VM options to include the JavaFX modules.

Run the Application: Run the Main class to start the application.

If you encounter any specific errors, please provide the error messages for further assistance.

 */

public class Main {
    public static void main(String[] args) {
        AppManager appManager = new AppManager();
        appManager.run();
    }
}
