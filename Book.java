import java.io.Serializable;
import java.util.List;

class Book implements Serializable {
    private String title;
    private List<Author> authors;

    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "\nBook" + "\ntitle = " + title  + "\nauthor = " + authors;
    }
}