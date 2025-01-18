package qrcodeapi;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
class QrCodeController {
    @GetMapping("/api/health")
    public HttpStatus getOkCode() {
        return HttpStatus.OK;
    }

    @GetMapping("/api/qrcode")
    @ResponseBody
    public ResponseEntity<?> getQrImage(@RequestParam String contents,
                                        @RequestParam(defaultValue = "png") String type,
                                        @RequestParam(defaultValue = "250") int size,
                                        @RequestParam(defaultValue = "L") String correction) {

        QRCodeWriter writer = new QRCodeWriter();
        BufferedImage bufferedImage = null;
        String mediaType = checkParams(contents, type, size, correction);

        Map<EncodeHintType, ?> hints = Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.valueOf(correction));
        try {
            BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, size, size, hints);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(mediaType)) // MediaType.IMAGE_XXX
                .body(bufferedImage);
    }

    private static String checkParams(String contents, String type, int size, String correction) {
        String mediaType = "image/" + type.toLowerCase();
        List<String> correctionLevels = Arrays.asList("L", "M", "Q", "H");

        if (contents.isBlank()) {
            throw new InvalidContentException();
        }
        if (size < 150 || size > 350) {
            throw new InvalidImageSizeException();
        }
        if (!correctionLevels.contains(correction.toUpperCase())) {
            throw new InvalidCorrectionLevelException();
        }
        if (!(MediaType.IMAGE_PNG_VALUE.equals(mediaType) ||
                MediaType.IMAGE_JPEG_VALUE.equals(mediaType) ||
                MediaType.IMAGE_GIF_VALUE.equals(mediaType))) {
            throw new InvalidImageTypeException();
        }
        return mediaType;
    }
}
