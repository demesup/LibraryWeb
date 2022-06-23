package library.objects;

import library.enums.AvailableActions;
import library.exceptions.ObjectAlreadyExistException;
import library.exceptions.ObjectDoesNotExist;

import java.io.IOException;
import java.util.LinkedList;

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
                    remove();
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

    public void remove() throws IOException, ObjectDoesNotExist {
        if (authors.size() > 0) {
            System.out.println("Enter surname:");
            String surname = READER.readLine();
            int index = findObject(authors, surname);
            if (index == -1) throw new ObjectDoesNotExist("No author with this name");
            authors.remove(index);
            System.out.println("Author is removed");
        } else System.out.println("Empty authors list");
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
