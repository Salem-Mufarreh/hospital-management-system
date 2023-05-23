package Hospital.Management.System.REST.API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/* create a run time error with code conflict to let the user know if any conflict happened */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class RecordExistsException extends RuntimeException{
    public RecordExistsException(String message) {
        super(message);
    }
}
