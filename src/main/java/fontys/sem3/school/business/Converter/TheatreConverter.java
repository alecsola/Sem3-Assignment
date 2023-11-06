package fontys.sem3.school.business.Converter;

import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.domain.Theatre;
import org.springframework.stereotype.Component;

@Component
public class TheatreConverter {

    public Theatre theatreRequestConverter(TheatreRequest request){
        Theatre theatre = new Theatre();
        theatre.setName(request.getName());
        theatre.setDetails(request.getDetails());
        theatre.setCountry(request.getCountry());
        theatre.setPopularity(request.getPopularity());
        theatre.setCity(request.getCity());

        return theatre;
    }

    public TheatreResponse theatreResponse (Theatre theatre){
        TheatreResponse theatreResponse = new TheatreResponse();
        theatreResponse.setId(theatre.getId());
        return theatreResponse;
    }

}
