package fr.iocean.application.helper;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class JsonHelper {

    private ObjectMapper objectMapper;

    public JsonHelper() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Serialize and object to a JSON String representation
     *
     * @param o The object to serialize
     * @return The JSON String representation
     */
    public String serialize(Object o) {
        OutputStream baOutputStream = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(baOutputStream, o);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return baOutputStream.toString();
    }

    /**
     * Serialize and object to a JSON String representation with a Jackson view
     *
     * @param o    The object to serialize
     * @param view The Jackson view to use
     * @return The JSON String representation
     */
    public String serialize(Object o, Class<?> view) {
        OutputStream baOutputStream = new ByteArrayOutputStream();
        try {
            ObjectWriter writter = objectMapper.writerWithView(view);
            writter.writeValue(baOutputStream, o);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return baOutputStream.toString();
    }

    /**
     * Deserialize a JSON string
     *
     * @param content The JSON String object representation
     * @param type    The type of the deserialized object instance
     * @return The deserialized object instance
     */
    public <T> T deserialize(String content, Class<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
