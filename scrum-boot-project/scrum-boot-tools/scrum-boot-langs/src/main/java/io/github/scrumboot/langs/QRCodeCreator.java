package io.github.scrumboot.langs;

import com.google.common.base.Strings;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.github.scrumboot.langs.exception.BadOperationException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 *
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
public class QRCodeCreator {

    private static final int DEFAULT_SIZE = 500;
    private static final String PNG = "PNG";
    private static final MatrixToImageConfig MATRIX_TO_IMAGE_CONFIG = new MatrixToImageConfig();

    private int width;
    private int height;
    private String content;
    private BitMatrix bitMatrix;

    public QRCodeCreator() {
        this.height = DEFAULT_SIZE;
        this.width = DEFAULT_SIZE;
    }

    public static QRCodeCreator encoder() {
        return new QRCodeCreator();
    }

    public static String decodeFromFile(String path) {
        return QRCodeUtil.decode(path);
    }

    public static String decodeFromStream(InputStream stream) {
        return QRCodeUtil.decode(stream);
    }

    public QRCodeCreator content(String content) {
        this.content = content;
        return this;
    }

    public QRCodeCreator size(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public QRCodeCreator create() {
        if (Strings.isNullOrEmpty(content)) {
            throw new BadOperationException("QRCode content is null!");
        }
        try {
            this.bitMatrix = QRCodeUtil.encode(content, width, height);
        } catch (Exception e) {
            throw new BadOperationException();
        }
        return this;
    }

    public BufferedImage getImage() {
        checkHasCreated();
        return MatrixToImageWriter.toBufferedImage(bitMatrix, MATRIX_TO_IMAGE_CONFIG);
    }

    public void toFile(String path) {
        checkHasCreated();
        try  {
            MatrixToImageWriter.writeToPath(bitMatrix, PNG, new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toStream(OutputStream out) {
        checkHasCreated();
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, PNG, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkHasCreated() {
        if (Objects.isNull(bitMatrix)) {
            throw new BadOperationException("QRCode has not yet been created!");
        }
    }

}
