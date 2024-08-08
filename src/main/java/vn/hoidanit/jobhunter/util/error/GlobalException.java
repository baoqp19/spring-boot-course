package vn.hoidanit.jobhunter.util.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import vn.hoidanit.jobhunter.domain.respone.RestResponse;

// ctrl + k + s lưu hết
@RestControllerAdvice
public class GlobalException {

    // xử lý sự kiện mà chưa có Exception ví dụ như gửi email, nếu truyền lên sai thì nó sẽ chạy vào Excepsion này

    // các lỗi nào chưa được định nghĩa thì nó chạy vào trong này để in ra lỗi
    @ExceptionHandler(Exception.class)     
    public ResponseEntity<RestResponse<Object>> handleAllException(Exception ex) {
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        res.setMessage(ex.getMessage());
        res.setError("Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

      
      @ExceptionHandler(value = {
                  UsernameNotFoundException.class,
                  BadCredentialsException.class,   // thông tin đăng nhập không hợp lệ
                  IdInvalidException.class,
      })
      public ResponseEntity<RestResponse<Object>> handleIdException(Exception ex) {
            RestResponse<Object> res = new RestResponse<Object>();
            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            res.setMessage(ex.getMessage());
            res.setError("Exception occurs...");
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
      }


      // khi đường dẫn bị lỗi 404
      @ExceptionHandler(value = {
                  NoResourceFoundException.class,
      })
      public ResponseEntity<RestResponse<Object>> handleNotFoundException(Exception ex) {
            RestResponse<Object> res = new RestResponse<Object>();
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage(ex.getMessage());
            res.setError("404 Not Found. URL may not exist...");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
      }


      // nó chạy vào khi username và password để trống notBlank và valid
      @ExceptionHandler(
            MethodArgumentNotValidException.class
      )
      public ResponseEntity<RestResponse<Object>> validationErError(MethodArgumentNotValidException ex) {
            BindingResult result = ex.getBindingResult();
            final List<FieldError> fielErrors = result.getFieldErrors();

            RestResponse<Object> res = new RestResponse<>();
            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            res.setError(ex.getBody().getDetail());

            // stream là API thao tác tập dữ liệu collect tập hợp là chuyển sang về dạng khác
            List<String> errors = fielErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
            res.setMessage(errors.size() > 1 ? errors : errors.get(0));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
      }
      
      
      @ExceptionHandler(value = {
                  StorageException.class,
      })
      public ResponseEntity<RestResponse<Object>> handleFileUploadException(Exception ex) {
            RestResponse<Object> res = new RestResponse<Object>();
            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            res.setMessage(ex.getMessage());
            res.setError("Exception upload file...");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
      }


      @ExceptionHandler(value = {
            PermissionException.class,
    })
    public ResponseEntity<RestResponse<Object>> handlePermissionException(Exception ex) {
        RestResponse<Object> res = new RestResponse<Object>();
            res.setStatusCode(HttpStatus.FORBIDDEN.value());
            res.setError("Forbidden");
            res.setMessage(ex.getMessage());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }





}
