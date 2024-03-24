
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створення книг, авторів та книжкових шаф
        Author author1 = new Author("John", "Doe");
        Author author2 = new Author("Jane", "Smith");
        Book book1 = new Book("Introduction to Java", List.of(author1));
        Book book2 = new Book("Python Basics", List.of(author2));
        Bookshelf shelf1 = new Bookshelf("Programming");
        shelf1.addBook(book1);
        shelf1.addBook(book2);

        Author author3 = new Author("Adam", "Johnson");
        Book book3 = new Book("History of World", List.of(author3));
        Bookshelf shelf2 = new Bookshelf("History");
        shelf2.addBook(book3);

        // Створення читачів
        BookReader reader1 = new BookReader(1);
        reader1.borrowBook(book1);
        reader1.borrowBook(book3);

        BookReader reader2 = new BookReader(2);
        reader2.borrowBook(book2);

        // Створення бібліотеки та додавання книг, читачів та книжкових шаф
        Library library = new Library("Central Library");
        library.addBookshelf(shelf1);
        library.addBookshelf(shelf2);
        library.addReader(reader1);
        library.addReader(reader2);

        // Виведення інформації про стан бібліотеки
        System.out.println(library);
    }
}
