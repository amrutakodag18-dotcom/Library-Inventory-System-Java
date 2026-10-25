import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static List<Book> bookList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Library Inventory Management System ---");
            System.out.println("1. Add Book (Create)");
            System.out.println("2. View All Books (Read)");
            System.out.println("3. Update Book (Update)");
            System.out.println("4. Delete Book (Delete)");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    viewBooks();
                    break;
                case "3":
                    updateBook();
                    break;
                case "4":
                    deleteBook();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        
        int year = 0;
        while (true) {
            System.out.print("Enter Publication Year: ");
            try {
                year = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year! Please enter numbers only.");
            }
        }

        Book newBook = new Book(nextId++, title, author, isbn, year);
        bookList.add(newBook);
        System.out.println("Success: Book added successfully!");
    }

    private static void viewBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books available in the inventory.");
            return;
        }
        System.out.println("\n--- Book Inventory List ---");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    private static void updateBook() {
        viewBooks();
        if (bookList.isEmpty()) return;

        System.out.print("Enter the Book ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book targetBook = null;
            for (Book b : bookList) {
                if (b.getId() == id) {
                    targetBook = b;
                    break;
                }
            }

            if (targetBook != null) {
                System.out.print("Enter new Title (or press enter to keep '" + targetBook.getTitle() + "'): ");
                String title = scanner.nextLine();
                if (!title.isEmpty()) targetBook.setTitle(title);

                System.out.print("Enter new Author (or press enter to keep '" + targetBook.getAuthor() + "'): ");
                String author = scanner.nextLine();
                if (!author.isEmpty()) targetBook.setAuthor(author);

                System.out.println("Success: Book updated successfully!");
            } else {
                System.out.println("Error: Book with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private static void deleteBook() {
        viewBooks();
        if (bookList.isEmpty()) return;

        System.out.print("Enter the Book ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean removed = bookList.removeIf(b -> b.getId() == id);
            if (removed) {
                System.out.println("Success: Book deleted successfully!");
            } else {
                System.out.println("Error: Book ID not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }
}