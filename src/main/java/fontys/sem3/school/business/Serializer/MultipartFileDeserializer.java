package fontys.sem3.school.business.Serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class MultipartFileDeserializer extends JsonDeserializer<MultipartFile> {
    @Override
    public MultipartFile deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // You need to implement the deserialization logic here, for example, creating a new MultipartFile
        return null;
    }
}
