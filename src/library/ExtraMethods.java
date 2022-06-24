package library;

import library.enums.AvailableActions;
import library.enums.AvailableObjects;
import library.exceptions.ObjectAlreadyExistException;
import library.objects.Author;
import library.objects.Genre;
import library.objects.LibraryObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class ExtraMethods {

    static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static LibraryObjects getFromUser(List<? extends LibraryObjects> list, AvailableObjects field) {
        try {
            int index;
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + " " + list.get(i));
                }
                System.out.println("Enter number or -1 to create new");
                index = readNumber();
            } else {
                System.out.println("List is empty.");
                index = -1;
            }
            if (index == -1) {
                System.out.println("Adding new...");
                switch (field) {
                    case AUTHOR:
                        new Author().add();
                        break;
                    case GENRE:
                        new Genre().add();
                }

                return getFromUser(list, field);
            }
            return list.get(index);
        } catch (ObjectAlreadyExistException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return getFromUser(list, field);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int findObject(List<? extends LibraryObjects> objects, String name) {
        for (LibraryObjects object : objects) {
            if (object.getName().equalsIgnoreCase(name)) return objects.indexOf(object);
        }
        return -1;
    }

    public static boolean checkIfHave(List<? extends LibraryObjects> objects, String name) {
        if (objects.size() == 0) return false;
        for (LibraryObjects object : objects) {
            if (object.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public static AvailableActions getAction() throws IOException {
        System.out.println("Enter action: add, remove, print all/press any key to exit");
        String string = READER.readLine().replaceAll(" ", "");

        AvailableActions action = null;

        for (AvailableActions avAction : AvailableActions.values()) {
            if (avAction.name().equalsIgnoreCase(string)) {
                action = AvailableActions.valueOf(string.toUpperCase());
                break;
            }
        }
        if (action == null) {
            throw new IllegalArgumentException("You can enter only action");
        }
        return action;
    }

    public static boolean askUser(String question) throws IOException {
        System.out.println(question);
        return READER.readLine().replaceAll(" ", "").equalsIgnoreCase("yes");
    }

    public static int readNumber() {
        System.out.println("Input number");
        try {
            return Integer.parseInt(READER.readLine());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return readNumber();
        }
    }
}
