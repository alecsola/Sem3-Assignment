package fontys.sem3.school.persistence.converters;

import fontys.sem3.school.domain.Theatre;
import fontys.sem3.school.persistence.JPAmappers.ImageJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.TheatreJPAmapper;

import java.util.ArrayList;
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
    public static List<Theatre> convertTheatre (List<TheatreJPAmapper> theatreJPAmappers) {
        List<Theatre> theatres = new ArrayList<>();
        for (TheatreJPAmapper theatreJPAmapper : theatreJPAmappers) {
            Theatre theatre;

            theatre = new Theatre(theatreJPAmapper.getId(), theatreJPAmapper.getName(), ImageConverter.mapToFiles(theatreJPAmapper.getImage()), theatreJPAmapper.getCountry(), theatreJPAmapper.getCity(), theatreJPAmapper.getDetails(), theatreJPAmapper.getCapacity(), theatreJPAmapper.getPopularity());
            theatres.add(theatre);



        }
        return theatres;
    }

}
