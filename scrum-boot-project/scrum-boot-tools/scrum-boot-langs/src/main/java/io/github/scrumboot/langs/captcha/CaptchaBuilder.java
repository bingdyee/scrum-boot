package io.github.scrumboot.langs.captcha;

import javax.imageio.*;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
public class CaptchaBuilder {

    private static final String GIF = "gif";
    protected static Font font = new Font("Verdana", Font.ITALIC | Font.BOLD, 28);
    private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;
    protected static Random random = new Random();

    public static final int WIDTH = 200;
    public static final int HEIGHT = 50;
    public static final int CHAR_LENGTH = 5;
    public static final int DELAY = 250;

    private ImageWriter writer;
    private ImageWriteParam params;
    private IIOMetadata metadata;

    private int width;
    private int height;
    private int charLength;
    private int delay;
    private boolean loop;


    protected CaptchaBuilder() {
        this.width = CaptchaBuilder.WIDTH;
        this.height = CaptchaBuilder.HEIGHT;
        this.charLength = CaptchaBuilder.CHAR_LENGTH;
        this.delay = DELAY;
        this.loop = true;
    }

    public CaptchaBuilder setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public CaptchaBuilder setCharLength(int len) {
        this.charLength = len;
        return this;
    }

    public CaptchaBuilder setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public CaptchaBuilder setLoop(boolean loop) {
        this.loop = loop;
        return this;
    }

    private void init(ImageOutputStream out) {
        writer = ImageIO.getImageWritersBySuffix(GIF).next();
        params = writer.getDefaultWriteParam();
        ImageTypeSpecifier imageTypeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(IMAGE_TYPE);
        metadata = writer.getDefaultImageMetadata(imageTypeSpecifier, params);
        try {
            configureRootMetadata(delay, loop);
            writer.setOutput(out);
            writer.prepareWriteSequence(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureRootMetadata(int delay, boolean loop) throws IIOInvalidTreeException {
        String metaFormatName = metadata.getNativeMetadataFormatName();
        IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(metaFormatName);

        IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");
        graphicsControlExtensionNode.setAttribute("disposalMethod", "none");
        graphicsControlExtensionNode.setAttribute("userInputFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("transparentColorFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("delayTime", Integer.toString(delay / 10));
        graphicsControlExtensionNode.setAttribute("transparentColorIndex", "0");

        IIOMetadataNode commentsNode = getNode(root, "CommentExtensions");
        commentsNode.setAttribute("CommentExtension", "Created by: Hikari");

        IIOMetadataNode appExtensionsNode = getNode(root, "ApplicationExtensions");
        IIOMetadataNode child = new IIOMetadataNode("ApplicationExtension");
        child.setAttribute("applicationID", "Hikari");
        child.setAttribute("authenticationCode", "1.0.0");

        int loopContinuously = loop ? 0 : 1;
        child.setUserObject(new byte[]{ 0x1, (byte) (loopContinuously & 0xFF), (byte) ((loopContinuously >> 8) & 0xFF)});
        appExtensionsNode.appendChild(child);
        metadata.setFromTree(metaFormatName, root);
    }

    private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName){
        int nNodes = rootNode.getLength();
        for (int i = 0; i < nNodes; i++){
            if (rootNode.item(i).getNodeName().equalsIgnoreCase(nodeName)){
                return (IIOMetadataNode) rootNode.item(i);
            }
        }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        rootNode.appendChild(node);
        return node;
    }

    private BufferedImage graphicsImage(Color[] fontcolor, char[] chars, int flag) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获得图形上下文
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        //利用指定颜色填充背景
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, width, height);
        AlphaComposite ac3;
        int h = height - ((height - font.getSize()) >> 1);
        int w = width / charLength;
        // 画10个噪点(颜色及位置随机)
        for (int i = 0; i < 10; i++) {
            // 随机颜色
            int rInt = random.nextInt(255);
            int gInt = random.nextInt(255);
            int bInt = random.nextInt(255);
            g2d.setColor(new Color(rInt, gInt, bInt));
            // 随机位置
            int xInt = random.nextInt(width - 3);
            int yInt = random.nextInt(height - 2);
            // 随机大小
            int fontSize = random.nextInt(15);
            Font tempFont = new Font("Default", Font.PLAIN, fontSize);
            g2d.setFont(tempFont);
            g2d.drawString(random.nextInt(10) + "", xInt, yInt);
        }
        g2d.setFont(font);
        for (int i = 0; i < charLength; i++) {
            int stringXint = 0;
            int stringYint = 0;
            ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha(flag, i));
            g2d.setComposite(ac3);
            g2d.setColor(fontcolor[i]);
            g2d.drawString(chars[i] + "", (width - (charLength - i) * w) + (w - font.getSize()) + 1 + stringXint, h - 4 + stringYint);
            // 随机位置
            int xInt = random.nextInt(width - 3);
            int yInt = random.nextInt(height - 2);
            int xInt2 = random.nextInt(width);
            int yInt2 = random.nextInt(height);
            g2d.setStroke(new BasicStroke(1f));
            g2d.drawLine(xInt, yInt, xInt2, yInt2);
        }
        g2d.dispose();
        return image;
    }

    /**
     * 获取透明度,从0到1,自动计算步长
     *
     * @return float 透明度
     */
    private float getAlpha(int i, int j) {
        int num = i + j;
        float r = (float) 1 / charLength, s = (charLength + 1) * r;
        return num > charLength ? (num * r - s) : num * r;
    }

    protected ByteArrayOutputStream createToCache(char[] chars) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeImage(new MemoryCacheImageOutputStream(out), chars);
        return out;
    }

    private void writeImage(ImageOutputStream output, char[] chars) {
        init(output);
        Color[] fontColor = new Color[charLength];
        for (int i = 0; i < charLength; i++) {
            fontColor[i] = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
        }
        try {
            for (int i = 0; i < charLength; ++i) {
                writer.writeToSequence(new IIOImage(graphicsImage(fontColor, chars, i), null, metadata), params);
            }
            writer.endWriteSequence();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createToFile(String path, Captcha captcha) {
        try {
            writeImage(new FileImageOutputStream(new File(path)), captcha.randomChars(charLength));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Captcha create() {
        Captcha captcha = new Captcha();
        captcha.setCaptchaBuilder(this);
        captcha.randomChars(charLength);
        return captcha;
    }

}
