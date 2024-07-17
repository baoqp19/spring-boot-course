package vn.hoidanit.jobhunter.service.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// ctrl + k + s lưu hết
@RestControllerAdvice
public class GlobalException {

      // ResponseEntity là genaric nên phải có <string>
      @ExceptionHandler(value = IdInvalidException.class)
      public ResponseEntity<String> handleIdException(IdInvalidException idException) {
          return  ResponseEntity.badRequest().body(idException.getMessage());
      }
}
