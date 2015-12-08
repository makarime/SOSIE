package utils;

import java.io.*;

public class Serialization {

    public static byte[] serialize(Serializable object) throws Exception {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)){
            out.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new Exception("Serialization error", e);
        }
    }

    public static Serializable deserialize(byte[] data) throws Exception {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInput in = new ObjectInputStream(bis)) {
            return (Serializable) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Deserialization error", e);
        }
    }

}
