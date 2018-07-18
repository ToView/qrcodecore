package org.oriboy.qrcode.common;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.resizers.configurations.Rendering;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * QR code generation parsing  class
 * 二维码生成/解析类
 * @author rocky
 * @date 2018-07-17 17:56
 */
public class QRCodeUtils {

    /**
     * 二维码默认宽度
     */
    private static final int QRCODE_WIDTH = 300;

    /**
     * 二维码默认高度
     */
    private static final int QRCODE_HEIGHT = 300;

    /**
     * logo默认宽度
     */
    private static final int LOGO_WIDTH = 90;

    /**
     * logo默认高度
     */
    private static final int LOGO_HEIGHT = 90;

    /**
     * 默认二维码文件格式
     */
    private static final String FORMAT = "png";

    /**
     * 生成二维码参数
     */
    private static final Map<EncodeHintType, Object> ENCODE_HINTS = new HashMap();

    /**
     * 解析二维码参数
     */
    private static final Map<DecodeHintType, Object> DECODE_HINTS = new HashMap<>();

    static {
        /* 字符编码 */
        ENCODE_HINTS.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        /* 容错等级 L、M、Q、H 其中 L 为最低, H 为最高 */
        ENCODE_HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        /* 二维码与图片边距,，默认为0 */
        ENCODE_HINTS.put(EncodeHintType.MARGIN, 0);

        /* 字符编码 */
        DECODE_HINTS.put(DecodeHintType.CHARACTER_SET, "UTF-8");
    }

    /**
     * @title 生成二维码图片
     * @description 根据文本内容生成默认格式的二维图图片
     * @param text 文本内容
     * @param filePath 生成图片路径
     * @throws WriterException
     * @throws IOException
     */
    public static void create(String text, String filePath) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT, ENCODE_HINTS);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, path);
    }

    /**
     * @title 生成二维码图片
     * @description 根据文本内容生成二维码图片的字节输出流
     * @param text 文本内容
     * @return 字节输出流
     * @throws WriterException
     * @throws IOException
     */
    public static ByteArrayOutputStream create(String text) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT, ENCODE_HINTS);
        return writeToStream(bitMatrix, FORMAT);
    }

    /**
     * @title 生成二维码图片
     * @description 根据文本内容，自定义宽度高度，生成所需要的二维码图片
     * @param text 文本内容
     * @param filePath 生成图片文件路径
     * @param width 宽度
     * @param height 高度
     */
    public static void create(String text, String filePath, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, width, height, ENCODE_HINTS);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, path);
    }

    /**
     * @title 生成二维码图片
     * @description 根据文本内容生成二维码图片的字节输出流
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
     * @title 生成二维码图片
     * @description 根据文本内容，自定义宽度高度，自定义图片格式，生成所需要的二维码图片
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
     * @title 生成二维码图片
     * @description 根据文本内容生成二维码图片的字节输出流
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
     * @title 生成二维码图片
     * @description 根据文本内容，自定义宽度高度，自定义图片格式，自定义配置信息，生成所需要的二维码图片
     * @param text 文本内容
     * @param filePath 生成图片文件路径
     * @param width 宽度
     * @param height 高度
     * @param format 图片格式
     * @param hintTypes 配置信息
     */
    public static void create(String text, String filePath, int width, int height, String format, Map<EncodeHintType, Object> hintTypes) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, width, height, hintTypes);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
    }

    /**
     * @title 生成二维码图片
     * @param text 文本内容
     * @param width 宽度
     * @param height 高度
     * @param format 图片格式
     * @param hintTypes 配置信息
     * @return 字节输出流
     * @throws WriterException
     * @throws IOException
     */
    public static ByteArrayOutputStream create(String text, int width, int height, String format,  Map<EncodeHintType, Object> hintTypes) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, width, height, hintTypes);
        return writeToStream(bitMatrix, format);
    }


    /**
     * @title 解析二维码
     * @description 解析二维码，获取其中文本信息
     * @param filePath 图片路径
     * @return 文本信息
     * @throws IOException
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    public static String read(String filePath) throws IOException, FormatException, ChecksumException, NotFoundException {
        BinaryBitmap binaryBitmap = createBinaryBitmap(new FileInputStream(filePath));
        return readQRcode(binaryBitmap, DECODE_HINTS).getText();
    }

    /**
     * @title 解析二维码
     * @description 解析二维码，获取其中文本信息
     * @param inputStream 输入流
     * @return 文本信息
     * @throws IOException
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    public static String read(InputStream inputStream) throws IOException, FormatException, ChecksumException, NotFoundException {
        BinaryBitmap binaryBitmap = createBinaryBitmap(inputStream);
        return readQRcode(binaryBitmap, DECODE_HINTS).getText();
    }

    /**
     * @title 解析二维码
     * @description 解析二维码，获取其中文本信息
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
        return readQRcode(binaryBitmap, decodeHints).getText();
    }

    /**
     * @title 解析二维码
     * @description 解析二维码，获取其中文本信息
     * @param inputStream 输入流
     * @return 文本信息
     * @throws IOException
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    public static String read(InputStream inputStream, Map<DecodeHintType, Object> decodeHints) throws IOException, FormatException, ChecksumException, NotFoundException {
        BinaryBitmap binaryBitmap = createBinaryBitmap(inputStream);
        return readQRcode(binaryBitmap, decodeHints).getText();
    }

    /**
     * 生成带logo的二维码图片
     * @param text 文本内容
     * @param filePath 生成文件存放路径
     * @param logoPath logo文件路径
     * @throws WriterException
     * @throws IOException
     */
    public static void createWithLogo(String text, String filePath, String logoPath) throws WriterException, IOException {
        createWidthLogo(text, new FileInputStream(logoPath), QRCODE_WIDTH, QRCODE_HEIGHT,
                LOGO_WIDTH, LOGO_HEIGHT, FORMAT, ENCODE_HINTS)
                .toFile(filePath);
    }

    /**
     * 生成带logo的二维码图片
     * @param text 文本内容
     * @param filePath 生成文件存放路径
     * @param logoPath logo文件路径
     * @param qrCodeWidth 二维码宽度
     * @param qrCodeHeight  二维码高度
     * @param logoWidth logo宽度
     * @param logoHeight logo高度
     * @throws IOException
     * @throws WriterException
     */
    public static void createWithLogo(String text, String filePath, String logoPath, int qrCodeWidth, int qrCodeHeight,
                                      int logoWidth, int logoHeight) throws IOException, WriterException {
        createWidthLogo(text, new FileInputStream(logoPath), qrCodeWidth, qrCodeHeight,
                logoWidth, logoHeight, FORMAT, ENCODE_HINTS)
                .toFile(filePath);
    }

    /**
     * 生成带logo的二维码图片
     * @param text 文本内容
     * @param filePath 生成文件存放路径
     * @param logoPath logo文件路径
     * @param qrCodeWidth 二维码宽度
     * @param qrCodeHeight  二维码高度
     * @param logoWidth logo宽度
     * @param logoHeight logo高度
     * @param format 图片格式
     * @throws IOException
     * @throws WriterException
     */
    public static void createWithLogo(String text, String filePath, String logoPath, int qrCodeWidth, int qrCodeHeight,
                                      int logoWidth, int logoHeight, String format) throws IOException, WriterException {
        createWidthLogo(text, new FileInputStream(logoPath), qrCodeWidth, qrCodeHeight,
                logoWidth, logoHeight, format, ENCODE_HINTS)
                .toFile(filePath);
    }


    /**
     * 生成带有logo的二维码图片
     * @param text 文本内容
     * @param logoPath logo存放路径
     * @return 生成的输出流
     * @throws IOException
     * @throws WriterException
     */
    public static OutputStream createWithLogo(String text, String logoPath) throws IOException, WriterException {
        OutputStream outputStream = new ByteArrayOutputStream();
        createWidthLogo(text, new FileInputStream(logoPath), QRCODE_WIDTH, QRCODE_HEIGHT,
                LOGO_WIDTH, LOGO_HEIGHT, FORMAT, ENCODE_HINTS)
                .toOutputStream(outputStream);
        return outputStream;
    }

    /**
     * 生成带有logo的二维码图片
     * @param text 文本内容
     * @param logoPath logo存放路径
     * @param qrCodeWidth 二维码宽度
     * @param qrCodeHeight 二维码高度
     * @param logoWidth logo宽度
     * @param logoHeight logo高度
     * @return 生成的输出流
     */
    public static OutputStream createWithLogo(String text, String logoPath, int qrCodeWidth, int qrCodeHeight, int logoWidth, int logoHeight)
            throws IOException, WriterException {
        OutputStream outputStream = new ByteArrayOutputStream();
        createWidthLogo(text, new FileInputStream(logoPath), qrCodeWidth, qrCodeHeight,
                logoWidth, logoHeight, FORMAT, ENCODE_HINTS)
                .toOutputStream(outputStream);
        return outputStream;
    }

    /**
     * 生成带有logo的二维码图片
     * @param text 文本内容
     * @param logoPath logo存放路径
     * @param qrCodeWidth 二维码宽度
     * @param qrCodeHeight 二维码高度
     * @param logoWidth logo宽度
     * @param logoHeight logo高度
     * @param format 图片格式
     * @return 生成的输出流
     */
    public static OutputStream createWithLogo(String text, String logoPath, int qrCodeWidth, int qrCodeHeight, int logoWidth,
                                              int logoHeight, String format) throws IOException, WriterException {
        OutputStream outputStream = new ByteArrayOutputStream();
        createWidthLogo(text, new FileInputStream(logoPath), qrCodeWidth, qrCodeHeight,
                logoWidth, logoHeight, format, ENCODE_HINTS)
                .toOutputStream(outputStream);
        return outputStream;
    }


    /**
     * @title 生成BitMatrix
     * @param text 文本内容
     * @param barcodeFormat 二维码格式
     * @param width 宽度
     * @param height 高度
     * @param hintTypes 配置
     * @return BitMatrix
     * @throws WriterException
     */
    private static BitMatrix createBitMatrix(String text, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, Object> hintTypes) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        return qrCodeWriter.encode(text, barcodeFormat, width, height, hintTypes);
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
     * 解析二维码
     * @param binaryBitmap 二维码图类
     * @return 解析信息
     * @throws FormatException
     * @throws ChecksumException
     * @throws NotFoundException
     */
    private static Result readQRcode(BinaryBitmap binaryBitmap, Map<DecodeHintType, Object> decodeHints) throws FormatException, ChecksumException, NotFoundException {
        QRCodeReader reader = new QRCodeReader();
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
     * 生成带有logo的二维码
     * @param text 生成文本
     * @param logoInputStream logo文件输入流
     * @param qrCodeWidth 二维码宽度
     * @param qrCodeHeight 二维码高度
     * @param logoWidth logo宽度
     * @param logoHeight logo高度
     * @param ENCODE_HINTS 二维码配置
     * @return 输出流
     * @throws WriterException
     * @throws IOException
     */
    private static Thumbnails.Builder<BufferedImage> createWidthLogo(String text, InputStream logoInputStream, int qrCodeWidth, int qrCodeHeight, int logoWidth, int logoHeight,
                                                String format, Map<EncodeHintType, Object> ENCODE_HINTS) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(text, BarcodeFormat.QR_CODE, qrCodeWidth, qrCodeHeight, ENCODE_HINTS);
        /* 生成二维码的bufferedImage */
        BufferedImage qrcode = MatrixToImageWriter.toBufferedImage(bitMatrix);
        /* 创建图片构件对象 */
        Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(new BufferedImage(qrCodeWidth, qrCodeHeight, BufferedImage.TYPE_INT_RGB));
        BufferedImage logo = ImageIO.read(logoInputStream);
        BufferedImage logoImage = Thumbnails.of(logo).size(logoWidth, logoHeight).asBufferedImage();
        /* 设置logo水印位置居中,不透明  */
        builder.watermark(Positions.CENTER, qrcode, 1F)
                .watermark(Positions.CENTER, logoImage, 1F)
                .scale(1F)
                .outputFormat(format);
        return builder;
    }

}
