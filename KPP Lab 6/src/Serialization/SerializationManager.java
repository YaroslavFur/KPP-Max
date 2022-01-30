package Serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationManager {
    public static <T> void serialize(ArrayList<T> list, String fileName) {
        fileName += ".dat";
        try (var objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(list);
            System.out.println("Serialization complete");
        } catch (Exception exception) {
            System.out.println("Serialization failed");
        }
    }

    public static <T> ArrayList<T> deserialize(ArrayList<T> list, String fileName)
    {
        fileName += ".dat";
        try(var objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            list = (ArrayList<T>)objectInputStream.readObject();
            System.out.println("Deserialization complete");
        }
        catch (Exception exception)
        {
            System.out.println("Deserialization failed");
        }
        return list;
    }
}
