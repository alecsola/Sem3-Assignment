package fontys.sem3.school.controller;


import fontys.sem3.school.business.Converter.TheatreConverter;
import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Response.Theatre.GetTheatreResponse;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.business.servicesInterfaces.ITheatreService;
import fontys.sem3.school.business.servicesInterfaces.ITheatreServiceADMIN;
import fontys.sem3.school.domain.Theatre;
import jakarta.annotation.security.RolesAllowed;
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


    private final ITheatreService theatreService;
    private final ITheatreServiceADMIN theatreServiceADMIN;

    @PostMapping("")
    @RolesAllowed("ADMIN")
    public long createTheatre(@RequestParam("name") String name, @RequestParam("image") List<MultipartFile> image,@RequestParam("country") String country,  @RequestParam("city") String city, @RequestParam("details") String details, @RequestParam("capacity") int capacity,@RequestParam ("popularity") int popularity) {
        TheatreRequest request = new TheatreRequest(name, image, country, city, details, capacity, popularity);
        return theatreService.createTheatre(request);
    }
//    @PostMapping("")
//    public long createTheatre(@RequestParam("name") String name, @RequestParam("image") List<MultipartFile> image,@RequestParam("country") String country,  @RequestParam("city") String city, @RequestParam("details") String details, @RequestParam("capacity") int capacity,@RequestParam ("popularity") int popularity) {
//        TheatreRequest request = new TheatreRequest(name, image, country, city, details, capacity, popularity);
//        return theatreServiceADMIN.createTheatre(request);
//    }
    @GetMapping("/filter")

    public GetTheatreResponse filterTheatres(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country) {
        return this.theatreService.filterTheatres(name, city, country);
    }
    @GetMapping("findAll")
    public GetTheatreResponse findAll(){
        return this.theatreService.findAll();
    }
    @GetMapping("getId/{id}")
    public Theatre getTheatrebyId(@PathVariable long id){
        return this.theatreService.getTheatrebyId(id);
    }


}
