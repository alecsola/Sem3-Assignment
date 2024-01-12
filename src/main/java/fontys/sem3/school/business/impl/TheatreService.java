package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Converter.ImageConverter;
import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Response.Theatre.GetTheatreResponse;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.business.impl.StorageService;
import fontys.sem3.school.business.interfaces.ITheatreRepositoryBusiness;
import fontys.sem3.school.business.servicesInterfaces.ITheatreService;
import fontys.sem3.school.domain.Image;
import fontys.sem3.school.domain.Theatre;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TheatreService implements ITheatreService {


    private final ITheatreRepositoryBusiness theatreRepository;
    private final StorageService storageService;

    /**
     * @should creatre theatre
     * @should return an error when not creating
     */
    public long createTheatre(TheatreRequest request) {
        try {
            Theatre theatre = new Theatre();
            theatre.setName(request.getName());
            theatre.setDetails(request.getDetails());
            theatre.setCountry(request.getCountry());
            theatre.setPopularity(request.getPopularity());
            theatre.setCity(request.getCity());

            // Convert MultipartFile to Image and set it in Theatre
            List<Image> images = ImageConverter.convertToImageObject(request.getImage());
            theatre.setImage(images);

            long result = theatreRepository.createTheatre(theatre);

            if (result != 0) {
                for (MultipartFile file : request.getImage()) {
                    storageService.store(file, result);
                }
            }

            return new TheatreResponse(result).getId();
        } catch (Exception e) {

            throw new IllegalArgumentException("Theatre could not be made");
        }
    }
    /**
     * @should filter theatre with name,city and country
     * @should return an empty list when nothing found
     */
    public GetTheatreResponse filterTheatres(String name, String city, String country) {
        List<Theatre> theatres;
        theatres = theatreRepository.filterTheatres(name, city, country);
        return new GetTheatreResponse(theatres);
    }
    public GetTheatreResponse findAll(){
        List<Theatre> theatres;
        theatres = theatreRepository.findAll();
        return new GetTheatreResponse(theatres);
    }
    public Theatre getTheatrebyId(long id){
        return theatreRepository.getTheatrebyId(id);
    }



}
