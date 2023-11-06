package fontys.sem3.school.persistence.JPAmappers;

import fontys.sem3.school.business.interfaces.TheatreService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class ImageJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "file_url")
    private String fileUrl;
    @Column (name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private TheatreJPAmapper theatre;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventJPAmapper event;

}
