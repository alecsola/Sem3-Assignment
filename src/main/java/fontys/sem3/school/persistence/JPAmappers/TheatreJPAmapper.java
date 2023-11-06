package fontys.sem3.school.persistence.JPAmappers;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theatre")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TheatreJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    private String Country;
    private String City;
    private String Details;
    private int Popularity;
    private int Capacity;
    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageJPAmapper> image;
}
