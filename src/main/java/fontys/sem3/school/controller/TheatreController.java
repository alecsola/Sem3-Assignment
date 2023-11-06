package fontys.sem3.school.controller;


import fontys.sem3.school.business.Converter.TheatreConverter;
import fontys.sem3.school.business.Request.Theatre.TheatreRequest;
import fontys.sem3.school.business.Request.Theatre.UpdateTheatreRequest;
import fontys.sem3.school.business.Response.Theatre.TheatreResponse;
import fontys.sem3.school.business.interfaces.TheatreService;
import fontys.sem3.school.domain.Theatre;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatre")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TheatreController {

    private final TheatreConverter converter;
    private final TheatreService theatreService;
    @GetMapping("{id}")
    public ResponseEntity<Theatre> getTheatrebyId(@PathVariable Long id){

        try {
            Theatre theatre = theatreService.getTheatrebyId(id);
            if (theatre != null) { //preguntar con el response.
                converter.theatreResponse(theatre);
                System.out.println("Theatre retrieved: " + theatre);
                return ResponseEntity.ok(theatre);
            } else {
                System.out.println("Theatre not found: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error retrieving theatre for id " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping
    public ResponseEntity<TheatreResponse> createTheatre(
            @Valid @RequestBody TheatreRequest request) {
        Theatre theatre = converter.theatreRequestConverter(request);
        Theatre createdTheatre = theatreService.createTheatre(theatre);
        // Convert the created User into CreateUserResponse
        TheatreResponse response = converter.theatreResponse(createdTheatre);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/updateTheatre")
    public ResponseEntity<TheatreResponse> updateUser(@RequestBody UpdateTheatreRequest request) {
        Theatre updatedTheatre = theatreService.updateTheatre(request.getId(), request.getName(), request.getDetails(), request.getCountry(), request.getCity(), request.getPopularity(), request.getCapacity());
        TheatreResponse response = converter.theatreResponse(updatedTheatre);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
