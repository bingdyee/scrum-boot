package io.github.scrumboot.langs;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
public class QRCodeUtil {

    public static BitMatrix encode(String content, int width, int height) throws Exception {
        return encodeQRCode(content, width, height);
    }

    public static String decode(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            return decodeQRCode(image);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static String decode(InputStream stream) {
        try {
            BufferedImage image = ImageIO.read(stream);
            return decodeQRCode(image);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    private static BitMatrix encodeQRCode(String content, int width, int height) throws Exception {
        final Map<EncodeHintType, Object> hints = new HashMap<>(4);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 2);
        return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

    }

    private static String decodeQRCode(BufferedImage image) throws NotFoundException {
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Map<DecodeHintType, Object> hintMap = new HashMap<>(4);
        hintMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        return new MultiFormatReader().decode(bitmap, hintMap).getText();
    }

}
