import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class BookReader implements Serializable {
    private int readerId;
    private List<Book> borrowedBooks;

    public BookReader(int readerId) {
        this.readerId = readerId;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getReaderId() {
        return readerId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    @Override
    public String toString() {
        return "BookReader\n" + "readerId = " + readerId + "\nborrowedBooks =" + borrowedBooks;
    }

}