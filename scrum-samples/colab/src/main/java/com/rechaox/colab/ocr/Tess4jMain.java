package com.rechaox.colab.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * https://api.pi.delivery/v1/pi?start=0&numberOfDigits=1000
 *
 * @author Bing D. Yee
 * @since 2021/12/15
 */
public class Tess4jMain {

    public static final String TESSDATA_PREFIX = "/Users/bingdyee/Data/tessdata";


    public static void main(String[] args) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("chi_sim");
        tesseract.setDatapath(TESSDATA_PREFIX);
        File file = new File("/Users/bingdyee/Workspaces/rechaox/scrum-boot/scrum-samples/colab/src/main/resources/test.png");
        String text = tesseract.doOCR(file);
        String[] lines = text.split("\n");
        for (String line : lines) {
            String[] words = line.split(" ");
            System.err.println(words[2]);
        }
    }

}
