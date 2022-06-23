package library.objects;

import library.enums.AvailableActions;
import library.exceptions.ObjectAlreadyExistException;
import library.exceptions.ObjectDoesNotExist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public interface FieldProcessing {
    BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    void workWithField(AvailableActions action) throws ObjectAlreadyExistException, IOException, ObjectDoesNotExist;

    void add() throws ObjectAlreadyExistException, IOException, ObjectDoesNotExist;

    void remove() throws IOException, ObjectDoesNotExist;

    default void print(List<? extends LibraryObjects> list){
        if (list.size() < 1) {
            System.out.println("Empty list");
            return;
        }
        for (LibraryObjects object: list) {
            System.out.println(object);
        }
    }
}
