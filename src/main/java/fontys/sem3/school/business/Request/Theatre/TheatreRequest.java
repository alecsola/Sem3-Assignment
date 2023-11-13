package fontys.sem3.school.business.Request.Theatre;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fontys.sem3.school.business.Serializer.MultipartFileDeserializer;
import fontys.sem3.school.business.Serializer.MultipartFileSerializer;
import fontys.sem3.school.domain.Image;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
//@JsonSerialize(using = MultipartFileSerializer.class)
//@JsonDeserialize(using = MultipartFileDeserializer.class)
public class TheatreRequest {

    private String name;
    private List<MultipartFile> image;
    private String country;
    private String city;
    private String details;
    private int capacity;
    private int popularity;
}
