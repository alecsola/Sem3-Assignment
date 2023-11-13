package fontys.sem3.school.persistence.converters;

import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.persistence.JPAmappers.ImageJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;

import java.util.List;

public class TheatreConverter {


    public static TheatreJPAmapper convertJPAtheatre (Theatre theatre){
        List<ImageJPAmapper> imageJPAmappers = ImageConverter.mapToJpaFiles(theatre.getImage());

        return TheatreJPAmapper.builder()

                .Name(theatre.getName())
                .Details(theatre.getDetails())
                .Country(theatre.getCountry())
                .City(theatre.getCity())
                .image(imageJPAmappers)
                .Popularity(theatre.getPopularity())
                .Capacity(theatre.getCapacity())
                .build();
    }


}
