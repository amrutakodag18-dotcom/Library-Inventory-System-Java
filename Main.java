import java.util.List;
import java.util.Scanner;

/**
 * Main presentation class handling user interactions via command line.
 */
public class Main {
    public static void main(String[] args) {
        LibraryService service = new LibraryService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Refactored Library Management System ===");

        while (running) {
            System.out.println("\n1. Add Book\n2. View Books\n3. Update Book\n4. Delete Book\n5. Exit");
            System.out.print("Select an option (1-5): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Year: ");
                    try {
                        int year = Integer.parseInt(scanner.nextLine());
                        service.addBook(title, author, isbn, year);
                        System.out.println("-> Success: Book added.");
                    } catch (NumberFormatException e) {
                        System.out.println("-> Error: Invalid year format.");
                    }
                    break;
                case "2":
                    List<Book> books = service.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("-> Inventory is empty.");
                    } else {
                        books.forEach(System.out::println);
                    }
                    break;
                case "3":
                    System.out.print("Enter Book ID to update: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("New Title (press enter to skip): ");
                        String newTitle = scanner.nextLine();
                        System.out.print("New Author (press enter to skip): ");
                        String newAuthor = scanner.nextLine();
                        
                        if (service.updateBook(id, newTitle, newAuthor)) {
                            System.out.println("-> Success: Book updated.");
                        } else {
                            System.out.println("-> Error: Book ID not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("-> Error: Invalid ID format.");
                    }
                    break;
                case "4":
                    System.out.print("Enter Book ID to delete: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        if (service.deleteBook(id)) {
                            System.out.println("-> Success: Book deleted.");
                        } else {
                            System.out.println("-> Error: Book ID not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("-> Error: Invalid ID format.");
                    }
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("-> Invalid choice. Enter 1-5.");
            }
        }
        scanner.close();
    }
}