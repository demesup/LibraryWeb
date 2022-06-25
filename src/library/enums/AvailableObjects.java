package library.enums;

public enum AvailableObjects {
    AUTHOR("Author"),
    BOOK("Book"),
    GENRE("Genre");

    private final String className;

    AvailableObjects(String className) {
    this.className = className;
    }

    public String getClassName(){
        return className;
    }
}
