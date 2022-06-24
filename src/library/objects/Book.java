package library.objects;

import library.enums.AvailableActions;
import library.enums.AvailableObjects;
import library.exceptions.ObjectAlreadyExistException;
import library.exceptions.ObjectDoesNotExist;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static library.ExtraMethods.*;

public class Book extends LibraryObjects {
    private Author author;
    private String description;
    private int year;
    private Genre genre;

    public Book() {
    }

    public Book(String name, String description, int year, Genre genre, Author author) {
        super(name);
        this.description = description;
        this.year = year;
        this.genre = genre;
        this.author = author;
    }

    @Override
    public String toString() {
        return "{Title='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", genre=" + genre + '}';
    }

    @Override
    public void workWithField(AvailableActions action) throws ObjectAlreadyExistException, IOException, ObjectDoesNotExist {
        try {
            switch (action) {
                case ADD:
                    add();
                    break;
                case REMOVE:
                    remove(books);
                    break;
                case PRINT:
                    print(books);
            }
        } catch (ObjectAlreadyExistException | ObjectDoesNotExist | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("You are in book field");
            workWithField(getAction());
        }
    }

    @Override
    public LibraryObjects remove(List<? extends LibraryObjects> list) throws IOException, ObjectDoesNotExist {
        Book book = (Book) super.remove(list);
        String authorName = book.getAuthor().name;
        int index = findObject(authors, authorName);
        authors.get(index).removeBook(book);
        return null;
    }

    @Override
    public void add() throws ObjectAlreadyExistException, IOException {
        System.out.println("Enter book title");
        String title = READER.readLine();
        if (findObject(books, title) != -1) throw new ObjectAlreadyExistException("Book already exist");
        System.out.println("Enter book description in 1 line");
        String description = READER.readLine();
        int year = getYearFromUser();

        System.out.println("Genre: ");
        Genre genre = (Genre) getFromUser(genres, AvailableObjects.GENRE);
        System.out.println("Author: ");
        Author author = (Author) getFromUser(authors, AvailableObjects.AUTHOR);
        Book book = new Book(title, description, year, genre, author);

        authors.get(findObject(authors, author.getName())).addBook(book);
        books.add(book);
        System.out.println("Book is added");
    }



    private int getYearFromUser() {
        int year = 0;
        try {
            System.out.println("Enter year in format 0000");
            String yearSTr = READER.readLine();
            while (yearSTr.length() != 4) {
                System.out.println("Wrong year, try again");
                yearSTr = READER.readLine();
            }

            year = Integer.parseInt(yearSTr);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            getYearFromUser();
        }
        return year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}