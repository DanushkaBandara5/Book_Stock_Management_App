package lk.ijse.dep10.app.advice;

import lk.ijse.dep10.app.business.exceptions.BOException;
import lk.ijse.dep10.app.business.exceptions.DuplicateException;
import lk.ijse.dep10.app.business.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BOException.class})
    public ResponseEntity<?> BOExceptionHandler(BOException exp){

        if(exp instanceof RecordNotFoundException){
           return new ResponseEntity(exp.getMessage(),HttpStatus.BAD_REQUEST);
        } else if (exp instanceof DuplicateException) {
            return new ResponseEntity(exp.getMessage(),HttpStatus.CONFLICT );
        }else{
           return new ResponseEntity(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> dataValidate(MethodArgumentNotValidException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
