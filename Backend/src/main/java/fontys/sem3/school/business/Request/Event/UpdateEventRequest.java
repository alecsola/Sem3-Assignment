package fontys.sem3.school.business.Request.Event;

import fontys.sem3.school.domain.Zone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdateEventRequest {

    private Long id;
    private String name;
    private Long theatreId;
    private Date date;
    private String time;
    private int completed;
    private List<Zone> zones;
    private List<MultipartFile> image;
}
