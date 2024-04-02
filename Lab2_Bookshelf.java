import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Bookshelf implements Serializable {
    private String name;
    private List<Book> books;

    public Bookshelf(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "Bookshelf" + "\nname='" + name + '\'' + "\nbooks=" + books;
    }
}
