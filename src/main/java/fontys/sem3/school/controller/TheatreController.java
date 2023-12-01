package fontys.sem3.school.controller;


import fontys.sem3.school.business.Converter.TheatreConverter;
import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Response.Theatre.GetTheatreResponse;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.business.servicesInterfaces.ITheatreService;
import fontys.sem3.school.domain.Theatre;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/theatre")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TheatreController {

    private final TheatreConverter converter;
    private final ITheatreService theatreService;
    @PostMapping("")
    public long createTheatre(@RequestParam("name") String name, @RequestParam("image") List<MultipartFile> image,@RequestParam("country") String country,  @RequestParam("city") String city, @RequestParam("details") String details, @RequestParam("capacity") int capacity,@RequestParam ("popularity") int popularity) {
        TheatreRequest request = new TheatreRequest(name, image, country, city, details, capacity, popularity);
        return theatreService.createTheatre(request);
    }
    @GetMapping("/filter")
    public GetTheatreResponse filterTheatres(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country) {
        return this.theatreService.filterTheatres(name, city, country);
    }


}
