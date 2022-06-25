package library.objects;

import java.io.Serializable;
import java.util.*;

public abstract class LibraryObjects implements FieldProcessing, Serializable {

    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<Genre> genres = new ArrayList<>();

    public LibraryObjects() {
    }

    protected String name;

    public LibraryObjects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}