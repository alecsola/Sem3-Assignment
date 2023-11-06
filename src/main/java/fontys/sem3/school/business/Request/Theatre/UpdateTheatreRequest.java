package fontys.sem3.school.business.Request.Theatre;


import lombok.Data;

@Data
public class UpdateTheatreRequest {

    private Long Id;
    private String Name;
    private String Country;
    private String City;
    private String Details;
    private int Capacity;
    private int Popularity;
}
