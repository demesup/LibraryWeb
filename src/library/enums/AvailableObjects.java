package library.enums;

public enum AvailableObjects {
    AUTHOR("Author"),
    BOOK("Book"),
    GENRE("Genre");

    private final String translation;

    AvailableObjects(String translation) {
    this.translation = translation;
    }

    public String getClassName(){
        return translation;
    }
}
