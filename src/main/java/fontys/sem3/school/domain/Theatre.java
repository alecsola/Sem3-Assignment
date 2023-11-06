package fontys.sem3.school.domain;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {

    private Long Id;
    private String Name;
    private String Image;
    private String Country;
    private String City;
    private String Details;
    private int Capacity;
    private int Popularity;


}
