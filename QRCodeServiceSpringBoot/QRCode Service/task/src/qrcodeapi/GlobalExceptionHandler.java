package qrcodeapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidImageSizeException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectImageSize() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Image size must be between 150 and 350 pixels");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidImageTypeException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectImageType() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Only png, jpeg and gif image types are supported");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidContentException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectContent() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Contents cannot be null or blank");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCorrectionLevelException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectCorrectionLevel() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Permitted error correction levels are L, M, Q, H");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

class InvalidImageSizeException extends RuntimeException {
    public InvalidImageSizeException() {
        super("Incorrect image size");
    }
}

class InvalidImageTypeException extends RuntimeException {
    public InvalidImageTypeException() {
        super("Incorrect image type");
    }
}

class InvalidContentException extends RuntimeException {
    public InvalidContentException() {
        super("Invalid content");
    }
}

class InvalidCorrectionLevelException extends RuntimeException {
    public InvalidCorrectionLevelException() {
        super("Invalid correction level");
    }
}
