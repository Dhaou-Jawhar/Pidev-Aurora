package pidev.tn.aurora.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpResponse {
     private int httpStatusCode;
     private String reason;
     private String message;
}
