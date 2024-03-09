package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import com.example.Book;

public class Database {

    // Item database
    // protected static ArrayList<Item> itemDatabase = new ArrayList<>();
    protected static ArrayList<Textbook> textBookDatabase = new ArrayList<>();
    protected static ArrayList<CD> cdDatabase = new ArrayList<>();
    protected static ArrayList<Magazine> magazineDatabase = new ArrayList<>();
    protected static ArrayList<Book> bookDatabase = new ArrayList<>();

    // User database
    // protected static ArrayList<? extends User> userDatabase = new ArrayList<>();
    protected static ArrayList<Student> studentDatabase = new ArrayList<>();
    protected static ArrayList<Visitor> visitorDatabase = new ArrayList<>();
    protected static ArrayList<Faculty> facultyDatabase = new ArrayList<>();
    protected static ArrayList<NonFaculty> nonFacultyDatabase = new ArrayList<>();
    protected static ArrayList<SystemAdmin> adminDatabase = new ArrayList<>();

    public static String fileLocation = "Deliverable2-danChanges\\deliverable\\src\\main\\java\\com\\example\\";



    public static void readData() {
        readItems();
        readUsers();
    }
    
    
    private static void readItems() {
        String line = "";
        String splitBy = ",";

        try {
            // Opening file for reading
            BufferedReader br = new BufferedReader(new FileReader(fileLocation + "items.csv"));
            boolean header = true;
            while ((line = br.readLine()) != null) { // Reads each line
                String[] item = line.split(splitBy); // Use comma as separator
                
                // To skip the header
                if (header) {
                    header = false;
                    continue;
                }
                
                // Parsing and adding items based on type
                String title = item[0];
                int copiesAvailable = Integer.parseInt(item[1]);
                String artist = item[2];
                String author = item[2];
                boolean canBePurchased = Boolean.parseBoolean(item[3]);
                String itemType = item[4];
                double price = Double.parseDouble(item[5]);
                String ISBN = item[6];
                String edition = item[7];
                int courseID = Integer.parseInt(item[8]);
                String issue = "";
                try {issue = item[9];} catch (Exception e) {}
                
                // Title            (0)
                // CopiesAvailable  (1)
                // Artist/Author    (2)
                // CanBePurchased   (3)
                // ItemType         (4)
                // Price            (5)
                // ISBN             (6)
                // Edition          (7)
                // CourseID         (8)
                // Issue            (9)
                
                switch (itemType) {
                    case "TEXTBOOK":
                        Textbook textbook = new Textbook(
                            title, 
                            copiesAvailable, 
                            author, 
                            canBePurchased, 
                            courseID, 
                            ISBN, 
                            edition, 
                            ItemType.TEXTBOOK, 
                            price);
                        textBookDatabase.add(textbook);
                        // itemDatabase.add(textbook);
                        break;
                        
                    case "CD":
                        CD cd = new CD(
                            title, 
                            copiesAvailable, 
                            canBePurchased, 
                            artist, 
                            ItemType.CD, 
                            price);
                            cdDatabase.add(cd);
                            // itemDatabase.add(cd);
                        break;
                        
                    case "MAGAZINE":
                        Magazine magazine = new Magazine(
                            title, 
                            copiesAvailable, 
                            author, 
                            canBePurchased, 
                            issue, 
                            ItemType.MAGAZINE, 
                            price);
                            magazineDatabase.add(magazine);
                            // itemDatabase.add(magazine);
                        break;
                            
                    case "BOOK":
                        Book book = new Book(
                            title, 
                            copiesAvailable, 
                            canBePurchased, 
                            author, 
                            ISBN, 
                            ItemType.BOOK, 
                            price);
                            bookDatabase.add(book);
                            // itemDatabase.add(book);
                        break;
                }
            }
                        
            br.close();
                        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void readUsers() {
        String line = "";
        String splitBy = ",";

        try {
            // Opening file for reading
            BufferedReader br = new BufferedReader(new FileReader(fileLocation + "users.csv"));
            boolean header = true;
            while ((line = br.readLine()) != null) { // Reads each line
                String[] item = line.split(splitBy); // Use comma as separator
                
                // To skip the header
                if (header) {
                    header = false;
                    continue;
                }
                
                // Parsing and adding items based on type
                int userID = Integer.parseInt(item[0]);
                String userName = item[1];
                String email = item[2];
                String userType = item[3];
                boolean isVerified = Boolean.parseBoolean(item[4]);
                double accountBalance = Double.parseDouble(item[5]);
                
                // userID      (0)
                // userName    (1)
                // email       (2)
                // userType    (3)
                // isVerified  (4)

                switch (userType) {
                    case "STUDENT":
                        Student student = new Student(
                            userID, 
                            userName, 
                            email, 
                            UserType.STUDENT, 
                            isVerified, 
                            userID,
                            accountBalance);
                        studentDatabase.add(student);
                        break;

                    case "FACULTY":
                        Faculty faculty = new Faculty(
                            userID, 
                            userName, 
                            email, 
                            UserType.FACULTY, 
                            isVerified,
                            accountBalance);
                        facultyDatabase.add(faculty);
                        break;
                        
                    case "NON-FACULTY":
                        NonFaculty nonFaculty = new NonFaculty(
                            userID, 
                            userName, 
                            email, 
                            UserType.NON_FACULTY, 
                            isVerified,
                            accountBalance);
                        nonFacultyDatabase.add(nonFaculty);
                        break;
                        
                    case "VISITOR":
                        Visitor visitor = new Visitor(
                            userID, 
                            userName, 
                            email, 
                            UserType.VISITOR, 
                            isVerified,
                            accountBalance);
                        visitorDatabase.add(visitor);
                        break;
                        
                    case "ADMIN":
                        SystemAdmin admin = new SystemAdmin(
                            userID, 
                            userName, 
                            email, 
                            UserType.ADMIN, 
                            isVerified,
                            accountBalance);
                        adminDatabase.add(admin);
                        break;
                }
            }
                        
            br.close();
                        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void writeItemData() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation + "items.csv"));

            // Write headers
            bw.write("Title,CopiesAvailable,Artist/Author,CanBePurchased,ItemType,Price,ISBN,Edition,CourseID,Issue\n");

            // Write Textbook entries
            for (Textbook t : textBookDatabase) bw.write(t.toStringCSV() + "\n");

            // Write CD entries
            for (CD cd : cdDatabase) bw.write(cd.toStringCSV() + "\n");
            
            // Write Magazine entries
            for (Magazine m : magazineDatabase) bw.write(m.toStringCSV() + "\n");

            // Write Book entries
            for (Book b : bookDatabase) bw.write(b.toStringCSV() + "\n");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    


    public static void writeUserData() {
        try {
            fileLocation = "Deliverable2-danChanges\\deliverable\\src\\main\\java\\com\\example\\users.csv";
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation));

            // Write headers
            bw.write("userID,userName,email,userType,isVerified,accountBalance\n");
            
            // Write Student entries
            for (Student s : studentDatabase) bw.write(s.toStringCSV() + "\n");
            
            // Write Faculty entries
            for (Faculty f : facultyDatabase) bw.write(f.toStringCSV() + "\n");
            
            // Write NonFaculty entries
            for (NonFaculty nf : nonFacultyDatabase) bw.write(nf.toStringCSV() + "\n");

            // Write Visitor entries
            for (Visitor v : visitorDatabase) bw.write(v.toStringCSV() + "\n");
            
            // Write SystemAdmin entries
            for (SystemAdmin a : adminDatabase) bw.write(a.toStringCSV() + "\n");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static ArrayList<Item> searchItem(String pattern, int numOfResults) {
        ArrayList<Item> retval = new ArrayList<>();
        ArrayList<Item> allItems = new ArrayList<>();

        for (Item item : textBookDatabase) allItems.add(item);
        for (Item item : bookDatabase) allItems.add(item);
        for (Item item : cdDatabase) allItems.add(item);
        for (Item item : magazineDatabase) allItems.add(item);
        
        System.out.println("Items found that match: '" + pattern + "'.");
        for (Item item : allItems) {
            String id = String.valueOf(item.itemID); 
            if (id==null) id="";
            String price = String.valueOf(item.price); 
            if (price==null) price="";
            String title = item.title; 
            if (title==null) title="";
            String itemType = String.valueOf(item.itemType); 
            if (itemType==null) itemType="";
            String copiesAvailable = String.valueOf(item.copiesAvailable); 
            if (copiesAvailable==null) copiesAvailable="";
            String canBoPerchased = String.valueOf(item.canBePurchased); 
            if (canBoPerchased==null) canBoPerchased="";

            if (numOfResults > 0) {
                if (id.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(item.toStringCSV());
                    retval.add(item);
                    numOfResults--;
                } else 
                if (price.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(item.toStringCSV());
                    retval.add(item);
                    numOfResults--;
                } else 
                if (title.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(item.toStringCSV());
                    retval.add(item);
                    numOfResults--;
                } else 
                if (itemType.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(item.toStringCSV());
                    retval.add(item);
                    numOfResults--;
                } else 
                if (copiesAvailable.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(item.toStringCSV());
                    retval.add(item);
                    numOfResults--;
                } else 
                if (canBoPerchased.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(item.toStringCSV());
                    retval.add(item);
                    numOfResults--;
                }
            }
        }

        return retval;
    }


    public static ArrayList<User> searchUser(String pattern, int numOfResults) {
        ArrayList<User> retval = new ArrayList<>();
        ArrayList<User> allUsers = new ArrayList<>();

        for (User user : studentDatabase) allUsers.add(user);
        for (User user : facultyDatabase) allUsers.add(user);
        for (User user : nonFacultyDatabase) allUsers.add(user);
        for (User user : adminDatabase) allUsers.add(user);
        for (User user : visitorDatabase) allUsers.add(user);

        System.out.println("Users found that match: '" + pattern + "'.");
        for (User user : allUsers) {
            String id = String.valueOf(user.getUserID()); 
            if (id==null) id="";
            String userName = user.getUserName(); 
            if (userName==null) userName="";
            String email = user.getEmail(); 
            if (email==null) email="";
            String password = user.getPassword(); 
            if (password==null) password="";
            String usertype = String.valueOf(user.getUserType()); 
            if (usertype==null) usertype="";
            String isVerified = String.valueOf(user.isVerified()); 
            if (isVerified==null) isVerified="";
            String paymentType = String.valueOf(user.getPaymentType()); 
            if (paymentType==null) paymentType="";
            String accountBalance = String.valueOf(user.getAccountBalance()); 
            if (accountBalance==null) accountBalance="";

            if (numOfResults > 0) {
                if (id.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(user.toStringCSV());
                    retval.add(user);
                    numOfResults--;
                } else 
                if (userName.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(user.toStringCSV());
                    retval.add(user);
                    numOfResults--;
                } else 
                if (email.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(user.toStringCSV());
                    retval.add(user);
                    numOfResults--;
                } else 
                if (password.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(user.toStringCSV());
                    retval.add(user);
                    numOfResults--;
                } else 
                if (usertype.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(user.toStringCSV());
                    retval.add(user);
                    numOfResults--;
                } else 
                if (isVerified.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(user.toStringCSV());
                    retval.add(user);
                    numOfResults--;
                } else 
                if (paymentType.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(user.toStringCSV());
                    retval.add(user);
                    numOfResults--;
                } else 
                if (accountBalance.toLowerCase().contains(pattern.toLowerCase())) {
                    System.out.println(user.toStringCSV());
                    retval.add(user);
                    numOfResults--;
                }
            }
        }

        return retval;
    }





//////////////////////////////  TESTING  //////////////////////////////
    public static void main(String[] args) {
        readData();

        // TESTING ITEMS
        for (Textbook t : textBookDatabase) {
            System.out.println(t.toStringCSV());
        }
        for (CD t : cdDatabase) {
            System.out.println(t.toStringCSV());
        }
        for (Magazine t : magazineDatabase) {
            System.out.println(t.toStringCSV());
        }
        for (Book t : bookDatabase) {
            System.out.println(t.toStringCSV());
        }
        
        for (Textbook t : textBookDatabase) {
            t.setCopiesAvailable(t.getCopiesAvailable() + 1);
        }  
        writeItemData();

        // TESTING USERS
        System.out.println("\n\n");
        for (Student t : studentDatabase) {
            System.out.println(t.toStringCSV());
        }
        for (Faculty t : facultyDatabase) {
            System.out.println(t.toStringCSV());
        }
        for (NonFaculty t : nonFacultyDatabase) {
            System.out.println(t.toStringCSV());
        }
        for (Visitor t : visitorDatabase) {
            System.out.println(t.toStringCSV());
        }
        for (SystemAdmin t : adminDatabase) {
            System.out.println(t.toStringCSV());
        }
        
        for (Visitor t : visitorDatabase) {
            // Flip the bool value of the VISITORS's verification
            t.setVerification(!t.isVerified());
        }
        writeUserData();

        System.out.println("\n\n");
        searchItem("tion", 4);
        System.out.println("\n");
        searchUser("stu", 4);

    }
}
