package org.oriboy.qrcode.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
     * 默认宽度
     */
    private static final int WIDTH = 300;

    /**
     * 默认高度
     */
    private static final int HEIGHT = 300;

    /**
     * 默认二维码文件格式
     */
    private static final String FORMAT = "png";

    /**
     * 二维码参数
     */
    private static final Map<EncodeHintType, Object> HINTS = new HashMap();

    static {
        /* 字符编码 */
        HINTS.put(EncodeHintType.CHARACTER_SET, "utf-8");
        /* 容错等级 L、M、Q、H 其中 L 为最低, H 为最高 */
        HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        /* 二维码与图片边距,，默认为0 */
        HINTS.put(EncodeHintType.MARGIN, 0);
    }

    /**
     * 生成二维码图片
     * @param text 文本内容
     * @param filePath 生成图片路径
     * @throws WriterException
     * @throws IOException
     */
    public static void create(String text, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, HINTS);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, path);
    }

    /**
     * 生成二维码图片
     * @param text 文本内容
     * @return 字节输出流
     * @throws WriterException
     * @throws IOException
     */
    public static ByteArrayOutputStream create(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, HINTS);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, outputStream);
        return outputStream;
    }


}
