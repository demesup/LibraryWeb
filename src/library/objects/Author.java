package library.objects;

import library.enums.*;
import library.exceptions.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static library.ExtraMethods.*;

public class Author extends LibraryObjects {
    private String firstname;
    private LinkedList<Book> authorsBooks = new LinkedList<>();

    public Author() {
    }

    public Author(String surname, String firstname) {
        super(surname);
        this.firstname = firstname;
    }

    @Override
    public void workWithField(AvailableActions action) throws ObjectAlreadyExistException, IOException, ObjectDoesNotExist {
        try {
            switch (action) {
                case ADD:
                    add();
                    break;
                case REMOVE:
                    remove(authors);
                    break;
                case PRINT:
                    print(authors);
            }
        } catch (ObjectAlreadyExistException | IOException | ObjectDoesNotExist e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("You are in author field");
            workWithField(getAction());
        }
    }

    @Override
    public LibraryObjects remove(List<? extends LibraryObjects> list) throws IOException, ObjectDoesNotExist {
        Author author = (Author) super.remove(list);
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                System.out.println("You need to change author for book " + book);
                Author authorNew = (Author) getFromUser(authors, AvailableObjects.AUTHOR);
                book.setAuthor(authorNew);
                int index = findObject(authors, authorNew.name);
                authors.get(index).addBook(book);
            }
        }
        return null;
    }

    public void add() throws ObjectAlreadyExistException, IOException {
        System.out.println("Enter surname:");
        String surname = READER.readLine();
        int index = findObject(authors, surname);
        if (index != -1) {
            throw new ObjectAlreadyExistException("Author already exist");
        }
        System.out.println("Enter firstname");
        String firstname = READER.readLine();
        Author author = new Author(surname, firstname);
        authors.add(author);
    }

    public void addBook(Book book) {
        authorsBooks.add(book);
    }

    public void removeBook(Book book) {
        authorsBooks.remove(book);
    }

    public String authorsBookEachNextLine() {
        StringBuilder builder = new StringBuilder();
        if (authorsBooks.size() == 1) return String.valueOf(authorsBooks);
        for (Book book : authorsBooks) {
            builder.append("\n\t").append(book);
        }
        return String.valueOf(builder);
    }

    @Override
    public String toString() {
        return "{Surname='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", books:" + authorsBookEachNextLine() + "}";
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LinkedList<Book> getAuthorsBooks() {
        return authorsBooks;
    }

    public void setAuthorsBooks(LinkedList<Book> authorsBooks) {
        this.authorsBooks = authorsBooks;
    }
}
