package fontys.sem3.school.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.school.business.Converter.EventConverterBusiness;
import fontys.sem3.school.business.Request.Event.EventRequest;
import fontys.sem3.school.business.Request.Event.UpdateEventRequest;
import fontys.sem3.school.business.Response.GetEventResponse;
import fontys.sem3.school.business.Response.UpdateEventResponse;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import fontys.sem3.school.domain.Zone;
import fontys.sem3.school.persistence.converters.EventConverter;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {
    private IEventService service;


    @PostMapping("create")
    public long createEvent(@RequestParam("name") String name, @RequestParam("image")List<MultipartFile>image, @RequestParam("theatreId")Long theatreId,
                            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam("time") String time, @RequestParam("zones")  String zonesJson) throws JsonProcessingException {
        List<Zone> zones = Arrays.asList(new ObjectMapper().readValue(zonesJson, Zone[].class));
        EventRequest request = new EventRequest(name,theatreId,date,time,zones,image);
        return service.createEvent(request);
    }
    @PostMapping("update/{id}")
    public UpdateEventResponse updateEvent(@PathVariable("id") Long id, @RequestParam("name") String name,
                                           @RequestPart("image")List<MultipartFile> image,
                                           @RequestParam("theatreId") Long theatreId,
                                           @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                           @RequestParam("time") String time,
                                           @RequestParam("completed") int completed,
                                           @RequestParam("zones") String zonesJson) throws JsonProcessingException {
        List<Zone> zones = Arrays.asList(new ObjectMapper().readValue(zonesJson, Zone[].class));
        UpdateEventRequest request = new UpdateEventRequest(id, name, theatreId, date, time,completed, zones, image);
        return service.updateEvent(request);
    }
    @GetMapping("filter")
    public GetEventResponse filterEvent(@RequestParam String name){
        return service.filterEvents(name);
    }
    @GetMapping("findAll")
    public GetEventResponse findAll(){
        return service.findAll();
    }
    @GetMapping("/getId/{id}")
    public UpdateEventResponse getById(@PathVariable Long id) {
        return service.getEventbyId(id);
    }
    @GetMapping("getNotCompleted")
    public GetEventResponse getNotCompleted(){
        return service.getNotCompleted();
    }
    @PostMapping("delete/{id}")
    public void deleteEvent(@PathVariable("id") Long id, @RequestParam("name") String name,
                            @RequestPart("image")List<MultipartFile> image,
                            @RequestParam("theatreId") Long theatreId,
                            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                            @RequestParam("time") String time,
                            @RequestParam("completed") int completed,
                            @RequestParam("zones") String zonesJson)throws JsonProcessingException{
        List<Zone> zones = Arrays.asList(new ObjectMapper().readValue(zonesJson, Zone[].class));
        UpdateEventRequest request = new UpdateEventRequest(id, name, theatreId, date, time,completed, zones, image);
        service.deleteEvent(request);
    }
    @GetMapping("getByPopularity")
    public GetEventResponse getEventsByPopularity(){
        return service.getEventsByPopularity();
    }
}