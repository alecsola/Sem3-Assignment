package fontys.sem3.school.business.impl.Admin;

import fontys.sem3.school.business.Converter.ImageConverter;
import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.business.impl.StorageService;
import fontys.sem3.school.business.interfaces.ITheatreRepositoryBusiness;
import fontys.sem3.school.business.servicesInterfaces.ITheatreService;
import fontys.sem3.school.business.servicesInterfaces.ITheatreServiceADMIN;
import fontys.sem3.school.domain.Image;
import fontys.sem3.school.domain.Theatre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreServiceADMIN implements ITheatreServiceADMIN {
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
}
