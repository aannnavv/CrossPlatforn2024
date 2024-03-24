import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Library implements Serializable {
    private String name;
    private List<Bookshelf> bookshelves;
    private List<BookReader> readers;

    public Library(String name) {
        this.name = name;
        this.bookshelves = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addBookshelf(Bookshelf bookshelf) {
        bookshelves.add(bookshelf);
    }

    public void addReader(BookReader reader) {
        readers.add(reader);
    }

    public List<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public List<BookReader> getReaders() {
        return readers;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Library Information\n");
        stringBuilder.append("-------------------\n\n");

        // Додамо інформацію про книги
        stringBuilder.append("Books:\n");
        stringBuilder.append("------\n");
        int bookIndex = 1;
        for (Bookshelf shelf : bookshelves) {
            for (Book book : shelf.getBooks()) {
                stringBuilder.append("Book ").append(bookIndex).append(": ").append(book).append("\n");
                bookIndex++;
            }
        }
        stringBuilder.append("\n");

        // Додамо інформацію про читачів
        stringBuilder.append("Readers:\n");
        stringBuilder.append("--------\n");
        int readerIndex = 1;
        for (BookReader reader : readers) {
            stringBuilder.append("Reader \n").append(readerIndex).append(": ").append(reader).append("\n");
            readerIndex++;
        }

        return stringBuilder.toString();
    }

}
