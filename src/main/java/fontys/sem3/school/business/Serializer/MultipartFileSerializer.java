package fontys.sem3.school.business.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class MultipartFileSerializer extends JsonSerializer<MultipartFile> {

    @Override
    public void serialize(MultipartFile value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // You can customize the serialization logic here, for example, just serialize the filename
        gen.writeString(value.getOriginalFilename());
    }
}
