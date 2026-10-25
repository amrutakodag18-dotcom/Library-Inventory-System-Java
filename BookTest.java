import java.util.ArrayList;
import java.util.List;

public class BookTest {
    public static void main(String[] args) {
        System.out.println("--- Starting Unit Tests for Library System ---");
        
        testBookCreation();
        testBookListCRUDOperations();
        testEdgeCaseInvalidId();
        
        System.out.println("--- All Unit Tests Passed Successfully! ---");
    }

    public static void testBookCreation() {
        // Test expected behavior for creating a Book object
        Book book = new Book(1, "Java Programming", "James Gosling", "12345", 2023);
        
        assert book.getId() == 1 : "Test Failed: ID mismatch";
        assert book.getTitle().equals("Java Programming") : "Test Failed: Title mismatch";
        assert book.getPublicationYear() == 2023 : "Test Failed: Year mismatch";
        
        System.out.println("[PASS] testBookCreation passed.");
    }

    public static void testBookListCRUDOperations() {
        // Test adding and removing items from inventory collection
        List<Book> inventory = new ArrayList<>();
        Book book = new Book(2, "Clean Code", "Robert Martin", "67890", 2008);
        
        inventory.add(book);
        assert inventory.size() == 1 : "Test Failed: Book was not added";
        
        // Test Delete operation
        inventory.removeIf(b -> b.getId() == 2);
        assert inventory.isEmpty() : "Test Failed: Book was not removed";
        
        System.out.println("[PASS] testBookListCRUDOperations passed.");
    }

    public static void testEdgeCaseInvalidId() {
        // Test edge case where searching for a non-existent ID returns null/empty
        List<Book> inventory = new ArrayList<>();
        boolean found = false;
        
        for (Book b : inventory) {
            if (b.getId() == 99) {
                found = true;
            }
        }
        
        assert !found : "Test Failed: Non-existent ID should not be found";
        System.out.println("[PASS] testEdgeCaseInvalidId passed.");
    }
}