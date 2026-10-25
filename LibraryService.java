import java.util.ArrayList;
import java.util.List;

/**
 * Service class handling all business logic and collection operations (Separation of Concerns).
 */
public class LibraryService {
    private List<Book> bookList = new ArrayList<>();
    private int nextId = 1;

    public void addBook(String title, String author, String isbn, int year) {
        Book newBook = new Book(nextId++, title, author, isbn, year);
        bookList.add(newBook);
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public boolean updateBook(int id, String newTitle, String newAuthor) {
        for (Book b : bookList) {
            if (b.getId() == id) {
                if (b.getTitle() != null && !newTitle.isEmpty()) b.setTitle(newTitle);
                if (b.getAuthor() != null && !newAuthor.isEmpty()) b.setAuthor(newAuthor);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(int id) {
        return bookList.removeIf(b -> b.getId() == id);
    }
}