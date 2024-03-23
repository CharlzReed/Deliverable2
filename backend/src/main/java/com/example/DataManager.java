package com.example;

import java.io.IOException;
import java.util.List;

public class DataManager {
    private static DataManager instance;
    private boolean dataLoaded = false;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void loadData() {
        if (!dataLoaded) {
            CSVReader.readALL();
            dataLoaded = true;
        }
    }

    public List<User> getUsers() {
        loadData();
        return Library.getInstance().getUsers();
    }

    public void saveData() {
        CSVReader.writeALL();
    }

    public void registerNewUser(User newUser) throws IOException {
        Library.getInstance().addUserSafely(newUser);
        saveData();
    }

}
