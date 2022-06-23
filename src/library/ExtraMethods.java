package library;

import library.enums.AvailableActions;
import library.objects.LibraryObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class ExtraMethods {

    static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));


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
