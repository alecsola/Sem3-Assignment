package fontys.sem3.school.business.Converter;

import fontys.sem3.school.domain.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class  ImageConverter {

    public static List<Image> convertToImageObject(List<MultipartFile> multipartFiles) {
        if (multipartFiles == null) {
            return Collections.emptyList(); // or return null, depending on your design
        }

        return multipartFiles.stream()
                .filter(Objects::nonNull) // Filter out any null elements
                .map(image -> Image.builder()
                        .url(image.getOriginalFilename())
                        .type(image.getContentType())
                        .build())
                .collect(Collectors.toList());
    }

}
