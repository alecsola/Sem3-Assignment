package fontys.sem3.school.persistence.converters;

import fontys.sem3.school.domain.Image;
import fontys.sem3.school.persistence.JPAmappers.ImageJPAmapper;

import java.util.ArrayList;
import java.util.List;

public class ImageConverter {

    public static List<Image> mapToFiles(List<ImageJPAmapper> imageUrl) {
        List<Image> images = new ArrayList<>();
        if (imageUrl != null) {
            for (ImageJPAmapper image : imageUrl) {
                images.add(createImage(image.getUrl(), image.getType()));
            }
        }
        return images;
    }

    private static Image createImage(String Url, String type) {
        return Image.builder()
                .url(Url)
                .type(type)
                .build();
    }



    public static List<ImageJPAmapper> mapToJpaFiles(List <Image> images) {
        List<ImageJPAmapper> jpaImages = new ArrayList<>();
        if (images != null) {
            for (Image image : images) {
                jpaImages.add(createJpaImage(image.getUrl(), image.getType()));
            }
        }
        return jpaImages;
    }

    private static ImageJPAmapper createJpaImage(String Url, String type) {
        return ImageJPAmapper.builder()
                .Url(Url)
                .type(type)
                .build();
    }


}
