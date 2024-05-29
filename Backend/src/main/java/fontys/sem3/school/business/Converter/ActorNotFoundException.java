package fontys.sem3.school.business.Converter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Actor Not Found")
public class ActorNotFoundException extends Exception {

}
