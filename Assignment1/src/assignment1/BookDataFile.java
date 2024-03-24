package assignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BookDataFile {
    public void saveBook(Member book) {
        try (BufferedWriter bookData = new BufferedWriter(new FileWriter("books.txt", true))) {
            bookData.write("Book title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() +
                    "\nPublisher: " + book.getPublisher() + "\nYear: " + book.getYear() +
                    "\nISBN: " + book.getIsbn() + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            System.out.println("No more records...\n");

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
