package karol.spring.shopapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NullValueException.class)
    public ResponseEntity<Object> handleNullValueException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Value can not be null!" + new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ValueNotFoundException.class)
    public ResponseEntity<Object> handleValueNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Value not found!" + new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ValueExsistException.class)
    public ResponseEntity<Object> handleValueExistsException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Value exists in database" + new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
