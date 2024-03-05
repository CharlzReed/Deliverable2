package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.example.Book;

public class Database {

    protected static ArrayList<Textbook> textBookDatabase = new ArrayList<>();
    protected static ArrayList<CD> cdDatabase = new ArrayList<>();
    protected static ArrayList<Magazine> magazineDatabase = new ArrayList<>();
    protected static ArrayList<Book> bookDatabase = new ArrayList<>();

    protected static ArrayList<User> userDatabase = new ArrayList<>();

    public static String fileLocation = "items.csv"; // Adjust this path

    public static void readData() {
        readItems();
    }

    private static void readItems() {
        String line = "";
        String splitBy = ","; // Assuming CSV values are separated by commas
        try {
            // Opening file for reading
            BufferedReader br = new BufferedReader(
                    new FileReader("deliverable\\src\\main\\java\\com\\example\\items.csv"));
            while ((line = br.readLine()) != null) { // Reads each line
                String[] item = line.split(splitBy); // Use comma as separator

                // Parsing and adding items based on type
                String itemType = item[4]; // Assuming the itemType is at index 4
                switch (itemType) {
                    case "TEXTBOOK":
                        Textbook textbook = new Textbook(item[0], Integer.parseInt(item[1]), item[2],
                                Boolean.parseBoolean(item[3]), Integer.parseInt(item[8]), item[7], item[8],
                                ItemType.TEXTBOOK, Double.parseDouble(item[5]));
                        textBookDatabase.add(textbook);
                        break;
                    case "CD":
                        CD cd = new CD(item[0], Integer.parseInt(item[1]), Boolean.parseBoolean(item[3]), item[2],
                                ItemType.CD, Double.parseDouble(item[5]));
                        cdDatabase.add(cd);
                        break;
                    case "MAGAZINE":
                        Magazine magazine = new Magazine(item[0], Integer.parseInt(item[1]),
                                item[2], Boolean.parseBoolean(item[3]), item[9], ItemType.MAGAZINE,
                                Double.parseDouble(item[5]));
                        magazineDatabase.add(magazine);
                        break;

                    case "BOOK":
                        Book book = new Book(item[0], Integer.parseInt(item[1]), Boolean.parseBoolean(item[3]), item[2],
                                item[6], ItemType.BOOK, Double.parseDouble(item[5]));
                        bookDatabase.add(book);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public static void writeData() {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("deliverable\\src\\main\\java\\com\\example\\items.csv"));

            // Write headers
            bw.write("Title,CopiesAvailable,Artist/Author,CanBePurchased,ItemType,Price,ISBN,Edition,CourseID,Issue\n");

            // Write Textbook entries
            for (Textbook textbook : textBookDatabase) {
                bw.write(textbook.toStringCSV() + "\n");
            }

            // Write CD entries
            for (CD cd : cdDatabase) {
                bw.write(cd.toStringCSV() + "\n");
            }

            // Write Magazine entries
            for (Magazine magazine : magazineDatabase) {
                bw.write(magazine.toStringCSV() + "\n");
            }

            // Write Book entries
            for (Book book : bookDatabase) {
                bw.write(book.toStringCSV() + "\n");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readData();
        for (Textbook t : textBookDatabase) {
            System.out.println(t.getTitle() + ", " + t.getAuthor());
        }
        for (CD t : cdDatabase) {
            System.out.println(t.getTitle() + ", " + t.getArtist());
        }
        for (Magazine t : magazineDatabase) {
            System.out.println(t.getTitle() + ", " + t.getIssue());
        }
        for (Book t : bookDatabase) {
            System.out.println(t.getTitle() + ", " + t.getISBN());
        }

        for (Textbook t : textBookDatabase) {
            t.setCopiesAvailable(t.getCopiesAvailable() + 1);
        }
        writeData();
    }
}
