package pidev.tn.aurora.services.Forum;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class ForbiddenWordsDetectorApi implements ForbiddenWordsDetector{
    private static final String API_URL = "http://localhost:5000/detect-forbidden-words";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean detect(String text) {
        String requestBody = "{ \"text\": \"" + text + "\" }";
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, requestBody, String.class);
        return response.getStatusCode() == HttpStatus.BAD_REQUEST;
    }
}
