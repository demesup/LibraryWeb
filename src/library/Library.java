package library;

import library.enums.*;
import library.music.Sound;
import library.objects.*;

import java.io.*;

import static library.ExtraMethods.*;
import static library.objects.LibraryObjects.*;

public class Library {
    static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    static String SEPARATOR = File.separator;
    static String PATH_TO_FILES = "C:" +SEPARATOR + "Users" + SEPARATOR + "User" + SEPARATOR+ "IdeaProjects" + SEPARATOR
            +"Library1" + SEPARATOR + "src" + SEPARATOR;
    static String FILE_NAME =PATH_TO_FILES + "libraryObjects.txt";

    static File file = new File(FILE_NAME);

    public static void main(String[] args) {
        if (file.length() > 0) getLastSaving();
        System.out.println("Welcome to library :) Some rules:" +
                "\n\t1.You can not enter similar names/surnames/titles" +
                "\n\t2.To save data, you need to exit from program properly(saving occurs in the end, if it is completed you will se the message)" +
                "\n\t3.Be happy)" +
                "\nYou can get to the saving by {exit from field-> exit from library}");
        try {
            music();
            start();
            save();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Good bye! :(");

    }

    private static void getLastSaving() {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            int authorSize = ois.readInt();
            for (int i = 0; i < authorSize; i++) {
                authors.add((Author) ois.readObject());
            }

            int bookSize = ois.readInt();
            for (int i = 0; i < bookSize; i++) {
                books.add((Book) ois.readObject());
            }

            int genreSize = ois.readInt();
            for (int i = 0; i < genreSize; i++) {
                genres.add((Genre) ois.readObject());
            }

            System.out.println("Last saving is received");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void save() throws IOException {
        file.delete();
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeInt(authors.size());
            for (Author author : authors) {
                oos.writeObject(author);
            }

            oos.writeInt(books.size());
            for (Book book : books) {
                oos.writeObject(book);
            }

            oos.writeInt(genres.size());
            for (Genre genre : genres) {
                oos.writeObject(genre);
            }

            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void music() {
        try {
            if (askUser("Do you want to listen classical music while working? Enter yes/ press any key to continue in silence")) {
                System.out.println("When the time is over music stops playing." +
                        "\nMusic will not be saved to a file." +
                        "\nIf you enter the number which is not on the list or any other key you will work without music.Press:" +
                        "\n\t1 - Mozart: Eine Kleine Nachtmusik (5min52sec)" +
                        "\n\t2 - Mozart: Alla turca from Piano Sonata No 11 (5min51sec)" +
                        "\n\t3 - Beethoven: Violin romance no 2 (9min50sec)" +
                        "\n\t4 - Bach: C Major Prelude and Allemande (French Suite No.2) (5min52sec)");
                String path = PATH_TO_FILES + "music" + SEPARATOR;

                File file;
                switch (readNumber()) {
                    case 1:
                        file = new File(path + "Bach.wav");
                        break;
                    case 2:
                        file = new File(path + "Mozart.wav");
                        break;
                    case 3:
                        file = new File(path + "Beethoven.wav");
                        break;
                    case 4:
                        file = new File(path + "Bach2.wav");
                        break;
                    default:
                        System.out.println("wrong number");
                        return;
                }
                Sound sound = new Sound(file);
                Thread thread = new Thread(sound);
                thread.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void start() throws IOException {
        try {
            System.out.println("Enter field you want to work with:genre, author, book(press any key to exit)");
            String str = READER.readLine().replaceAll(" ", "");

            AvailableObjects field = getAvailableObjects(str);
            AvailableActions action = getAction();

            switch (field) {
                case GENRE:
                    new Genre().workWithField(action);
                    break;
                case AUTHOR:
                    new Author().workWithField(action);
                    break;
                case BOOK:
                    new Book().workWithField(action);
            }

        } catch (Exception e) {
            if (!askUser("Do you want to exit and save changes?")) {
                start();
            }
        }
    }

    private static AvailableObjects getAvailableObjects(String str) {
        AvailableObjects field = null;

        for (AvailableObjects object : AvailableObjects.values()) {
            if (object.name().equalsIgnoreCase(str)) {
                field = AvailableObjects.valueOf(str.toUpperCase());
                break;
            }
        }
        if (field == null) {
            throw new IllegalArgumentException("You can enter only fields");
        }
        return field;
    }
}
