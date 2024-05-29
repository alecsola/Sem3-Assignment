package fontys.sem3.school.business.Request.Theatre;

import lombok.Data;

import java.util.Optional;

@Data
public class FilterTheatreRequest {

    private String name;
    private String country;
    private String city;

}
