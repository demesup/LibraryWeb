package library.objects;

import library.enums.AvailableActions;
import library.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static library.ExtraMethods.findObject;

public interface FieldProcessing {
    BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    void workWithField(AvailableActions action) throws ObjectAlreadyExistException, IOException, ObjectDoesNotExist;

    void add() throws ObjectAlreadyExistException, IOException, ObjectDoesNotExist;

    default LibraryObjects remove(List<? extends LibraryObjects> list) throws IOException, ObjectDoesNotExist {
        if (list.size() == 0) {
            System.out.println("Empty list");
            return null;
        }

        System.out.println("Enter name:");
        String name = READER.readLine();
        int index = findObject(list, name);
        if (index == -1) throw new ObjectDoesNotExist("No object with this name");
        LibraryObjects obj = list.get(index);
        list.remove(index);
        System.out.println("Object is removed");
        return obj;
    }

    default void print(List<? extends LibraryObjects> list) {
        if (list.size() < 1) {
            System.out.println("Empty list");
            return;
        }
        for (LibraryObjects object : list) {
            System.out.println(object);
        }
    }
}