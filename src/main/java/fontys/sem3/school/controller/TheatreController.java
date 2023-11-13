package fontys.sem3.school.controller;


import fontys.sem3.school.business.Converter.TheatreConverter;
import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
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
//    @GetMapping("{id}")
//    public ResponseEntity<Theatre> getTheatrebyId(@PathVariable Long id){
//
//        try {
//            Theatre theatre = theatreService.getTheatrebyId(id);
//            if (theatre != null) { //preguntar con el response.
//                converter.theatreResponse(theatre);
//                System.out.println("Theatre retrieved: " + theatre);
//                return ResponseEntity.ok(theatre);
//            } else {
//                System.out.println("Theatre not found: " + id);
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            System.err.println("Error retrieving theatre for id " + id + ": " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    @PostMapping("")
    public long createTheatre(@RequestParam("name") String name, @RequestParam("image") List<MultipartFile> image,@RequestParam("country") String country,  @RequestParam("city") String city, @RequestParam("details") String details, @RequestParam("capacity") int capacity,@RequestParam ("popularity") int popularity) {
        TheatreRequest request = new TheatreRequest(name, image, country, city, details, capacity, popularity);
        return theatreService.createTheatre(request);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Theatre>> filterTheatres(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country) {

        List<Theatre> filteredTheatres = theatreService.filterTheatres(name, city, country);

        return new ResponseEntity<>(filteredTheatres, HttpStatus.OK);
    }
//    @PostMapping("/updateTheatre")
//    public ResponseEntity<TheatreResponse> updateUser(@RequestBody UpdateTheatreRequest request) {
//        Theatre updatedTheatre = theatreService.updateTheatre(request.getId(), request.getName(), request.getDetails(), request.getCountry(), request.getCity(), request.getPopularity(), request.getCapacity());
//        TheatreResponse response = converter.theatreResponse(updatedTheatre);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

}
