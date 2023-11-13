package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Converter.ImageConverter;
import fontys.sem3.school.business.Request.Theatre.FilterTheatreRequest;
import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.business.interfaces.ITheatreRepositoryBusiness;
import fontys.sem3.school.business.servicesInterfaces.ITheatreService;
import fontys.sem3.school.domain.Image;
import fontys.sem3.school.domain.Theatre;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheatreService implements ITheatreService {


    private final ITheatreRepositoryBusiness theatreRepository;
    private final StorageService storageService;

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
            e.printStackTrace();
            throw new IllegalArgumentException("Theatre not found");
        }
    }

    public List<Theatre> filterTheatres(String name, String city, String country) {
        return theatreRepository.filterTheatres(name, city, country);
    }



}
