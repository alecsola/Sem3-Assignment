package fontys.sem3.school.business.Request.Theatre;

import lombok.Data;

import java.util.Optional;

@Data
public class FilterTheatreRequest {

    private Optional<String> name;
    private Optional<String> country;
    private Optional<String> city;
    private Optional<Integer> seatCapacity;
}
