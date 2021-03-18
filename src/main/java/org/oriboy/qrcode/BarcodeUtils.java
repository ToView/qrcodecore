package org.oriboy.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author UW-JAVA
 * @date 2021/3/18 15:25
 */
public class BarcodeUtils {

    /**
     * 条形码默认宽度
     */
    private static final int BARCODE_WIDTH = 180;

    /**
     * 条形默认高度
     */
    private static final int BARCODE_HEIGHT = 70;


    /**
     * 默认条形码文件格式
     */
    private static final String FORMAT = "png";

    /**
     * 条形条形码参数
     */
    private static final EnumMap<EncodeHintType, Object> ENCODE_HINTS = new EnumMap<>(EncodeHintType.class);

    /**
     * 解析条形码参数
     */
    private static final EnumMap<DecodeHintType, Object> DECODE_HINTS = new EnumMap<>(DecodeHintType.class);

    /** 微软雅黑常规字体库 字体库 */
    private static final String BASE_FONT = "font/msyh.ttc";

    static {
        /* 字符编码 */
        ENCODE_HINTS.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        /* 容错等级 L、M、Q、H 其中 L 为最低, H 为最高 */
        ENCODE_HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        /* 字符编码 */
        DECODE_HINTS.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        /* 优化精度 */
        DECODE_HINTS.put(DecodeHintType.TRY_HARDER, true);
    }

    private BarcodeUtils() {
    }

    /**
     * @title 生成条形图片
     * @description 根据文本内容生成默认格式的二维图图片
     * @param text 文本内容
     * @param filePath 生成图片路径
     * @throws WriterException
     * @throws IOException
     */
    public static void create(String text, String filePath) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.CODE_128, BARCODE_WIDTH, BARCODE_HEIGHT, ENCODE_HINTS);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, path);
    }

    /**
     * @title 生成条形码图片
     * @description 根据文本内容生成条形码图片的字节输出流
     * @param text 文本内容
     * @return 字节输出流
     * @throws WriterException
     * @throws IOException
     */
    public static ByteArrayOutputStream create(String text) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.CODE_128, BARCODE_WIDTH, BARCODE_HEIGHT, ENCODE_HINTS);
        return writeToStream(bitMatrix, FORMAT);
    }

    /**
     * @title 生成条形码图片
     * @description 根据文本内容，自定义宽度高度，生成所需要的条形码图片
     * @param text 文本内容
     * @param filePath 生成图片文件路径
     * @param width 宽度
     * @param height 高度
     */
    public static void create(String text, String filePath, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.CODE_128, width, height, ENCODE_HINTS);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, path);
    }

    /**
     * @title 生成条形图片
     * @description 根据文本内容，自定义宽度高度，生成所需要的条形码图片
     * @param text 文本内容
     * @param filePath 生成图片文件路径
     * @param width 宽度
     * @param height 高度
     * @param onColor 条形码颜色
     * @param offColor 条形码背景颜色
     */
    public static void create(String text, String filePath, int width, int height, Color onColor, Color offColor) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.CODE_128, width, height, ENCODE_HINTS);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, path, new MatrixToImageConfig(onColor.getRGB(), offColor.getRGB()));
    }

    /**
     * @title 生成条形码图片
     * @description 根据文本内容生成条形码图片的字节输出流
     * @param text 文本内容
     * @param width 宽度
     * @param height 高度
     * @return 字节输出流
     */
    public static ByteArrayOutputStream create(String text, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, width, height, ENCODE_HINTS);
        return writeToStream(bitMatrix, FORMAT);
    }

    /**
     * @title 生成条形码图片
     * @description 根据文本内容，自定义宽度高度，自定义图片格式，生成所需要的条形码图片
     * @param text 文本内容
     * @param filePath 生成图片文件路径
     * @param width 宽度
     * @param height 高度
     * @param format 图片格式
     */
    public static void create(String text, String filePath, int width, int height, String format) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, width, height, ENCODE_HINTS);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
    }

    /**
     * @title 生成条形图片
     * @description 根据文本内容生成条形图片的字节输出流
     * @param text 文本内容
     * @param width 宽度
     * @param height 高度
     * @param format 自定义图片格式
     * @return 字节输出流
     */
    public static ByteArrayOutputStream create(String text, int width, int height, String format) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, width, height, ENCODE_HINTS);
        return writeToStream(bitMatrix, format);
    }

    /**
     * @title 生成条形码图片
     * @description 根据文本内容，自定义宽度高度，自定义图片格式，自定义配置信息，生成所需要的条形码图片
     * @param text 文本内容
     * @param filePath 生成图片文件路径
     * @param width 宽度
     * @param height 高度
     * @param format 图片格式
     * @param hintTypes 配置信息
     */
    public static void create(String text, String filePath, int width, int height, String format, EnumMap<EncodeHintType, Object> hintTypes) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, width, height, hintTypes);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
    }

    /**
     * @title 生成条形码图片
     * @param text 文本内容
     * @param width 宽度
     * @param height 高度
     * @param format 图片格式
     * @param hintTypes 配置信息
     * @return 字节输出流
     * @throws WriterException
     * @throws IOException
     */
    public static ByteArrayOutputStream create(String text, int width, int height, String format,  EnumMap<EncodeHintType, Object> hintTypes) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, width, height, hintTypes);
        return writeToStream(bitMatrix, format);
    }

    /**
     * 生成带文字的条形图片
     * @param text 文本内容
     * @param filePath 生成文件存放路径
     */
    public static void createWithText(String text, String filePath) throws IOException, WriterException {
        createWidthLogo(text, BARCODE_WIDTH, BARCODE_HEIGHT, FORMAT, ENCODE_HINTS)
                .toFile(filePath);
    }


    /**
     * @title 解析条形码
     * @description 解析条形，获取其中文本信息
     * @param filePath 图片路径
     * @return 文本信息
     * @throws IOException
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    public static String read(String filePath) throws IOException, FormatException, ChecksumException, NotFoundException {
        BinaryBitmap binaryBitmap = createBinaryBitmap(new FileInputStream(filePath));
        return readBarcode(binaryBitmap, DECODE_HINTS).getText();
    }

    /**
     * @title 解析条形码
     * @description 解析条形码，获取其中文本信息
     * @param inputStream 输入流
     * @return 文本信息
     * @throws IOException
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    public static String read(InputStream inputStream) throws IOException, FormatException, ChecksumException, NotFoundException {
        BinaryBitmap binaryBitmap = createBinaryBitmap(inputStream);
        return readBarcode(binaryBitmap, DECODE_HINTS).getText();
    }

    /**
     * @title 解析条形码
     * @description 解析条形码，获取其中文本信息
     * @param filePath 图片路径
     * @param decodeHints 解析配置
     * @return 文本信息
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     * @throws IOException
     */
    private static String read(String filePath, Map<DecodeHintType, Object> decodeHints) throws FormatException, ChecksumException, NotFoundException, IOException {
        BinaryBitmap binaryBitmap = createBinaryBitmap(new FileInputStream(filePath));
        return readBarcode(binaryBitmap, decodeHints).getText();
    }

    /**
     * @title 解析条形码
     * @description 解析条形码，获取其中文本信息
     * @param inputStream 输入流
     * @return 文本信息
     * @throws IOException
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    public static String read(InputStream inputStream, Map<DecodeHintType, Object> decodeHints) throws IOException, FormatException, ChecksumException, NotFoundException {
        BinaryBitmap binaryBitmap = createBinaryBitmap(inputStream);
        return readBarcode(binaryBitmap, decodeHints).getText();
    }
    
    
    /**
     * @title 生成BitMatrix
     * @param text 文本内容
     * @param barcodeFormat 条形码格式
     * @param width 宽度
     * @param height 高度
     * @param hintTypes 配置
     * @return BitMatrix
     * @throws WriterException
     */
    private static BitMatrix createBitMatrix(String text, BarcodeFormat barcodeFormat, int width, int height, EnumMap<EncodeHintType, Object> hintTypes) throws WriterException {
        MultiFormatWriter writer = new MultiFormatWriter();
        return writer.encode(text, barcodeFormat, width, height, hintTypes);
    }

    /**
     * 转成字符输出流
     * @param bitMatrix bitMatrix
     * @param format 图片格式
     * @return
     * @throws IOException
     */
    private static ByteArrayOutputStream writeToStream(BitMatrix bitMatrix, String format) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
        return outputStream;
    }

    /**
     * 解析条形码
     * @param binaryBitmap 条形码图类
     * @return 解析信息
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    private static Result readBarcode(BinaryBitmap binaryBitmap, Map<DecodeHintType, Object> decodeHints) throws FormatException, ChecksumException, NotFoundException {
        MultiFormatReader reader = new MultiFormatReader();
        return reader.decode(binaryBitmap, decodeHints);
    }

    /**
     * 生成BinaryBitmap
     * @param inputStream 输入流
     * @return
     * @throws IOException
     */
    private static BinaryBitmap createBinaryBitmap(InputStream inputStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        Binarizer binarizer = new HybridBinarizer(source);
        return new BinaryBitmap(binarizer);
    }

    /**
     * 生成带有文本的条形码
     * @param text 生成文本
     * @param qrCodeWidth 条形码宽度
     * @param qrCodeHeight 条形码高度
     * @param format 图片格式
     * @param encodeHints 条形码配置
     * @return 输出流
     * @throws WriterException
     * @throws IOException
     */
    private static Thumbnails.Builder<BufferedImage> createWidthLogo(String text, int qrCodeWidth, int qrCodeHeight, String format,
                                                                     EnumMap<EncodeHintType, Object> encodeHints) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.CODE_128, qrCodeWidth, qrCodeHeight, encodeHints);
        /* 生成条形码的bufferedImage */
        BufferedImage barcode = MatrixToImageWriter.toBufferedImage(bitMatrix);
        /* 创建图片构件对象 */
        BufferedImage bg = new BufferedImage(qrCodeWidth, qrCodeHeight + 10, BufferedImage.TYPE_INT_RGB);
        Graphics bgGraphics = bg.getGraphics();
        bgGraphics.setColor(Color.WHITE);
        bgGraphics.fillRect(0, 0, qrCodeWidth, qrCodeHeight);
        Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(bg);
        String fontFile = BarcodeUtils.class.getClassLoader()
                .getResource(BASE_FONT)
                .getPath();
        Font font = new Font(fontFile, Font.PLAIN, 12);
        BufferedImage textImage = new BufferedImage(qrCodeWidth, font.getSize(), BufferedImage.TYPE_INT_ARGB);
        //获得图片的笔刷
        Graphics graphics = textImage.getGraphics();
        // 背景颜色
        bgGraphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, qrCodeWidth, font.getSize());
        graphics.setColor(Color.BLACK);
        //文字居中
        FontMetrics fm = graphics.getFontMetrics();
        graphics.setFont(font);
        graphics.drawString(text, (qrCodeWidth - fm.stringWidth(text)) / 2 , font.getSize());
        BufferedImage textBufferedImage = Thumbnails.of(textImage).size(qrCodeWidth, font.getSize()).asBufferedImage();
        /* 设置l位置居中,不透明  */
        builder.watermark(Positions.CENTER, barcode, 1F)
                .watermark(Positions.BOTTOM_CENTER, textBufferedImage, 1F)
                .scale(1F)
                .outputFormat(format);
        return builder;
    }

}
