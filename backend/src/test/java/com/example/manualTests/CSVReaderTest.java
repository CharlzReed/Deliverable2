package com.example.manualTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.example.*;
// import com.example.Course;
// import com.example.Item;
// import com.example.ItemType;
// import com.example.Library;
// import com.example.LocationType;
// import com.example.StatusType;
// import com.example.User;
// import com.example.UserType;

public class CSVReaderTest {

    public static String fileLocation = "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "databaseFiles" + File.separator;

    
    @Test
    public void testReadItems() {

        Library lib = Library.newInstance(); // FRESH LIBRARY

        CSVReader.readALL(); // FILL LIBRARY

        ArrayList<Integer> expectedIDs = new ArrayList<>(
            Arrays.asList(3, 4, 5, 1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013, 1014, 3148, 1)
        );

        ArrayList<Item> fromLib = lib.getItems(); // SHOULD NOT BE EMPTY

        // Compare expected with actual results and return if they are the same
        boolean works = true;
        for (int i = 0; i < fromLib.size(); i++) {
            if (fromLib.get(i).itemID != expectedIDs.get(i)) {
                works = false;
            }
        }
        
        assertTrue(works);
    }


    
    @Test
    public void testReadUsers() throws IOException {
        Library lib = Library.newInstance();

        ArrayList<Integer> expectedIDs = new ArrayList<>(
            Arrays.asList(2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2593, 9978, 7699, 6116, 3022, 9039, 4334, 9048)
        );

        CSVReader.readALL();

        boolean works = true;
        for (int i = 0; i < Library.getInstance().getUsers().size(); i++) {
            if (Library.getInstance().getUsers().get(i).getUserID() != expectedIDs.get(i)) {
                works = false;
            }
        }

        assertTrue(works);
    }



    @Test
    public void testreadCourses() throws IOException {
        Library lib = Library.newInstance(); // FRESH LIBRARY

        ArrayList<Integer> expectedIDs = new ArrayList<>(
            Arrays.asList(3000, 3001, 3002)
        );

        CSVReader.readALL();

        boolean works = true;
        for (int i = 0; i < Library.getInstance().getCourses().size(); i++) {
            if (Library.getInstance().getCourses().get(i).courseID != expectedIDs.get(i)) {
                works = false;
            }
        }

        assertTrue(works);
    }



    @Test
    public void testreadCourse2textbook() throws IOException {
        Library lib = Library.newInstance(); // FRESH LIBRARY

        HashMap<Integer, Integer> expectedHashMap = new HashMap<>();
        expectedHashMap.put(3000, 1012);
        expectedHashMap.put(3001, 1013);
        expectedHashMap.put(3002, 1014);

        CSVReader.readALL();

        boolean works = true;
        for (int i = 0; i < Library.getInstance().getCourses().size(); i++) {
            Course c = Library.getInstance().getCourses().get(i);
            Item e = Library.getInstance().getCourse2textbook().get(c);
            if (expectedHashMap.get(c.courseID) != e.itemID) {
                works = false;
            }
        }

        assertTrue(works);
    }



    @Test
    public void testreadUser2item() throws IOException {
        Library lib = Library.newInstance(); // FRESH LIBRARY

        HashMap<Integer, ArrayList<Integer>> expectedHashMap = new HashMap<>();

        expectedHashMap.put(2000, new ArrayList<>()); expectedHashMap.put(2001, new ArrayList<>());
        expectedHashMap.put(2002, new ArrayList<>()); expectedHashMap.put(2003, new ArrayList<>());
        expectedHashMap.put(2004, new ArrayList<>()); expectedHashMap.put(2005, new ArrayList<>());
        expectedHashMap.put(2006, new ArrayList<>()); expectedHashMap.put(2007, new ArrayList<>());
        expectedHashMap.put(2008, new ArrayList<>()); expectedHashMap.put(9978, new ArrayList<>());

        expectedHashMap.get(2000).add(1006); expectedHashMap.get(2000).add(1007);
        expectedHashMap.get(2000).add(1000); expectedHashMap.get(2000).add(1000);
        expectedHashMap.get(2001).add(1000); expectedHashMap.get(2001).add(1001);
        expectedHashMap.get(2001).add(1002); expectedHashMap.get(2001).add(1011);
        expectedHashMap.get(2002).add(1012); expectedHashMap.get(2002).add(1013);
        expectedHashMap.get(2002).add(1014); expectedHashMap.get(2003).add(1003);
        expectedHashMap.get(2003).add(1004); expectedHashMap.get(2003).add(1005);
        expectedHashMap.get(2004).add(1008); expectedHashMap.get(2005).add(1006);
        expectedHashMap.get(2005).add(1007); expectedHashMap.get(2006).add(1000);
        expectedHashMap.get(2006).add(1001); expectedHashMap.get(2006).add(1002);
        expectedHashMap.get(2007).add(1012); expectedHashMap.get(2007).add(1013);
        expectedHashMap.get(2007).add(1014); expectedHashMap.get(2008).add(1003);
        expectedHashMap.get(2008).add(1004); expectedHashMap.get(2008).add(1005);
        expectedHashMap.get(2008).add(1006); expectedHashMap.get(2008).add(1007);
        expectedHashMap.get(2008).add(1008); expectedHashMap.get(2008).add(1012);
        expectedHashMap.get(9978).add(1014);

        CSVReader.readALL();

        boolean works = true;
        for (int i = 0; i < Library.getInstance().getUsers().size(); i++) {
            User u = Library.getInstance().getUsers().get(i);
            ArrayList<Item> items = u.getRentedItems();
            for (Item item : items) {
                if (!(expectedHashMap.get(u.getUserID()).contains(item.itemID))) {
                    works = false;
                }
            }
        }
        assertTrue(works);
    }



    @Test
    public void testreadUser2course() throws IOException {
        Library lib = Library.newInstance(); // FRESH LIBRARY

        HashMap<Integer, ArrayList<Integer>> expectedHashMap = new HashMap<>();

        expectedHashMap.put(2002, new ArrayList<>()); expectedHashMap.put(2003, new ArrayList<>());
        expectedHashMap.put(2004, new ArrayList<>()); expectedHashMap.put(2005, new ArrayList<>());

        expectedHashMap.get(2002).add(3000); expectedHashMap.get(2002).add(3001);
        expectedHashMap.get(2002).add(3002); expectedHashMap.get(2003).add(3000);
        expectedHashMap.get(2003).add(3001); expectedHashMap.get(2003).add(3002);
        expectedHashMap.get(2004).add(3000); expectedHashMap.get(2004).add(3001);
        expectedHashMap.get(2005).add(3002);

        CSVReader.readALL();

        boolean works = true;
        for (int i = 0; i < Library.getInstance().getUsers().size(); i++) {
            User u = Library.getInstance().getUsers().get(i);
            ArrayList<Course> courses = u.getCourses();
            for (Course course : courses) {
                if (!(expectedHashMap.get(u.getUserID()).contains(course.courseID))) {
                    works = false;
                }
            }
        }
        assertTrue(works);
    }



    @Test
    public void testreadU2I2D() throws IOException {
        Library lib = Library.newInstance(); // FRESH LIBRARY

        HashMap<Integer,LocalDate> expectedHashMap = new HashMap<>();

        expectedHashMap.put(1006,LocalDate.parse("2024-03-08")); expectedHashMap.put(1007,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1000,LocalDate.parse("2024-03-21")); expectedHashMap.put(1000,LocalDate.parse("2024-03-21"));
        expectedHashMap.put(1000,LocalDate.parse("2024-03-08")); expectedHashMap.put(1001,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1002,LocalDate.parse("2024-03-08")); expectedHashMap.put(1011,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1012,LocalDate.parse("2024-03-08")); expectedHashMap.put(1013,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1014,LocalDate.parse("2024-03-08")); expectedHashMap.put(1003,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1004,LocalDate.parse("2024-03-08")); expectedHashMap.put(1005,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1008,LocalDate.parse("2024-03-08")); expectedHashMap.put(1006,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1007,LocalDate.parse("2024-03-08")); expectedHashMap.put(1000,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1001,LocalDate.parse("2024-03-08")); expectedHashMap.put(1002,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1012,LocalDate.parse("2024-03-08")); expectedHashMap.put(1013,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1014,LocalDate.parse("2024-03-08")); expectedHashMap.put(1003,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1004,LocalDate.parse("2024-03-08")); expectedHashMap.put(1005,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1006,LocalDate.parse("2024-03-08")); expectedHashMap.put(1007,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1008,LocalDate.parse("2024-03-08")); expectedHashMap.put(1012,LocalDate.parse("2024-03-08"));
        expectedHashMap.put(1014,LocalDate.parse("2024-03-20"));

        CSVReader.readALL();

        boolean works = true;
        for (int i = 0; i < Library.getInstance().getUsers().size(); i++) {
            User u = Library.getInstance().getUsers().get(i);
            HashMap<Item, LocalDate> rentLog = u.getRentLog();
            ArrayList<Item> items = u.getRentedItems();
            for (Item item : items) {
                if (expectedHashMap.get(item.itemID) != null && rentLog.get(item) != null) {
                    works = false;
                }
            }
        }
        assertTrue(works);
    }



    private static String getFileContent(String fileName) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            return contentBuilder.toString();
        }
    }



    @Test
    public void testwriteObjectArrayToFile_items() throws IOException {
        String fileName = "items.csv";
        Library lib = Library.newInstance(); // FRESH LIBRARY
        CSVReader.readALL();

        String fileContentBeforeWriting = getFileContent(fileName);
        CSVReader.writeALL();
        String fileContentAfterWriting = getFileContent(fileName);

        boolean works = true;
        if (!(fileContentBeforeWriting.equals(fileContentAfterWriting))) {
            works = false;
        }
        assertTrue(works);
    }



    @Test
    public void testwriteObjectArrayToFile_users() throws IOException {
        String fileName = "users.csv";
        Library lib = Library.newInstance(); // FRESH LIBRARY
        CSVReader.readALL();

        String fileContentBeforeWriting = getFileContent(fileName);
        CSVReader.writeALL();
        String fileContentAfterWriting = getFileContent(fileName);

        boolean works = true;
        if (!(fileContentBeforeWriting.equals(fileContentAfterWriting))) {
            works = false;
        }
        assertTrue(works);
    }



    @Test
    public void testwriteObjectArrayToFile_courses() throws IOException {
        String fileName = "courses.csv";
        Library lib = Library.newInstance(); // FRESH LIBRARY
        CSVReader.readALL();

        String fileContentBeforeWriting = getFileContent(fileName);
        CSVReader.writeALL();
        String fileContentAfterWriting = getFileContent(fileName);

        boolean works = true;
        if (!(fileContentBeforeWriting.equals(fileContentAfterWriting))) {
            works = false;
        }
        assertTrue(works);
    }


    @Test
    public void testwriteAssociations() throws IOException {
        String fileName1 = "user2item.csv";
        String fileName2 = "user2course.csv";
        Library lib = Library.newInstance(); // FRESH LIBRARY
        CSVReader.readALL();

        String fileContentBeforeWriting1 = getFileContent(fileName1);
        String fileContentBeforeWriting2 = getFileContent(fileName2);
        CSVReader.writeALL();
        String fileContentAfterWriting1 = getFileContent(fileName1);
        String fileContentAfterWriting2 = getFileContent(fileName2);

        boolean works = false;

        // Both of these should be True
        boolean condition1 = fileContentBeforeWriting1.equals(fileContentAfterWriting1);
        boolean condition2 = fileContentBeforeWriting2.equals(fileContentAfterWriting2);
        if (condition1 == true && condition2 == true) {
            works = true;
        }

        assertTrue(works);
    }



    @Test
    public void testwriteU2I2D() throws IOException {
        String fileName = "user2item2date.csv";
        Library lib = Library.newInstance(); // FRESH LIBRARY
        CSVReader.readALL();

        String fileContentBeforeWriting = getFileContent(fileName);
        CSVReader.writeALL();
        String fileContentAfterWriting = getFileContent(fileName);

        boolean works = false; // Should end up being true

        // Both of these should be True
        boolean condition1 = fileContentBeforeWriting.equals(fileContentAfterWriting);
        if (condition1 == true) {
            works = true;
        }

        assertTrue(works);
    }
}
