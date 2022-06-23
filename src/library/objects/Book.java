package library.objects;

import library.enums.AvailableActions;
import library.exceptions.ObjectAlreadyExistException;
import library.exceptions.ObjectDoesNotExist;

import java.io.IOException;

import static library.ExtraMethods.*;

public class Book extends LibraryObjects {
    private Author author;
    private String description;
    private int year;
    private Genre genre;

    public Book() {}

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
                    remove();
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
    public void add() throws ObjectAlreadyExistException, IOException {
        System.out.println("Enter book title");
        String title = READER.readLine();
        if (findObject(books, title) != -1) throw new ObjectAlreadyExistException("Book already exist");
        System.out.println("Enter book description in 1 line");
        String description = READER.readLine();
        int year = getYearFromUser();

        Genre genre = getGenreFromUser();
        Author author = getAuthorFromUser();
        Book book = new Book(title, description, year, genre, author);

        authors.get(findObject(authors, author.getName())).addBook(book);
        books.add(book);
        System.out.println("Book is added");
    }

    private Author getAuthorFromUser() throws ObjectAlreadyExistException, IOException {
        try {
            int authorIndex;
            System.out.println("Authors: ");
            if (authors.size() > 0) {
                for (int i = 0; i < authors.size(); i++) {
                    System.out.println(i + " " + authors.get(i));
                }
                System.out.println("Enter number of author or -1 to create new");
                authorIndex = readNumber();
            } else {
                System.out.println("Author list is empty. Adding new");
                authorIndex = -1;
            }
            if (authorIndex == -1) {
                new Author().add();
                return getAuthorFromUser();
            }
            return authors.get(authorIndex);
        } catch (ObjectAlreadyExistException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return getAuthorFromUser();
        }
    }

    private Genre getGenreFromUser() throws ObjectAlreadyExistException, IOException {
        try {
            int genreIndex;
            System.out.println("Genres: ");
            if (genres.size() > 0) {
                for (int i = 0; i < genres.size(); i++) {
                    System.out.println(i + " " + genres.get(i));
                }
                System.out.println("Enter number of genre or -1 to create new");
                genreIndex = readNumber();
            } else {
                System.out.println("Genre list is empty. Adding new");
                genreIndex = -1;
            }
            if (genreIndex == -1) {
                new Genre().add();
                return getGenreFromUser();
            }
            return genres.get(genreIndex);
        } catch (ObjectAlreadyExistException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return getGenreFromUser();
        }
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

    @Override
    public void remove() throws ObjectDoesNotExist, IOException {
        if (books.size() > 0) {
            System.out.println("Enter name");
            String title = READER.readLine();
            int index = findObject(books, title);
            if (index == -1) throw new ObjectDoesNotExist("No book with name " + name);
            books.remove(index);
            System.out.println("Book is removed");
        } else System.out.println("Empty book list");
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