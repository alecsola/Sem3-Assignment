package fontys.sem3.school.controller;


import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {
    private IEventService service;


    @PostMapping
    @RolesAllowed("ADMIN")
    public long createEvent(@RequestBody @Valid EventRequest request){
        return service.createEvent(request);
    }
}
