package demo;

import com.google.zxing.WriterException;
import org.oriboy.qrcode.common.QRCodeUtils;

import java.io.IOException;

/**
 * @author rocky
 * @date 2018-07-18 10:31
 */
public class Demo1 {
    public static void main(String[] args) {
        /* 生成二维码图片 */
        String filePath = "E:\\ideaProject\\qrcodecore\\img\\demo1.png";
        try {
            QRCodeUtils.create("http://www.baidu.com", filePath);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
