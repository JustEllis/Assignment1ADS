package assignment1;

public class MemberTest {
    public static void main(String[] args) {
        BookDataFile bookDataFile = new BookDataFile();

        // Creating two book objects
        Member book1 = new Member("Network Security", "Mark Ciampa", "Thomson Course Technology", 2005, "0-619-21566-6");
        Member book2 = new Member("Firewalls and Network Security", "Greg Holden", "Thomson Course Technology", 2005,
                "0-619-13039-3");

        // Saving books to the file
        bookDataFile.saveBook(book1);
        bookDataFile.saveBook(book2);

        // Reading back and displaying books from the file
        bookDataFile.displayBooks();
    }
}
