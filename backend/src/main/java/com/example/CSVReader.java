package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class CSVReader {

    public static String fileLocation = "backend" + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "databaseFiles" + File.separator;

    public static void readALL() {
        try {
            readUsers();
            readItems();
            readCourses();
            readCourse2textbook();
            readU2I2D();
            readUser2item();
            readUser2course();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeALL() {
        try {
            String header = "itemID,name,itemType,locationInLibrary,cost,statusType";
            writeObjectArrayToFile("items.csv", header, Library.items);

            header = "userID,name,email,password,userType,accountBalance";
            writeObjectArrayToFile("users.csv", header, Library.users);

            header = "courseID,courseName,courseCode,startDate,endDate";
            writeObjectArrayToFile("courses.csv", header, Library.courses);

            String header1 = "userID,itemID";
            String header2 = "userID,courseID";
            writeAssociations(header1, header2, Library.users);

            writeU2I2D();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readItems() throws IOException {
        String headers = "itemID,name,itemType,locationInLibrary,cost,statusType";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation + "items.csv"))) {
            String line;
            String[] headerArray = headers.split(",");
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != headerArray.length) {
                    System.err.println("(Items) Skipping invalid row: " + line);
                    continue;
                }

                int id = Integer.parseInt(values[0]);
                String name = values[1];
                ItemType itemType = ItemType.valueOf(values[2]);
                LocationType location = LocationType.valueOf(values[3]);
                double cost = Double.parseDouble(values[4]);
                StatusType statusType = StatusType.valueOf(values[5]);

                Library.addItem(new Item(id, name, itemType, location, cost, statusType));
            }
        }
    }

    private static void readUsers() throws IOException {
        String headers = "userID,name,email,password,userType,accountBalance";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation + "users.csv"))) {
            String line;
            String[] headerArray = headers.split(",");
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != headerArray.length) {
                    System.err.println("(Users) Skipping invalid row: " + line);
                    continue;
                }

                int userID = Integer.parseInt(values[0]);
                String name = values[1];
                String email = values[2];
                String password = values[3];
                UserType userType = UserType.valueOf(values[4]);
                double accountBalance = Double.parseDouble(values[5]);

                Library.addUser(new User(userID, name, email, password, userType, accountBalance));
            }
        }
    }

    private static void readCourses() throws IOException {
        String headers = "courseID,courseName,courseCode,startDate,endDate";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation + "courses.csv"))) {
            String line;
            String[] headerArray = headers.split(",");
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != headerArray.length) {
                    System.err.println("(Courses) Skipping invalid row: " + line);
                    continue;
                }

                int courseID = Integer.parseInt(values[0]);
                String courseName = values[1];
                String courseCode = values[2];
                LocalDate startDate = LocalDate.parse(values[3]);
                LocalDate endDate = LocalDate.parse(values[4]);

                Library.addCourse(new Course(courseID, courseName, courseCode, startDate, endDate));
            }
        }
    }

    private static void readCourse2textbook() throws IOException {
        String headers = "courseID,textbookID";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation + "course2textbook.csv"))) {
            String line;
            String[] headerArray = headers.split(",");
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != headerArray.length) {
                    System.err.println("(Course2textbook) Skipping invalid row: " + line);
                    continue;
                }

                int courseID = Integer.parseInt(values[0]);
                int textbookID = Integer.parseInt(values[1]);

                Course tempCourse = null;
                Item tempItem = null;

                for (int i = 0; i < Library.courses.size(); i++) {
                    tempCourse = Library.courses.get(i);
                    if (tempCourse.courseID == courseID) {
                        break;
                    }
                }
                for (int i = 0; i < Library.items.size(); i++) {
                    tempItem = Library.items.get(i);
                    if (tempItem.itemID == textbookID) {
                        break;
                    }
                }

                Library.assign_course_textbook(tempCourse, tempItem);
            }
        }
    }

    private static void readUser2item() throws IOException {
        String headers = "userID,itemID";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation + "user2item.csv"))) {
            String line;
            String[] headerArray = headers.split(",");
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != headerArray.length) {
                    System.err.println("(User2item) Skipping invalid row: " + line);
                    continue;
                }

                int userID = Integer.parseInt(values[0]);
                int itemID = Integer.parseInt(values[1]);

                User tempUser = null;
                Item tempItem = null;

                for (int i = 0; i < Library.users.size(); i++) {
                    tempUser = Library.users.get(i);
                    if (tempUser.userID == userID) {
                        break;
                    }
                }
                for (int i = 0; i < Library.items.size(); i++) {
                    tempItem = Library.items.get(i);
                    if (tempItem.itemID == itemID) {
                        break;
                    }
                }

                // tempUser.rentedItems.add(tempItem);
                tempUser.addItem(tempItem);
            }
        }
    }

    private static void readUser2course() throws IOException {
        String headers = "userID,courseID";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation + "user2course.csv"))) {
            String line;
            String[] headerArray = headers.split(",");
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != headerArray.length) {
                    System.err.println("(User2course) Skipping invalid row: " + line);
                    continue;
                }

                int userID = Integer.parseInt(values[0]);
                int courseID = Integer.parseInt(values[1]);

                User tempUser = null;
                Course tempCourse = null;

                for (int i = 0; i < Library.users.size(); i++) {
                    tempUser = Library.users.get(i);
                    if (tempUser.userID == userID) {
                        break;
                    }
                }
                for (int i = 0; i < Library.courses.size(); i++) {
                    tempCourse = Library.courses.get(i);
                    if (tempCourse.courseID == courseID) {
                        break;
                    }
                }

                tempUser.addCourse(tempCourse);
            }
        }
    }

    private static void readU2I2D() throws IOException {
        String headers = "userID,itemID,dateAdded";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation + "user2item2date.csv"))) {
            String line;
            String[] headerArray = headers.split(",");
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != headerArray.length) {
                    System.err.println("(u2i2d) Skipping invalid row: " + line);
                    continue;
                }

                int userID = Integer.parseInt(values[0]);
                int itemID = Integer.parseInt(values[1]);
                LocalDate date = LocalDate.parse(values[2]);

                User tempUser = null;
                Item tempItem = null;

                for (int i = 0; i < Library.users.size(); i++) {
                    tempUser = Library.users.get(i);
                    if (tempUser.userID == userID) {
                        break;
                    }
                }
                for (int i = 0; i < Library.items.size(); i++) {
                    tempItem = Library.items.get(i);
                    if (tempItem.itemID == itemID) {
                        break;
                    }
                }

                tempUser.rentLog.put(tempItem, date);
            }
        }
    }

    private static void writeObjectArrayToFile(String fileName, String header, ArrayList<?> objArray)
            throws IOException {
        try (FileWriter writer = new FileWriter(fileLocation + fileName)) {
            // Write header
            writer.write(header);
            writer.write("\n");
            String tempS = "";

            // Write data
            for (Object obj : objArray) {
                if (obj instanceof Item) {
                    Item tempobj = (Item) obj;
                    tempS = tempobj.csvFormat();
                }
                if (obj instanceof User) {
                    User tempobj = (User) obj;
                    tempS = tempobj.csvFormat();
                }
                if (obj instanceof Course) {
                    Course tempobj = (Course) obj;
                    tempS = tempobj.csvFormat();
                }
                writer.write(tempS);
                writer.write("\n");
            }
        }
    }

    private static void writeAssociations(String itemHeader, String courseHeader, ArrayList<User> objArray)
            throws IOException {
        ArrayList<String> user2itemArrayList = new ArrayList<>();
        ArrayList<String> user2CourseArrayList = new ArrayList<>();
        for (int i = 0; i < Library.users.size(); i++) {
            for (int j = 0; j < Library.users.get(i).rentedItems.size(); j++) {
                String t = String.format("%s,%s", Library.users.get(i).userID,
                        Library.users.get(i).rentedItems.get(j).itemID);
                user2itemArrayList.add(t);
            }
            for (int j = 0; j < Library.users.get(i).courses.size(); j++) {
                String t = String.format("%s,%s", Library.users.get(i).userID,
                        Library.users.get(i).courses.get(j).courseID);
                user2CourseArrayList.add(t);
            }
        }
        try (FileWriter writer = new FileWriter(fileLocation + "user2item.csv")) {
            // Write header
            writer.write(itemHeader);
            writer.write("\n");

            // Write data
            for (String s : user2itemArrayList) {
                writer.write(s);
                writer.write("\n");
            }
        }
        try (FileWriter writer = new FileWriter(fileLocation + "user2course.csv")) {
            // Write header
            writer.write(courseHeader);
            writer.write("\n");

            // Write data
            for (String s : user2CourseArrayList) {
                writer.write(s);
                writer.write("\n");
            }
        }
    }

    private static void writeU2I2D() throws IOException {
        String header = "userID,itemID,dateAdded";
        ArrayList<String> user2item2dateArrayList = new ArrayList<>();
        for (int i = 0; i < Library.users.size(); i++) {
            for (int j = 0; j < Library.users.get(i).rentedItems.size(); j++) {
                int uid = Library.users.get(i).userID;
                int iid = Library.users.get(i).rentedItems.get(j).itemID;
                LocalDate d = Library.users.get(i).rentLog.get(Library.users.get(i).rentedItems.get(j));
                String t = String.format("%s,%s,%s", uid, iid, d);
                user2item2dateArrayList.add(t);
            }
        }
        try (FileWriter writer = new FileWriter(fileLocation + "user2item2date.csv")) {
            // Write header
            writer.write(header);
            writer.write("\n");

            // Write data
            for (String s : user2item2dateArrayList) {
                writer.write(s);
                writer.write("\n");
            }
        }
    }

    public static void writeNewUser(User newUser) throws IOException {
        String newUserLine = newUser.csvFormat();
        try (FileWriter fw = new FileWriter(fileLocation + "users.csv", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(newUserLine);
        } catch (IOException e) {
            throw new IOException("Error writing new user to file", e);
        }
    }

}
