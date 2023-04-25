package pidev.tn.aurora.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;


@Getter
@Setter
public class HttpResponse {
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Africa/Tunis")
     private Date timeStamp;
     private int httpStatusCode; // 200, 201, 400, 500
     private HttpStatus httpStatus;
     private String reason;
     private String message;

     public HttpResponse(){}
     public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message)
     {
          this.timeStamp = new Date();
          this.httpStatusCode = httpStatusCode;
          this.httpStatus = httpStatus;
          this.reason = reason;
          this.message = message;
     }
}
