import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibrarySystem {
    //Book class
    static class Book {
        private String id;
        private String title;
        private String author;
        private boolean isIssued;

        public Book(String id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public String getId() {
            return id;
        }

        public boolean isIssued() {
            return isIssued;
        }

        public void issue() {
            isIssued = true;
        }

        public void returnBook() {
            isIssued = false;
        }

        public String toString() {
            return title + " by " + author + " (ID: " + id + ") - " + (isIssued ? "Issued" : "Available");
        }
    }

    //user class
    static class User {
        private String userId;
        private String name;

        public User(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        public String getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return name + " (User ID: " + userId + ")";
        }
    }

    //Library class
    static class Library {
        private Map<String, Book> books = new HashMap<>();
        private Map<String, User> users = new HashMap<>();
        private Map<String, String> issuedBooks = new HashMap<>();

        public void addBook(Book book) {
            books.put(book.getId(), book);
        }

        public void registerUser(User user) {
            users.put(user.getUserId(), user);
        }

        public boolean issueBook(String bookId, String userId) {
            Book book = books.get(bookId);
            User user = users.get(userId);

            if(book == null || user == null) {
                System.out.println("Invalid book or user ID.");
                return false;
            }

            if(book.isIssued()) {
                System.out.println("Book already Issued.");
                return false;
            }

            book.issue();
            issuedBooks.put(bookId, userId);
            System.out.println("Book '" + book.getId() + "' issued to " + user.getName());
            return true;
        }

        public boolean returnBook(String bookId) {
            Book book = books.get(bookId);

            if(book == null) {
                System.out.println("Invalid book ID.");
                return false;
            }

            if(!book.isIssued()) {
                System.out.println("Book is not currently issued.");
                return false;
            }

            book.returnBook();
            issuedBooks.remove(bookId);
            System.out.println("Book '" + book.getId() + "' returned.");
            return true;
        }

        public void listBooks() {
            System.out.println("--- Books in Library ---");
            for(Book book : books.values()){
                System.out.println(book);
            }
        }

        public void listUsers() {
            System.out.println("--- Registered Users ---");
            for(User user : users.values()) {
                System.out.println(user);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Library System ---");
            System.out.println("1. Add a Book");
            System.out.println("2. Register a User");
            System.out.println("3. List Books");
            System.out.println("4. List Users");
            System.out.println("5. Issue a Book");
            System.out.println("6. Return a Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Add a Book
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    System.out.println("Book added successfully!");
                    break;

                case 2: // Register a User
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter User Name: ");
                    String name = scanner.nextLine();
                    library.registerUser(new User(userId, name));
                    System.out.println("User registered successfully!");
                    break;

                case 3: // List Books
                    library.listBooks();
                    break;

                case 4: // List Users
                    library.listUsers();
                    break;

                case 5: // Issue a Book
                    System.out.print("Enter Book ID to issue: ");
                    String bookIdToIssue = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String userIdToIssue = scanner.nextLine();
                    library.issueBook(bookIdToIssue, userIdToIssue);
                    break;

                case 6: // Return a Book
                    System.out.print("Enter Book ID to return: ");
                    String bookIdToReturn = scanner.nextLine();
                    library.returnBook(bookIdToReturn);
                    break;

                case 7: // Exit
                    running = false;
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
